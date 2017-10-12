<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/19/2017
  Time: 11:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="CustomerPage.jsp"%>
<html>
<head>
    <title>Make Deposit</title>
</head>
<body>
<form method="post" action="makeDeposit">
    <center>
        <table border="1" width="30%" cellpadding="5">
            <thead>
            <tr>
                <th colspan="2">Make Deposit</th>
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
                <td>Amount to Deposit</td>
                <td><input type="number" name="amount" value="" /></td>
            </tr>

            <tr>
                <td><input type="submit" value="Make Deposit" /></td>
                <td><input type="reset" value="Reset" /></td>
            </tr>

            </tbody>
        </table>
    </center>
</form>
</body>
</html>
