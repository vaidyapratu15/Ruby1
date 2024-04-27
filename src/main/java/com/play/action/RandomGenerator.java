package com.play.action;

import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import com.github.javafaker.Faker;

public class RandomGenerator {
    
    
    
    public  String generateRandomPassword() {
        Faker faker = new Faker();
        String password = faker.regexify("[A-Z][a-z0-9@#$%]{7,11}");
        return password;
    }
    
    public String generateRandomUsername() {
        Faker faker = new Faker();
        String username = faker.regexify("[a-zA-Z]{8}");
        return username;
    }
    
    public String generateRandomName(int number) {
	    String name = RandomStringUtils.randomAlphabetic(number).toLowerCase();
	    return name;
	}
    
    public  String generateRandomNumber(int number) {
        Faker faker = new Faker();
        String mobileNumber = faker.number().digits(number);
        return mobileNumber;
    }
    
    public  String generateRandomStrongPassword() {
        Faker faker = new Faker();
        String password = faker.bothify("P???@###");
        return password;
    }
    
    public  String generateRandomWeakPassword() {
        Faker faker = new Faker();
        String password = faker.bothify("P?@##");
        return password;
    }

    public String generateRandomWords() {   //itaque pariatur similique pariatur dolor odit
	    Faker faker = new Faker();
	    List<String> words = faker.lorem().words(6);
	    String name = String.join(" ", words);
	    return name;
	}
    
    public String generateRandomName() {    //any no of letter
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }
	


}
