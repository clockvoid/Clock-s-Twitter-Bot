package Bot;

import twitter4j.*;
import Gui.*;

import java.util.*;

public class StreamListener implements UserStreamListener{
	private static List<UpdateBotClass> botClasses;
	private static GuiMain gui;
	private static Twitter twitter;
	
	public StreamListener(Twitter twi, GuiMain tmp) throws TwitterException, IllegalStateException {
		twitter = twi;
		gui = tmp;
		botClasses = new ArrayList<UpdateBotClass>();
		botClasses.add(new UpdateNamer(twitter, gui));
		botClasses.add(new UpdateStatuser(twitter, gui));
	}
	
	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatus(Status arg0) {
		// TODO Auto-generated method stub
		gui.addText(arg0.getText() + "\n-by @" + arg0.getUser().getScreenName());
		int min = 140, indexNum = -1, count = 0;
		for(UpdateBotClass bot : botClasses ) {
			int num = bot.searchWord(arg0);
			if(num < min && num != -1) { min = num; indexNum = count; }
			count ++;
		}
		try {
			if(indexNum != -1)botClasses.get(indexNum).update();
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			gui.addText("例外が発生しました。ご確認ください→" + e.getClass().toString());
		}
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub
		gui.addText(arg0.getMessage());
	}

	@Override
	public void onBlock(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeletionNotice(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDirectMessage(DirectMessage arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFollow(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFriendList(long[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnblock(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnfavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListCreation(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListDeletion(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListUpdate(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserProfileUpdate(User arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnfollow(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
