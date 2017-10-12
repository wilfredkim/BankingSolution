<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/20/2017
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="CustomerPage.jsp"%>
<html>
<head>
    <title>View Statement</title>
</head>

<body>
<TABLE BORDER="3" width="100%" class="table">
    <tr>
        <th width="25%">Time</th>
        <th width="25%">Activity</th>
        <th width="25%">Amount</th>
        <th width="25%">Balance </th>
    </tr>
    <c:forEach var="statement" items="${viewstatement}">
        <tr>
            <td width="20%"><c:out value="${statement.time}"></c:out></td>
            <td width="20%"><c:out value="${statement.detail}"></c:out></td>
            <td width="20%"><c:out value="${statement.amount}"></c:out></td>
            <td width="20%"><c:out value="${statement.bal}"></c:out></td>

        </tr>

    </c:forEach>
</TABLE>
<button><a href="http://localhost:8081/BankingSolution/rest/download/statement">Download</a> </button>
</body>
</html>
