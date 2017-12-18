package ramunas.Alksnys;

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
	
}
