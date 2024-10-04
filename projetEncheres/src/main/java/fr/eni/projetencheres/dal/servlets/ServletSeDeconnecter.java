package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class ServletSeDeconnecter
 */
@WebServlet("/ServletSeDeconnecter")
public class ServletSeDeconnecter extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private static final long INACTIVITY_TIMEOUT = 5 * 60 * 1000; // 5 minutes

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Affichez la page de déconnexion
        request.getRequestDispatcher("Deconnection.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            long lastActivityTime = session.getLastAccessedTime();
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastActivityTime >= INACTIVITY_TIMEOUT) {
                session.invalidate();
                response.sendRedirect("Deconnection.jsp");
            } else {
                response.sendRedirect("Deconnection.jsp?error=inactivity");
            }
        }
    }
}
