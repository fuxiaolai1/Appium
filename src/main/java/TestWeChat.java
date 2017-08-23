
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Func：appium测试微信发现模块
 * Desc:
 * Author：JHF
 * Data：2017-08-23 11:30
 * Mail：jihaifeng@raiyi.com
 */
public class TestWeChat {
    private static AndroidDriver driver;

    public static final String deviceName = "vivo X7";
    public static final String platformName = "Android";
    public static final String platformVersion = "7.1.1";
    public static final String appPackage = "com.tencent.mm";
    public static final String appActivity = "com.tencent.mm.ui.LauncherUI";

    @BeforeClass
    public void setUp() throws Exception {
        //设置apk的路径
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "微信.apk");

        //设置自动化相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        //设置安卓系统版本
        capabilities.setCapability("platformVersion", platformVersion);
//        //设置apk路径
//        capabilities.setCapability("app", app.getAbsolutePath());

        //设置app的主包名和主类名
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        //初始化
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test
    public void testWeChat() {
        System.out.println("driver：" + driver);
//        WebElement el = driver.findElement(By.name("Add Contact"));
//        el.click();
//        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
//        textFieldsList.get(0).sendKeys("Some Name");
//        textFieldsList.get(2).sendKeys("Some@example.com");
//        driver.swipe(100, 500, 100, 100, 2);
//        driver.findElementByName("Save").click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
