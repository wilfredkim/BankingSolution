<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/20/2017
  Time: 8:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="CustomerPage.jsp"%>
<html>
<head>
    <title>Withdraw</title>
</head>
<body>
<form method="post" action="makeWithdrawal">
    <center>
        <table border="1" width="30%" cellpadding="5">
            <thead>
            <tr>
                <th colspan="2">Make Withdrawal</th>
            </tr>
            </thead>
            <tbody>


            <tr>
            <tr>
                <td> Account Number</td>
                <td><input type="number" name="accnum" value="" /></td>
            </tr>
            <tr>
            <tr>
            <tr>
                <td>Amount to withdraw</td>
                <td><input type="number" name="amount" value="" /></td>
            </tr>

            <tr>
                <td><input type="submit" value="Withdraw" /></td>
                <td><input type="reset" value="Reset" /></td>
            </tr>

            </tbody>
        </table>
    </center>
</form>
</body>
</html>
