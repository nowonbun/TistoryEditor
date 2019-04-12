package Common;

public abstract class AbstractServletController extends AbstractController {

	protected String redirect(String url) {
		return "redirect:" + url;
	}

	protected String error() {
		return redirect("./error.html");
	}
}
