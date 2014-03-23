package Bot;

import twitter4j.*;
import Gui.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateStatuser extends UpdateBotClass implements UpdateBot {
	private Twitter twitter;
	private GuiMain gui;
	private String tweet;
	private Status status;
	
	public UpdateStatuser(Twitter tmp, GuiMain guitmp) {
		twitter = tmp;
		gui = guitmp;
		tweet = "";
	}

	@Override
	public int searchWord(Status status) {
		// TODO Auto-generated method stub
		this.status = status;
		tweet = "";
		
		Matcher matcherSay;
		Matcher matcherUpdateStatus;
		
		try {
			matcherSay = Pattern.compile("^[@＠]" + twitter.getScreenName() + "[ 　]say[ 　]").matcher(status.getText());
			matcherUpdateStatus = Pattern.compile("^[@＠]" + twitter.getScreenName() + "[ 　]update_status[ 　]").matcher(status.getText());	
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			gui.addText("例外が発生しました。ご確認ください。→" + e.getClass().toString());
			return -1;
		}
		
		if(matcherSay.find()) {
			tweet = status.getText().replaceAll(matcherSay.group(), "");
			tweet = tweet.replaceAll("say", "");
			return 0;
		} else if(matcherUpdateStatus.find()) {
			tweet = status.getText().replaceAll(matcherUpdateStatus.group(), "");
			tweet = tweet.replaceAll("say", "");
			return 0;
		} else return -1;
	}

	@Override
	public void update() throws TwitterException {
		// TODO Auto-generated method stub
		if(!tweet.equals("")) {
			twitter.updateStatus(tweet);
			gui.addText(tweet + "とツイートしました。");
		} else {
			twitter.updateStatus(status.getUser().getScreenName() + "say の後になにか入力してください");
			gui.addText("文字列が空白の旨伝えました");
		}
	}
}
