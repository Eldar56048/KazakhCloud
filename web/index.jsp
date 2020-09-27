<%@include file="jsp/header.jsp"%> <%--There we include header.php--%>
    <%String path = (String) request.getAttribute("Path");
    %>
    <%!
        private String backPath(String path){
            String newPath = "";
            for (int i = path.length() - 1; i >= 0; i--) {
                if (path.charAt(i) == '/') {
                    newPath = path.substring(0, i);
                    System.out.println(newPath);
                    break;
                }
            }
            if (newPath.equals("C:/Users/DELL/Desktop")) {
                System.out.println(newPath);
                return "C:/Users/DELL/Desktop/php";
            }
            System.out.println(newPath);
            return newPath;
        }
    %>
    <div class="all">
        <h1 style="font-size:2vw;">Path Name: <%=path%></h1><%--There we can se path of the file--%>
    </div>
    <div class="all">
        <button >
            <a href="<%=request.getContextPath()%>/FirstServlet?fileRoot=<%=backPath(path)%>">Back</a>
        </button>
        <button >
            <form method="post" action="<%=request.getContextPath()%>/Upload"<%--This form for upload files--%>
                  enctype="multipart/form-data">
                Select file to upload: <input  type="file" name="myfile"><br />
                <br />
                <%
                    String stringHello = path.replace("\\","%2F");
                %>
                <input type="hidden" value="<%=stringHello%>" name="uploadedfileRoot">
                <input type="submit" value="Upload" />
            </form>
        </button>
        <button >
            <form method="post" action="<%=request.getContextPath()%>/FirstServlet"><%--This form for the search--%>
                <input  type="searchtext" name="searchtext" placeholder="Search"><br />
                <br />
                <input type="hidden" value="<%=stringHello%>" name="searchFile">
                <input type="submit" value="Search" />
            </form>
        </button>
    </div>
    <div class="all"><%--In the down we show the list of files--%>

    <%

        ArrayList<AboutFile> files = new ArrayList<>();
        files = (ArrayList<AboutFile>) request.getAttribute("ListFile");
        String str ="";
        try {
            for (AboutFile file1 : files) {
                str = file1.getPath();
                str = str.replace("\\", "%2F");
                out.println("<a href=\"" + request.getContextPath() + "/Servlet?fileRoot=" + str + "\">");
                out.println("<div class=\"files\">");
                if (file1.isFile()) {
                    out.println("<img src=\"https://icon-library.com/images/file-icon/file-icon-6.jpg\" class=\"d-block w-50\" alt=\"\">");
                } else {
                    out.println("<img src=\"https://cdn.onlinewebfonts.com/svg/img_720.png\" class=\"d-block w-50\" alt=\"\">");
                }
                out.println("<p style=\"font-size:1.2vw;\"> Name: " + file1.getName() + "</p>");
                out.println("<p style=\"font-size:1.2vw;\"> Size: " + file1.getSize() + "kb</p>");
                out.println("<p style=\"font-size:1.2vw;\"> Date" + file1.getDate() + "</p>");
                if (file1.isFile()) {
                    out.println("<a href=\"" + request.getContextPath() + "/Delete?fileRoot=" + str + "\">Delete</a>");
                    out.println("<a href=\"" + request.getContextPath() + "/Download?fileRoot=" + str + "\">Download file</a>");
                }
                out.println("</div>");
                out.println("</a>");

            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new Exception("Something went wrong");
        }
    %>

<%@include file="jsp/footer.jsp"%><%--There we include footer.jsp--%>