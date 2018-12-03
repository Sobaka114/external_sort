package com.sobaka.sort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface SortedChunck {
    void add(String s);
    String[] getSortedList();
//    void readFullChunk(String fileName) throws FileNotFoundException;

    void readFullChunk(InputStream inputStream) throws IOException;

    void saveChunk() throws IOException;
}
