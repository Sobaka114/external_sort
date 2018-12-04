package com.sobaka.sort;

import com.sobaka.sort.chunk.GuavaSortedChunk;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortedChunkTest {
    @Test
    public void addTest() {
        GuavaSortedChunk guavaSortedChunk = new GuavaSortedChunk("test");
        String[] list = new String[] {"b", "A", "a", "c", ""};

        guavaSortedChunk.add(list[0]);
        guavaSortedChunk.add(list[1]);
        guavaSortedChunk.add(list[2]);
        guavaSortedChunk.add(list[3]);
        guavaSortedChunk.add(list[4]);

        Arrays.sort(list);

        int i = 0;
        for (String s : guavaSortedChunk.getSortedList()) {
            System.out.println(s);
            Assert.assertEquals(list[i], s);
            i++;
        }

    }
}