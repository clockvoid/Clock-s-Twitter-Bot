package Bot;

import twitter4j.*;
import Gui.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class StreamListener implements UserStreamListener{
	private static List<UpdateBot> botClasses;
	private static GuiMain gui;
	private static Twitter twitter;
	
	@SuppressWarnings("resource" )
	private void getPlugins() {
		File pluginDir = new File(System.getProperty("user.dir") + File.separator + "plugins");
		for(File f : pluginDir.listFiles()) {
			try {
				if(f.getName().endsWith(".jar")) {
					JarFile jar = new JarFile(f);
					URLClassLoader loader = new URLClassLoader(new URL[] { f.toURI().toURL() });
					for (Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements();) {
						JarEntry ze = (JarEntry)e.nextElement();
						if (!ze.isDirectory() && ze.getName().endsWith(".class")) {
							Class<?> c = loader.loadClass(ze.getName().replace('/', '.').replace(".class", ""));
							for(int i = 0; i < c.getInterfaces().length; i ++) {
								if (c.getInterfaces()[i] == UpdateBot.class) {
									gui.addText("Loaded Plugin -> " + c.getName());
									UpdateBot p = (UpdateBot)c.newInstance();
									p.setGui(gui);
									p.setTwitter(twitter);
									botClasses.add(p);
								} else {
									gui.addText(c.getName() + "はプラグインとして認められません");
								}
							}
						}
					}
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
				gui.addText("例外が発生しました。ご確認ください。→" + e.getClass().getName());
			}
		}
	}
	
	public StreamListener(Twitter twi, GuiMain tmp) throws TwitterException, IllegalStateException {
		twitter = twi;
		gui = tmp;
		botClasses = new ArrayList<UpdateBot>();
		getPlugins();
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
		int min = 140, indexNum = -1, count = 0;
		for(UpdateBot bot : botClasses ) {
			int num = bot.searchWord(arg0);
			if(num < min && num != -1) { min = num; indexNum = count; }
			count ++;
		}
		try {
			if(indexNum != -1) {
				gui.addText(arg0.getText() + "\n-by @" + arg0.getUser().getScreenName());
				botClasses.get(indexNum).update();
			}
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
