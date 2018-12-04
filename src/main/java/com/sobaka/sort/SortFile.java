package com.sobaka.sort;



import com.sobaka.sort.chunk.creator.ChunkCreator;
import com.sobaka.sort.chunk.creator.GuavaUniqueChunkCreator;
import com.sobaka.sort.chunk.SortedChunk;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.sobaka.Constants.FILE_FOR_SORT;

public class SortFile {
    public static final ChunkCreator chunkCreator = new GuavaUniqueChunkCreator();
    public static final Set<String> chunkNames = new HashSet<>();

    public static void main(String[] args) throws IOException {
        sortFile(FILE_FOR_SORT);
    }

    private static void sortFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)))) {
            boolean end = false;
            while (!end) {
                SortedChunk sortedChunk = chunkCreator.createSortedChunk();
                //System.out.println(sortedChunk.getName());
                end = sortedChunk.readFullChunk(br);
                chunkNames.add(sortedChunk.getName());
                sortedChunk.saveChunk();
            }
        }

        long merge = 0;
        while (chunkNames.size() > 1) {
            Iterator<String> iterator = chunkNames.iterator();
            String left = iterator.next();
            String right = iterator.next();
            mergeFiles(left, right, merge);
            merge ++;
        }

    }

    private static void mergeFiles(String chunkL, String chunkR, long merge) throws IOException {
        InputStream inputStreamL = new FileInputStream(chunkL);
        InputStream inputStreamR = new FileInputStream(chunkR);

        BufferedReader brL = new BufferedReader(new InputStreamReader(inputStreamL));
        BufferedReader brR = new BufferedReader(new InputStreamReader(inputStreamR));

        String stringL = brL.readLine();
        String stringR = brR.readLine();

        String resultFileName = "merge" + merge;
        FileWriter fileWriter = new FileWriter(resultFileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        boolean needLn = false;
        while (stringL != null && stringR != null) {
            if(needLn) {
                printWriter.println();
            }
            if (stringL.compareTo(stringR) <= 0) {
                printWriter.print(stringL);
                stringL = brL.readLine();
            } else {
                printWriter.print(stringR);
                stringR = brR.readLine();
            }
            needLn = true;
        }
        String s;
        if(stringL != null) {
            printWriter.println();
            printWriter.print(stringL);
            while ((s = brL.readLine()) != null) {
                printWriter.println();
                printWriter.print(s);
            }
        } else if (stringR != null) {
            printWriter.println();
            printWriter.print(stringR);
            while ((s = brR.readLine()) != null) {
                printWriter.println();
                printWriter.print(s);
            }
        }
        printWriter.close();
        brL.close();
        brR.close();
        Files.delete(Paths.get(chunkL));
        Files.delete(Paths.get(chunkR));
        chunkNames.remove(chunkL);
        chunkNames.remove(chunkR);
        chunkNames.add(resultFileName);
    }
}
