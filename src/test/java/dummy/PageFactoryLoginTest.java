package dummy;

import org.testng.annotations.Test;
import com.selenium.pom.tests.BaseTest;

public class PageFactoryLoginTest extends BaseTest {

	@Test
	public void loginUser() {
		PageFactoryLoginPage lp = new PageFactoryLoginPage(driver);
		lp.clickSignInlink();
		lp.doLogin("John","John12345");
	}


}
