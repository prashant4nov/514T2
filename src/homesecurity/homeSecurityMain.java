package homesecurity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import com.hmkcode.App;

//import com.skype.SkypeException;

//author: Qiumao Ma

public class homeSecurityMain implements Runnable {

	public int alarms[];
	public int processing;
    
	int smokeAlarm = 1;
//	int doorAlarm = 2;
//	int gasAlarm = 3;

	//skypeCall skype;

	//
	public homeSecurityMain() {
		processing = 0;
//		alarms = new int[3];
	//	skype = new skypeCall();
		try {
			Setting.readFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// a thread for sound recg
		new Thread(new DetectionTest()).start();
		//

	}

	//
	public int check() {
		while (true) {
			for (int i = 0; i < AlarmStores.alarms.length; i++) {
				if ( AlarmStores.alarms[i] != 0 && processing !=  AlarmStores.alarms[i]) {
					int rsp = processAlarms( AlarmStores.alarms[i]);
					if (rsp == 0) {
						removeAlarm( AlarmStores.alarms[i]);
						processing = 0;
					}
				}
			}
		}
		// return 0;

	}

//	int addAlarm(int alarm) {
//		int exist = 0;
//		for (int i = 0; i < alarms.length; i++) {
//			if (alarms[i] == alarm) {
//				exist = 1;
//				System.out.println("already there!");
//			}
//
//		}
//		if (exist == 0) {
//			for (int i = 0; i < alarms.length; i++) {
//				if (alarms[i] == 0) {
//					alarms[i] = alarm;
//					System.out.println("New Alarm ADDED!");
//					break;
//				}
//
//			}
//		}
//		return 0;
//	}

	int removeAlarm(int alarm) {
		for (int i = 0; i <  AlarmStores.alarms.length; i++) {
			if ( AlarmStores.alarms[i] == alarm) {
				 AlarmStores.alarms[i] = 0;
				System.out.println("Alarm removed!");
			}

		}
		return 0;
	}

	int processAlarms(int alarm) {

		// show alert window on Computer and App
		if (alarm ==  AlarmStores.smokeAlarm) {
			// showAlert(smokeAlarm,Setting.smokeTime);
			SmokeAlarm al = new SmokeAlarm();
			al.setVisible(true);
			App sendAlert = new App();
			sendAlert.sendAlert("Fire Detected!");
			// Code to send message to the mobile app.
			try {
				Thread.sleep(Setting.smokeTime * 1000);
				al.setVisible(false);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (al.cancelFlag == 1) {
				removeAlarm(alarm);
				processing = 0;
				System.out.println("ALarm is cancelled by User!");
			} else {
				System.out
						.println("Alarm is NOT cancelled, will call for help!");
				int ret= callViaSkype(Setting.smokeContact1,
						Setting.smokeContact2);
				if(ret==0)
				{
					File Clap = new File( "FirealarmGreeting.wav" );
			        JavaSoundDisplay.PlaySound( Clap );
					
				}
				return ret;
			}
		} else if (alarm ==  AlarmStores.doorAlarm) {
			// showAlert(doorAlarm,Setting.doorTime);
			DoorAlarm al = new DoorAlarm();
			al.setVisible(true);
			App sendAlert = new App();
			sendAlert.sendAlert("Somebody has broken into the house!");
			try {
				Thread.sleep(Setting.doorTime * 1000);
				al.setVisible(false);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (al.cancelFlag == 1) {
				removeAlarm(alarm);
				processing = 0;
				System.out.println("ALarm is cancelled by User!");
			} else {
				System.out
						.println("Alarm is NOT cancelled, will call for help!");
				String fixPwd = "123456";
				String changePwd = "http://192.168.0.105:8080/set_users.cgi?user1=admin&pwd1=team2&pri1=2&user2=security&pwd2="
						+ fixPwd
						+ "&pri2=0&user3=Anonymous&pwd3=Anonymous&pri3=0&user4=test&pwd4=12345&pri4=0&user5=&pwd5=&pri5=0&user6=&pwd6=&pri6=0&user7=&pwd7=&pri7=0&user8=&pwd8=&pri8=0&user=admin&pwd=team2";
			//	System.out.println(changePwd);
				SimpleSwingBrowser vc = new SimpleSwingBrowser();
				vc.loadURL(changePwd);
				new Thread(new changePwd()).start();
				int ret= callViaSkype(Setting.doorContact1, Setting.doorContact2);
				if(ret==0)
				{
					File Clap = new File( "WindowalarmGreeting.wav" );
			        JavaSoundDisplay.PlaySound( Clap );

//					}
				}
				return ret;

			}
		} else if (alarm ==  AlarmStores.gasAlarm) {
			// showAlert(gasAlarm,Setting.gasTime);
			GasAlarm al = new GasAlarm();
			al.setVisible(true);
			App sendAlert = new App();
			sendAlert.sendAlert("Gas Detected!");
			try {
				Thread.sleep(Setting.gasTime * 1000);
				al.setVisible(false);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (al.cancelFlag == 1) {
				removeAlarm(alarm);
				processing = 0;
				System.out.println("ALarm is cancelled by User!");
			} else {
				System.out
						.println("Alarm is NOT cancelled, will call for help!");
				int ret= callViaSkype(Setting.gasContact1, Setting.gasContact2);
				if(ret==0)
				{
					File Clap = new File( "GasAlarmGreeting.wav" );
			        JavaSoundDisplay.PlaySound( Clap );
					
				}
				return ret;
			}
		} else {
			System.out.print("Wrong ALRAM type:" + alarm);
		}

		return 0;
	}

	int callViaSkype(String callID1, String callID2) {
		return 0;
		/**
		int ret = 0;
		// skypeCall call=new skypeCall();
		try {

			ret = skype.call(callID1);
			if (ret != 0) {
				ret = skype.call(callID2);
				return ret;
			}
		} catch (SkypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;*/
	}

	// int showAlert(int alarmType, int delayTime) {
	// // call app
	// // call windows
	//
	// return 0;
	// }

	public static void main(String args[]) {
		homeSecurityMain app = new homeSecurityMain();
		AlarmStores.addAlarm(AlarmStores.doorAlarm);
		// app.addAlarm(app.gasAlarm);
		// app.addAlarm(app.doorAlarm);
		// app.addAlarm(app.smokeAlarm);
		// app.addAlarm(app.gasAlarm);
		// app.removeAlarm(app.doorAlarm);
		app.check();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// addAlarm(smokeAlarm);
		// addAlarm(gasAlarm);
		AlarmStores.addAlarm(AlarmStores.doorAlarm);
		this.check();
	}
}
