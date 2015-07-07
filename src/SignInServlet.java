

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
*/
import facebook4j.*;
import timeline.AuthFB;
import timeline.AuthTwitter;
import twitter4j.*;

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
		//Facebook
		facebook4j.conf.ConfigurationBuilder cb = new facebook4j.conf.ConfigurationBuilder();
		AuthFB tokens_facebook = new AuthFB();
		
		cb.setDebugEnabled(true)
		  .setOAuthAppId(tokens_facebook.OAuthAppId)
		  .setOAuthAppSecret(tokens_facebook.OAuthAppSecret)
		  .setOAuthAccessToken(tokens_facebook.OAuthAccessToken)
		  .setOAuthPermissions("read_stream");
		FacebookFactory ff = new FacebookFactory(cb.build());
		Facebook facebook = ff.getInstance();
        
        //Twitter
		AuthTwitter tokens_twitter = new AuthTwitter();
		
		twitter4j.conf.ConfigurationBuilder cb1 = new twitter4j.conf.ConfigurationBuilder();
		cb1.setDebugEnabled(true)
	            .setOAuthConsumerKey(tokens_twitter.OAuthConsumerKey)
	            .setOAuthConsumerSecret(tokens_twitter.OAuthConsumerSecret)
	            .setOAuthAccessToken(tokens_twitter.OAuthAccessToken)
	            .setOAuthAccessTokenSecret(tokens_twitter.OAuthAccessTokenSecret);

	    TwitterFactory tf = new TwitterFactory(cb1.build());
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
        try {
			request.getSession().setAttribute("facebook", facebook.getHome());
			request.getSession().setAttribute("facebook_user", facebook.getMe());
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
