

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
*/
/*
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
*/
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*Facebook facebook = new FacebookFactory().getInstance();
        
		//Facebook
        try {
			ResponseList<facebook4j.Post> feed = facebook.getHome();
			request.setAttribute("feed", feed);
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        //Twitter
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	            .setOAuthConsumerKey("OcJo7XALkdADVC0tpEelo8c1y")
	            .setOAuthConsumerSecret("I6kKqOB1mXtLPy0g1DboiJY422iqflsodwUbkr4XUQuj09cQrl")
	            .setOAuthAccessToken("1285039172-SXLtypoM69niNyezQKU6WBJEd1FTGiMN5lkBid1")
	            .setOAuthAccessTokenSecret("GZuy1Vg7sfzO22etlEnTfNCkHduvrC6FCSxiGZMGRMBM6");

	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
/*
	    JSONObject obj = null;
		try {
			obj = new JSONObject(twitter.getHomeTimeline());
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    JSONArray arr = obj.getJSONArray("StatusJSONImpl");
	    String[] posts = new String[256];
	    for (int i = 0; i < arr.length(); i++)
	    {
	    	posts[i] = arr.getJSONObject(i).getString("text");
	    }*/
        
        try {
			request.getSession().setAttribute("timeline", twitter.getHomeTimeline());
			request.getSession().setAttribute("user", twitter.verifyCredentials().getScreenName());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        
        /*request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("CallbackServlet");
        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
