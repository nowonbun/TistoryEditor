package Common;

import org.apache.log4j.Logger;

public abstract class AbstractServletController extends AbstractController {

	private Logger logger = null;

	protected Logger getLogger() {
		if (logger == null) {
			logger = LoggerManager.getLogger(this.getClass());
		}
		return logger;
	}
	
	protected String redirect(String url) {
		return "redirect:" + url;
	}

	protected String error() {
		return redirect("./error.html");
	}
}
