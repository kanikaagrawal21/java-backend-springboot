

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String VALID_USERNAME = "Kanika";
    private static final String VALID_PASSWORD = "@123Kanika";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-+=<>?/{}~|";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!isValidName(user)) {
            out.println("<h3> Invalid Username:</h3>");
            out.println("<p>Must start with uppercase and have at least 3 characters.</p>");
            return;
        }

        if (!isValidPassword(pass)) {
            out.println("<h3> Invalid Password:</h3>");
            out.println("<ul>");
            out.println("<li>At least 8 characters</li>");
            out.println("<li>At least 1 uppercase letter</li>");
            out.println("<li>At least 1 number</li>");
            out.println("<li>Exactly 1 special character</li>");
            out.println("</ul>");
            return;
        }

        if (VALID_USERNAME.equals(user) && VALID_PASSWORD.equals(pass)) {
            out.println("<h1> Login Successful!</h1>");
        } else {
            out.println("<h1> Invalid Credentials</h1>");
        }
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("^[A-Z][a-zA-Z]{2,}$");
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;

        int upper = 0, digit = 0, special = 0;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) upper++;
            else if (Character.isDigit(ch)) digit++;
            else if (SPECIAL_CHARS.contains(String.valueOf(ch))) special++;
        }

        return upper >= 1 && digit >= 1 && special == 1;
    }
}
