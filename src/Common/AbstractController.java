package Common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import Bean.UserBean;

public class AbstractController {

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
