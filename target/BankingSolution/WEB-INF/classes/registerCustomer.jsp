
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="tellerPage.jsp"%>
<html>
<head>
    <title>SIGN-UP</title>
</head>
<body>
<form method="post" action="CustomerRegister">
    <center>
        <table border="1" width="30%" cellpadding="5">
            <thead>
            <tr>
                <th colspan="2">Register Customer</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="" /></td>
            </tr>

            <tr>
                <td>Email</td>
                <td><input type="text" name="email" value="" /></td>
            </tr>

            <tr>
                <td>PassWord</td>
                <td><input type="password" name="pass" value="" /></td>
            </tr>
            <tr>
            <tr>
                <td> Confirm PassWord</td>
                <td><input type="password" name="conpass" value="" /></td>
            </tr>
            <tr>
            <tr>
                <td> ID Number</td>
                <td><input type="number" name="idnum" value="" /></td>
            </tr>
            <tr>
            <tr>
                <td> Address</td>r
                <td><input type="text" name="address" value="" /></td>
            </tr>
            <tr>
                <td> PhoneNumber</td>r
                <td><input type="text" name="phone" value="" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Register" /></td>
                <td><input type="reset" value="Reset" /></td>
            </tr>
            <%--<tr>
                <td colspan="2">Already registered!! <a href="customerLogin.jsp">Login Here</a></td>
            </tr>--%>
            </tbody>
        </table>
    </center>
</form>
</body>
</html>
