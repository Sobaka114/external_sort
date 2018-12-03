package com.sobaka.sort;

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import java.io.*;

public class GuavaSortedChunk implements SortedChunck {
    private SortedMultiset<String> sorted = TreeMultiset.create();

    public GuavaSortedChunk(String name) {
        this.name = name;
    }

    private String name;

    private boolean endOfReading() {
        //TODO analyse heap size
        return sorted.size() > 1000;
    }

    @Override
    public void add(String s) {
        sorted.add(s);
    }

    @Override
    public String[] getSortedList() {
        String[] arr = new String[sorted.size()];
        return sorted.toArray(arr);
    }

    @Override
    public void readFullChunk(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.add(line);
                if(endOfReading()) {
                    break;
                }
            }
        }


    }

    @Override
    public void saveChunk() throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        sorted.forEach(s -> printWriter.println(s));
        printWriter.close();
    }
}
