package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Delete extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = request.getParameter("fileRoot");
            File file = new File(path);
            response.setContentType("text/html");
            String fileName = file.getName();
            if (file.delete()) {
                System.out.println("Deleted the folder: " + file.getName());
                path = path.replace("/", "%2F");
                path = path.replace(fileName, "");

                response.sendRedirect(request.getContextPath() + "/Servlet?fileRoot=" + path);
            } else {
                try {
                    throw new Exception("Failed to delete the folder.");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        catch (Exception e){
            throw e;
        }

    }
}