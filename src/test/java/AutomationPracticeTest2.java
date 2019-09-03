import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class AutomationPracticeTest2 {
    private final By COLORS = By.xpath(".//a[@class = 'color_pick']");
    private final By DRESSES = By.xpath(".//div[@class = 'product-container']");
    private final By ORANGE_FILTER = By.xpath(".//input[@id = 'layered_id_attribute_group_13']");
    private final By PRICE = By.xpath(".//span[@id='our_price_display']");
    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage;
    private DressesPage dressesPage;
    private CartPage cartPage;
    private String URL = "http://automationpractice.com/index.php";
    private String orangeColor;
    private double price = 0;

    @Test
    public void test() {
        baseFunc.openPage(URL);
        homePage = new HomePage(baseFunc);
        homePage.pushDresses();
        dressesPage = new DressesPage(baseFunc);
        dressesPage.pushOrange();
        orangeColor = dressesPage.getColor(ORANGE_FILTER);
        dressesPage.checkFilteredItemsColor(baseFunc.getAllElements(DRESSES), baseFunc.getAllElements(COLORS), orangeColor);
        dressesPage.pushRandomDress(baseFunc.getAllElements(DRESSES));
        dressesPage.checkSelectedColorOnQuickView(orangeColor);
        baseFunc.switchToMainFrame();
        dressesPage.closeItemQuickView();

        for (int i = 0; i < 2; i++) {
            dressesPage.pushRandomDress(baseFunc.getAllElements(DRESSES));
            price += baseFunc.getPrice(PRICE);
            dressesPage.pushAddToCart();
            dressesPage.pushContinueShopping();
            baseFunc.switchToMainFrame();
        }
        dressesPage.pushCart();
        cartPage = new CartPage(baseFunc);
        Assertions.assertEquals(price, cartPage.getTotalProductPrice(), "Price is calculated wrong!");
        baseFunc.closeBrowser();
    }


}
