import org.testng.annotations.DataProvider;
import utils.CSVUtils;
import utils.ExcelUtils;
import utils.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class DataProviders {

    private final String excelFile = "data.xlsx";
    private final String sheetName = "Hoja1";
    private final String csvFile = "data.csv";

    @DataProvider
    public Object[][] getAuthenticationDataFromExcel() throws Exception {
        String path = getClass().getResource(excelFile).getPath();
        return ExcelUtils.getExcelAsArray(path, sheetName, 1, 2);
    }

    @DataProvider
    public Object[][] getAuthenticationDataFromCSV() throws Exception {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/" + csvFile)));
        List<User> listUsers = CSVUtils.getUsersData(bReader);
        Object[][] arr = new Object[listUsers.size()][1];
        for(int i =0; i < listUsers.size();i++){
            arr[i][0] = listUsers.get(i);
        }
        return arr;
    }




}
