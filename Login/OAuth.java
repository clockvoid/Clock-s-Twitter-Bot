package Login;

import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.conf.*;
import Gui.GuiMain;

import java.io.*;
import java.awt.Desktop;
import java.net.*;

public class OAuth {
	private String consummer_key = "Y8eSCzmcjQLB55E2qYSVWA";
	private String consummer_secret = "iR29PiA2TKnrxvXHc6yfREMlg13RLGHgeDQsTIIKM";
	private Twitter twitter;
	
	private ConfigurationBuilder createBuilder(String Access_token, String Access_token_secret) {
		ConfigurationBuilder conf = new ConfigurationBuilder();
		conf.setOAuthConsumerKey(consummer_key);
		conf.setOAuthConsumerSecret(consummer_secret);
		conf.setOAuthAccessToken(Access_token);
		conf.setOAuthAccessTokenSecret(Access_token_secret);
		conf.setUserStreamBaseURL("https://userstream.twitter.com/2/");
		return conf;
	}
	
	public OAuth(String Access_token, String Access_token_secret) {
		twitter = new TwitterFactory(createBuilder(Access_token, Access_token_secret).build()).getInstance();
	}
	
	private String getPin(RequestToken requesttoken, GuiMain gui) throws IOException, URISyntaxException {
		Desktop.getDesktop().browse(new URI(requesttoken.getAuthorizationURL()));
		return gui.getPIN();
	}
	
	private void storeAccessToken(AccessToken accesstoken, String path) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path)), "utf-8"));
		writer.write(accesstoken.getToken() + "," + accesstoken.getTokenSecret());
		writer.newLine();
		writer.close();
	}
	
	public OAuth(String filePath, GuiMain gui) throws IOException, TwitterException, URISyntaxException {
		//try {
		twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer(consummer_key, consummer_secret);
		RequestToken requesttoken = twitter.getOAuthRequestToken();
		String pin = getPin(requesttoken, gui);
		AccessToken accesstoken = twitter.getOAuthAccessToken(requesttoken, pin);
		storeAccessToken(accesstoken, filePath);
		twitter = new TwitterFactory(createBuilder(accesstoken.getToken(), accesstoken.getTokenSecret()).build()).getInstance();
		/*} catch(TwitterException te) {
			if(te.getStatusCode() == 401) {
				System.out.println("Unable to Access Token.");
			}
		} catch(URISyntaxException e) {
			System.out.println("Unable to URL");
		}*/
	}
	
	public Twitter getTwitter() {
		return twitter;
	}
}
