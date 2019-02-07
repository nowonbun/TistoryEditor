package BlogApi;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

import Common.PropertyMap;
import Common.Util;

public class BlogApiConnectionBuilder {
	private static BlogApiConnectionBuilder singleton = null;

	public static BlogApiConnectionBuilder instance() {
		if (singleton == null) {
			singleton = new BlogApiConnectionBuilder();
		}
		return singleton;
	}

	public BlogApiConnection build(String url, Map<String, String> parameter) {

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
			URL obj = new URL(url + "?" + param);
			String proxy = PropertyMap.getInstance().getProperty("config", "proxy");
			int port = PropertyMap.getInstance().getPropertyInt("config", "proxy_port");
			if (Util.StringIsEmptyOrNull(proxy)) {
				return new BlogApiConnection(obj.openConnection());
			}
			return new BlogApiConnection(obj.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy, port))));
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}
