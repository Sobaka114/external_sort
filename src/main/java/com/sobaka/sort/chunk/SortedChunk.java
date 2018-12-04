package com.sobaka.sort.chunk;

import java.io.BufferedReader;
import java.io.IOException;

public interface SortedChunk {
    void add(String s);
    String[] getSortedList();
//    void readFullChunk(String fileName) throws FileNotFoundException;

    boolean readFullChunk(BufferedReader inputStream) throws IOException;

    void saveChunk() throws IOException;

    String getName();

    Integer getSize();
}
