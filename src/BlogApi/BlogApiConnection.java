package BlogApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;

import Common.LoggerManager;
import Common.PropertyMap;
import Common.Util;

public class BlogApiConnection {
	private Logger logger = LoggerManager.getLogger(BlogApiConnection.class);
	private String response = null;
	private String error = null;

	public BlogApiConnection(String url, String parameter, HttpMethod method) {
		try {
			if (Util.StringIsEmptyOrNull(parameter)) {
				parameter = "";
			}
			HttpsURLConnection connection;
			if (method == HttpMethod.GET) {
				url = url + "?" + parameter;
				connection = getConnection(url);
				connection.setRequestMethod(HttpMethod.GET.toString());
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.setUseCaches(false);
				connection.setDoInput(true);
				connection.setDoOutput(false);
			} else if (method == HttpMethod.POST) {
				connection = getConnection(url);
				connection.setRequestMethod(HttpMethod.POST.toString());
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.setUseCaches(false);
				connection.setDoInput(true);
				connection.setDoOutput(true);
				try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
					wr.writeBytes(parameter);
					wr.flush();
				}
			} else {
				logger.error("[Constructor] The request method was not supproted.");
				throw new UnsupportedOperationException();
			}
			this.run(connection);
		} catch (Throwable e) {
			logger.error("[Constructor]", e);
			throw new RuntimeException(e);
		}
	}

	private HttpsURLConnection getConnection(String url) throws IOException {
		URL obj = new URL(url);
		String proxy = PropertyMap.getInstance().getProperty("config", "proxy");
		int port = PropertyMap.getInstance().getPropertyInt("config", "proxy_port");
		if (Util.StringIsEmptyOrNull(proxy)) {
			return (HttpsURLConnection) obj.openConnection();
		}
		logger.info("[getConnection] Proxy mode");
		return (HttpsURLConnection) obj.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy, port)));
	}

	private void run(HttpsURLConnection connection) {
		try {
			int resCode = connection.getResponseCode();
			logger.info("[run] State Code : " + resCode);
			InputStream is = null;
			try {
				if (resCode == 200) {
					is = connection.getInputStream();
				} else {
					is = connection.getErrorStream();
				}
				try (InputStreamReader rd = new InputStreamReader(is)) {
					try (BufferedReader br = new BufferedReader(rd)) {
						String line;
						StringBuffer response = new StringBuffer();
						while ((line = br.readLine()) != null) {
							response.append(line);
							response.append('\r');
						}
						if (resCode == 200) {
							this.response = response.toString();
						} else {
							this.error = response.toString();
							logger.error("[run] Error message : " + this.error);
						}
					}
				}
			} finally {
				if (is != null) {
					is.close();
				}
			}
		} catch (Throwable e) {
			logger.error("[run]", e);
		}
	}

	public String getResponse() {
		return this.response;
	}

	public String getError() {
		return this.error;
	}
}
