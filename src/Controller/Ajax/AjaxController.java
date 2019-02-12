package Controller.Ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import Bean.SyncStateBean;
import BlogApi.BlogApiThread;
import Common.AbstractAjaxController;

@Controller
public class AjaxController extends AbstractAjaxController {
	@RequestMapping(value = "/sync.ajax")
	public void sync(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		BlogApiThread.instance().run();
		OKAjax(res);
	}

	@RequestMapping(value = "/syncStatus.ajax")
	public void syncStatus(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		SyncStateBean bean = new SyncStateBean();
		bean.setState(BlogApiThread.status().toString());
		bean.setMessgae(BlogApiThread.message());
		returnJson(res, bean);
	}
}
