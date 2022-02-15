package com.selenioCurse.Udemy;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
    @Test
    public void loginTest() {


        //      Create friver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();

        // maximize browser
        driver.manage().window().maximize();
        sleep(3000);

        //    open page and
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        //enter usr Name
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        //enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        // enter Login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();


        //    verifications
        //    new url is matched
        String exceptedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, exceptedUrl,"Actual page url is not the same excepted");

        //   logout button is visable
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(),"LogOut button is not visible");
        //    succesful login message

        WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
        String excpectedMessage = "You logged into a secure area!\n" +
                "×";
        String actualMessage = successMessage.getText();
        Assert.assertTrue(actualMessage.contains(excpectedMessage),"Actual does not conteins Excepted message ");

        //Close Broser
        driver.close();

    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

