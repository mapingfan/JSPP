<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- User: 马平凡 Date: 2017/12/01 Time: 20:50 --%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>EL/JSTL学习</title>
</head>
<body>
<%--EL负责取值，JSTL负责逻辑，两个技术目的就是代替JSP页面的脚本--%>
<%--JSTL导入两个包,需要用到taglib指令,默认前缀c--%>
<%
    request.setAttribute("count", 10);
%></
>
<%--jstl之foreach--%>
<c:forEach begin="0" end="5" step="1" var="i">
    ${i}<br>
    <%
        List<String> list = new LinkedList<>();
        list.add("111");
        list.add("112");
        list.add("113");
        list.add("114");
        Map<String, String> map = new HashMap<>();
        map.put("1", "11");
        map.put("2", "12");
        map.put("3", "13");

        session.setAttribute("list", list);
        session.setAttribute("map", map);
    %>
</c:forEach>
<%--items表示集合或者数据， var代表集合中某一个元素--%>
<%--模拟增强for循环--%>
<c:forEach var="item" items="${list}">
    ${item}
</c:forEach>
<c:forEach items="${map}" var="item">
    ${item.key}
    ${item.value}
</c:forEach>
</body>
</html>
