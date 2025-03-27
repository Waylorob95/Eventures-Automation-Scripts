package com.eventures.base;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static String getRandomUsername() {
        return faker.regexify("[a-zA-Z]{5,10}");
    }

    public static String getRandomInvalidUsername() {
        return faker.regexify("[a-zA-Z]{1,4}");
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword() {
        return faker.internet().password(8, 16);
    }

    public static String getRandomInvalidPassword() {
        return faker.internet().password(1, 5);
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEventName(){
        return faker.funnyName().name();
    }

    public static String getRandomEventNameInvalid(){
        return faker.regexify("[a-zA-Z]{1,3}");
    }

    public static String getRandomEventPlace(){
        return faker.address().city();
    }
}
