package base_test.data;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierDataGenerate {

    public static String generateLogin(){
        return "login" + RandomStringUtils.randomAlphabetic(4);
    }

    public static String generatePassword(){
        return "password" + RandomStringUtils.randomAlphabetic(4);
    }

    public static String generateFirstName(){
        return "firstName" + RandomStringUtils.randomAlphabetic(4);
    }
}
