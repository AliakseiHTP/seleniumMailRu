package run;

import entity.BaseMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

import static entity.BaseMethod.set_iRandom_number;
import static entity.PropertyManager.getProperty;

public class MailApp {
    private static final String CHROME = "webdriver.chrome.driver";
    private static final String CHROME_PATH = "src//main//resources//chromedriver.exe";
    private static final String LOGIN_INPUT = "//input[@id='mailbox:login']";
    private static final String PASSWORD_INPUT = "//input[@id='mailbox:password']";
    private static final String LOGIN_BTN = "//input[@class='o-control']";
    private static final String REMEMBER_ME_CH_BX = "//input[@class='mailbox__saveauth'][@checked='checked']";
    private static final String LOGIN = getProperty("login");
    private static final String PASSWORD = getProperty("password");
    private static final String EMAIL_TO = getProperty("email_to");
    private static final String EMAIL_TOPIC = getProperty("mail_topic");
    private static final String EMAIL_TEXT = getProperty("mail_text");
    private static final String I_FRAME = "//iframe[contains(@id,'composeEditor_ifr')]";
    private static final String SEND_MESSAGE = "//div[@id='b-toolbar__left']//a[@data-name='compose']";
    private static final String SEND_TO = "//textarea[@data-original-name='To']";
    private static final String EMAIL_TOPIC_INPUT = "//input[@name='Subject']";
    private static final String EMAIL_TEXT_INPUT = "//body[@id='tinymce']";
    private static final String EMAIL_SEND = "(//div[@data-name='send']//span[@class='b-toolbar__btn__text'])[1]";
    private static final String SENT_MESSAGE = "//a[@href='/messages/sent/']";
    private static final String NEED_MESSAGE = "//a[@data-subject='%s']";
    private static final String DELETE_MESSAGE = "(//div[@data-mnemo='toolbar-letter']//div[@data-name='remove'])[1]";


    public static void main(String[] args) throws InterruptedException {
        System.setProperty(CHROME, CHROME_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(Arrays.asList("--incognito", "--start-maximized","--lang=ru"));
        WebDriver driverCH = new ChromeDriver(chromeOptions);
        driverCH.get("https://www.mail.ru/");

        WebElement loginTbx = driverCH.findElement(By.xpath(LOGIN_INPUT));
        loginTbx.sendKeys(LOGIN);
        WebElement passwordTbx = driverCH.findElement(By.xpath(PASSWORD_INPUT));
        passwordTbx.sendKeys(PASSWORD);

        if(BaseMethod.isPresentXpath(driverCH, REMEMBER_ME_CH_BX)) {
            WebElement rememberMeChBx = driverCH.findElement(By.xpath(REMEMBER_ME_CH_BX));
            rememberMeChBx.click();
        }
        WebElement logInBtn = driverCH.findElement(By.xpath(LOGIN_BTN));
        logInBtn.click();
        Thread.sleep(5000);

        WebElement sendMessage = driverCH.findElement(By.xpath(SEND_MESSAGE));
        sendMessage.click();
        Thread.sleep(2000);

        WebElement sendTo = driverCH.findElement(By.xpath(SEND_TO));
        sendTo.sendKeys(EMAIL_TO);

        WebElement emailTopic = driverCH.findElement(By.xpath(EMAIL_TOPIC_INPUT));
        String sTopic = (EMAIL_TOPIC + set_iRandom_number(1,1000));
        emailTopic.sendKeys(sTopic);

        WebElement iFrame = driverCH.findElement(By.xpath(I_FRAME));
        driverCH.switchTo().frame(iFrame);
        WebElement emailText = driverCH.findElement(By.xpath(EMAIL_TEXT_INPUT));
        emailText.sendKeys(EMAIL_TEXT);
        driverCH.switchTo().defaultContent();
        WebElement sendEmail = driverCH.findElement(By.xpath(EMAIL_SEND));
        sendEmail.click();
        Thread.sleep(2000);


        WebElement sentMessage = driverCH.findElement(By.xpath(SENT_MESSAGE));
        sentMessage.click();
        Thread.sleep(2000);

        if(BaseMethod.isPresentXpath(driverCH, String.format(NEED_MESSAGE,sTopic))) {
            WebElement needMail = driverCH.findElement(By.xpath(String.format(NEED_MESSAGE, sTopic)));
            needMail.click();
            Thread.sleep(2000);
            System.out.println("The letter was sent");
        } else System.out.println("The letter wasn't send");

        WebElement deleteMessage = driverCH.findElement(By.xpath(DELETE_MESSAGE));
        deleteMessage.click();
        Thread.sleep(2000);
        System.out.println("The letter deleted");
        driverCH.close();
    }
}
