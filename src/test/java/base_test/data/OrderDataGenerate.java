package base_test.data;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderDataGenerate {

    public static String generateFirstName(){
        return "firstName" + RandomStringUtils.randomAlphabetic(4);
    }

    public static String generateLastName(){
        return "lastName" + RandomStringUtils.randomAlphabetic(4);
    }

    public static String generateAddress(){
        Random random = new Random();
        random.nextInt( 100);
        return "Город, улица " + random + ", дом " + random;
    }

    public static int generatMemetroStation(){
        int numberOfOption = 224;

        Random random = new Random();
        return random.nextInt(numberOfOption + 1);
    }

    public static String generatePhone(){
        Random random = new Random();

        int block1 = random.nextInt(900) + 100; // Генерирует число от 100 до 999
        int block2 = random.nextInt(900) + 100; // Генерирует число от 100 до 999
        int block3 = random.nextInt(90) + 10;   // Генерирует число от 10 до 99
        int block4 = random.nextInt(90) + 10;   // Генерирует число от 10 до 99

        return String.format("+7 %d %d %d %d", block1, block2, block3, block4);
    }

    public static int generateRentTime(){
        int numberOfOption = 7;

        Random random = new Random();
        return random.nextInt(numberOfOption) + 1;
}

    public static String generateDeliveryDate(){
        int maxDays = 27;
        int maxMonth = 11;
        int actualYear = 2025;
        int maxYear = 2026;

        Random random = new Random();
        int randomDays = random.nextInt(maxDays) + 1;
        int randomMonth = random.nextInt(maxMonth) + 1;
        int randomYear = random.nextInt(maxYear - actualYear + 1) + actualYear;

        LocalDateTime dateTime = LocalDateTime.of(randomYear, randomMonth, randomDays, 21, 0, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        String deliveryDate = dateTime.atOffset(ZoneOffset.UTC).format(formatter);

        return deliveryDate;
    }


    public static String generateComment(){
        return "Comment" + RandomStringUtils.randomAlphabetic(4);
    }

    public static int generateNumberOfOrdersToCreate(){
        int numberOfOrdersToCreate = 30;

        Random random = new Random();
        return random.nextInt(numberOfOrdersToCreate) + 1;
    }
}

