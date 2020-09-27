package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String path = "";
            if (req.getParameter("fileRoot") == null) {
                path = "C:\\Users\\DELL\\Desktop\\php";
            } else {
                path = req.getParameter("fileRoot");
            }
            File file = new File(path);
            ArrayList<AboutFile> files = new ArrayList<>();
            for (File file1 : file.listFiles()) {
                files.add(new AboutFile(file1));
            }
            req.setAttribute("ListFile", files);
            req.setAttribute("Path", path);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        catch (Exception e){
            System.out.println(e);
            try {
                throw new Exception("Something went wrong");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  str= req.getParameter("searchtext");
        String  path= req.getParameter("searchFile");
        File file=new File(path);
        AboutFile aboutFile=new AboutFile();
        ArrayList<File> arrayList=aboutFile.getFiles(file,str);
        req.setAttribute("ListFile",arrayList);
        req.setAttribute("Path",path);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }*/


}
