/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homesecurity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author YAN
 */
public class Setting {

    public static String doorContact1;
    public static String doorContact2;
    public static int doorTime;

    public static String gasContact1;
    public static String gasContact2;
    public static int gasTime;

    public static String smokeContact1;
    public static String smokeContact2;
    public static int smokeTime;

    public Setting() throws FileNotFoundException {
       
    }
    
    
    /*
    Read the setting from the setting.txt
    */
    public static void readFile() throws FileNotFoundException {
        File file = new File("setting.txt");

        try (Scanner fileScanner = new Scanner(file)) {
            String line=fileScanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            doorContact1=lineScanner.next();
            doorContact2=lineScanner.next();
            doorTime=lineScanner.nextInt();
            lineScanner.close();
            
            line=fileScanner.nextLine();
            lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            gasContact1=lineScanner.next();
            gasContact2=lineScanner.next();
            gasTime=lineScanner.nextInt();
            lineScanner.close();
            
            line=fileScanner.nextLine();
            lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            smokeContact1=lineScanner.next();
            smokeContact2=lineScanner.next();
            smokeTime=lineScanner.nextInt();
            lineScanner.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    /*
    Write the current setting to setting.txt
    */
    public static void writeFile() throws FileNotFoundException {
     
        File outFile = new File("setting.txt");

        try (PrintWriter out = new PrintWriter(outFile)) {
            String line1 = doorContact1 + "," + doorContact2 + "," + doorTime;
            String line2 = gasContact1 + "," + gasContact2 + "," + gasTime;
            String line3 = smokeContact1 + "," + smokeContact2 + "," + smokeTime;
            
            out.println(line1);
            out.println(line2);
            out.println(line3);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}


