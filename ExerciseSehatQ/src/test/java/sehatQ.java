import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class sehatQ {

    @Test // this scenario login with valid credential
    public void login() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions(); //this line spesifically for web socket connection
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.sehatq.com");

        driver.manage().window().maximize();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait until search field shown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("searchInput")));

        driver.findElement(By.xpath("//img[contains(@alt,'SehatQ Profile')]")).click();
        driver.findElement(By.id("email")).sendKeys("widyamasril@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Widya123");
        driver.findElement(By.xpath("//BUTTON[@type='submit'][text()='Login']")).click();

        Assert.assertEquals(true, driver.findElement(By.xpath("//img[@src='https://static.sehatq.com/account/picture-empty/image-20220105-100017.png']")).isDisplayed());

        driver.quit();

    }

    @Test //this scenario login without input credential
    public void loginWithoutCred(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions(); //this line spesifically for web socket connection
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.sehatq.com");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait until search field shown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("searchInput")));

        driver.findElement(By.xpath("//img[contains(@alt,'SehatQ Profile')]")).click();
        driver.findElement(By.id("email")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.xpath("//BUTTON[@type='submit'][text()='Login']")).click();

        Assert.assertEquals(true, driver.findElement(By.xpath("(//div[contains(.,'Alamat Email wajib diisi')])[10]")).isDisplayed());

        driver.quit();

    }
    
    @Test //this scenario to edit profil
    public void editProfile() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions(); //this line spesifically for web socket connection
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.sehatq.com");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait until search field shown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("searchInput")));

        driver.findElement(By.xpath("//img[contains(@alt,'SehatQ Profile')]")).click();
        driver.findElement(By.id("email")).sendKeys("widyamasril@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Widya123");
        driver.findElement(By.xpath("//BUTTON[@type='submit'][text()='Login']")).click();
        Thread.sleep(3000);

        //click profile icon
        driver.findElement(By.xpath("//div[3]/div/div[2]//img")).click();
        driver.findElement(By.xpath("//a[. = 'Profil']")).click();
        Thread.sleep(5000);

        //click edit profile button
        driver.findElement(By.xpath("//a[. = 'Edit Profil']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='address']")));

        //edit address
        driver.findElement(By.xpath("//textarea[@name='address']")).click();
        driver.findElement(By.xpath("//textarea[@name='address']")).clear();
        driver.findElement(By.xpath("//textarea[@name='address']")).sendKeys("Jakarta");

        //click save button
        driver.findElement(By.xpath("//button[contains(.,'Simpan')]")).click();

        driver.quit();

    }
}
