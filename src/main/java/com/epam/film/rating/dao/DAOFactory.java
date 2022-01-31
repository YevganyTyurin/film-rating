package com.epam.film.rating.dao;

import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewApprovalDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.dao.impl.UserDAOImpl;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();
    private final UserDAO userDAO;
    private final FilmDAO filmDAO;
    private final ReviewDAO reviewDAO;
    private final ReviewApprovalDAO reviewApprovalDAO;

    private DAOFactory(){
        userDAO = new UserDAOImpl();
        filmDAO = new FilmDAOImpl();
        reviewDAO = new ReviewDAOImpl();
        reviewApprovalDAO = new ReviewApprovalDAOImpl();
    }

    public ReviewApprovalDAO getReviewApprovalDAO() {return reviewApprovalDAO;}

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public FilmDAO getFilmDAO() {
        return filmDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}