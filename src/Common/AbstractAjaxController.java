package Common;

import javax.servlet.http.HttpServletResponse;

import Bean.ObjectBean;

public abstract class AbstractAjaxController extends AbstractController {

	protected void returnAjax(HttpServletResponse res, Object data) {
		try {
			res.setContentType("content-type: application/json; charset=utf-8");
			String ret = JsonConverter.create(data);
			//TODO: The log is too many.
			//getLogger().info(ret);
			res.getWriter().println(ret);
		} catch (Throwable e) {
			e.printStackTrace();
			//getLogger().error(e);
		}
	}
	
	protected void OKAjax(HttpServletResponse res) {
		ObjectBean bean = new ObjectBean();
		bean.setRet(true);
		returnAjax(res, bean);
	}
}