package BlogApi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
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
					param.put("client_id", "04802847dd0e9aa399fa8cfa23535cfa");
					param.put("redirect_uri", "http://localhost:8080/TisoryTest/tisotry.auth");
					param.put("response_type", "code");
					param.put("state", "");
					BlogApiConnectionBuilder.instance().build("https://www.tistory.com/oauth/authorize", param);
				} finally {
					alive = false;
				}
			});
		}
	}
}
