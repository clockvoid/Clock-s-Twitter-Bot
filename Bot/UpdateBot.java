package Bot;

import twitter4j.*;

public interface UpdateBot {
	public int searchWord(Status status);
	public void update() throws TwitterException;
}
