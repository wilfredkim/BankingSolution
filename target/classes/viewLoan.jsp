<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/20/2017
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="managerPage.jsp"%>
<html>
<head>
    <title>Loans</title>
</head>
<body>

    <TABLE BORDER="3" width="100%" class="table">
        <tr>
            <th width="14%">Customer Id</th>
            <th width="14%">Acount Number</th>
            <th width="14%">Amount Requested</th>
            <th width="14%">Loan Type </th>
            <th width="14%">Reason For Loan</th>
            <th width="14%">Type of Loan</th>
            <th width="14%">Action </th>
        </tr>
        <c:forEach var="loans" items="${loanlist}">
            <tr>
                <td width="14%"><c:out value="${loans.cutomerId}"></c:out></td>
                <td width="14%"><c:out value="${loans.accNumber}"></c:out></td>
                <td width="14%"><c:out value="${loans.amountRequest}"></c:out></td>
                <td width="14%"><c:out value="${loans.loanType}"></c:out></td>
                <td width="14%"><c:out value="${loans.reasonForLoan}"></c:out></td>
                <td width="14%"><c:out value="${loans.loanType}"></c:out></td>
                <form action="approveLoan" method="post">
                    <input type="hidden" name="accnumber" value="${loans.accNumber}">
                    <td width="16%"><input type="submit" value="Approve+"></td>
                </form>

            </tr>

        </c:forEach>
    </TABLE>


</body>
</html>
