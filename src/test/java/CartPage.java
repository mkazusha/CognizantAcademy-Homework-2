import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class CartPage {

    private final By TOTAL_PRICE = By.xpath(".//td[@id = 'total_product']");
    private BaseFunc baseFunc;

    public CartPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public double getTotalProductPrice() {
        return baseFunc.getPrice(TOTAL_PRICE);
    }

    public void checkCartPriceCalculation(double expectedPrice, double actualPrice){
        Assertions.assertEquals(expectedPrice, actualPrice, "Price is calculated wrong!");
    }
}
