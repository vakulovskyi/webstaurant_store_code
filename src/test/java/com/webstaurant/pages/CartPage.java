package com.webstaurant.pages;

import com.webstaurant.utilities.BrowserUtils;
import com.webstaurant.utilities.ConfigurationReader;
import org.openqa.selenium.By;

import java.sql.Driver;

import static com.webstaurant.utilities.BrowserUtils.*;
import static com.webstaurant.utilities.BrowserUtils.clickElement;

public class CartPage extends Page{


    private final static By all_products_available_for_purchase = By.xpath("//input[@name='addToCartButton']");
    private final static By cart_button = By.xpath("//a[@class= 'btn btn-small btn-primary']");
    private final static By item_in_cart = By.cssSelector("li[class^='cartItemWrapper']");
    private final static By number_item_in_cart = By.id("cartItemCountSpan");
    private final static By empty_cart_button = By.xpath("//button[.='Empty Cart']");
    private final static By empty_cart_button_alert = By.xpath("//footer[@class]//button[.='Empty Cart']");
    private final static By notification_cart_is_empty = By.className("empty-cart__text");

    private final static By close_cart_notification_alert = By.xpath("//button[@aria-label='close']");



    public void addProductsToCart() {

        int numberProductsForCart = Integer.parseInt(ConfigurationReader.getProperty("productsForCart"));

        int numberProductsAvailableForPurchaseOnPage = getElements(all_products_available_for_purchase).size();

        if(numberProductsForCart > numberProductsAvailableForPurchaseOnPage) {

            int difference = numberProductsForCart - numberProductsAvailableForPurchaseOnPage;

            for (int i = numberProductsAvailableForPurchaseOnPage;
                 i >= 1; i--) {

                clickElement(By.xpath("(//input[@name='addToCartButton'])[" + i + "]"));


                if(isElementDisplayed(close_cart_notification_alert)){

                    clickElement(close_cart_notification_alert);

                }

            }

           BrowserUtils.moveBack();

           for (int i = numberProductsAvailableForPurchaseOnPage;

                 i >= numberProductsAvailableForPurchaseOnPage - difference; i--) {

                clickElement(By.xpath("(//input[@name='addToCartButton'])[" + i + "]"));

                if(isElementDisplayed(close_cart_notification_alert)){

                    if( i == ((numberProductsAvailableForPurchaseOnPage - difference)+1)){
                        continue;
                    }

                    clickElement(close_cart_notification_alert);

                }

            }

        }else{
            int count = 0;
            for (int i = numberProductsAvailableForPurchaseOnPage;
                 i >= numberProductsAvailableForPurchaseOnPage - (numberProductsForCart-1); i--) {

                clickElement(By.xpath("(//input[@name='addToCartButton'])[" + i + "]"));



                if(isElementDisplayed(close_cart_notification_alert) && count == 3){





                    if( i == ((numberProductsAvailableForPurchaseOnPage - numberProductsForCart)+2)){
                        continue;
                    }



                    clickElement(close_cart_notification_alert);

                }

                count += 1;

            }

        }

    }

    public void openCart() {

        clickElement(cart_button);
    }

    public boolean isCartEmpty() {
        return !isElementDisplayed(item_in_cart);
    }

    public String amountItemsInCart() {
        String items = getText(number_item_in_cart);
        return items;
    }

    public void emptyCart() {
        BrowserUtils.sleep(1);
        clickElement(empty_cart_button);
        clickElement(empty_cart_button_alert);
    }

    public boolean isCartEmptyNotification() {

        return isElementDisplayed(notification_cart_is_empty);
    }

}
