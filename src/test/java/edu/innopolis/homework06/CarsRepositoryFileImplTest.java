package edu.innopolis.homework06;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;


class CarsRepositoryFileImplTest {
    private static CarsRepository carsRepository;
    private static String carsRepositoryFileName = "test_cars.txt";
    private static Path path = Paths.get(carsRepositoryFileName);
    private static List<Car> allCarList;

    @BeforeAll
    static void beforeAll() {
        carsRepository = new CarsRepositoryFileImpl(carsRepositoryFileName);

        if(!Files.exists(path) && !Files.isDirectory(path))
            try {
                new FileOutputStream(carsRepositoryFileName, false).close();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
        }

        if(carsRepository.findAll().size() == 0) {
            carsRepository.save(new Car("В157МЕ716", "Tiguan", "grey", 0, 2300000));
            carsRepository.save(new Car("А555МЕ716", "Camry", "black", 205000, 900000));
            carsRepository.save(new Car("А001АА777", "Niva", "white", 245000, 210000));
            carsRepository.save(new Car("В999МЕ777", "Duster", "green", 65000, 720000));
            carsRepository.save(new Car("У15УУ716", "X-Trail", "grey", 185000, 790000));
            carsRepository.save(new Car("Р545НН716", "Aveo", "brown", 82000, 350000));
            carsRepository.save(new Car("П178РР16", "Volga", "grey", 698000, 30000));
            carsRepository.save(new Car("Р159РР716", "SX-4", "black", 165000, 710000));
            carsRepository.save(new Car("О158МЕ716", "VAZ2114", "grey", 320000, 140000));
            carsRepository.save(new Car("С015СО718", "Duster", "black", 75000, 780000));
            carsRepository.save(new Car("В001МЕ716", "Camry", "yellow", 85000, 1200000));
            carsRepository.save(new Car("В001МЕ716", "Camry", "yellow", 25000, 1800000));
            carsRepository.save(new Car("А001АЕ716", "Land Cruiser", "white", 0, 5200000));
        }
        allCarList = carsRepository.findAll();
        System.out.println("Общий список авто:");
        allCarList.forEach(System.out::println);
        System.out.println("------------------\n");
    }


    @Test
    void givenList_1_whenBlackColourOrKmAge_thenReturnNumber () {

        System.out.println("1) Номера всех автомобилей, имеющих черный цвет или нулевой пробег.");

        List<Car> carList = allCarList.stream()
                .filter(p -> (p.getColour().equals("black"))||(p.getKmAge() == 0))
                .collect(Collectors.toList());

        boolean isTestPassed = true;

        for(Car car : carList) {

            System.out.println(car.getNumber());

            if(!car.getColour().equals("black") && !(car.getKmAge() == 0)) {
                isTestPassed = false;
                break;
            }
        }
        System.out.println("------------------\n");

        Assertions.assertTrue(isTestPassed);

    }

    @Test
    void givenList_2_whenPriceInRange_thenReturnDistinctMark () {

        System.out.println("2) Количество уникальных моделей в ценовом диапазоне от 700 до 800 тыс.");

        List<String> markList = allCarList.stream()
                .filter(p -> (p.getPrice() >= 700000 && p.getPrice() <= 800000))
                .map(Car::getMark)
                .distinct()
                .collect(Collectors.toList());

        boolean isTestPassed = true;

        for(String mark : markList) {
            if(!(mark.equals("Duster")) && !(mark.equals("X-Trail")) && !(mark.equals("SX-4"))) {
                isTestPassed = false;
                break;
            }
            System.out.println(mark);
        }

        Assertions.assertTrue(isTestPassed);
        System.out.println("------------------\n");

    }

    @Test
    void givenList_3_whenMinPrice_thenReturnColour () {

        System.out.println("3) Вывести цвет автомобиля с минимальной стоимостью.");

        Optional<Car> optionalCar = allCarList.stream()
                .min((o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));

        boolean isTestPassed;

        if(optionalCar.isPresent()) {
            System.out.println(optionalCar.get().getColour());
            System.out.println(optionalCar.get());
            isTestPassed = optionalCar.get().getColour().equals("grey");
        } else isTestPassed = false;

        Assertions.assertTrue(isTestPassed);
        System.out.println("------------------\n");

    }

    @Test
    void givenList_4_whenMark_thenAvgPrice () {

        System.out.println("4) Средняя стоимость Camry");
        final String mark = "Camry";

        allCarList.stream()
                .filter(p -> mark.equals(p.getMark()))
                .forEach(System.out::println);


        double avgPrice = allCarList.stream()
                .filter(p -> p.getMark().equals(mark))
                .mapToInt(car -> (int) car.getPrice())
                .summaryStatistics()
                .getAverage();


        System.out.println("Average price is " + avgPrice);

        boolean isTestPassed = (avgPrice == 1300000.0);

        Assertions.assertTrue(isTestPassed);
        System.out.println("------------------\n");

    }

}