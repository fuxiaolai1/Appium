
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


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
    private boolean isInstall = false;

    public void startRecord() throws IOException {
        Runtime rt = Runtime.getRuntime();
        // this code for record the screen of your device
        rt.exec("cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4");
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        //启动appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "lge-nexus_4-005475cbccd279d4");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4.2");

        //配置测试apk
        capabilities.setCapability("appPackage", "com.zhihu.android");
        capabilities.setCapability("appActivity", ".app.ui.activity.MainActivity ");
        capabilities.setCapability("sessionOverride", true);    //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        capabilities.setCapability("unicodeKeyboard", true);    //设置键盘
        capabilities.setCapability("resetKeyboard", false);     //设置默认键盘为appium的键盘
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //如果真机设备已经安装知乎，则不需要重新安装
        if (isInstall) {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot, "apps");
            File app = new File(appDir, "zhihu.apk");
            capabilities.setCapability("app", app.getAbsolutePath());
        }
        startRecord();
    }

    @Test
    public void loginWithMicroBlog() throws InterruptedException {
        // swipe to right
        driver.findElementById("com.zhihu.android:id/register_btn").click();
        driver.findElementById("com.zhihu.android:id/username").sendKeys("手机号");
        driver.findElementById("com.zhihu.android:id/password").sendKeys("登录密码");
        driver.findElementById("com.zhihu.android:id/fullname").sendKeys("用户昵称");
        WebElement NxtBtn = driver.findElementById("com.zhihu.android:id/btn_progress");
        Assert.assertEquals(NxtBtn.isEnabled(), true);
        Thread.sleep(2000);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
