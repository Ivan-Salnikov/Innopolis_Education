package edu.innopolis.homework06;

import edu.innopolis.homework05.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CarsRepositoryFileImpl implements CarsRepository {
    private final String fileName;
    private Path path;

    private static final Function<String, Car> carMapFunction =
            line -> {
                String[] fields = line.split("\\[(.*?)\\]");
                String number = fields[0];
                String mark = fields[1];
                String colour = fields[2];
                int kmAge = Integer.parseInt(fields[3]);
                double price = Double.parseDouble(fields[4]);

                return new Car(number, mark, colour, kmAge, price);
            };

    public CarsRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
        this.path = Paths.get(fileName);
        if(!Files.exists(path) && !Files.isDirectory(path)) try {
            Files.createFile(Paths.get(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public List<Car> findAll() {
        try (Stream<String> carStream = Files.newBufferedReader(path).lines()){
            return carStream.map(carMapFunction).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Car car) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String carAsLine = "[" + car.getNumber() + "][" + car.getMark() + "][" + car.getColour() + "]["
                    + car.getKmAge() + "][" + car.getKmAge() + "][" + car.getPrice() + "]";
            writer.write(carAsLine);
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
