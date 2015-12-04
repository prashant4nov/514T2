package homesecurity;

public class DetectionTest implements Runnable {
	static final long RECORD_TIME = 10000;

	public void detect() {
		int i = 0;
		while (i > -1) {
			// if i%2==0 record, i%1==1 process, time is determined by Thread
			// Time
			String fileName = "";
			if (i % 2 == 0) {
				// record the sound
				fileName += "cs514.wav";
				final JavaSoundRecorder1 recorder = new JavaSoundRecorder1(
						fileName);
				Thread stopper = new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(RECORD_TIME);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
						recorder.finish();
					}
				});
				stopper.start();
				recorder.start();
			} else {
				// process the wav file
				if (i == 1) {
					System.out.println(4);
				} else {
					fileName += "cs514.wav";
					System.out.println("Hello");
					ReadExample rdexam = new ReadExample();
					int res = rdexam.readSound(fileName);
					System.out.println(res);
					if (res != AlarmStores.noneAlarm) {
						AlarmStores.addAlarm(res);
					}
				}
			}
			i++;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.detect();
	}

}
