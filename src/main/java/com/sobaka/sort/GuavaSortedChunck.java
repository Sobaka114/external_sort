package com.sobaka.sort;

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

public class GuavaSortedChunck implements SortedChunck {
    private SortedMultiset<String> sorted = TreeMultiset.create();
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
    public void readFullChunck(String fileName) {

    }

    @Override
    public void saveChunck(String fileName) {

    }
}
