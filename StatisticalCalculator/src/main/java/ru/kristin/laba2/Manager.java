package ru.kristin.laba2;

import java.io.IOException;

public class Manager {

    private Data storage = new Data();

    public void Export(String fileName) throws IOException {
        storage.loadResults(fileName);
    }

    public void Import(String fileName, String sheetNameOrIndex, boolean isIndex) throws IOException {
        storage.loadData(fileName, sheetNameOrIndex, isIndex);
    }

    public Data getStorage() {
        return storage;
    }
    
}
