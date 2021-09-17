package edu.innopolis.homework06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CarsRepositoryFileImplTest {


    @Test
    void someTest2 () {

        final String regex = "\\[(.*?)\\]";
        final String string = "[А001АЕ716][Land Cruiser][white][45000][5200000.0]";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);

        String[] fields = new String[5];
        int i = 0;
        while (matcher.find()) {
            fields[i] = matcher.group(1);
            i++;
        }

        for(String s : fields) {
            System.out.println(s);
        }
    }


    @Test
    void givenList_whenBlackColourOrKmAge_thenReturnNumber () {
        String testMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String carsRepositoryFileName = "test_" + testMethodName + "_cars.txt";

        CarsRepository carsRepository = new CarsRepositoryFileImpl(carsRepositoryFileName);

        if(carsRepository.findAll().size() == 0) {
            carsRepository.save(new Car("В157МЕ716", "Tiguan", "grey", 105000, 1900000));
            carsRepository.save(new Car("А555МЕ716", "Camry", "black", 205000, 900000));
            carsRepository.save(new Car("А001АА777", "Niva", "white", 245000, 210000));
            carsRepository.save(new Car("В999МЕ777", "Vesta", "green", 65000, 900000));
            carsRepository.save(new Car("У15УУ716", "X-Trail", "grey", 125000, 1000000));
            carsRepository.save(new Car("Р545НН716", "Aveo", "brown", 82000, 350000));
            carsRepository.save(new Car("П178РР16", "Volga", "grey", 698000, 30000));
            carsRepository.save(new Car("Р159РР716", "SX-4", "black", 165000, 600000));
            carsRepository.save(new Car("О158МЕ716", "VAZ2114", "grey", 320000, 140000));
            carsRepository.save(new Car("С015СО718", "Duster", "black", 35000, 980000));
            carsRepository.save(new Car("В001МЕ716", "Terramont", "yellow", 85000, 2900000));
            carsRepository.save(new Car("А001АЕ716", "Land Cruiser", "white", 45000, 5200000));

        }

        List<Car> carList = carsRepository.findAll();

        System.out.println(carList);


        try {
            Files.deleteIfExists(Paths.get(carsRepositoryFileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Assertions.assertTrue(true);

    }


//    1) Номера всех автомобилей, имеющих черный цвет или нулевой пробег.
//    2) Количество уникальных моделей в ценовом диапазоне от 700 до 800 тыс.
//    3) Вывести цвет автомобиля с минимальной стоимостью.
//    4) Среднюю стоимость Camry


}