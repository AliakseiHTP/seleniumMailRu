package entity;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Math.random;

public class BaseMethod {
    public static boolean isPresent(WebDriver driver, String id) throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElements(By.id(id)).size() > 0;
    }

    public static boolean isPresentXpath(WebDriver driver, String xpath) throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public static int set_iRandom_number(int iFrom, int iTo) {
        return iFrom + (int) (random() * iTo);
    }

    public void moveMouseOn(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void needSleep(int iTime) throws InterruptedException {
        Thread.sleep(iTime);
        System.out.println(String.format("waiting %d millis", iTime));
    }

    public static void compareLabelText(String s1, String s2, WebDriver driver) throws InterruptedException{
        WebElement textOnSiteLabel = driver.findElement(By.xpath(s1));
        needSleep(1000);
        String sTextOnSite = textOnSiteLabel.getText();
        System.out.println(String.format("compares the text - '%s' (on site) & '%s' (reference)", sTextOnSite,s2));
        if(sTextOnSite.equals(s2)) {
            System.out.println("comparing texts... - texts are coincided");
        } else System.out.println("comparing texts... - texts are not coincide");
    }

    public static void clickOnElementJE(WebElement element, WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",element);
        System.out.println("Clicking using JavascriptExecutor");
    }
}
