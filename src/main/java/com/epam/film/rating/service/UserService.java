package com.epam.film.rating.service;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.exception.ServiceException;

public interface UserService {

    boolean validateLogin(String login);

    boolean validatePassword(String password);

    boolean validateNickname(String nickname);

    boolean validateName(String name);

    boolean validateSurname(String surname);

    boolean validatePhoneNumber(String phoneNumber);

    boolean validateEmail(String email);

    boolean validateUser (User user);

    User login(String login, String password) throws ServiceException;

    int add (User user) throws ServiceException;

    User findById(int id) throws ServiceException;
}
