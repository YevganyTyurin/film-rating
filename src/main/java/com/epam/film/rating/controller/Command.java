package com.epam.film.rating.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet command interface.
 */

public interface Command {
    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
