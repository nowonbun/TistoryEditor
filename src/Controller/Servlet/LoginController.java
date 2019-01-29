package Controller.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Common.AbstractServletController;
import Common.PropertyMap;

@Controller
public class LoginController extends AbstractServletController {
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(PropertyMap.getInstance().getProperty("config", "email"));
		System.out.println(PropertyMap.getInstance().getProperty("config", "id"));
		return "index";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		return redirect("main.html");
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		return redirect("index.html");
	}
}
