<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/20/2017
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="CustomerPage.jsp"%>
<html>
<head>
    <title>Check Balance</title>
</head>
<body>
<form method="post" action="checkBalance">
    <center>
        <table border="1" width="30%" cellpadding="5">
            <thead>
            <tr>
                <th colspan="2">Check Balance</th>
            </tr>
            </thead>
            <tbody>


            <tr>
            <tr>
                <td> Account Number</td>
                <td><input type="number" name="accnum" value="" /></td>
            </tr>
            <tr>

                <td><input type="submit" value="Check Blance" /></td>
                <td><input type="reset" value="Reset" /></td>
            </tr>

            </tbody>
        </table>
    </center>
</form>
</body>
</html>
