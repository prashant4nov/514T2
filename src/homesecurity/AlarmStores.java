package homesecurity;

public class AlarmStores {

	public static int alarms[] = new int[3];

	public static int smokeAlarm = 1;
	public static int doorAlarm = 2;
	public static int gasAlarm = 3;
	public static int noneAlarm = 4;
	
	
	public static int addAlarm(int alarm) {
		int exist = 0;
		for (int i = 0; i < alarms.length; i++) {
			if (alarms[i] == alarm) {
				exist = 1;
				System.out.println("already there!");
			}

		}
		if (exist == 0) {
			for (int i = 0; i < alarms.length; i++) {
				if (alarms[i] == 0) {
					alarms[i] = alarm;
					System.out.println("New Alarm ADDED!");
					break;
				}

			}
		}
		return 0;
	}

}
