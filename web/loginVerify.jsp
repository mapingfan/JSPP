<%
    request.setCharacterEncoding("utf-8");
    String verifycode = request.getParameter("verifycode");
    String checkcode_session = (String) session.getAttribute("checkcode_session");
    if (verifycode != null && checkcode_session != null) {
        if (checkcode_session.equals(verifycode)) {
            response.sendRedirect("/index.jsp");
        }else {
            session.setAttribute("message","验证码错误");
            System.out.println("-----");
            response.sendRedirect("/login.jsp");
        }
    }

%>