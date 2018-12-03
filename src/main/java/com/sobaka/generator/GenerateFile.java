package com.sobaka.generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static com.sobaka.Constants.FILE_FOR_SORT;

public class GenerateFile {

    private static String getRandomString(int maxLength) {
        Random r = new Random();
        int l = r.nextInt(maxLength);

        // really random (hard for testing)
        // string return RandomStringUtils.random(l);

        //only a-zA-Z0-9
        return RandomStringUtils.randomAlphanumeric(l);
    }

    private static void generateFile(int stringNum, int maxLength, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(int i = 0; i < stringNum; i++) {
            String randomString = getRandomString(maxLength);
            printWriter.println(randomString);
        }
        printWriter.close();
    }
    public static void main(String[] args) throws IOException {
        int maxLength = 100;
        int stringNum = 10;
        if(args.length == 2) {
            //todo
            System.out.println("TODO");
        }
        generateFile(stringNum, maxLength, FILE_FOR_SORT);
    }
}
