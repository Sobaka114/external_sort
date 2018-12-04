package com.sobaka.sort.chunk;

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import java.io.*;

public class GuavaSortedChunk implements SortedChunk {
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
    public boolean readFullChunk(BufferedReader br) throws IOException {
        boolean end = true;
        String line;
        while ((line = br.readLine()) != null) {
            this.add(line);
            if (endOfReading()) {
                end = false;
                break;
            }
        }
        return end;
    }

    @Override
    public void saveChunk() throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        final boolean[] needLn = {false};
        sorted.forEach(s ->  {
            if(needLn[0]) {
                printWriter.println();
            }
            printWriter.print(s);
            needLn[0] = true;

        });
        printWriter.close();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getSize() {
        return sorted.size();
    }
}
