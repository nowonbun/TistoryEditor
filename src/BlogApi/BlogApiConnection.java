package BlogApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class BlogApiConnection {
	private HttpsURLConnection connection;
	private String response = null;
	private String error = null;

	public BlogApiConnection(URLConnection connection) {
		this.connection = (HttpsURLConnection) connection;
		this.run();
	}

	private void run() {
		try {
			this.connection.setRequestMethod("GET");
			this.connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			this.connection.setUseCaches(false);
			this.connection.setDoInput(true);
			this.connection.setDoOutput(false);

			int resCode = connection.getResponseCode();
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
						}
					}
				}
			} finally {
				if (is != null) {
					is.close();
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public String getResponse() {
		return this.response;
	}
	
	public String getError() {
		return this.error;
	}
}
