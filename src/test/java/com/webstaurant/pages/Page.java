package com.webstaurant.pages;

import com.webstaurant.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class Page {

    public Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
