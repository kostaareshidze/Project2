package ge.tbc.tbcitacademy.swoop.data;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public Object[][] givePriceValues(){
        return new Object[][]{
                {100, 200},
                {50, 100},
                {400, 465},
                {100, 800},
                {50, 600}
        };
    }
}
