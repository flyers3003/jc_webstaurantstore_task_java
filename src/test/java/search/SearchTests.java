package search;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CartPage;
import pages.HomePage;
import pages.SearchResultsPage;
import static org.testng.Assert.*;

import java.util.List;


public class SearchTests extends BaseTests {
    @Test
    public void testTableSearchResults(){
        HomePage homePage = BasePage.returnHomePage();
        //From the Home Page, enter the text "stainless work table" into the search bar and perform a search
        //The resulting search should now put us on the Search Results Page
        SearchResultsPage searchResultsPage = BasePage.searchForText("stainless work table");

        //The Search Results Page should contain text with the number of results found for the search string
        String resultsTitle = searchResultsPage.getSearchResultsTitleText();

        //The Search Results Page has a maximum of 60 items shown on one page - this does not appear to be adjustable
        int maxSearchResultPerPage = 60;

        //Get the total number of results from the Title by parsing the String prior to the word " Results"
        int totalResults = Integer.parseInt(resultsTitle.substring(0, resultsTitle.indexOf(" Results")));
        //The number of pages of results should equal the total/60
        int numPages = totalResults/maxSearchResultPerPage;
        //The number of results on the last page will either be (totalResults - numPages*60) or 60 if totalResults is perfectly divisible by 60
        int lastPageTotal = totalResults-(numPages*maxSearchResultPerPage) > 0 ? totalResults-(numPages*maxSearchResultPerPage) : maxSearchResultPerPage;
        //Just printing out the information for verification purposes.  CAN BE REMOVED later.
        System.out.println("There are " + totalResults +
                " total results found, which means there are " + numPages +
                " pages of results, with " + lastPageTotal + " items on the last page");

        //Here we need to verify that every search result has the word "Table" in its description
        //In order to do so we must iterate over all 60 results on each page until we get to the last page
        for (int i = 0; i < numPages - 1; i++){
            //This will get us a list of all item descriptions on the page
            List<String> itemDescriptions = searchResultsPage.getItemDescriptions();
            //Iterate on each description on the page and verify it contains "Table"
            for (String string : itemDescriptions){
                //Did a try...catch to be able to keep going through the test if "Table" is not found in a description
                try {
                    assertTrue(string.contains("Table"));
                }catch (AssertionError e)
                {
                    //Want to show exactly which search result had an error and what the description text is that failed
                    System.out.println("ERROR: Search result " + ((i * 60) + itemDescriptions.indexOf(string) + 1) + ", \"" + string + "\" did not contain \"table\"");
                }
            }
            searchResultsPage.advanceToNextPage();
        }
        List<String> itemDescriptions = searchResultsPage.getItemDescriptions();
        for (String string : itemDescriptions){
            try {
                assertTrue(string.contains("Table"));
            }catch (AssertionError e)
            {
                System.out.println("ERROR: Search result " + (((numPages - 1) * 60) + itemDescriptions.indexOf(string) + 1) + ", \"" + string + "\" did not contain \"table\"");
            }
        }

        //Now we want to add the last item in the search results to the cart
        searchResultsPage.selectLastAddToCartButton();

        //Navigate to the cart
        CartPage cartPage = searchResultsPage.selectCart();
        String cartItemDescription = cartPage.getItemDescriptionText();
        String lastSearchResultDescription = itemDescriptions.get(itemDescriptions.size() -1);
        try {
            assertEquals(cartItemDescription, lastSearchResultDescription);
        }catch (AssertionError e)
        {
            System.out.println("ERROR: Cart Item text " + cartItemDescription + " does not match last search result " + lastSearchResultDescription);
        }
        System.out.println(cartItemDescription + "\n" + lastSearchResultDescription);
        cartPage.clearCart();
        try{
            assertEquals(cartPage.getEmptyCartText(), "Your cart is empty.");
        }catch (AssertionError e)
        {
            System.out.println("ERROR: The cart does not appear to be empty");
        }
    }
}
