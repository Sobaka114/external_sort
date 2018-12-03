package com.sobaka.sort;

import com.google.common.io.Files;

import java.io.*;
import java.util.*;

import static com.sobaka.Constants.FILE_FOR_SORT;

public class SortFile {
    public static final ChunkCreator chunkCreator = new GuavaUniqieChunckCreator();
    public static final Map<String, Integer> chunkNames = new HashMap<>();

    public static void main(String[] args) throws IOException {
        sortFile(FILE_FOR_SORT);
    }

    private static void sortFile(String fileName) throws IOException {
        try (InputStream inputStream = new FileInputStream(fileName)) {
            boolean end = false;
            while (!end) {
                SortedChunck sortedChunk = chunkCreator.createSortedChunk();
                System.out.println(sortedChunk.getName());
                end = sortedChunk.readFullChunk(inputStream);
                chunkNames.put(sortedChunk.getName(), sortedChunk.getSize());
                sortedChunk.saveChunk();
            }

            while (chunkNames.size() != 1) {
                Set<String> strings = chunkNames.keySet();
                Iterator<Map.Entry<String, Integer>> iterator = chunkNames.entrySet().iterator();
                Map.Entry<String, Integer> entry1 = iterator.next();
                Map.Entry<String, Integer> entry2 = iterator.next();

                mergeFiles(entry1.getKey(), entry2.getKey());
            }
        }
    }

    private static void mergeFiles(String chunkL, String chunkR) throws IOException {
        InputStream inputStreamL = new FileInputStream(chunkL);
        InputStream inputStreamR = new FileInputStream(chunkR);

        BufferedReader brL = new BufferedReader(new InputStreamReader(inputStreamL));
        BufferedReader brR = new BufferedReader(new InputStreamReader(inputStreamR));

        int lengthL = chunkNames.get(chunkL);
        int lengthR = chunkNames.get(chunkR);
        int resultLength = lengthL + lengthR;

        int i = 1;
        int j = 1;
        String nextToWrite;
        String stringL = brL.readLine();
        String stringR = brR.readLine();

        String resultFileName = chunkL + chunkR;
        FileWriter fileWriter = new FileWriter(resultFileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        while (i < lengthL && j < lengthR && stringL != null && stringR != null) {
            if(stringL.compareTo(stringR) <= 0) {
                printWriter.println(stringL);
                stringL = brL.readLine();
                i++;
            } else {
                printWriter.println(stringR);
                stringR = brL.readLine();
                j++;
            }


        }
        String s;
        while ((s = brL.readLine()) != null) {
            printWriter.println(s);
        }

        while ((s = brR.readLine()) != null) {
            printWriter.println(s);
        }
        printWriter.close();
        brL.close();
        brR.close();

        chunkNames.remove(chunkL);
        chunkNames.remove(chunkR);
        chunkNames.put(resultFileName, resultLength);
    }
}
