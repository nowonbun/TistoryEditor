package BlogApi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import Common.PropertyMap;

@Controller
public class BlogApiServlet {
	@RequestMapping(value = "/syncstart.auth")
	public String syncstart(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		return "redirect:https://www.tistory.com/oauth/authorize?"
				+ "client_id=" + PropertyMap.getInstance().getProperty("config", "client_id") + "&"
				+ "redirect_uri=" + PropertyMap.getInstance().getProperty("config", "redirect_uri") + "&"
				+ "response_type=code&"
				+ "state=" + req.getParameter("type");
	}
	
	@RequestMapping(value = "/tistory.auth")
	public String tistory(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		if ("pull".equals(state)) {
			BlogApiThread.start(code, BlogSyncType.pull);
		} else if ("push".equals(state)) {
			BlogApiThread.start(code, BlogSyncType.push);
		}
		return "redirect:admin.html";
	}
}
