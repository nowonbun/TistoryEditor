package BlogApi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import Common.LoggerManager;
import Common.PropertyMap;

@Controller
public class BlogApiServlet {
	private Logger logger = LoggerManager.getLogger(BlogApiServlet.class);

	@RequestMapping(value = "/syncstart.auth")
	public String syncstart(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		logger.info(" The Sync was requested. ");
		String redirectUrl = "redirect:https://www.tistory.com/oauth/authorize?" + "client_id=" + PropertyMap.getInstance().getProperty("config", "client_id") + "&" + "redirect_uri="
				+ PropertyMap.getInstance().getProperty("config", "redirect_uri") + "&" + "response_type=code&" + "state=" + req.getParameter("type");
		logger.info(" Redirect Url : " + redirectUrl);
		return redirectUrl;
	}

	@RequestMapping(value = "/tistory.auth")
	public String tistory(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		logger.info(" The Sync was started. ");
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		logger.info(" code : " + code);
		logger.info(" state : " + state);
		if ("pull".equals(state)) {
			BlogApiThread.start(code, BlogSyncType.pull);
		} else if ("push".equals(state)) {
			BlogApiThread.start(code, BlogSyncType.push);
		}
		return "redirect:admin.html";
	}
}
