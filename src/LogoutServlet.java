

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String accessToken = "";
        try {
        	accessToken = facebook.getOAuthAccessToken().getToken();
        } catch (Exception e) {
            throw new ServletException(e);
        }
        request.getSession().invalidate();

        // Log Out of the Facebook
        StringBuffer next = request.getRequestURL();
        int index = next.lastIndexOf("/");
        next.replace(index+1, next.length(), "");
        response.sendRedirect("http://www.facebook.com/logout.php?next=" + next.toString() + "&access_token=" + accessToken);
	}

}
