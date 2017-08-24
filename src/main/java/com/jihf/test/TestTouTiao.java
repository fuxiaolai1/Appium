package com.jihf.test;

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

    public static final String deviceName = "F8UDU15120002671";
    public static final String platformName = "Android";
    public static final String platformVersion = "6.0";
    public static final String appPackage = "com.ss.android.article.news";
    public static final String appActivity = "com.ss.android.article.news.activity.SplashActivity";

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

        sleep(2);

        if (null != webElement) {
            webElement.isDisplayed();
        }
        System.out.println("driver：" + driver);

        sleep(2);

        //使用wait.until方法替代之前为了确保到达主页而做的点击
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.ss.android.article.news:id/category_text\").text(\"热点\")").click();//这个步骤完全是为了确保应用启动到首页了，而不是还在启动页

        sleep(2);

        driver.findElementByClassName("android.widget.ImageView").click();// 个人中心是整个页面的第一个ImageView
        List<WebElement> textViewList = driver.findElementsByClassName("android.widget.TextView");
        WebElement moreType = null;
        for (WebElement textView : textViewList) {
            if (textView.getText().equals("更多登录方式")) {
                moreType = textView;
                break;
            }
        }
        moreType.click();
        List<WebElement> buttonList = driver.findElementsByClassName("android.widget.Button");    //注册新账号为当前页面第二个button，且只有2个button
        buttonList.get(1).click();

        driver.findElementByClassName("android.widget.EditText").sendKeys("12345678910");// 注册整个页面只有一个EditText
        driver.findElementByClassName("android.widget.Button").click();// 此界面只有一个button“下一步”
        driver.getPageSource().contains("手机号注册");//这一步输入不正确的手机号码时弹出框是toast，appium无法校验,所以验证界面还停留在当前页面即为成功

    }

    private void sleep(int i) throws InterruptedException {
        Thread.sleep(i * 1000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
