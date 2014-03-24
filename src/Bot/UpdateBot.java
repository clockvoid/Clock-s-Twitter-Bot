package Bot;

import twitter4j.*;

public interface UpdateBot {
	public int searchWord(Status status);
	public void update() throws TwitterException;
	public void setTwitter(Twitter twitter);
	public void setGui(Object gui);
}
