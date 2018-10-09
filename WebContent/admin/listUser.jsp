<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<h2>User</h2>
<div class="table-responsive">
  <table class="table table-striped table-bordered table-sm">
    <thead>
      <tr>
        <th>#</th>
        <th>Email</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${userList}" var="u">
      <tr>
        <th>${ u.id }</th>
        <th>${ u.email }</th>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>




<!-- Pagination -->
<div>
	<%@include file="../include/admin/adminPage.jsp" %>
</div>



<%@include file="../include/admin/adminFooter.jsp"%>
