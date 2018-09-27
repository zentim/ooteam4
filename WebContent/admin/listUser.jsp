<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../component/admin/adminHeader.jsp"%>
<%@include file="../component/admin/adminNavigator.jsp"%>	

<h2>User</h2>
<div class="table-responsive">
  <table class="table table-striped table-sm">
    <thead>
      <tr>
        <th>#</th>
        <th>使用者名稱</th>
      </tr>
    </thead>
    
    <tbody>
      <c:forEach items="${userList}" var="userList">
      <tr>
        <th>${ userList.id }</th>
        <th>${ userList.name }</th>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>




<!-- Pagination -->
<div>
	<%@include file="../component/admin/adminPage.jsp" %>
</div>



<%@include file="../component/admin/adminFooter.jsp"%>