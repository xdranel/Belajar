package gendhiramona.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/todolist")
public class TodolistServlet extends HttpServlet {

    private List<String> todos = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("todos", todos);
//        req.getRequestDispatcher("/WEB-INF/todolist.jsp").forward(req, resp);
//        todos.add("Belajar Servlet");
//        todos.add("Belajar JPA");
//        todos.add("Belajar Spring MVC");
//        todos.add("Belajar Spring Boot");

        resp.getWriter().println(todos);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String todo = req.getParameter("todo");
        if (todo != null) {
            todos.add(todo);
            resp.getWriter().println("Add todo : " + todo);
        } else {
            resp.getWriter().println("Todo Parameter Must Exist");
        }
    }
}
