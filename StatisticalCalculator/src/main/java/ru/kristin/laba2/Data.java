package ru.kristin.laba2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<String, Map<String, Double>> resultsMap = new HashMap<>();
    private Map<String, double[]> data = new HashMap<>();
//    private Map<String, double[]> processedData = new HashMap<>();

    public void loadData(String filename, String sheetNameorIndex, boolean isIndex) throws IOException {
        data = new ExcelProvider().read(filename, sheetNameorIndex, isIndex);
    }

    public void loadResults(String fileName) throws IOException {
        resultsMap = StatFunction.getCalculations(data);
        new ExcelProvider().write(resultsMap, StatFunction.CovMatrix(data), fileName);
    }

    public Map<String, double[]> getData() {
        return data;
    }
}
