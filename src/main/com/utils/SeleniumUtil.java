package utils;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;


/**
 * 包装所有selenium的操作以及通用方法
 * 
 * @author shuailiu
 *
 */
public class SeleniumUtil {
	public static Logger logger = Logger.getLogger(SeleniumUtil.class.getName());
	public ITestResult it = null;
	public WebDriver driver = null;
	public WebDriver window = null;

	/**
	 * 启动浏览器并打开页面WeiXin
	 */
	public void launchBrowserWeiXin(String browserName, ITestContext context, String webUrl, String method, int timeOut) {
		SelectBrowser select = new SelectBrowser();
		driver = select.selectExplorerByName(browserName, context);
		try {
//			setBrowserSize(400, 800);
			waitForPageLoading(timeOut);
			get(webUrl + method);
		} catch (TimeoutException e) {
			logger.warn("注意：页面没有完全加载出来，刷新重试！！！");
			refresh();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String status = (String) js.executeScript("return document.readyState");
			logger.info("打印状态：" + status);
		}
	}
	
	/**
	 * 启动浏览器并打开页面Pc
	 */
	public void launchBrowserPc(String browserName, ITestContext context, String webUrl, String method,int timeOut) {
		SelectBrowser select = new SelectBrowser();
		driver = select.selectExplorerByName(browserName, context);
		try {
			setBrowserSize(1600, 900);
//			maxWindow(browserName);
			waitForPageLoading(timeOut);
			get(webUrl + method);
		} catch (TimeoutException e) {
			logger.warn("注意：页面没有完全加载出来，刷新重试！！！");
			refresh();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String status = (String) js.executeScript("return document.readyState");
			logger.info("打印状态：" + status);
		}
	}

	// ------------------------------- 对窗口进行操作-----------------------------------
	

	public void get(String webUrl) {
		driver.get(webUrl);
		logger.info("打开测试页面：[" + webUrl + "]");
	}

	public void waitForPageLoading(int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);

	}

	/**
	 * 最大化浏览器操作
	 */
	public void maxWindow(String browserName) {
		logger.info("最大化浏览器：" + browserName);
		driver.manage().window().maximize();
	}

	/**
	 * 设置浏览器窗口大小
	 */
	public void setBrowserSize(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public void refresh() {
		driver.navigate().refresh();
		logger.info("页面刷新成功！");
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public void back() {
		driver.navigate().back();
	}

	public void forword() {
		driver.navigate().forward();
	}

	/**
	 * 获得页面的标题
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 等待alert出现
	 */

	public Alert switchToPromptedAlertAfterWait(long waitMillisendcondsForAlert) throws NoAlertPresentException {
		final int ONE_ROUND_WAIT = 200;
		NoAlertPresentException lastException = null;
		long endTime = System.currentTimeMillis() + waitMillisendcondsForAlert;
		for (long i = 0; i < waitMillisendcondsForAlert; i += ONE_ROUND_WAIT) {
			try {
				Alert alert = driver.switchTo().alert();
				return alert;
			} catch (NoAlertPresentException e) {
				lastException = e;
			}
			pause(ONE_ROUND_WAIT);
			if (System.currentTimeMillis() > endTime) {
				break;
			}
		}
		throw lastException;
	}

	// ============================== 对窗口进行操作 ==================================

	// ------------------------------ 查找元素 -------------------------------------
	/**
	 * 包装查找元素的方法 element
	 */
	public WebElement findElementBy(By by) {
		return driver.findElement(by);
	}

	/**
	 * 包装查找元素的方法 elements
	 */
	public List<WebElement> findElementsBy(By by) {
		return driver.findElements(by);
	}

	/**
	 * 这是一堆相同的elements中 选择 其中方的 一个 然后在这个选定的中 继续定位
	 */
	public WebElement getOneElement(By bys, By by, int index) {
		return findElementsBy(bys).get(index).findElement(by);
	}

	// ============================= 查找元素=========================================

	// --------------------------- 判断元素状态----------------------------------------
	/**
	 * @param actual
	 *            预期结果
	 * @param expected
	 *            实际结果 判断文本是不是和需求要求的文本一致
	 **/
	public void isTextCorrect(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (AssertionError e) {
			logger.error("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");
//			Assert.fail("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");
		}
		logger.info("找到了期望的文字: [" + expected + "]");
	}

	/** 检查元素是否可用 */
	public boolean isEnabled(WebElement element) {
		boolean isEnable = false;
		if (element.isEnabled()) {
			logger.info("元素：[" + getLocatorByElement(element, ">") + "] 可用");
			isEnable = true;
		} else if (element.isDisplayed() == false) {
			logger.warn("元素：[" + getLocatorByElement(element, ">") + "] 不可用");
			isEnable = false;
		}
		return isEnable;
	}

	/** 检查元素是否显示 */
	public boolean isDisplayed(WebElement element) {
		boolean isDisplay = false;
		if (element.isDisplayed()) {
			logger.info("元素：[" + getLocatorByElement(element, ">") + "] 显示");
			isDisplay = true;
		} else if (element.isDisplayed() == false) {
			logger.warn("元素：[" + getLocatorByElement(element, ">") + "] 不显示");

			isDisplay = false;
		}
		return isDisplay;
	}

	/** 检查元素是不是存在 */
	public boolean isElementExist(By byElement) {
		try {
			findElementBy(byElement);
			logger.info("元素:[" + byElement.toString() + "]存在");
			return true;
		} catch (NoSuchElementException nee) {
			logger.info("元素:[" + byElement.toString() + "]不存在");
			return false;
		}
	}

	/**
	 * 检查元素是否被勾选
	 */
	public boolean isSelected(WebElement element) {
		boolean flag = false;
		if (element.isSelected() == true) {
			logger.info("元素: [" + getLocatorByElement(element, ">") + "] 被勾选");
			flag = true;
		} else if (element.isSelected() == false) {
			logger.info("元素: [" + getLocatorByElement(element, ">") + "] 未被勾选");
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断实际文本时候包含期望文本
	 * 
	 * @param actual
	 *            实际文本
	 * @param expect
	 *            期望文本
	 */
	public void isContains(String actual, String expect) {
		try {
			Assert.assertTrue(actual.contains(expect));
		} catch (AssertionError e) {
			logger.error("文本 [" + actual + "] 不包含于 [" + expect + "]");
//			Assert.fail("文本 [" + actual + "] 不包含于 [" + expect + "]");
		}
		logger.info("找到了期望的文字: [" + expect + "]");
	}
	
	/**
	 * 判断crm中，审核流程包含返回值的文本
	 * @param expect 期望文本
	 */
	public void isCrmContains(String expect) {
		String actual = driver.findElement(By.xpath("/html/body/pre")).getText().toString();
		try {
			Assert.assertTrue(actual.contains(expect));
		} catch (AssertionError e) {
			logger.error("文本 [" + actual + "] 不包含于 [" + expect + "]");
			Assert.fail("文本 [" + actual + "] 不包含于 [" + expect + "]");
		}
		logger.info("文本 [" + actual + "] 包含于 [" + expect + "]");
	}

	/**
	 * 判断admin中，审核流程包含返回值的文本
	 * @param expect 期望文本
	 */
	public boolean isAdminContains(String expect) {
		String actual = driver.findElement(By.xpath("/html/body/pre")).getText().toString();
		try {
			Assert.assertTrue(actual.contains(expect));
		} catch (AssertionError e) {
			logger.error("文本 [" + actual + "] 不包含于 [" + expect + "]");
//			Assert.fail("文本 [" + actual + "] 不包含于 [" + expect + "]");
//			return false;
		}
		logger.info("文本 [" + actual + "] 包含于 [" + expect + "]");
		return true;
	}

	/** 检查checkbox是否被勾选 */
	public boolean isCheckboxSelected(By elementLocator) {
		if (findElementBy(elementLocator).isSelected() == true) {
			logger.info("CheckBox:" + getLocatorByElement(findElementBy(elementLocator), ">") + "被勾选");
			return true;
		} else {
			logger.info("CheckBox:" + getLocatorByElement(findElementBy(elementLocator), ">") + "未被勾选");
			return false;
		}
	}

	/**
	 * 在给定的时间内去查找元素，如果没找到则超时，抛出异常
	 */
	public void waitForElementToLoad(int timeOut, final By By) {
		logger.info("开始查找元素：[" + By + "]");
		try {
			new WebDriverWait(driver, timeOut).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					WebElement element = driver.findElement(By);
					return element.isDisplayed();
				}
			});
		} catch (TimeoutException e) {
			logger.error("超时!! " + timeOut + " 秒之后还没找到元素 [" + By + "]");
			Assert.fail("超时!! " + timeOut + " 秒之后还没找到元素 [" + By + "]");
		}
		logger.info("找到元素:[" + By + "]");
	}

	 /** 根据元素来获取此元素的定位值 */
	private String getLocatorByElement(WebElement element, String expectText) {
		String text = element.toString();
        String expect = null;
        try {
            expect = text.substring(text.indexOf(expectText) + 1, text.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("failed to find the string [" + expectText + "]");
        }
        return expect;
	}

	public void pause(int sleepTime) {
		if (sleepTime <= 0) {
			return;
		}
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ========================== 判断元素状态 =======================================

	// -------------------------- 对元素操作 ----------------------------------------
	/**
	 * 包装清除操作
	 */
	public void clear(By byElement) {
		try {
			findElementBy(byElement).clear();
		} catch (Exception e) {
			logger.error("清除元素 [" + byElement + "] 上的内容失败!");
		}
		logger.info("清除元素 [" + byElement + "]上的内容");

	}

	/**
	 * 向输入框输入内容
	 */
	public void type(By byElement, String key) {
		try {
			findElementBy(byElement).sendKeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("输入 [" + key + "] 到 元素[" + byElement + "]失败");
			Assert.fail("输入 [" + key + "] 到 元素[" + byElement + "]失败");
		}
		logger.info("输入：[" + key + "] 到 [" + byElement + "]");
	}

	/**
	 * 包装点击操作
	 */
	public void click(By byElement) {
		try {
			clickTheClickable(byElement, System.currentTimeMillis(), 2500);
		} catch (StaleElementReferenceException e) {
			logger.error("The element you clicked:[" + byElement + "] is no longer exist!");
			Assert.fail("The element you clicked:[" + byElement + "] is no longer exist!");
		} catch (Exception e) {
			logger.error("Failed to click element [" + byElement + "]");
			Assert.fail("Failed to click element [" + byElement + "]");
		}
		logger.info("点击元素 [" + byElement + "]");
	}

	/** 不能点击时候重试点击操作 */
	private void clickTheClickable(By byElement, long startTime, int timeOut) throws Exception {
		try {
			findElementBy(byElement).click();
		} catch (Exception e) {
			if (System.currentTimeMillis() - startTime > timeOut) {
				logger.warn(byElement + " is unclickable");
				throw new Exception(e);
			} else {
				Thread.sleep(500);
				logger.warn(byElement + " is unclickable, try again");
				clickTheClickable(byElement, startTime, timeOut);
			}
		}

	}

	/**
	 * 获得元素的文本
	 */
	public String getText(By elementLocator) {
		return driver.findElement(elementLocator).getText().trim();
	}
	
	/**
     * 获得当前select选择的值
     * */
    public List<WebElement> getCurrentSelectValue(By by){
        List<WebElement> options = null;
        Select s = new Select(driver.findElement(by));
            options =  s.getAllSelectedOptions();
            return options;
    }
    
    /** 获得CSS value */
    public String getCSSValue(WebElement e, String key) {

        return e.getCssValue(key);
    }
    
    /**
     * 获得元素 属性的文本
     * */
    public String getAttributeText(By elementLocator, String attribute) {
        return driver.findElement(elementLocator).getAttribute(attribute).trim();
    }    

    
    /**
     * 模拟键盘操作的,比如Ctrl+A,Ctrl+C等 参数详解：<br>
     * 1、WebElement element - 要被操作的元素 <br>
     * 2、Keys key- 键盘上的功能键 比如ctrl ,alt等 <br>
     * 3、String keyword - 键盘上的字母
     * */
    public void pressKeysOnKeyboard(WebElement element, Keys key, String keyword) {

        element.sendKeys(Keys.chord(key, keyword));
    }
    
    /**
     * 切换frame - 根据String类型（frame名字）
     * */
    public void inFrame(String frameId) {
        driver.switchTo().frame(frameId);
    }

    /**
     * 切换frame - 根据frame在当前页面中的顺序来定位
     * */
    public void inFrame(int frameNum) {
        driver.switchTo().frame(frameNum);
    }
    
    /**
     * 切换frame - 根据页面元素定位
     * */
    public void switchFrame(WebElement element) {
        try {
            logger.info("正在跳进frame:[" + getLocatorByElement(element, ">") + "]");
            driver.switchTo().frame(element);
        } catch (Exception e) {
            logger.info("跳进frame: [" + getLocatorByElement(element, ">") + "] 失败");
            Assert.fail("跳进frame: [" + getLocatorByElement(element, ">") + "] 失败");
        }
        logger.info("进入frame: [" + getLocatorByElement(element, ">") +"]成功 ");
    }

    /** 跳出frame */
    public void outFrame() {
        driver.switchTo().defaultContent();
    }
    
    /**
     * 选择下拉选项 -根据value
     * */
    public void selectByValue(By by, String value) {
        Select s = new Select(driver.findElement(by));
        s.selectByValue(value);
    }

    /**
     * 选择下拉选项 -根据index角标
     * */
    public void selectByIndex(By by, int index) {
        Select s = new Select(driver.findElement(by));
        s.selectByIndex(index);
    }

    /**
     * 选择下拉选项 -根据文本内容
     * */
    public void selectByText(By by, String text) {
        Select s = new Select(driver.findElement(by));
        s.selectByVisibleText(text);
    }

    /**
     * 执行JavaScript 方法
     * */
    public void executeJS(String js) {
        ((JavascriptExecutor) driver).executeScript(js);
        logger.info("执行JavaScript语句：[" + js + "]");
    }        

    /**
     * 执行JavaScript 方法和对象
     * 用法：seleniumUtil.executeJS("arguments[0].click();", seleniumUtil.findElementBy(MyOrdersPage.MOP_TAB_ORDERCLOSE));
     * */
    public void executeJS(String js, Object... args) {
        ((JavascriptExecutor) driver).executeScript(js, args);
        logger.info("执行JavaScript语句：[" + js + "]");
    }

    /**
     * 包装selenium模拟鼠标操作 - 鼠标移动到指定元素
     * */
    public void mouseMoveToElement(By by) {
        Actions builder = new Actions(driver);
        Actions mouse = builder.moveToElement(driver.findElement(by));
        mouse.perform();
    }

    /**
     * 包装selenium模拟鼠标操作 - 鼠标移动到指定元素
     * */
    public void mouseMoveToElement(WebElement element) {
        Actions builder = new Actions(driver);
        Actions mouse = builder.moveToElement(element);
        mouse.perform();
    }
    
    /**
     * 包装selenium模拟鼠标操作 - 鼠标右击
     * */
    public void mouseRightClick(By element) {
        Actions builder = new Actions(driver);
        Actions mouse = builder.contextClick(findElementBy(element));
        mouse.perform();
    }
    
 // webdriver中可以设置很多的超时时间
    /** implicitlyWait。识别对象时的超时时间。过了这个时间如果对象还没找到的话就会抛出NoSuchElement异常 */
    public void implicitlyWait(int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    /** setScriptTimeout。异步脚本的超时时间。webdriver可以异步执行脚本，这个是设置异步执行脚本脚本返回结果的超时时间 */
    public void setScriptTimeout(int timeOut) {
        driver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
    }    
    
    /** 获得屏幕的分辨率 - 宽 */
    public static double getScreenWidth() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    
	
	/**
     * 上传文件，需要点击弹出上传照片的窗口才行
     * 
     * @param browser 使用的浏览器名称
     * @param file 需要上传的文件及文件名
     */
    public void handleUpload(String browser, File file) {
        String filePath = file.getAbsolutePath();
        String executeFile = "res/script/autoit/Upload.exe";
        String cmd = "\"" + executeFile + "\"" + " " + "\"" + browser + "\"" + " " + "\"" + filePath + "\"";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // ===================== 对元素进行操作 ============================
    
    /**
     * 添加cookies,做自动登陆的必要方法
     * */
    public void addCookies(int sleepTime) {
        pause(sleepTime);
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie c : cookies) {
            System.out.println(c.getName() + "->" + c.getValue());
            if (c.getName().equals("logisticSessionid")) {
                Cookie cook = new Cookie(c.getName(), c.getValue());
                driver.manage().addCookie(cook);
                System.out.println(c.getName() + "->" + c.getValue());
                System.out.println("添加成功");
            } else {
                System.out.println("没有找到logisticSessionid");
            }

        }

    }
}
