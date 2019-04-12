package Common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Bean.UserBean;

public class AbstractController {

	private Logger logger = null;

	protected Logger getLogger() {
		if (logger == null) {
			logger = LoggerManager.getLogger(this.getClass());
		}
		return logger;
	}

	protected Cookie[] getCookies(HttpServletRequest request) {
		return request.getCookies();
	}

	protected Cookie getCookie(HttpServletRequest request, String name) {
		return Util.searchArray(getCookies(request), (node) -> {
			return Util.StringEquals(name, node.getName());
		});
	}

	protected UserBean getCurrentUser(HttpSession session) {
		return (UserBean) session.getAttribute(Define.USER_SESSION_NAME);
	}
}
