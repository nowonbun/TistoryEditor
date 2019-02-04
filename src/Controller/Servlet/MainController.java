package Controller.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Common.AbstractServletController;

@Controller
public class MainController extends AbstractServletController {
	@RequestMapping(value = "/main.html", method = RequestMethod.GET)
	public String main(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		return "main";
	}

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public String admin(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		return "admin";
	}
}
