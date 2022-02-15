package com.epam.film.rating.service;

import com.epam.film.rating.dao.exception.DAOException;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorTest {
    @Test
    public void validateValidLogin() throws DAOException {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{5,49}$");
        Matcher matcher = pattern.matcher("login123");
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void validateInvalidLogin() throws DAOException {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{5,49}$");
        Matcher matcher = pattern.matcher("log");
        Assert.assertFalse(matcher.matches());
    }

    @Test
    public void validatePhoneNumber() throws DAOException {
        Pattern pattern = Pattern.compile("^((375(\\d{2})|\\+375(\\d{2}))|\\d{2})\\d{7}");
        Matcher matcher = pattern.matcher("+375256399515");
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void validateUserName() throws DAOException {
        Pattern pattern = Pattern.compile("^[A-Za-zА-Яа-я]\\w{2,29}$");
        Matcher matcher = pattern.matcher("Jim");
        Assert.assertTrue(matcher.matches());
    }
}
