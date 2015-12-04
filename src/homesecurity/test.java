package homesecurity;

public class test {
public static void main(String args[])
{
	homeSecurityMain app=new homeSecurityMain();
	AlarmStores.addAlarm(AlarmStores.doorAlarm);
	app.check();
	}
}
