<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/19/2017
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="tellerPage.jsp"%>
<html>
<head>
    <title>Open Account</title>
</head>
<body>
<form method="post" action="openAccount">
    <center>
        <table border="1" width="30%" cellpadding="5">
            <thead>
            <tr>
                <th colspan="2">Open Account For Customer</th>
            </tr>
            </thead>
            <tbody>
            <%--<tr>
                <td>Customer IDNumber</td>
                <td><input type="number" name="custId" value="" /></td>
            </tr>--%>

            <tr>
            <tr>
                <td> Account Number</td>
                <td><input type="number" name="accnum" value="" /></td>
            </tr>
            <tr>
            <tr>
                <td>Type of Account</td>
                <td>
                    <select name="type">
                        <option>Current Account</option>
                        <option>Savings Account</option>
                        <option>Business Account</option>

                    </select>
                </td>
            </tr>

            <tr>
                <td><input type="submit" value="Open Account" /></td>
                <td><input type="reset" value="Reset" /></td>
            </tr>

            </tbody>
        </table>
    </center>
</form>
</body>
</html>
