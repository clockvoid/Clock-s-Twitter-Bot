import java.util.Date;

import twitter4j.*;

public class Shutdown extends Thread {
	private Twitter twitter;
	private TwitterStream stream;
	
	public Shutdown (Twitter twi, TwitterStream str) {
		twitter = twi;
		stream = str;
	}
	
	public void run() {
		try {
			twitter.updateStatus("Šî‘b‚ÌTwitterBot‚Íˆê‰ñ—Ž‚¿‚Ü‚·\n" + new Date());
			stream.shutdown();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
