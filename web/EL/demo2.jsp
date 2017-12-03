<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%-- User: 马平凡 Date: 2017/12/02 Time: 16:46 --%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>记录访问时间</title>
</head>
<body>
    <%
        String strDate = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss").format(new Date()).trim();
        Cookie cookie = new Cookie("lastAccessTime", strDate);
        cookie.setMaxAge(10 * 60);
        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie1 : cookies) {
            if (cookie1.getName().equals("lastAccessTime")) {
    %>
    <p>上一次访问时间是<%=cookie1.getValue()%></p>
    <%
                break;
            }
        }


    %>
</body>
</html>
