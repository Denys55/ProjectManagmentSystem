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
   <form id="form_create" method="post" action="customer">
      <input type="hidden" name="create" value="true"/>
      <label>Name</label>
      <input type="text" class="text" name="name"/>
      <label>Budget</label>
      <input type="text" class="text" name="budget"/>
      <button type="submit">Create</button>
   </form>
</div>
<div>
   <h3>List Customers</h3>
   <c:forEach var="customer" items="${customers}">
      <div>
         <form id="customer_form" method="post" action="customer">
            <p> id: ${customer.id} </p>
            <input type="hidden" name="id" value="${customer.id}"/>
            <input type="text" class="text" name="name" value="${customer.name}" placeholder="${customer.name}"/>
            <input type="text" class="text" name="budget" value="${customer.budget}"
                   placeholder="${customer.budget}"/>
            <button type="submit">Update</button>
         </form>
         <form id="form_delete" action="customer" method="post">
            <input type="hidden" name="delete" value="true"/>
            <input type="hidden" name="id" value="${customer.id}"/>
            <input type="hidden" class="text" name="name" value="${customer.name}"
                   placeholder="${customer.name}"/>
            <input type="hidden" class="text" name="budget" value="${customer.budget}"
                   placeholder="${customer.budget}"/>
            <button type="submit">delete</button>
         </form>
      </div>
   </c:forEach>
</div>
<div>

</div>
</body>
</html>
