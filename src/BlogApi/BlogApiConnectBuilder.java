package BlogApi;

public class BlogApiConnectBuilder {
	private static BlogApiConnectBuilder singleton = null;
	public static BlogApiConnectBuilder instance() {
		if(singleton == null) {
			singleton = new BlogApiConnectBuilder();
		}
		return singleton;
	}
}
