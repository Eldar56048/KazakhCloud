package main;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class DownloadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        FileInputStream fileInputStream = null;
        try {
            String path = request.getParameter("fileRoot");
            File file = new File(path);
            response.setContentType("text/html");
            String filename = file.getName();
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            fileInputStream = new FileInputStream(path);
            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            fileInputStream.close();
            out.close();
        }
    }

}
