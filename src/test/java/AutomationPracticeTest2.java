import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutomationPracticeTest2 {

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
        orangeColor = dressesPage.getOrangeColor();
        dressesPage.checkFilteredItemsColor(dressesPage.getFilteredDresses(), dressesPage.getColorsOfFilteredDresses(), orangeColor);
        dressesPage.pushRandomDress(dressesPage.getFilteredDresses());
        dressesPage.checkSelectedColorOnQuickView(orangeColor);
        baseFunc.switchToMainFrame();
        dressesPage.closeItemQuickView();

        for (int i = 0; i < 2; i++) {
            dressesPage.pushRandomDress(dressesPage.getFilteredDresses());
            price += dressesPage.getDressPrice();
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
