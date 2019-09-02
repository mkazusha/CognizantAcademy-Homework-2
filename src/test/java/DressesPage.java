import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class DressesPage {
    private final By BANNER_NAME = By.xpath(".//div[@class = 'cat_desc']/span");
    private final By BANNER = By.xpath((".//div[@class = 'content_scene_cat']"));
    private final By ORANGE_FILTER = By.xpath(".//input[@id = 'layered_id_attribute_group_13']");
    private final By QUICK_VIEW_BTN = By.xpath(".//a[@class='product_img_link']");
    private final By QUICK_VIEW = By.xpath(".//iframe[@class='fancybox-iframe']");
    private final By SELECTED_COLOR = By.xpath(".//li[@class='selected']");
    private final By CLOSE_QUICK_VIEW_BTN = By.xpath(".//a[@class = 'fancybox-item fancybox-close']");
    private final By ADD_TO_CART = By.xpath(".//button[@type='submit']");
    private final By CART = By.xpath(".//b[contains(text(), 'Cart')]");
    private final By CONTINUE_SHOPPING_BTN = By.xpath(".//i[@class='icon-chevron-left left']");
    int match = 0;
    Random rand = new Random();

    private BaseFunc basefunc;
    private String bannerName = "Dresses";

    public DressesPage(BaseFunc baseFunc) {
        this.basefunc = baseFunc;
        Assertions.assertEquals(baseFunc.getElement(BANNER_NAME).getText(), bannerName, "Wrong Banner name");
        Assertions.assertFalse(baseFunc.isElementPresent(BANNER));
    }

    public void pushOrange() {
        basefunc.getElement(ORANGE_FILTER).click();
    }

    public void checkFilteredItemsColor(List<WebElement> filteredItems, List<WebElement> colors, String colorToCheck) {
        for (WebElement color : colors) {
            if (isColorEqual(colorToCheck, color)) match++;
        }
        Assertions.assertEquals(filteredItems.size(), match, "Filter is not working!!! Matched only " + match + " of " + filteredItems.size());
    }

    public boolean isColorEqual(String colorToCheck, WebElement color) {
        return colorToCheck.equals(color.getAttribute("style"));
    }

    public String getColor(By locator) {
        return basefunc.getElement(locator).getAttribute("style");
    }

    public void pushRandomDress(List<WebElement> dresses) {
        dresses.get(rand.nextInt(dresses.size())).findElement(QUICK_VIEW_BTN).click();
        basefunc.waitForElement(QUICK_VIEW);
        basefunc.switchToFrame(QUICK_VIEW);
    }

    public WebElement getSelectedColor() {
        return basefunc.getElement(SELECTED_COLOR);
    }

    public String getActualColor(WebElement element) {
        return element.findElement(By.tagName("a")).getAttribute("style");
    }

    public void checkSelectedColorOnQuickView(String expectedColor) {
        Assertions.assertEquals(expectedColor, getActualColor(getSelectedColor()), "Orange color is not selected by default!!!");
    }

    public void closeItemQuickView() {
        basefunc.waitForElement(CLOSE_QUICK_VIEW_BTN);
        basefunc.getElement(CLOSE_QUICK_VIEW_BTN).click();
    }

    public void pushAddToCart() {
        basefunc.getElement(ADD_TO_CART).click();
    }

    public void pushCart() {
        basefunc.getElement(CART).click();
    }

    public void pushContinueShopping() {
        basefunc.waitForElement(CONTINUE_SHOPPING_BTN);
        basefunc.getElement(CONTINUE_SHOPPING_BTN).click();
    }


}
