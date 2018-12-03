package com.sobaka.sort;

import java.io.IOException;
import java.io.InputStream;

public interface SortedChunck {
    void add(String s);
    String[] getSortedList();
//    void readFullChunk(String fileName) throws FileNotFoundException;

    boolean readFullChunk(InputStream inputStream) throws IOException;

    void saveChunk() throws IOException;

    String getName();

    Integer getSize();
}
