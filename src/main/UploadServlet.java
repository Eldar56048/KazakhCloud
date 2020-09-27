package main;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,//2mb
        maxFileSize = 1024 * 1024 * 10,//10mb
        maxRequestSize = 1024 * 1024 * 50//50 mb
)
public class UploadServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/plain;charset=UTF-8");
        ServletOutputStream os = response.getOutputStream();
        ServletConfig sc = getServletConfig();
        String path = request.getParameter("uploadedfileRoot");
        System.out.println(path);
            Part filePart = request.getPart("myfile");
            String fileName = filePart.getSubmittedFileName();
            InputStream is = filePart.getInputStream();
            Files.copy(is, Paths.get(path +"/"+fileName),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println(path+fileName);
        os.print("File successfully uploaded");
        path = path.replace("/","%2F");
        response.sendRedirect(request.getContextPath()+"/Servlet?fileRoot="+path);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }



}
