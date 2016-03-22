package fr.afcepf.al26.framework.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe {@link HttpServlet} qui fait office de controller.
 */
public class MaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest paramHttpServletRequest,
                         HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
        super.doGet(paramHttpServletRequest, paramHttpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest paramHttpServletRequest,
                          HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
        super.doPost(paramHttpServletRequest, paramHttpServletResponse);
    }
}
