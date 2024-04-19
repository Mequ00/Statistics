package ru.kristin.laba2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelProvider {

    public Map<String, double[]> read(String fileName, String sheetNameorIndex, boolean isIndex) throws FileNotFoundException, IOException {
        Map<String, double[]> excelData = new HashMap<>();
        Map<String, List<Double>> data = new HashMap<>();
        XSSFWorkbook myBook = new XSSFWorkbook(new FileInputStream(fileName));
        XSSFSheet sheet;
        if (isIndex == true) {
            int index = Integer.parseInt(sheetNameorIndex.trim()) - 1;
            int numberOfSheets = myBook.getNumberOfSheets();
            if (index <= numberOfSheets && index >= 0) {
                sheet = myBook.getSheetAt(index);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            sheet = myBook.getSheet(sheetNameorIndex.trim());
        }
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            data.put(cell.getStringCellValue(), new ArrayList<>());
        }
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            int columnindex = 0;
            for (String key : data.keySet()) {
                Cell cell = row.getCell(columnindex);
                data.get(key).add(cell.getNumericCellValue());
                columnindex++;
            }
        }
        for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
            double[] values = new double[entry.getValue().size()];
            for (int i = 0; i < entry.getValue().size(); i++) {
                values[i] = entry.getValue().get(i);
            }
            excelData.put(entry.getKey(), values);
        }
        return excelData;
    }

    public void write(Map<String, Map<String, Double>> resultsMap, double[][] covMatrix, String path) throws FileNotFoundException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Calculations");
        Row row = sheet.createRow(0);
        int numberOfNames = resultsMap.keySet().size();
        String[] names = resultsMap.keySet().toArray(new String[numberOfNames]);
        int numberOfParameters = resultsMap.get(names[0]).keySet().size();
        String[] parameterNames = resultsMap.get(names[0]).keySet().toArray(new String[numberOfParameters]);
        for (int i = 0; i < numberOfParameters; i++) {
            row.createCell(i).setCellValue(parameterNames[i]);
        }
        for (int i = 0; i < numberOfNames; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < numberOfParameters; j++) {
                Cell cell = row.createCell(j);
                double value = resultsMap.get(names[i]).get(parameterNames[j]);
                cell.setCellValue(value);
            }
        }
        sheet = workbook.createSheet("Covariance matrix");
        for (int i = 0; i < covMatrix.length; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < covMatrix[i].length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(covMatrix[i][j]);
            }
        }
        workbook.write(new FileOutputStream(path));
        workbook.close();
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Вычисления");
//        Row row = sheet.createRow(0);
//        int numberOfNames = processedData.keySet().size();
//        String[] names = processedData.keySet().toArray(new String[numberOfNames]);
//        int numberOfParameters = processedData.get(names[0]).length;
//        for (int i = 0; i < numberOfNames; i++) {
//            row.createCell(i).setCellValue(names[i]);
//        }
//        for (int j = 0; j < numberOfParameters; j++) {
//            row = sheet.createRow(j + 1);
//            for (int i = 0; i < numberOfNames; i++) {
//                Cell cell = row.createCell(i);
//                cell.setCellValue(processedData.get(names[i])[j]);
//            }
//        }
//        XSSFSheet sheetForMatrix = workbook.createSheet("Матрица");
//        for (int i = 0; i < covMatrix.length; i++) {
//            Row roww = sheetForMatrix.createRow(i);
//            for (int j = 0; j < covMatrix[i].length; j++) {
//                Cell cell = roww.createCell(j);
//                cell.setCellValue(covMatrix[i][j]);
//            }
//        }
//        workbook.write(new FileOutputStream(fileName));
//        workbook.close();
    }
}
