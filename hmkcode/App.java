package com.hmkcode;

import com.hmkcode.vo.Content;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Sending POST to GCM" );
        
        String apiKey = "AIzaSyC0WDMFC8LDctE83m-ROFcnJYANIg1FmgM";
        Content content = createContent();
        
        POST2GCM.post(apiKey, content);
    }
    
    public static Content createContent(){
		
		Content c = new Content();
		
		c.addRegId("dpizFPgPbf8:APA91bEpr4ApSlAYY07xN76BNyRpnja9j5cGi310DibuHxLev47FIPriVYd33agAiuZsrslBJtadWhmEejB1A4EeDOsbHZaxLjc3inXQ4dAUkB3zRAoGmXppFHZto22EU1sAJDSLzgQF");
		c.createData("Test Title", "Test Message 233");
		
		return c;
	}
}
