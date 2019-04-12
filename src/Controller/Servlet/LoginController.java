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
		getLogger().info("[index] request!");
		try {
			String isLogin = PropertyMap.getInstance().getProperty("config", "login");
			if (!Util.StringIsEmptyOrNull(isLogin) && "false".equals(isLogin.toLowerCase())) {
				getLogger().info("[index] No login Mode");
				session.setAttribute(Define.USER_SESSION_NAME, new UserBean());
			}
			if (super.getCurrentUser(session) != null) {
				getLogger().info("[index] The signin was already.");
				return redirect("main.html");
			}
			return "index";
		} catch (Throwable e) {
			getLogger().error("[index]", e);
			return error();
		}
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[login] request!");
		try {
			String pId = req.getParameter("id");
			String pEmail = req.getParameter("email");
			String id = PropertyMap.getInstance().getProperty("config", "id");
			String email = PropertyMap.getInstance().getProperty("config", "email");
			if ((Util.StringEquals(id, pId) && Util.StringEquals(email, pEmail)) || (Util.StringIsEmptyOrNull(id) && Util.StringIsEmptyOrNull(email))) {
				UserBean user = new UserBean();
				user.setId(pId);
				user.setEmail(pEmail);
				session.setAttribute(Define.USER_SESSION_NAME, user);
				getLogger().info("[login] Login status : OK!");
				return redirect("main.html");
			} else {
				getLogger().info("[login] pId : " + pId);
				getLogger().info("[login] pEmail : " + pEmail);
			}
			return redirect("loginfailed.html");
		} catch (Throwable e) {
			getLogger().error("[login]", e);
			return error();
		}
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[logout] request!");
		try {
			session.setAttribute(Define.USER_SESSION_NAME, null);
			return redirect("index.html");
		} catch (Throwable e) {
			getLogger().error("[login]", e);
			return error();
		}
	}

	@RequestMapping(value = "/loginfailed.html", method = RequestMethod.GET)
	public String loginfailed(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		getLogger().info("[loginfailed] request!");
		try {
			modelmap.addAttribute("message", "로그인에 실패하였습니다.");
			return redirect("error.html");
		} catch (Throwable e) {
			getLogger().error("[login]", e);
			return error();
		}
	}
}
