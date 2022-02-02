package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    int add(User user) throws DAOException;

    User login(String login, String password) throws DAOException;

    User findById(int id) throws DAOException;

    boolean isBanned(int id) throws DAOException;

    boolean updateIsBanned(int id, boolean isBanned) throws DAOException;

    public List<User> getAll() throws SQLException;
}
