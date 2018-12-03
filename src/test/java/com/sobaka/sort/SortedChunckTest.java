package com.sobaka.sort;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SortedChunckTest {
    @Test
    public void addTest() {
        GuavaSortedChunck guavaSortedChunck = new GuavaSortedChunck();
        String[] list = new String[] {"b", "A", "a", "c", ""};

        guavaSortedChunck.add(list[0]);
        guavaSortedChunck.add(list[1]);
        guavaSortedChunck.add(list[2]);
        guavaSortedChunck.add(list[3]);
        guavaSortedChunck.add(list[4]);

        Arrays.sort(list);

        int i = 0;
        for (String s : guavaSortedChunck.getSortedList()) {
            System.out.println(s);
            Assert.assertEquals(list[i], s);
            i++;
        }

    }
}