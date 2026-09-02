package gendhiramona.webmvc.controller;

import gendhiramona.webmvc.model.dto.HelloRequest;
import gendhiramona.webmvc.model.entity.Hello;
import gendhiramona.webmvc.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/web")
public class HelloController {

    private final HelloService helloService;

    //Receive any HTTP method
    //@RequestMapping("/hello")
    //Receive only GET method
    //@RequestMapping(value = "/hello", method = RequestMethod.GET)

    //Better way to do it, can be done also with POST, PUT, DELETE, etc.
    //By using Annotation @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, etc.
//    @GetMapping("/hello")
//    public void helloWorld(@RequestParam(name = "name", required = false) String name,
//                           HttpServletResponse response) throws IOException {
//        //Using @RequestParam to get the value of the parameter
//        response.setContentType("text/html");
//        if (Objects.isNull(name) || Objects.equals(name, "")) {
//            response.sendRedirect("/hello?name=Guest");
//            response.getWriter().println("<h1>Hello Guest</h1>");
//        } else {
//            response.getWriter().println("<h1>Hello " + name + "</h1>");
//        }

        //All this 3 code below still using HttpServletRequest
//        response.setContentType("text/html");
//        if (request.getParameter("name") != null) {
//            response.getWriter().println("<h1>" + helloService.hello(request.getParameter("name")) + "</h1>");
//        } else {
//            response.getWriter().println("<h1>" + helloService.hello(null) + "</h1>");
//        }

//        if (request.getParameter("name") != null) {
//            response.getWriter().println("<h1>Hello " + request.getParameter("name") + "</h1>");
//        } else if (request.getParameter("name") == null || request.getParameter("name").isEmpty()) {
//            response.getWriter().println("<h1>Hello Guest</h1>");
//        }

//        response.setContentType("text/html");
//        String name = request.getParameter("name");
//        if (Objects.isNull(name)) {
//            name = "Guest";
//        }
//        response.sendRedirect("/hello?name=" + name);
//        response.getWriter().println("<h1>Hello " + name + "</h1>");
//    }

//    @GetMapping("/web/hello")
//    //Using ModelAndView to return a view
//    public ModelAndView hello(
//            @RequestParam(name = "name", required = false) String name
//    ) {
//        if (Objects.isNull(name) || Objects.equals(name, "")) {
//            name = "Guest";
//        }
//        return new ModelAndView("hello", Map.of(
//                "title", "Belajar View",
//                "name", "Hello " + name
//        ));
//    }

//    @GetMapping("/web/hello")
//    public String getHello(
//            Model model,
//            HelloRequest request
//    ) {
//        String helloName = helloService.hello(request.getName());
//        model.addAttribute("title", "Belajar View");
//        model.addAttribute("name", helloName);
//        return "hello";
//    }

//    @GetMapping("/web/hello-with-age")
//    public String getHelloWithAge(
//            Model model,
//            @Valid @ModelAttribute("hello") Hello hello, // recommended to use @Valid
//            BindingResult bindingResult
//    ) {
//        model.addAttribute("title", "Belajar View With Age");
//
//        // Check if validation failed
//        if (bindingResult.hasErrors()) {
//            return "hello";
//        }
//
//
//        // Proceed as normal if validation passes
//        String helloName = helloService.helloWithAge(hello);
//        model.addAttribute("name", helloName);
//        return "hello";
//    }


    // Constructor Injection
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String showForm(Model model) {
        model.addAttribute("title", "Belajar View");
        model.addAttribute("hello", new Hello());
        return "hello";
    }

    @PostMapping("/hello")
    public String processForm(
            Model model,
            @Valid @ModelAttribute("hello") Hello hello,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ){
        model.addAttribute("title", "Belajar View");

        if(bindingResult.hasErrors()){
            return "hello";
        }

        String helloName = helloService.helloWithAge(hello);
        redirectAttributes.addFlashAttribute("name", helloName);

        // Signal to the view that submission was successful so JS can clear storage
        redirectAttributes.addFlashAttribute("submitted", true);
        return "redirect:/web/hello";
    }
}
