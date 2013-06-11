<%-- 
    Document   : index
    Created on : 11.06.2013, 09:44:59
    Author     : Simon
--%>

<%@page import="service.GreetingService"%>
<%@page import="javax.ejb.EJB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    @EJB
    GreetingService greetingService;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><% greetingService.sayHello(); %></h1>
    </body>
</html>
