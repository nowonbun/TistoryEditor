package BlogApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class BlogApiConnection {
	private HttpsURLConnection connection;
	private byte[] parameter;
	private String response;

	public BlogApiConnection(URLConnection connection, String parameter) {
		this.connection = (HttpsURLConnection) connection;
		if (parameter != null) {
			this.parameter = parameter.getBytes();
		} else {
			this.parameter = new byte[0];
		}
		this.run();
	}

	private void run() {
		try {
			this.connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(parameter.length));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			try (OutputStream stream = connection.getOutputStream()) {
				stream.write(parameter, 0, parameter.length);
			}

			// 
			try (InputStream is = connection.getInputStream()) {
				try (InputStreamReader rd = new InputStreamReader(is)) {
					try (BufferedReader br = new BufferedReader(rd)) {
						String line;
						StringBuffer response = new StringBuffer();
						while ((line = br.readLine()) != null) {
							response.append(line);
							response.append('\r');
						}
						this.response = response.toString();
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
