package Bot;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import twitter4j.*;
import Gui.*;

public class UpdateNamer extends UpdateBotClass implements UpdateBot {
	private Twitter twitter;
	private GuiMain gui;
	private String name;
	private Status status;
	
	public UpdateNamer(Twitter twitter, GuiMain tmp) {
		this.twitter = twitter;
		gui = tmp;
		name = "";
	}

	@Override
	public int searchWord(Status status) {
		// TODO Auto-generated method stub
		this.status = status;
		name = "";
		
		Matcher matcherUpdateName;
		Matcher matcherBracket;
		
		try {
			matcherUpdateName = Pattern.compile("^[@��]" + twitter.getScreenName() + "[ �@]update_name[ �@]").matcher(status.getText());
			matcherBracket = Pattern.compile("(\\(|�i)(@|��)" + twitter.getScreenName() + "(\\)|�j)").matcher(status.getText().replaceAll(" ", ""));
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			gui.addText("��O���������܂����B���m�F���������B��" + e.getClass().toString());
			return -1;
		}
		
		if(matcherUpdateName.find()) {
			name = status.getText().replaceAll(matcherUpdateName.group(), "");
			return 0;
		} else if(matcherBracket.find()) {
			name = status.getText().replaceAll(matcherBracket.group(), "");
			return matcherBracket.start();
		}else return -1;
	}

	@Override
	public void update() throws TwitterException {
		// TODO Auto-generated method stub
		if(name.length() > 20) {
			twitter.updateStatus("@" + status.getUser().getScreenName() + " ���O�̕��������������܂�");
			gui.addText("����������������|�`���܂���");
		} else if(name.length() == 0) {
			twitter.updateStatus("@" + status.getUser().getScreenName() + " ���O����͂��Ă�������");
			gui.addText("���O�����͂���ĂȂ��|�`���܂����B");
		} else {
			twitter.updateProfile(name, null, null, null);
			twitter.updateStatus(".@" + status.getUser().getScreenName() + "(" + status.getUser().getName() + ")�����\"" + name + "\"�Ƀ��l�[������܂����B");
			gui.addText("���O��ς��āA����ɂ��ăc�C�[�g���܂���");
		}
	}
}
