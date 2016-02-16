import Utils.RozetkaPage;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class Tests {
    private static RozetkaPage rozetka;

    @BeforeClass
    public void setup() {
        rozetka = new RozetkaPage();
    }

    @Test(priority=1)
    public void searchPositive() {
        String searchText = "Apple MacBook Air 13";
        rozetka.search(searchText);
        assertTrue(rozetka.getTitle().contains("Результаты поиска"), "Search page title should be as expected");
        String firstResult = rozetka.getFirstSearchResult();
        String searchResult = rozetka.findLink(searchText);
        String expectedResult = "Apple MacBook Air 13\" (MJVE2UA/A)";
        assertEquals(firstResult, searchResult, "Search results should be the same");
        assertTrue(searchResult.startsWith(expectedResult), "Search result should be as expected");
    }

    @Test(priority=2)
    public void alwaysFailingTest() {
        if (!rozetka.getTitle().equals("Результаты поиска")) {
            rozetka.screen();
            fail("Page title should be as expected");
        }
    }

    @Test(priority=4)
    public void loginPositive() {
        rozetka.login("a.tst.gml123@gmail.com", "tstPwd987");
        String expectedTitle = "ROZETKA — Личные данные | Личный кабинет";
        assertEquals(rozetka.getTitle(), expectedTitle, "Page title should be as expected");
    }

    @Test(priority=3)
    public void loginNegative() {
        rozetka.getRequest("https://my.rozetka.com.ua/signin/");
        rozetka.login("a.tst.gml123@gmail.com", "");
        String expectedStyle = "background-color: rgb(255, 254, 254);";
        assertEquals(rozetka.getPasswordField().getAttribute("style"), expectedStyle, "Password should be coloured red when incorrect password is submitted");
    }

    @AfterClass
    public void teardown() {
        rozetka.close();
    }
}
