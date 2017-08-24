package com.jihf.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Func：appium测试微信发现模块
 * Desc:
 * Author：JHF
 * Data：2017-08-23 11:30
 * Mail：jihaifeng@raiyi.com
 */
public class TestWeChat {
    private AndroidDriver driver;

    public static final String deviceName = "F8UDU15120002671";
    public static final String platformName = "Android";
    public static final String platformVersion = "6.0";
    public static final String appPackage = "com.tencent.mm";
    public static final String appActivity = "com.tencent.mm.ui.LauncherUI";


    @Before
    public void setUp() throws Exception {
        //设置apk的路径
        File app = new File("apps/wx.apk");

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
    public void testFind() throws InterruptedException {
        // swipe to right
        System.out.println("driver："+driver);

        sleep(20);

    }

    private void sleep(int i) throws InterruptedException {
        Thread.sleep(i * 1000);
    }

    @After
    public void tearDown() throws Exception {
//        driver.quit();
    }
}