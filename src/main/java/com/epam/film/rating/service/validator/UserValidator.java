package com.epam.film.rating.service.validator;

import com.epam.film.rating.entity.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String PATTERN_LOGIN = "^[a-zA-Z0-9_]{5,49}$";
    private static final String PATTERN_PASSWORD = "^[a-zA-Z0-9_]{5,49}$";
    private static final String PATTERN_NICKNAME = "^[A-Za-zА-Яа-я]\\w{2,29}$";
    private static final String PATTERN_NAME = "^[A-Za-zА-Яа-я]\\w{2,29}$";
    private static final String PATTERN_SURNAME = "^[A-Za-zА-Яа-я]\\w{3,29}$";
    private static final String PATTERN_PHONE_NUMBER = "^((375(\\d{2})|\\+375(\\d{2}))|\\d{2})\\d{7}";
    private static final String PATTERN_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w]+$";

    public boolean validateLogin(String login){
        return isBasicValidated(login) && validateParameterByPattern(login, PATTERN_LOGIN);
    }

    public boolean validatePassword(String password){
        return isBasicValidated(password) && validateParameterByPattern(password, PATTERN_PASSWORD);
    }

    public boolean validateNickname(String nickname){
        return isBasicValidated(nickname) && validateParameterByPattern(nickname, PATTERN_NICKNAME);
    }

    public boolean validateName(String name){
        return isBasicValidated(name) && validateParameterByPattern(name, PATTERN_NAME);
    }

    public boolean validateSurname(String surname){
        return isBasicValidated(surname) && validateParameterByPattern(surname, PATTERN_SURNAME);
    }

    public boolean validatePhoneNumber(String phoneNumber){
        return isBasicValidated(phoneNumber) && validateParameterByPattern(phoneNumber, PATTERN_PHONE_NUMBER);
    }

    public boolean validateEmail(String email){
        return isBasicValidated(email) && validateParameterByPattern(email, PATTERN_EMAIL);
    }

    public boolean validateUserRegistration(User user) {
        return validateLogin(user.getLogin()) &&
                validatePassword(user.getPassword()) &&
                validateNickname(user.getNickname()) &&
                validateName(user.getName()) &&
                validateSurname(user.getSurname()) &&
                validatePhoneNumber(user.getPhoneNumber()) &&
                validateEmail(user.geteMail());
    }

    public boolean validateUserLogin(String login, String password) {
        return validateLogin(login) && validatePassword(password);
    }

    private boolean isBasicValidated(String parameter) {
        return !parameter.equals("") && parameter != null;
    }

    private boolean validateParameterByPattern(String parameter, String regex){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(parameter);
        return matcher.matches();
    }
}
