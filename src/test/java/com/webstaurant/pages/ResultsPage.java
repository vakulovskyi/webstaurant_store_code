package com.webstaurant.pages;

import com.webstaurant.utilities.ConfigurationReader;
import org.openqa.selenium.By;

import java.util.List;

import static com.webstaurant.utilities.BrowserUtils.*;

public class ResultsPage extends Page{

    private final By product_titles_displayed_on_page = By.cssSelector("a[data-testid='itemLink']");


    public List<String> allSearchResultTitles() {

        int pages = Integer.parseInt(getText(By.xpath("//a[starts-with(@aria-label, 'last page')]")));

        String firstPageURL = getPageURL();

        List<String> resultsString = getElementsText(product_titles_displayed_on_page);


        for (int i = 2; i <= pages; i++) {

            navigateTo(firstPageURL + "?page=" + i);

            resultsString.addAll(getElementsText(product_titles_displayed_on_page));

        }

             int page = 0;
             int newIndex = 0;

        for (String each : resultsString ) {

            if (!(each.contains(ConfigurationReader.getProperty("checkWord")))){

                int index = resultsString.indexOf(each);

                if(index <= 59 ){
                    page = 1;
                    newIndex = index + 1;
                }else  if (index <= 119){
                    page = 2;
                    newIndex = (index + 1)-(60*(page-1));
                }else  if (index <= 179){
                    page = 3;
                    newIndex = (index + 1)-(60*(page-1));
                }else  if (index <= 239){
                    page = 4;
                    newIndex = (index + 1)-(60*(page-1));
                }else  if (index <= 299){
                    page = 5;
                    newIndex = (index + 1)-(60*(page-1));
                }else  if (index <= 359){
                    page = 6;
                    newIndex = (index + 1)-(60*(page-1));
                }else  if (index <= 419){
                    page = 7;
                    newIndex = (index + 1)-(60*(page-1));
                }else  if (index <= 479){
                    page = 8;
                    newIndex = (index + 1)-(60*(page-1));
                }else {
                    page = 9;
                    newIndex = (index + 1)-(60*(page-1));
                }

                if(each.isBlank() || each.isEmpty()){
                    continue;
                }


                System.out.println("Page " + page + " Index " + newIndex + " dose not contain : " + ConfigurationReader.getProperty("checkWord"));

            }



        }

        return resultsString;
    }

    public boolean checkAllSearchResultsContain(List<String> results, String searchItem) {
        for (String result : results) {
            if (!(result.contains(searchItem))) {
                return false;
            }
        }
        return true;
    }
}
