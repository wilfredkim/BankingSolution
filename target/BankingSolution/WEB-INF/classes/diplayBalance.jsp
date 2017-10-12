<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/20/2017
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="CustomerPage.jsp"%>
<html>
<head>
    <title>Balance</title>
</head>
<body>
<%="Balance is:"+session.getAttribute("Balance")%>
<br/>
<a href="CustomerPage.jsp">  Back </a>
</body>
</html>
