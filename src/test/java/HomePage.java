import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class HomePage {
    private BaseFunc baseFunc;
    private final By DRESSES_BTN = By.xpath("(.//a[@title = 'Dresses'])[2]");
    private final By LOGO = By.xpath(".//img[@class = 'logo img-responsive']");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(LOGO), "LOGO IS NOT PRESENT");
    }

    public void pushDresses() {
        baseFunc.getElement(DRESSES_BTN).click();
    }


}
