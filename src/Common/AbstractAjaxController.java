package Common;

import javax.servlet.http.HttpServletResponse;

import Bean.AjaxReturnBean;
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

	protected void NGAjax(HttpServletResponse res) {
		returnJson(res, new ObjectBean(false));
	}

	protected void OKAjax(HttpServletResponse res, String message) {
		AjaxReturnBean bean = new AjaxReturnBean();
		bean.setRet(true);
		bean.setMessage(message);
		returnJson(res, bean);
	}

	protected void NGAjax(HttpServletResponse res, String message) {
		AjaxReturnBean bean = new AjaxReturnBean();
		bean.setRet(false);
		bean.setMessage(message);
		returnJson(res, bean);
	}
}