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
  <h3>Create Developer</h3>
  <form id="company_create" method="post" action="developer">
    <input type="hidden" name="create" value="true"/>
    <label>Name</label>
    <input type="text" class="text" name="name"/>
    <label>Age</label>
    <input type="text" class="text" name="age"/>
    <label>Salary</label>
    <input type="text" class="text" name="salary"/>
    <button type="submit">Create</button>
  </form>
</div>
<div>
  <h3>List Developesr</h3>
  <c:forEach var="developer" items="${developers}">
    <div>
      <form id="company_form" method="post" action="developer">
        <p> id: ${developer.id} </p>
        <input type="hidden" name="id" value="${developer.id}"/>
        <input type="text" class="text" name="name" value="${developer.name}" placeholder="${developer.name}"/>
        <input type="text" class="text" name="age" value="${developer.age}"
               placeholder="${developer.age}"/>
        <input type="text" class="text" name="salary" value="${developer.salary}"
               placeholder="${developer.salary}"/>
        <button type="submit">Update</button>
      </form>
      <form id="delete_form" action="developer" method="post">
        <input type="hidden" name="delete" value="true"/>
        <input type="hidden" name="id" value="${developer.id}"/>
        <input type="hidden" class="text" name="name" value="${developer.name}"
               placeholder="${developer.name}"/>
        <input type="hidden" class="text" name="age" value="${developer.age}"/>
        <input type="hidden" class="text" name="salary" value="${developer.salary}"/>
        <button type="submit">delete</button>
      </form>
    </div>
  </c:forEach>
</div>
<div>

</div>
</body>
</html>