package Automatizacion.Navegacion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.script.Region;
import org.testng.annotations.Test;
import org.sikuli.script.Finder;
import org.sikuli.script.Image;
import org.sikuli.script.Pattern;

public class PruebaApp {

	WebDriver driver;

	List<CicActual> listaCic;

	DesiredCapabilities capabilities;

	public PruebaApp() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver","/Users/moviltest/Downloads/chromedriver");

//		listaCic = new ArrayList<CicActual>();

//		capabilities = DesiredCapabilities.chrome();
		//
		// ChromeOptions options = new ChromeOptions();
		//// options.addExtensions(new File("src/test/resources/manifest.json"));
		// options.setBinary(new File("/Applications/Google Chrome.app"));
		// options.addArguments("start-maximized");
		//

//		capabilities.setCapability("recreateChromeDriverSessions", true);
//
//		driver = new RemoteWebDriver(new URL("http://192.168.0.11:4444/wd/hub"), capabilities);
		
		
		driver = new ChromeDriver();

	}

	@Test
	public void prueba() {
		
		
		driver.get("https://www.facebook.com");
		
		driver.close();
		driver.quit();
		
		
	}
	
	
	public void iniciarSesion() {

		try {
			System.out.println("Session:" + ((RemoteWebDriver) driver).getSessionId());

			driver.get("http://192.168.0.18/CIC/CIC.html");

			TimeUnit.SECONDS.sleep(3);

			JOptionPane.showMessageDialog(null, "En espera del loggin", "Iniciar Robot",
					JOptionPane.INFORMATION_MESSAGE);

			driver.get("http://192.168.0.18/CIC/CIC2.html");
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void obtenerDataTabla1(String Cedula) {

		try {

			driver.get("http://192.168.0.18/CIC/CIC2.html");

			TimeUnit.SECONDS.sleep(1);

			driver.switchTo().frame("framePrincipal");

			TimeUnit.SECONDS.sleep(1);

			WebElement tabla = driver.findElement(
					By.id("objUCReporteCrediticioParaCiudadano_objUCSituacionActual_dgOperacionesSituacionActual"));

			ArrayList<WebElement> rowElements = (ArrayList<WebElement>) tabla.findElements(By.tagName("tr"));

			for (int i = 1; i < rowElements.size() - 1; i++) {

				ArrayList<WebElement> headerElements = (ArrayList<WebElement>) rowElements.get(i)
						.findElements(By.tagName("td"));

				CicActual cic = new CicActual();
				cic.setEntidad(headerElements.get(0).getText());
				cic.setIdOperacion(headerElements.get(1).getText());
				cic.setMoneda(headerElements.get(2).getText());
				cic.setFechaVencimiento(headerElements.get(3).getText());
				cic.setSaldo(headerElements.get(4).getText());
				cic.setMontoDesembolso(headerElements.get(5).getText());
				cic.setCuotaPrincipal(headerElements.get(6).getText());
				cic.setCuotaIntereses(headerElements.get(7).getText());
				cic.setTipoOperacion(headerElements.get(8).getText());
				cic.setFrecuenciaPago(headerElements.get(9).getText());
				cic.setCondicion(headerElements.get(10).getText());
				cic.setEstadoOperacion(headerElements.get(11).getText());
				cic.setDiasAtraso(headerElements.get(12).getText());
				cic.setTasaInteresNominal(headerElements.get(13).getText());
				cic.setTipoTasa(headerElements.get(14).getText());
				cic.setFechaCambioTipoTasa(headerElements.get(15).getText());
				cic.setParametroRefrencia(headerElements.get(16).getText());
				cic.setFrecuenciaAjusteTasa(headerElements.get(17).getText());
				cic.setComponenteFijoTasa(headerElements.get(18).getText());
				cic.setPisoTasa(headerElements.get(19).getText());
				cic.setTechoTasa(headerElements.get(20).getText());
				cic.setGenerador(headerElements.get(21).getText());
				cic.setFechaActualzaicion(headerElements.get(22).getText());

				listaCic.add(cic);

				// columnNames.add(headerElement.getText());

			}

			for (int i = 0; i < listaCic.size() - 1; i++) {
				System.out.println(listaCic.get(i).getEntidad() + "--" + listaCic.get(i).getIdOperacion() + "--"
						+ listaCic.get(i).getMoneda() + "--" + listaCic.get(i).getFechaVencimiento() + "--"
						+ listaCic.get(i).getSaldo() + "--" + listaCic.get(i).getMontoDesembolso() + "--"
						+ listaCic.get(i).getCuotaPrincipal() + "--" + listaCic.get(i).getCuotaIntereses() + "--"
						+ listaCic.get(i).getTipoOperacion() + "--" + listaCic.get(i).getFrecuenciaPago() + "--"
						+ listaCic.get(i).getCondicion() + "--" + listaCic.get(i).getEstadoOperacion() + "--"
						+ listaCic.get(i).getDiasAtraso() + "--" + listaCic.get(i).getTasaInteresNominal() + "--"
						+ listaCic.get(i).getTipoTasa() + "--" + listaCic.get(i).getFechaCambioTipoTasa() + "--"
						+ listaCic.get(i).getParametroRefrencia() + "--" + listaCic.get(i).getFrecuenciaAjusteTasa()
						+ "--" + listaCic.get(i).getComponenteFijoTasa() + "--" + listaCic.get(i).getPisoTasa() + "--"
						+ listaCic.get(i).getTechoTasa() + "--" + listaCic.get(i).getGenerador() + "--"
						+ listaCic.get(i).getFechaActualzaicion() + "--\n");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public String ingresardata(String data) {

		return JOptionPane.showInputDialog(data);

	}

	
	public void leerCaptcha() throws IOException {
		
		Pattern valorV = new Pattern("src/test/resources/V.png");
		String retorno = "";
		
		File img = new File("src/test/resources/CaptchaImage.png");
		
	
		BufferedImage bimg = ImageIO.read(img);

		
		Finder loginFinder = new Finder(img.getAbsolutePath(), new Region(0,0, bimg.getWidth(), bimg.getHeight()));
		
		loginFinder.find(valorV);
		
		

		
		if(loginFinder.hasNext()) {
			
			retorno = "V";
			
		} else {
			retorno = "No se encuentra nada";
		}
		
		System.out.println("\nDireccion img: " + img.getAbsolutePath());
		System.out.println("\nDimenciones captcha x:" + bimg.getWidth() + " y: " + bimg.getHeight());
		System.out.println("\nLoggin finder: " + loginFinder);
		
		System.out.println(retorno);

	}
	
}
