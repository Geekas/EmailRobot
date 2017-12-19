package ramunas.Alksnys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Const {
	
	private Const() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}
	public static final String titleMain = "Email robot \u00a9Ramunas Alksnys Enjoy :)";
	public static final String titleConGmail = "Connect to Gmail";
	public static final String bSubmit = "Submit"; 
	public static final String bConGmail = "Connect gmail"; 
	public static String userLabel = "User Name";
	public static String passLabel = "Password";
	public static String userDefText = "Write your username";
	public static String selectAFileText = "Select files to atach";
	public static String clearFListText = "Clear atached files";
	public static String selectContFileText = "Sellect contacts file";
	public static String startSendText = "Start sending";
	public static String letterSubText = "Letters subject: ";
	public static String letterContText = "Write your letter content here: ";
	public static String chosenFileL = "Chosen files: ";
	public static String popUpTitle = "Gmail connection establishment";
	public static String questText = "If you are sign-in still will need to resign \nchoose option for you best";
	public static String noConnAutoText = "Connec automatic with your given data";
	public static String noConnManText = "Connect manual by me";
	public static String yesText = "Yes, I am connected";
	public static String url = "https://www.google.lt";	
	public static String errorText = "Please fill all the fields!";	
	public static String reminderText = "Please do not close your new opened window when log in to gmail, because this page works on our server";	
	public static String noAtachFileText = "You didnt choose any file";	
	public static String noContactFileText = "You didnt choose any contact file";
	public static String selectText = "Select file you want to atach";
	public static String selectContText = "Select contact file";	
}
