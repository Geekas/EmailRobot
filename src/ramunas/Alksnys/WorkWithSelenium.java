package ramunas.Alksnys;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WorkWithSelenium {
	private WebDriver driver;
	private List<String[]> contactList = new ArrayList<>();

	public WorkWithSelenium() {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Romarijo\\workspace\\EmailRobot\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	public void letUserConManual() {
		loadPage();
		driver.findElement(By.xpath(".//*[@id='gb_70']")).click();
	}

	public void logInWithData(String userName, char[] password) throws InterruptedException {
		loadPage();
		driver.findElement(By.xpath(".//*[@id='gb_70']")).click();
		checkIsLoaded();
		enterUsername(userName);
		enterPassword(password);
		goToGmail();

	}

	public void startSendEmails(String subject, String emalContent, FileManager manager, JTextArea proccessLog) {
		//checkContents(subject, emalContent);
		if (subject.isEmpty()) {
			subject = Const.defaultSubjText;
		}
		if (emalContent.isEmpty() || emalContent.equals("")) {
			emalContent = Const.defaultContText;
		}
		System.out.println("Subject: " + subject);
		System.out.println("Content: " + emalContent);
		int index = -1;
		List<String> contacts = new ArrayList<>();
		List<String> company = new ArrayList(contacts);
		try {
			contacts = new FileReader().getTestData(manager.getContFile());
			filterData(contacts, company);
			// System.out.println(Arrays.deepToString(contactList.toArray()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proccessLog.append("Start sendind emails, Subject is: " + subject + "\nEmail content: " + emalContent);
		System.out.println("Start sendind emails, Subject is: " + subject + "\nEmail content: " + emalContent);
		for (String email[] : contactList) {
			proccessLog.append("\nSending to  Company: " + company.get(++index));
			System.out.println("\nSending to  Company: " + company.get(index));
			enterComposeEmail();
			enterReceiver(email[email.length - 1]);
			enterEmailContent(emalContent);
			enterSubject(subject);
			atachFiles(manager.getList());
			send();
		}
	}

	private void checkContents(String subject, String emalContent) {
		if (subject.isEmpty()) {
			subject = Const.defaultSubjText;
		}
		if (emalContent.isEmpty() || emalContent.equals("")) {
			emalContent = Const.defaultContText;
		}

	}

	private void enterEmailContent(String emalContent) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@class='Am Al editable LW-avf']")).sendKeys(emalContent);

	}

	private void atachFiles(List<String> list) {
		for (String file : list) {
			driver.findElement(By.xpath(".//*[@class='Am Al editable LW-avf']")).click();
			driver.findElement(By.xpath(".//div[@class='wG J-Z-I' and @aria-label='Attach files']")).click();
			StringSelection ss = new StringSelection(file);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Robot robot;
			try {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_V);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (AWTException e) {
				e.printStackTrace();
			}
//			 try {
//			 Thread.sleep(10000);
//			 } catch (InterruptedException e) {
//			 e.printStackTrace();
//			 }
		}

	}

	private void filterData(List<String> contacts, List<String> company) {
		List<String> tempLines = new ArrayList<>();
		for (String line : contacts) {
			String words[] = line.split(" ");
			if (!words[words.length - 1].contains("@")) {
				tempLines.add(line);
			} else {
				contactList.add(words);
				company.add(makeString(words));
			}

		}

	}

	private String makeString(String[] words) {
		List<String> wordsList = new ArrayList<>(Arrays.asList(words));
		wordsList.remove(wordsList.size()-1);
		StringBuilder sb = new StringBuilder();		
		for (String word : wordsList) {
			sb.append(word).append(" ");
		}
		return sb.toString();
	}

	private void send() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[contains(@data-tooltip,'Send')]")).click();
	}

	private void enterSubject(String text) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//input[@placeholder='Subject']")).sendKeys(text);
	}

	private void enterReceiver(String text) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//td[@class='eV']//textarea")).sendKeys(text);
	}

	private void enterComposeEmail() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@class='T-I J-J5-Ji T-I-KE L3']")).click();
	}

	private void goToGmail() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@class='gb_P']")).click();
	}

	private void enterUsername(String userName) {
		driver.findElement(By.xpath(".//*[@id='identifierId']")).sendKeys(userName);
		checkIsLoaded();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='identifierNext']")).click();
	}

	private void enterPassword(char[] password) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		checkIsLoaded();
		driver.findElement(By.xpath(".//*[@class='whsOnd zHQkBf']")).sendKeys(String.valueOf(password));
		driver.findElement(By.xpath(".//*[@id='passwordNext']")).click();
		checkIsLoaded();
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void waitForElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void checkIsLoaded() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	private void loadPage() {
		driver.get(Const.url);
		checkIsLoaded();
	}

	public void waitUntilElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

}
