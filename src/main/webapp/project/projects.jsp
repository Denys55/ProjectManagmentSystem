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
    <h3>Create Project</h3>
    <form id="company_create" method="post" action="project">
        <input type="hidden" name="create" value="true"/>
        <label>Name</label>
        <input type="text" class="text" name="name"/>
        <label>Complexity</label>
        <input type="text" class="text" name="complexity"/>
        <label>Cost</label>
        <input type="text" class="text" name="cost"/>
        <button type="submit">Create</button>
    </form>
</div>
<div>
    <h3>List Projects</h3>
    <c:forEach var="project" items="${projects}">
        <div>
            <form id="company_form" method="post" action="project">
                <p> id: ${project.id} </p>
                <input type="hidden" name="id" value="${project.id}"/>
                <input type="text" class="text" name="name" value="${project.name}" placeholder="${project.name}"/>
                <input type="text" class="text" name="complexity" value="${project.complexity}"
                       placeholder="${project.complexity}"/>
                <input type="text" class="text" name="cost" value="${project.cost}"
                       placeholder="${project.cost}"/>
                <button type="submit">Update</button>
            </form>
            <form id="delete_form" action="project" method="post">
                <input type="hidden" name="delete" value="true"/>
                <input type="hidden" name="id" value="${project.id}"/>
                <input type="hidden" class="text" name="name" value="${project.name}"
                       placeholder="${project.name}"/>
                <input type="hidden" class="text" name="complexity" value="${project.complexity}"/>
                <input type="hidden" class="text" name="cost" value="${project.cost}"/>
                <button type="submit">delete</button>
            </form>
        </div>
    </c:forEach>
</div>
<div>

</div>
</body>
</html>