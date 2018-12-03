package com.sobaka.sort;

public interface SortedChunck {
    void add(String s);
    String[] getSortedList();
    void readFullChunck(String fileName);
    void saveChunck(String fileName);
}
