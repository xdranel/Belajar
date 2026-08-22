package gendhiramona.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Synchronized;

import java.io.IOException;

@WebServlet(urlPatterns = "/unsafe")
public class UnsafeServlet extends HttpServlet {

    // Simple Solution
    // private String response = "";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long sleep = Long.parseLong(req.getParameter("sleep"));

        String response = "Hello " + name;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().println(response);
    }
}
