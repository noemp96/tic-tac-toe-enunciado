package es.codeurjc.ais.tictactoe;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;


public class TicTacToeSeleniumTest {

    private WebDriver driver1, driver2;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebApp.start();
    }

    @AfterClass
    public static void teardownClass() {
        WebApp.stop();
    }

    @Before
    public void setupTest() {
        WebApp.start();
        driver1 = new ChromeDriver();
        driver2 = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver1 != null) {
            driver1.quit();
        }
        if (driver2 != null) {
            driver2.quit();
        }
    }

    
    public void TicTacToe() throws InterruptedException {
    	
    	//Se abren los navegadores
    	driver1.get("http://localhost:8080/"); //abrimos el navegador del Jugador1
        driver2.get("http://localhost:8080/"); //abrimos el navegador del Jugador2
        
        //Los jugadores se registran 
        driver1.findElement(By.id("nickname")).sendKeys("Jugador1"); //se registra el Jugador1
        driver1.findElement(By.id("startBtn")).click(); //el Jugador1 pulsa el boton de start     
        driver2.findElement(By.id("nickname")).sendKeys("Jugador2"); //se registra el Jugador2
        driver2.findElement(By.id("startBtn")).click(); //el Jugador2 pulsa el boton de start
        
        //Comienzan a jugar
        TimeUnit.SECONDS.sleep(1); //utilizamos estos sleep para poder observar su funcionamiento
        driver1.findElement(By.id("cell-0")).click();
        TimeUnit.SECONDS.sleep(1);
        driver2.findElement(By.id("cell-2")).click();
        TimeUnit.SECONDS.sleep(1);
        driver1.findElement(By.id("cell-1")).click();
        TimeUnit.SECONDS.sleep(1);
        driver2.findElement(By.id("cell-4")).click();
        TimeUnit.SECONDS.sleep(1);
        driver1.findElement(By.id("cell-5")).click();
        TimeUnit.SECONDS.sleep(1);
    }
    
    @Test
    public void TicTacToeSeleniumEmpate() throws InterruptedException {
    	
    	TicTacToe();
    	
    	//Siguen jugando y terminan empate
    	driver2.findElement(By.id("cell-3")).click();
    	TimeUnit.SECONDS.sleep(1);
    	driver1.findElement(By.id("cell-6")).click();
    	TimeUnit.SECONDS.sleep(1);
    	driver2.findElement(By.id("cell-7")).click();
    	TimeUnit.SECONDS.sleep(1);
        driver1.findElement(By.id("cell-8")).click();
        TimeUnit.SECONDS.sleep(1);
        
        //Comprobamos que el mensaje es el correcto
        Assert.assertEquals(driver1.switchTo().alert().getText(), "Draw!");
        Assert.assertEquals(driver2.switchTo().alert().getText(), "Draw!");
    }
    
    @Test
    public void TicTacToeSeleniumPerder() throws InterruptedException {
    	
    	TicTacToe();
    	
    	//Siguen jugando y pierde el Jugador1
    	TimeUnit.SECONDS.sleep(1);
    	driver2.findElement(By.id("cell-6")).click();
    	TimeUnit.SECONDS.sleep(1);
    	
    	//Comprobamos que el mensaje es el correcto
    	Assert.assertEquals(driver1.switchTo().alert().getText(), "Jugador2 wins! Jugador1 looses.");
    	Assert.assertEquals(driver2.switchTo().alert().getText(), "Jugador2 wins! Jugador1 looses.");
    }
    
    @Test
    public void TicTacToeSeleniumGanar() throws InterruptedException {
    	
    	TicTacToe();
    	
    	//Siguen jugando y gana el Jugador1
    	driver2.findElement(By.id("cell-8")).click();
    	TimeUnit.SECONDS.sleep(1);
    	driver1.findElement(By.id("cell-6")).click();
    	TimeUnit.SECONDS.sleep(1);
    	driver2.findElement(By.id("cell-7")).click();
    	TimeUnit.SECONDS.sleep(1);
        driver1.findElement(By.id("cell-3")).click();
        TimeUnit.SECONDS.sleep(1);
        
      //Comprobamos que el mensaje es el correcto
        Assert.assertEquals(driver1.switchTo().alert().getText(), "Jugador1 wins! Jugador2 looses.");
        Assert.assertEquals(driver2.switchTo().alert().getText(), "Jugador1 wins! Jugador2 looses.");
    }
    
}

