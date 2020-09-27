package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String str1 = req.getParameter("fileRoot");
            File file = new File(str1);
            if (file.isFile()) {
                System.out.println("It is file");
            } else if (file.isDirectory()) {
                System.out.println("It is directory");
                req.getRequestDispatcher("/FirstServlet").forward(req, resp);
            }
        }
        catch (Exception  e){
            try {
                throw new Exception("Something went wrong");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }
}
