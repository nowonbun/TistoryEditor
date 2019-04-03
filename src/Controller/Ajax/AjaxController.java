package Controller.Ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import Bean.MenuBean;
import Bean.SyncStateBean;
import BlogApi.BlogApiThread;
import BlogApi.MenuBuilder;
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

	@RequestMapping(value = "/menu.ajax")
	public void menu(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		List<MenuBean> list = MenuBuilder.get().getMenu();
		returnJson(res, list);
	}
}
