package Controller.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Bean.UserBean;
import Common.AbstractServletController;
import Common.Define;
import Common.PropertyMap;
import Common.Util;

@Controller
public class LoginController extends AbstractServletController {
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String isLogin = PropertyMap.getInstance().getProperty("config", "login");
		if (!Util.StringIsEmptyOrNull(isLogin) && "false".equals(isLogin.toLowerCase())) {
			session.setAttribute(Define.USER_SESSION_NAME, new UserBean());
		}
		if (super.getCurrentUser(session) != null) {
			return redirect("main.html");
		}
		// TODO: If have not the cookie key of database, this is not able to cookie
		// auth.
		/*
		 * Cookie cookie = getCookie(req, Define.COOKIE_KEY); if (cookie != null) {
		 * String key = cookie.getValue(); if (loginCookie != null) {
		 * session.setAttribute(Define.USER_SESSION_NAME, loginCookie.getUser()); return
		 * "redirect:main.html"; } cookie.setPath(Util.getCookiePath());
		 * cookie.setMaxAge(0); res.addCookie(cookie); }
		 */
		return "index";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		String pId = req.getParameter("id");
		String pEmail = req.getParameter("email");
		String id = PropertyMap.getInstance().getProperty("config", "id");
		String email = PropertyMap.getInstance().getProperty("config", "email");
		if ((Util.StringEquals(id, pId) && Util.StringEquals(email, pEmail)) || (Util.StringIsEmptyOrNull(id) && Util.StringIsEmptyOrNull(email))) {
			UserBean user = new UserBean();
			user.setId(pId);
			user.setEmail(pEmail);
			session.setAttribute(Define.USER_SESSION_NAME, user);
			return redirect("main.html");
		}
		res.setStatus(403);
		return null;
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		session.setAttribute(Define.USER_SESSION_NAME, null);
		return redirect("index.html");
	}
}
