package edu.innopolis.homework05;

import java.io.*;
import java.util.Scanner;

public class IdGeneratorFileImpl implements IdGenerator {

    private final String fileName;

    public IdGeneratorFileImpl(String fileName) {

        this.fileName = fileName;
        File file = new File(fileName);
        if(!file.exists() && !file.isDirectory()) {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
                printWriter.print(0);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }



    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int lastId;

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            lastId = scanner.nextInt();
            lastId++;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            printWriter.print(lastId);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return lastId;

    }
}
