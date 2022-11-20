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
    <h3>Create Skill</h3>
    <form id="company_create" method="post" action="skill">
        <input type="hidden" name="create" value="true"/>
        <label>Language</label>
        <input type="text" class="text" name="languages"/>
        <label>Level</label>
        <input type="text" class="text" name="level"/>
        <button type="submit">Create</button>
    </form>
</div>
<div>
    <h3>List Skills</h3>
    <c:forEach var="skill" items="${skills}">
        <div>
            <form id="skill_form" method="post" action="skill">
                <p> id: ${skill.id} </p>
                <input type="hidden" name="id" value="${skill.id}"/>
                <input type="text" class="text" name="languages" value="${skill.language}"
                       placeholder="${skill.language}"/>
                <input type="text" class="text" name="level" value="${skill.level}"
                       placeholder="${skill.level}"/>
                <button type="submit">Update</button>
            </form>
            <form id="delete_form" action="skill" method="post">
                <input type="hidden" name="delete" value="true"/>
                <input type="hidden" name="id" value="${skill.id}"/>
                <input type="hidden" class="text" name="languages" value="${skill.language}"/>
                <input type="hidden" class="text" name="level" value="${skill.level}"/>
                <button type="submit">delete</button>
            </form>
        </div>
    </c:forEach>
</div>
<div>

</div>
</body>
</html>