package com.wingify;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Wingify {
	WebDriver driver;
	static String sort;

	@BeforeClass
	public void browseropen() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://sakshingp.github.io/assignment/login.html");
	}

	@Test
	public void loginpage() {
		driver.findElement(By.id("username")).sendKeys("asdf");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
	}

	@Test
	public void sort() {
		System.out.println("before sort application");
		List<WebElement> column = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));
		String[] beforesort = new String[column.size()];
		for (int i = 0; i < column.size(); i++) {
			beforesort[i] = column.get(i).getText().trim();
			System.out.println(beforesort[i]);
		}
		System.out.println("---------------------");
		System.out.println("sorting value using sort method");
		for (int i = 0; i < column.size(); i++) {
			Arrays.sort(beforesort);
			sort = beforesort[i];
			System.out.println(sort);
		}
		System.out.println("---------------------");
		driver.findElement(By.xpath("//th[@id='amount']")).click();

		column = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));
		String[] aftersort = new String[column.size()];
		System.out.println("After sort in application");
		System.out.println("---------------------");
		for (int i = 0; i < column.size(); i++) {
			aftersort[i] = column.get(i).getText().trim();
			System.out.println(aftersort[i]);
		}
		Assert.assertEquals(aftersort, beforesort);
		System.out.println("sorting successfully");
	}

	@AfterClass
	public void teardown() {
		driver.close();
	}
}
