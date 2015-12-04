package homesecurity;


public class changePwd implements Runnable {
	public void run() {
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String unknowPwd = "12345678";

		String changePwdBack = "http://192.168.0.105:8080/set_users.cgi?user1=admin&pwd1=team2&pri1=2&user2=security&pwd2="
				+ unknowPwd
				+ "&pri2=0&user3=Anonymous&pwd3=Anonymous&pri3=0&user4=test&pwd4=12345&pri4=0&user5=&pwd5=&pri5=0&user6=&pwd6=&pri6=0&user7=&pwd7=&pri7=0&user8=&pwd8=&pri8=0&user=admin&pwd=team2";
		SimpleSwingBrowser vc=new SimpleSwingBrowser();
        vc.loadURL(changePwdBack);
        System.out.println("Password changed back.");
		
	}
	public static void main(String args[])
	{
		changePwd c= new changePwd();
		c.run();
	}
}
