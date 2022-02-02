package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.user.Role;
import com.epam.film.rating.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String NICKNAME = "nickname";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String EMAIL = "email";
    public static final String IS_BANNED = "is_banned";
    public static final String AVATAR_IMAGE = "avatar_image";
    public static final String RATING = "rating";
    public static final String ROLE_ID = "user_role_id";
    public static final String STATUS_ID = "user_status_id";
    public static final String STATUS = "status";


    public static String GET_USERS = "select * from user;";
    public static String GET_USERS_BY_LOGIN_PASSWORD = "select * from user where login=? and password=?;";
    public static String GET_USERS_BY_LOGIN_PASSWORD2 = "select user.id, user.login, user.password, user.nickname, user.name, user.surname, user.phone_number, user.email, user.is_banned, user.avatar_image, user.rating, user.user_role_id, user.user_status_id, user_status.status from user JOIN user_status ON user.user_status_id=user_status.id where login=? and password=?;";
    public static String GET_USER_BY_ID = "select user.id, user.login, user.password, user.nickname, user.name, user.surname, user.phone_number, user.email, user.is_banned, user.avatar_image, user.rating, user.user_role_id, user.user_status_id, user_status.status from user JOIN user_status ON user.user_status_id=user_status.id where user.id=?;";

    public static String GET_IS_BANNED_BY_ID = "SELECT user.is_banned FROM user WHERE user.id=?;";
    public static String GET_ROLE_ID = "SELECT user.user_role_id FROM user WHERE user.id=?;";


    //    public static String GET_USERS_BY_LOGIN_PASSWORD2 = "select user.login, user.password, user.nickname, user.name, user.surname, user.phone_number, user.email, user.is_banned, user.avatar_image, user.rating, user.user_role_id, user.user_status_id, user_status.status from user JOIN user_status ON user.user_status_id=user_status.id where login=? and password=?;";
 //   public static String ADD_USER = "insert into user (id, login, password, nickname, name, surname, phone_number, email, user_role_id, user_status_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static String ADD_USER = "insert into user (login, password, nickname, name, surname, phone_number, email) values(?, ?, ?, ?, ?, ?, ?);";

    public static String UPDATE_USER = "update user set login=?, password=?, nickname=?, name=?, surname=?, phone_number=?, email=?  where id=?;";
    public static String DELETE_USER = "delete from user where id=?;";
    public static String UPDATE_IS_BANNED = "update user set is_banned=? where id=?;";
    public static String UPDATE_ROLE = "UPDATE user SET user_role_id=? WHERE id=?;";

    public static String LOG_IN = "select * from user where login=? and password=?;";

    public static String ADD_USER2 = "insert into user (login, password, nickname, name, surname, phone_number, email) values(?, ?, ?, ?, ?, ?, ?);";

    ConnectionPool connectable = ConnectionPool.getInstance();

    public UserDAOImpl() {}

    @Override
    public List<User> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<User> users = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(InstanceBuilder.buildUser(resultSet));
            }
            return users;
        }catch (SQLException  sqlE) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public boolean isBanned (int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            boolean isBanned;
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_IS_BANNED_BY_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getBoolean(UserDAOImpl.IS_BANNED);
            }
            return false;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getRoleId (int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_ROLE_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(UserDAOImpl.ROLE_ID);
            }
            return resultSet.getInt(UserDAOImpl.ROLE_ID);
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public boolean updateRole(int userId, int roleId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ROLE);
            preparedStatement.setInt(1, roleId);
            preparedStatement.setInt(2, userId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    @Override
    public User login(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            User user = new User();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_USERS_BY_LOGIN_PASSWORD2);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = InstanceBuilder.buildUser(resultSet);
            }
            return user;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public User findById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            User user = new User();
            connection = connectable.getConnection();

            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = InstanceBuilder.buildUser(resultSet);
            }
            return user;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int add (User user) throws DAOException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_USER);

            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getNickname());
            pr.setString(4, user.getName());
            pr.setString(5, user.getSurname());
            pr.setString(6, user.getPhoneNumber());
            pr.setString(7, user.geteMail());

            pr.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return 1;
    }

    public int addUser (User user) throws DAOException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_USER);

            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getNickname());
            pr.setString(4, user.getName());
            pr.setString(5, user.getSurname());
            pr.setString(6, user.getPhoneNumber());
            pr.setString(7, user.geteMail());



            pr.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return 1;

    }

    public int update (User user, int id) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNickname());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.geteMail());
            preparedStatement.setInt(8, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return 1;
    }

    @Override
    public boolean updateIsBanned(int id, boolean isBanned) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_IS_BANNED);
            preparedStatement.setInt(2, id);
            preparedStatement.setBoolean(1, isBanned);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }


    public int delete (int id) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return 1;
    }

}
