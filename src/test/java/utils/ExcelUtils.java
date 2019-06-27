package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

    /**
     * Este metodo es para obtener los datos del excel recibe como parametros la cantidad de filas y columnas a leer
     * @param RowNum: cantidad de filas
     * @param ColNum: cantidad de columnas
     * @return String con el contenido de la celda
     * @throws Exception
     */
    public static String getCellData(int RowNum, int ColNum) throws Exception{
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String CellData = formatter.formatCellValue(Cell);
            return CellData;
        }
        catch (Exception e){
            return "";
        }
    }

    /**
     * Este metodo es para leer los datos del excel y convertirlo a un Object[][]
     * @param FilePath: ruta donde esta el excel
     * @param SheetName: hoja a leer
     * @param startRow: fila desde donde inicia a leer (se comienza a enumerar por 0)
     * @param totalCols: cantidad de columnas a leer
     * @return Object[][] con los datos para el DataProvider
     * @throws Exception
     */
    public static Object[][] getExcelAsArray(String FilePath, String SheetName, int startRow, int totalCols) throws Exception {
        String[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int startCol = 0;
            int ci, cj;
            int totalRows = ExcelWSheet.getLastRowNum();
            tabArray = new String[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj=0;
                for (int j = startCol; j < totalCols; j++, cj++){
                    tabArray[ci][cj] = getCellData(i, j);
                }
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch(IOException e){
            System.err.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return tabArray;
    }

}

