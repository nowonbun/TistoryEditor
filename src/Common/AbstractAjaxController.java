package Common;

import javax.servlet.http.HttpServletResponse;

import Bean.ObjectBean;

public abstract class AbstractAjaxController extends AbstractController {

	protected void returnString(HttpServletResponse res, Object data) {
		try {
			res.setContentType("content-type: application/text; charset=utf-8");
			res.getWriter().println(data.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void returnJson(HttpServletResponse res, Object data) {
		try {
			res.setContentType("content-type: application/json; charset=utf-8");
			String ret = JsonConverter.create(data);
			res.getWriter().println(ret);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void OKAjax(HttpServletResponse res) {
		returnJson(res, new ObjectBean(true));
	}
}