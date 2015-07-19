

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sola.instagram.InstagramSession;
import com.sola.instagram.auth.InstagramAuthentication;
import com.sola.instagram.exception.InstagramException;

import facebook4j.*;
import timeline.AuthFB;
import timeline.AuthInstagram;
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
	    List<Status> twitterTimeleine = null;
		try {
			twitterTimeleine = twitter.getHomeTimeline();
		} catch (TwitterException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
	    //Instagram
		InstagramSession instagram = new InstagramSession();
		
		/*InstagramAuthentication auth =  new InstagramAuthentication();
		String authUrl = null;
		try {
			authUrl = auth.setRedirectUri(AuthInstagram.CALLBACK_URL)
			                     .setClientSecret(AuthInstagram.CLIENT_SECRET)
			                     .setClientId(AuthInstagram.CLIENT_ID)
			                     .getAuthorizationUri();
		} catch (InstagramException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			if(request.getParameter("code").isEmpty())
				instagram = auth.build(request.getParameter("code"));
			else
				request.getRequestDispatcher(authUrl).forward(request, response);
		} catch (InstagramException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
	    
	    try {
	    	if(instagram.getFeed(0).equals(null))
    		{
        		request.getSession().setAttribute("instagram", "No data to display!");
    		}
        	else
        		request.getSession().setAttribute("instagram", instagram.getFeed(0));
		} catch (InstagramException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
        try {
        	if(twitter.equals(null))
    		{
        		request.getSession().setAttribute("twitter", "No data to display!");
    		}
        	else
        		request.getSession().setAttribute("twitter", twitterTimeleine);
			
			request.getSession().setAttribute("user", twitter.verifyCredentials().getScreenName());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
        	if(facebook.getFeed().equals(null))
    		{
        		request.getSession().setAttribute("facebook", "No data to display!");
    		}
        	else
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
