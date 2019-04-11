package BlogApi;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;

import Common.LoggerManager;

public class BlogApiConnectionBuilder {
	private static BlogApiConnectionBuilder singleton = null;
	private Logger logger = LoggerManager.getLogger(BlogApiConnectionBuilder.class);

	public static BlogApiConnectionBuilder instance() {
		if (singleton == null) {
			singleton = new BlogApiConnectionBuilder();
		}
		return singleton;
	}

	private BlogApiConnectionBuilder() {
		logger.info("[Constructor] This class will be allocated. : BlogApiConnectionBuilder");
	}

	public BlogApiConnection build(String url, Map<String, String> parameter, HttpMethod method) {
		String param = null;
		for (String key : parameter.keySet()) {
			if (param == null) {
				param = "";
			} else {
				param += "&";
			}
			param += key + "=" + parameter.get(key);
		}
		try {
			logger.info("[build] The http connection will be allocated.");
			logger.info("[build] url : " + url);
			logger.info("[build] param : " + param);
			logger.info("[build] method : " + method.toString());
			return new BlogApiConnection(url, param, method);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}
