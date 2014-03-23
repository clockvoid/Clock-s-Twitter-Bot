import twitter4j.*;
import Bot.StreamListener;
import Login.*;
import Gui.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Date;

public class TwitterBot {
	private static Twitter twitter;
	private static TwitterStream stream;
	private static String filePath;
	private static GuiMain gui;
	
	private static BufferedReader createReader() {
		BufferedReader reader;
		filePath = "acounts.txt";
		File acounts = new File(filePath);
		try {
			//if(!dir.exists())dir.mkdirs();
			if(!acounts.exists())acounts.createNewFile();
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		return reader;
	}
	
	private static boolean login() {
		OAuth oauth;
		String str;
		BufferedReader reader;
		if((reader = createReader()) == null)return false;
		try {
			str = reader.readLine();
			reader.close();
			if(str != null)oauth = new OAuth(str.split(",")[0], str.split(",")[1]);
			else oauth = new OAuth(filePath, gui);
			twitter = oauth.getTwitter();
			return true;
		} catch (IOException | TwitterException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			gui = new GuiMain();
			if(!login()){System.out.println("Unable to Login"); return;}
			twitter.updateStatus("Šî‘b‚ÌTwitterBot‚ª‹N“®‚µ‚½‚æ!\n" + new Date());
			stream = new TwitterStreamFactory(twitter.getConfiguration()).getInstance();
			stream.addListener(new StreamListener(twitter, gui));
			stream.user();
			Runtime.getRuntime().addShutdownHook(new Shutdown(twitter, stream));
		} catch(TwitterException e) {
			e.printStackTrace();
		}
	}
}
