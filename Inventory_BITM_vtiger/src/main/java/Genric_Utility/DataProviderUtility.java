package Genric_Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderUtility {
	
	 @DataProvider(name = "loginDetails")
	    public static Object[][] loginData() throws IOException {
	        String path = System.getProperty("user.dir")+ "./src/test/resources/vtiger_testdata.xlsx";
	        FileInputStream fis = new FileInputStream(path);
	        Workbook wb = WorkbookFactory.create(fis);
	        Sheet sheet = wb.getSheet("Sheet7");
	        DataFormatter formatter = new DataFormatter();
	        int lastRow = sheet.getLastRowNum();
	        Object[][] data = new Object[lastRow][2];
	        for (int i = 1; i <= lastRow; i++) {
	            Row row = sheet.getRow(i);
	            if (row == null) {
	                data[i - 1][0] = "";
	                data[i - 1][1] = "";
	                continue;
	            }
	            data[i - 1][0] = formatter.formatCellValue(row.getCell(0)).trim();
	            data[i - 1][1] = formatter.formatCellValue(row.getCell(1)).trim();
	        }
	        wb.close();
	        fis.close();
	        return data;
	    }

    @DataProvider(name = "getServiceData")
    public static Object[][] getServiceData() throws IOException {
        String filePath = System.getProperty("user.dir")+ "./src/test/resources/vtiger_testdata.xlsx";
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet("sheet1");
            if (sheet == null) {
                throw new IllegalArgumentException("Excel sheet 'sheet1' was not found");
            }
            DataFormatter formatter = new DataFormatter();
            List<Object[]> dataList = new ArrayList<>();
            int firstDataRow = 1;
            int lastRow = sheet.getLastRowNum();
            for (int i = firstDataRow; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                // Skip rows that are completely empty
                if (row == null) {
                    continue;
                }
                String serviceName = formatter.formatCellValue(row.getCell(0)).trim();
                String priceValue = formatter.formatCellValue(row.getCell(1)).trim();
                String unitsValue = formatter.formatCellValue(row.getCell(2)).trim();
                // Skip rows where all three cells are blank
                if (serviceName.isEmpty() && priceValue.isEmpty() && unitsValue.isEmpty()) {
                    continue;
                }
                // Reject partially completed records
                if (serviceName.isEmpty() || priceValue.isEmpty() || unitsValue.isEmpty()) {
                    throw new IllegalArgumentException("Incomplete data at Excel row " + (i + 1));
                }
                // Store only valid records
                dataList.add(new Object[] {serviceName,Integer.parseInt(priceValue),Integer.parseInt(unitsValue)});
            }
            // Return only the records collected from Excel
            return dataList.toArray(new Object[0][]);
        }
    }

	@DataProvider(name = "getServiceUpdateData")
	public static Object[][] getServiceUpdateData() throws IOException {
	    String filePath = System.getProperty("user.dir")+ "./src/test/resources/vtiger_testdata.xlsx";
	    try (FileInputStream fis = new FileInputStream(filePath);
	        Workbook workbook = WorkbookFactory.create(fis)) {
	        Sheet sheet = workbook.getSheet("sheet1");
	        if (sheet == null) {
	            throw new IllegalArgumentException("Excel sheet 'sheet1' was not found");
	        }
	        DataFormatter formatter = new DataFormatter();
	        List<Object[]> dataList = new ArrayList<>();
	        Set<String> serviceNames = new HashSet<>();
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            if (row == null) {
	                continue;
	            }
	            String serviceName = formatter.formatCellValue(row.getCell(0)).trim();
	            String description = formatter.formatCellValue(row.getCell(3)).trim();
	            if (serviceName.isEmpty() && description.isEmpty()) {
	                continue;
	            }
	            if (serviceName.isEmpty() || description.isEmpty()) {
	                throw new IllegalArgumentException("Incomplete update data at Excel row " + (i + 1));
	            }
	            if (!serviceNames.add(serviceName.toLowerCase())) {
	                continue;
	            }
	            dataList.add(new Object[] {serviceName, description});
	        }
	        return dataList.toArray(new Object[0][]);
	    }
	}
	
	@DataProvider(name = "serviceData")
	public Object[][] serviceData() throws IOException {
	    String path = System.getProperty("user.dir")+ "/src/test/resources/vtiger_testdata.xlsx";
	    try (FileInputStream fis = new FileInputStream(path);
	         Workbook workbook = WorkbookFactory.create(fis)) {	
	        Sheet sheet = workbook.getSheet("Sheet2");
	        DataFormatter formatter = new DataFormatter();
	        List<Object[]> testData = new ArrayList<>();
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            if (row == null) {
	                continue;
	            }
	            String serviceName = formatter.formatCellValue(row.getCell(0)).trim();
	            // Skip empty Excel rows
	            if (serviceName.isEmpty()) {
	                continue;
	            }
	            String minimumValue = formatter.formatCellValue(row.getCell(1)).trim();
	            String zeroValue = formatter.formatCellValue(row.getCell(2)).trim();
	            String negativeValue = formatter.formatCellValue(row.getCell(3)).trim();
	            testData.add(new Object[] {serviceName,minimumValue,zeroValue,negativeValue});
	        }	
	        return testData.toArray(new Object[0][]);
	    }
	}
	
	@DataProvider(name = "quoteData")
	public Object[][] getQuoteData() throws Exception {
	    FileInputStream fis = new FileInputStream("src/test/resources/vtiger_testdata.xlsx");
	    Workbook workbook = WorkbookFactory.create(fis);
	    Sheet sheet = workbook.getSheet("Sheet3");
	    // Get column count dynamically from the header row
	    Row headerRow = sheet.getRow(0);
	    int columnCount = headerRow.getLastCellNum();
	    // Get the number of data rows
	    int lastRow = sheet.getLastRowNum();
	    Object[][] data = new Object[lastRow][columnCount];
	    DataFormatter formatter = new DataFormatter();
	    // Read data starting from row 2
	    for (int i = 1; i <= lastRow; i++) {
	        Row row = sheet.getRow(i);
	        for (int j = 0; j < columnCount; j++) {
	            if (row != null && row.getCell(j) != null) {
	                data[i - 1][j] = formatter.formatCellValue(row.getCell(j)).trim();
	            } else {
	                data[i - 1][j] = "";
	            }
	        }
	    }
	    workbook.close();
	    fis.close();
	    return data;
	}

	@DataProvider(name = "serviceWorkflowData")
	public Object[][] getServiceWorkflowData() throws Exception {
	    FileInputStream fis = new FileInputStream("src/test/resources/vtiger_testdata.xlsx");
	    Workbook workbook = WorkbookFactory.create(fis);
	    Sheet sheet = workbook.getSheet("Sheet1");
	    DataFormatter formatter = new DataFormatter();
	    // Read the header row and first data row
	    Row headerRow = sheet.getRow(0);
	    Row row = sheet.getRow(1);
	    if (headerRow == null || row == null) {
	        workbook.close();
	        fis.close();
	        throw new RuntimeException("Sheet1 must contain a header row and one data row");
	    }
	    // Determine the number of columns dynamically
	    int columnCount = 3;
	    Object[][] data = new Object[1][columnCount];
	    // Read only the first data row
	    for (int j = 0; j < columnCount; j++) {
	        if (row.getCell(j) != null) {
	            data[0][j] = formatter.formatCellValue(row.getCell(j)).trim();
	        } else {
	            data[0][j] = "";
	        }
	    }
	    workbook.close();
	    fis.close();
	    return data;
	}

	@DataProvider(name = "serviceIsolationData")
	public Object[][] getServiceIsolationData() throws Exception {
	    FileInputStream fis = new FileInputStream("./src/test/resources/vtiger_testdata.xlsx");
	    Workbook workbook = WorkbookFactory.create(fis);
	    Sheet sheet = workbook.getSheet("sheet4");
	    DataFormatter formatter = new DataFormatter();
	    Row row = sheet.getRow(1);
	    if (row == null) {
	        workbook.close();
	        fis.close();
	        throw new RuntimeException("ServiceIntegration data is missing");
	    }
	    Object[][] data = new Object[1][3];
	    for (int i = 0; i < 3; i++) {
	        data[0][i] = row.getCell(i) == null? "": formatter.formatCellValue(row.getCell(i)).trim();
	    }
	    workbook.close();
	    fis.close();
	    return data;
	}
	
	@DataProvider(name = "serviceRenameData")
	public Object[][] serviceRenameData() throws IOException {
	    FileInputStream fis = new FileInputStream("./src/test/resources/vtiger_testdata.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    Sheet sheet = wb.getSheet("sheet5");
	    Row row = sheet.getRow(1);
	    DataFormatter formatter = new DataFormatter();
	    String originalServiceName = formatter.formatCellValue(row.getCell(0)).trim();
	    String updatedServiceName = formatter.formatCellValue(row.getCell(1)).trim();
	    wb.close();
	    fis.close();
	    return new Object[][] {{ originalServiceName, updatedServiceName }};
	}

	@DataProvider(name = "orderNameData")
    public Object[][] orderNameData() {
        return new Object[][] {{"New_Order"}};
    }
	
	@DataProvider(name = "priceBookNameData")
	public Object[][] priceBookNameData() {
	    return new Object[][] {{"Updated_new_PriceBook"}};
	}
	
	@DataProvider(name = "vendorNameData")
	public Object[][] vendorNameData() {
	    return new Object[][] {{"Updated_new_vendor"}};
	}
	
	@DataProvider(name = "AssetNameData")
	public Object[][] AssetNameData() {
	    return new Object[][] {{"Updated_new_Asset"}};
	}
	
	@DataProvider(name = "assetData")
    public static Object[][] getAssetData() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/vtiger_testdata.xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet("Sheet6");
        int rows = sheet.getLastRowNum();
        Object[][] data = new Object[rows][4];
        DataFormatter formatter = new DataFormatter();
        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            data[i - 1][0] = formatter.formatCellValue(row.getCell(0)).trim(); // Serial Number
            data[i - 1][1] = formatter.formatCellValue(row.getCell(1)).trim(); // Date Sold
            data[i - 1][2] = formatter.formatCellValue(row.getCell(2)).trim(); // Asset Name
            data[i - 1][3] = formatter.formatCellValue(row.getCell(3)).trim(); // Date in Service
        }
        workbook.close();
        fis.close();
        return data;
    }
}

