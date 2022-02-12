package com.epam.film.rating.service;

import com.epam.film.rating.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService;
    private final ReviewService reviewService;
    private final FilmService filmService;
    private final ReviewApprovalService reviewApprovalService;

    private final DtoService dtoService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        reviewService = new ReviewServiceImpl();
        filmService = new FilmServiceImpl();
        reviewApprovalService = new ReviewApprovalImpl();
        dtoService = new DtoServiceImpl();
    }

    public ReviewApprovalService getReviewApprovalService() {
        return reviewApprovalService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public UserService getUserService() {
        return userService;
    }

    public DtoService getDtoService() {
        return dtoService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
