package com.epam.film.rating.filter;

import com.epam.film.rating.entity.user.Role;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockingUserFilter implements Filter{
    private String encoding;
    List<String> commands = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commands.add("updateLike");
        commands.add("updateDislike");
        commands.add("leaveReview");
        commands.add("findFilmsByParameters");
        commands.add("goToFilmDescriptionPage");
        commands.add("findReviewsByParameters");
        commands.add("goToReviewChangePage");
        commands.add("updateReview");
        commands.add("deleteReview");
        commands.add("findUsers");
        commands.add("changeUserRole");
        commands.add("addFilm");
        commands.add("goToAdminPage");
        commands.add("goToFilmsChoicePage");
        commands.add("login");
        commands.add("registration");
        commands.add("goToLoginPage");
        commands.add("goToRegistrationPage");
        commands.add("changeUserRole");
        commands.add("activateAccount");
        commands.add("goToMainPage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String urlQuery = request.getQueryString();

        if (request.getSession().getAttribute("isBanned") != null && request.getSession().getAttribute("isBanned").toString().trim().equals("true")) {

            int endOfString = urlQuery.indexOf("&");
            if (endOfString == -1) {
                endOfString = urlQuery.length();
            }
            int startOfString = urlQuery.indexOf("=") + 1;
            String substringOfURL = urlQuery.substring(startOfString, endOfString);

            if (commands.toString().contains(substringOfURL)) {
                request.setAttribute("systemMessage", "Your account is blocked");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
                dispatcher.forward(request, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
