package TraditionalTestsV1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class TraditionalTests extends BaseTest {

    // locators
    By showFilterBtnId = By.id("ti-filter");
    By profileIconCss = By.cssSelector(".access_link");
    By wishListIconCss = By.cssSelector(".wishlist");
    By bottomLeftFilterBtnId = By.id("filterBtn");
    By btnSearchMobClass = By.className("btn_search_mob");

    By addToCardFirstItemCss = By.cssSelector("li a[data-original-title='Add to cart']");
    By addToFavoriteFirstItemCss = By.cssSelector("li a[data-original-title='Add to favorites']");
    By addToCompareFirstItemCss = By.cssSelector("li a[data-original-title='Add to compare']");
    By blackOptionXpath = By.xpath("//label[contains(text(),'Black')]");
    By gridItemsXpath = By.xpath("//div[@class='grid_item']");

    By shoeName = By.id("shoe_name");
    By price = By.id("new_price");
    By productImageXpath = By.xpath("//img[@alt='Appli Air x Night']");
    By newPriceFirstItem = By.xpath("//a/h3[text()='Appli Air x Night']/../../div/span[@class='new_price']");

    @Test(priority = 1)
    public void task1CrossDeviceElementsTest() {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(hackathonReporter(1, "CDET - Profile icon", profileIconCss.toString(), utils.isDisplayed(profileIconCss)), "The Profile icon is not displayed");
        softAssertion.assertTrue(hackathonReporter(1, "CDET - WishList Icon", wishListIconCss.toString(), utils.isDisplayed(wishListIconCss)), "The WishList icon is not displayed");
        softAssertion.assertTrue(hackathonReporter(1, "CDET - Search Btn (Mobile)", btnSearchMobClass.toString(), utils.isDisplayed(btnSearchMobClass)), "The Search btn for Mobile is not displayed");

        WebElement showFilterButton = driver.findElement(showFilterBtnId);
        showFilterButton.click();

        softAssertion.assertTrue(hackathonReporter(1, "CDET - Left Filter Button", bottomLeftFilterBtnId.toString(), utils.isDisplayed(bottomLeftFilterBtnId)), "The bottom left filter button is not displayed");
        softAssertion.assertTrue(hackathonReporter(1, "CDET - Add To Cart (low res)", addToCardFirstItemCss.toString(), utils.isDisplayed(addToCardFirstItemCss)), "The 'add to cart' button is not present");
        softAssertion.assertAll();
    }

    @Test(priority = 2)
    public void task2ShoppingExperienceTest() {
        SoftAssert softAssertion = new SoftAssert();

        WebElement showFilterButton = driver.findElement(showFilterBtnId);
        showFilterButton.click();

        WebElement filterBtn = driver.findElement(bottomLeftFilterBtnId);
        softAssertion.assertTrue(hackathonReporter(2, "FR - Left Filter Button", bottomLeftFilterBtnId.toString(), filterBtn.isDisplayed()), "The bottom left filter button is not displayed");

        WebElement blackOption = driver.findElement(blackOptionXpath);
        blackOption.click();

        filterBtn.click();
        List<WebElement> gridItemsResults = driver.findElements(gridItemsXpath);
        softAssertion.assertTrue(hackathonReporter(2, "FR - count results", gridItemsXpath.toString(), gridItemsResults.size() == 2), "The number of black shoes should be 2 but Found " + gridItemsResults.size());

        softAssertion.assertTrue(hackathonReporter(2, "FR - Add To Favorite (low res)", addToFavoriteFirstItemCss.toString(), utils.isDisplayed(addToFavoriteFirstItemCss)), "The 'add to favorite' button is not present");
        softAssertion.assertTrue(hackathonReporter(2, "FR - Add To Compare (low res)", addToCompareFirstItemCss.toString(), utils.isDisplayed(addToCompareFirstItemCss)), "The 'add to compare' button is not present");
        softAssertion.assertTrue(hackathonReporter(2, "FR - Add To Cart (low res)", addToCardFirstItemCss.toString(), utils.isDisplayed(addToCardFirstItemCss)), "The 'add to cart' button is not present");
        softAssertion.assertAll();
    }

    @Test(priority = 3)
    public void task3ProductDetailsTest() {
        SoftAssert softAssertion = new SoftAssert();

        if (driver.findElement(showFilterBtnId).isDisplayed())
            driver.findElement(showFilterBtnId).click();
        driver.findElement(blackOptionXpath).click();
        driver.findElement(bottomLeftFilterBtnId).click();

        String expectedPrice = driver.findElement(newPriceFirstItem).getText();

        // choose first product
        driver.findElement(productImageXpath).click();

        softAssertion.assertTrue(hackathonReporter(3, "PDtest - Product Name", shoeName.toString(), driver.findElement(shoeName).getText().equals("Appli Air x Night")), "Shoe name is not correct");
        softAssertion.assertTrue(hackathonReporter(3, "PDtest - Price", price.toString(), driver.findElement(price).getText().equals(expectedPrice)), "Shoe price is not correct");

        softAssertion.assertTrue(hackathonReporter(3, "PDtest - Add To Favorite (low res)", addToFavoriteFirstItemCss.toString(), utils.isDisplayed(addToFavoriteFirstItemCss)), "The 'add to favorite' button is not present");
        softAssertion.assertTrue(hackathonReporter(3, "PDtest - Add To Compare (low res)", addToCompareFirstItemCss.toString(), utils.isDisplayed(addToCompareFirstItemCss)), "The 'add to compare' button is not present");
        softAssertion.assertTrue(hackathonReporter(3, "PDtest - Add To Cart (low res)", addToCardFirstItemCss.toString(), utils.isDisplayed(addToCardFirstItemCss)), "The 'add to cart' button is not present");
        softAssertion.assertAll();
    }

}