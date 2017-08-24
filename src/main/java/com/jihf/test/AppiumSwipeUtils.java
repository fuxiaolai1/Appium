package com.jihf.test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * Func：
 * Desc:
 * Author：JHF
 * Data：2017-08-24 17:53
 * Mail：jihaifeng@raiyi.com
 */
public class AppiumSwipeUtils {
    /**
     * This Method for swipe up
     *
     * @param driver
     * @author Young
     */
    public static void swipeToUp(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction tAction=new TouchAction(driver);
        tAction.press(width / 2, height * 3 / 4).waitAction(800).moveTo(width / 2, height / 4).release().perform();
        // wait for page loading
    }

    /**
     * This Method for swipe down
     *
     * @param driver
     * @author Young
     */
    public static void swipeToDown(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction tAction=new TouchAction(driver);
        tAction.press(width/ 2,height / 4).waitAction(800).moveTo(width / 2,height * 3 / 4).release().perform();
        // wait for page loading
    }

    /**
     * This Method for swipe Left
     *
     * @param driver
     * @author Young
     */
    public static void swipeToLeft(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction tAction=new TouchAction(driver);
        tAction.press(width * 3 / 4,height / 2).waitAction(800).moveTo(width / 4,height / 2).release().perform();
        // wait for page loading
    }

    /**
     * This Method for swipe Right
     *
     * @param driver
     * @author Young
     */
    public static void swipeToRight(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction tAction=new TouchAction(driver);
        tAction.press(width / 4, height / 2).waitAction(800).moveTo(width * 3 / 4, height / 2).release().perform();
        // wait for page loading
    }
}
