package BlogApi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.apache.jasper.tagplugins.jstl.core.Url;

public class BlogApiThread implements Runnable {
	private static BlogApiThread singleton = null;
	private boolean alive = false;

	private BlogApiThread() {

	}

	public static BlogApiThread instance() {
		if (singleton == null) {
			singleton = new BlogApiThread();
		}
		return singleton;
	}

	public static void start() {
		BlogApiThread obj = BlogApiThread.instance();
		obj.run();
	}

	public static boolean status() {
		return BlogApiThread.instance().alive;
	}

	// TODO: https://yookeun.github.io/java/2015/06/17/java-thread-pool/
	@Override
	public void run() {
		if (!alive) {
			alive = true;
			Executors.newSingleThreadExecutor().execute(() -> {
				try {
					Map<String, String> param = new HashMap<>();
					param.put("client_id", "7d1c7950ebc07c641438b6a5255eddeb");
					param.put("redirect_uri", "http://localhost:8080/TisoryTest/auth.jsp");
					
					BlogApiConnectionBuilder.instance().build("https://www.tistory.com/oauth/authorize", param);
				} finally {
					alive = false;
				}
			});
		}
	}
}
