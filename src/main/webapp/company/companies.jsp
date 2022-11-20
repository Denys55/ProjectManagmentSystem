<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/header.css">
    <mylib:header></mylib:header>
</head>
<body>
<br>

<div>
    <h3>Create Company</h3>
    <form id="company_create" method="post" action="company">
        <input type="hidden" name="create" value="true"/>
        <label>Name</label>
        <input type="text" class="text" name="name"/>
        <label>Country</label>
        <input type="text" class="text" name="country"/>
        <button type="submit">Create</button>
    </form>
</div>
<div>
    <h3>List Companies</h3>
    <c:forEach var="company" items="${companies}">
        <div>
            <form id="company_form" method="post" action="company">
                <p> id: ${company.id} </p>
                <input type="hidden" name="id" value="${company.id}"/>
                <input type="text" class="text" name="name" value="${company.name}" placeholder="${company.name}"/>
                <input type="text" class="text" name="country" value="${company.country}"
                       placeholder="${company.country}"/>
                <button type="submit">Update</button>
            </form>
            <form id="delete_form" action="company" method="post">
                <input type="hidden" name="delete" value="true"/>
                <input type="hidden" name="id" value="${company.id}"/>
                <input type="hidden" class="text" name="name" value="${company.name}"
                       placeholder="${company.name}"/>
                <input type="hidden" class="text" name="country" value="${company.country}"
                       placeholder="${company.country}"/>
                <button type="submit">delete</button>
            </form>
        </div>
    </c:forEach>
</div>
<div>

</div>
</body>
</html>