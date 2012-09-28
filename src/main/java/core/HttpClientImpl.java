package core;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import utils.BasicAuth;

/**
 * 
 * @author linda.velte
 * 
 */
public class HttpClientImpl {

	private static HttpClientImpl instance;

	public static HttpClientImpl getInstance() {
		if (instance == null) {
			instance = new HttpClientImpl();
		}
		return instance;
	}

	private HttpClientImpl() {
	}

	public HttpResponse getRequest(String url, Configuration conf) {

		// get https client
		DefaultHttpClient client = getHttpClient();

		try {
			HttpUriRequest req = new HttpGet(conf.getBaseURL() + url);
			addBasicHeaders(req, conf);
			HttpResponse response = client.execute(req);
			return response;

		} catch (Exception e) {
			return null;
		}
	}

	public HttpResponse postRequest(String url, Configuration conf, String json) {

		// get https client
		DefaultHttpClient client = getHttpClient();

		try {
			HttpPost req = new HttpPost(conf.getBaseURL() + url);
			addBasicHeaders(req, conf);
			req.setEntity(new StringEntity(json));
			req.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			HttpResponse response = client.execute(req);
			return response;

		} catch (Exception e) {
			return null;
		}
	}

	private void addBasicHeaders(HttpRequest req, Configuration conf) {
		req.addHeader("Accept", conf.getAccept());
		req.addHeader("Authorization", BasicAuth.getAuthorization(conf.getUsername(), conf.getPassword()));
	}

	private DefaultHttpClient getHttpClient() {
		DefaultHttpClient client = new DefaultHttpClient();
		client = (DefaultHttpClient) wrapClient(client);
		return client;
	}

	private static HttpClient wrapClient(HttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = base.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, base.getParams());
		} catch (Exception ex) {
			return null;
		}
	}
}
