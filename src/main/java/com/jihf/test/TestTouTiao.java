package com.jihf.test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Func：
 * Desc:
 * Author：JHF
 * Data：2017-08-24 16:29
 * Mail：jihaifeng@raiyi.com
 */
public class TestTouTiao {

    private static AndroidDriver driver;

    private static final String deviceName = "F8UDU15120002671";
    private static final String platformName = "Android";
    private static final String platformVersion = "6.0";
    private static final String appPackage = "com.ss.android.article.news";
    private static final String appActivity = "com.ss.android.article.news.activity.SplashActivity";

    @Before
    public void setUp() throws Exception {
        //设置apk的路径
        File app = new File("apps/jinritoutiao.apk");

        //设置自动化相关参数
        DesiredCapabilities capa = new DesiredCapabilities();
        capa.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capa.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        //设置安卓系统版本
        capa.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capa.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capa.setCapability("appPackage", appPackage);
        capa.setCapability("appActivity", appActivity);
        capa.setCapability("noReset", true);
        //初始化
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capa);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    @Test
    public void byClassNameTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement webElement = wait.until(new ExpectedCondition<WebElement>() {

            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("com.ss.android.article.news:id/category_text"));
            }
        });
        System.out.println("webElement：" + webElement);

        if (null != webElement) {
            webElement.isDisplayed();
        }
        System.out.println("driver：" + driver);

        sleep(2);

        //点击 <热点> tab标签
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/category_text\").text(\"热点\")").click();

        sleep(3);

        int i = 0;
        while (i < 9) {
            if (i < 2) {
                AppiumSwipeUtils.swipeToDown(driver);
                System.out.println("下滑");
            }
            if (i >= 2 && i < 4) {
                AppiumSwipeUtils.swipeToUp(driver);
                System.out.println("上滑");
            }
            if (i >= 4 && i < 6) {
                AppiumSwipeUtils.swipeToLeft(driver);
                System.out.println("左滑");
            }
            if (i >= 6 && i < 8) {
                AppiumSwipeUtils.swipeToRight(driver);
                System.out.println("右滑");
            }
            i++;
            sleep(3);
        }

        driver.findElementById("com.ss.android.article.news:id/icon_category").click();

        sleep(3);

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"图片\")").click();

        sleep(3);

        // 个人中心是整个页面的第一个ImageView
        driver.findElementByClassName("android.widget.ImageView").click();
        sleep(3);


        driver.findElementById("com.ss.android.article.news:id/night_btn").click();

        sleep(3);


        driver.findElementById("com.ss.android.article.news:id/night_btn").click();

        sleep(3);


        driver.findElementById("com.ss.android.article.news:id/setting_btn").click();

        sleep(3);

        driver.findElementById("com.ss.android.article.news:id/back").click();

        sleep(3);

        List<WebElement> textViewList = driver.findElementsByClassName("android.widget.TextView");
        WebElement moreLogin = null;
        for (WebElement textView : textViewList) {
            if (textView.getText().contains("更多登录")) {
                moreLogin = textView;
                break;
            }
        }
        if (moreLogin != null) {
            moreLogin.click();
        }

        sleep(3);

        //注册新账号为当前页面第二个button，且只有2个button
        List<WebElement> buttonList = driver.findElementsByClassName("android.widget.Button");

        buttonList.get(1).click();

        sleep(3);
        // 注册整个页面只有一个EditText
        driver.findElementByClassName("android.widget.EditText").sendKeys("12345678910");

        sleep(3);
        // 此界面只有一个button“下一步”
        driver.findElementByClassName("android.widget.Button").click();

        sleep(3);

        driver.findElementById("com.ss.android.article.news:id/back").click();

        sleep(3);

        driver.findElementById("com.ss.android.article.news:id/back").click();

        sleep(3);

        driver.findElementById("com.ss.android.article.news:id/back").click();

        sleep(3);

    }

    private void sleep(int i) throws InterruptedException {
        Thread.sleep(i * 1000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
