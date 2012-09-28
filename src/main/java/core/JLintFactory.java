package core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author linda.velte
 * 
 */
public class JLintFactory {

	private final Constructor<JLint> JLINT_CONSTRUCTOR;

	private final JLint SINGLETON;

	@SuppressWarnings("unchecked")
	public JLintFactory(Configuration conf) {

		String className = "core.JLintImpl";
		Constructor<JLint> constructor;

		@SuppressWarnings("rawtypes")
		Class clazz;
		try {
			clazz = Class.forName(className);
			constructor = clazz.getDeclaredConstructor(Configuration.class);
		} catch (NoSuchMethodException e) {
			throw new AssertionError(e);
		} catch (ClassNotFoundException e) {
			throw new AssertionError(e);
		}
		JLINT_CONSTRUCTOR = constructor;

		try {
			SINGLETON = JLINT_CONSTRUCTOR.newInstance(conf);
		} catch (InstantiationException e) {
			throw new AssertionError(e);
		} catch (IllegalAccessException e) {
			throw new AssertionError(e);
		} catch (InvocationTargetException e) {
			throw new AssertionError(e);
		}
	}

	public JLint getInstance() {
		return SINGLETON;
	}
}
