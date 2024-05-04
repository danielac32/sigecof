
package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
 
//import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author daniel
 */

class CsvToExcel {
      public static final char CSV_FILE_DELIMITER = ',';

      public boolean isDigits(String str){
        try{
            int resultado = Integer.parseInt(str);
            return true;
        }catch(Exception e){
            return false;
        }
      }
      public boolean isNumber(String str){
        try{
            double resultado = Double.parseDouble(str);
            return true;
        }catch(Exception e){
            return false;
        }
      }
      public void convertCsvToExcel(String strSource, String extension)
            throws IllegalArgumentException, IOException {
 
        Workbook workBook = null;
        FileOutputStream fos = null;
 
        // Check that the source file exists.
        File sourceFile = new File(strSource);
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException("The source CSV file cannot be found at " + sourceFile);
        }
 
        // Getting BufferedReader object
        BufferedReader br = new BufferedReader(new FileReader(sourceFile));
 
        // Getting XSSFWorkbook or HSSFWorkbook object based on excel file format
        if (extension.equals(".xlsx")) {
            workBook = new XSSFWorkbook();
        } else {
            workBook = new HSSFWorkbook();
        }
 
        Sheet sheet = workBook.createSheet("Sheet");
 
        String nextLine;
        int rowNum = 0;
        while ((nextLine = br.readLine()) != null) {
            Row currentRow = sheet.createRow(rowNum++);
            String rowData[] = nextLine.split(String.valueOf(CSV_FILE_DELIMITER));
            for (int i = 0; i < rowData.length; i++) {
                if (isDigits(rowData[i])) {
                    currentRow.createCell(i).setCellValue(Integer.parseInt(rowData[i]));
                } else if (isNumber(rowData[i])) {
                    currentRow.createCell(i).setCellValue(Double.parseDouble(rowData[i]));
                } else {
                    currentRow.createCell(i).setCellValue(rowData[i]);
                }
            }
            if(nextLine.equals("") || rowNum>=65000){
                break;
            }
        }

        String filename = new utils().save_file_dialog();
        
        fos = new FileOutputStream(filename);
        workBook.write(fos);
 
        try {
            // Closing workbook, fos, and br object
            workBook.close();
            fos.close();
            br.close();
 
        } catch (IOException e) {
            System.out.println("Exception While Closing I/O Objects");
            e.printStackTrace();
        }
 
    }

      public void start() {
        long startTime = System.currentTimeMillis();
        boolean converted = true;
        try {
            CsvToExcel converter = new CsvToExcel();
            String strSource = "file.csv";
            converter.convertCsvToExcel(strSource, ".xls");
        } catch (Exception e) {
            System.out.println("Unexpected exception");
            e.printStackTrace();
            converted = false;
        }
        if (converted) {
            System.out.println("Conversion " + ((System.currentTimeMillis() - startTime) / 1000) + " seconds");
        }
    }

}
