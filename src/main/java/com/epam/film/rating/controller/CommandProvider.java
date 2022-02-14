package com.epam.film.rating.controller;

import com.epam.film.rating.controller.impl.*;
import com.epam.film.rating.controller.impl.LeaveReview;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("goToAdminPage", new GoToAdminPage());
        commands.put("goToFilmsChoicePage", new GoToFilmsChoicePage());
        commands.put("goToRegistrationPage", new GoToRegistrationPage());
        commands.put("goToLoginPage", new GoToLoginPage());
        commands.put("goToMainPage", new GoToMainPage());
        commands.put("goToFilmDescriptionPage", new GoToFilmDescriptionPage());
        commands.put("goToReviewChangePage", new GoToReviewChangePage());

        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("changeLanguage", new ChangeLanguage());
        commands.put("findFilmsByParameters", new FindFilmsByParameters());
        commands.put("leaveReview", new LeaveReview());
        commands.put("updateLike", new Like());
        commands.put("updateDislike", new Dislike());
        commands.put("findReviewsByParameters", new FindReviewsByParameters());
        commands.put("updateReview", new UpdateReview());
        commands.put("deleteReview", new DeleteReview());
        commands.put("changeUserIsBanned", new ChangeUserIsBanned());
        commands.put("findUsers", new FindUsers());
        commands.put("logOut", new LogOut());
        commands.put("changeUserRole", new ChangeUserRole());
        commands.put("addFilm", new AddFilm());
        commands.put("activateAccount", new ActivateAccount());
    }

    public final Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
