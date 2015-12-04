package homesecurity;
/**
//Author: Qiumao Ma
import com.skype.Call;
import com.skype.Call.Status;
import com.skype.Chat;
import com.skype.Skype;
import com.skype.SkypeException;

public class skypeCall {
	Call currentCall;

	public skypeCall() {

		currentCall = null;
	}
	public int sendMsg(String callID, String msg) 
	{
		Chat c;
		try {
			c = Skype.chat(callID);		
			c.send(msg);
			Chat.Status i=c.getStatus();
		//	System.out.println(i.toString());
		} catch (SkypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	public int call(String callID) throws SkypeException {
		// Skype.isRunning()
		// Skype.r
		Call[] calls = Skype.getAllActiveCalls();
		for (int i = 0; i < calls.length; i++) {
			System.out.println(calls[i]);
			calls[i].finish();
		}
		Call c = Skype.call(callID);
		currentCall = c;
		
		int code = c.getErrorCode();
	//	System.out.println("code" + code);
		if (code != 0) {
			System.out.println("Call unsuccesful!");
			return -1;
		}
		Status s = c.getStatus();
		long startTime=System.currentTimeMillis();
		while (true) {
			long currentTime=System.currentTimeMillis();
			if(currentTime-startTime>=30000)
			{
				System.out.println("NO answer!");
				return -1;
			}
			s = c.getStatus();
			if (Status.REFUSED == s || Status.FAILED == s || Status.MISSED == s
					|| Status.UNPLACED == s || Status.VM_CANCELLED == s ||Status.BUSY==s || Status.CANCELLED==s) {
				System.out.println("REFUSED!");
				return -1;
				// break;
			} else if (Status.INPROGRESS == s) {
				System.out.println("INPROGRESS!");
				return 0;
				// System.out.println("NOT REFUSED!");
			}
			
		}
		// int dur=c.getDuration();
		// if(dur<=2)
		// {
		// System.out.println("DUR Call unsuccesful!");
		// }
		// return 0;

	}

	public static void main(String[] args) throws SkypeException {

		skypeCall sky = new skypeCall();
		sky.call("dengyan.china");
		sky.sendMsg("dengyan.china","test");
	}
}
*/