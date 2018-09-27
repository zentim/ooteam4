<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../component/admin/adminHeader.jsp"%>
<%@include file="../component/admin/adminNavigator.jsp"%>	

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_category_list">所有分類</a></li>
    <li class="breadcrumb-item"><a href="admin_property_list?cid=${c.id}">${c.name}</a></li>
    <li class="breadcrumb-item active" aria-current="page">屬性管理</li>
  </ol>
</nav>
	
<div class="table-responsive">
  <table class="table table-striped table-sm table-bordered table-sm">
    <thead>
      <tr>
        <th>#</th>
        <th>屬性名稱</th>
        <th>編輯</th>
        <th>刪除</th>
      </tr>
    </thead>
    
    <tbody>
      <c:forEach items="${ps}" var="p">
      <tr>
        <td>${ p.id }</td>
        <td>${ p.name }</td>
        <td>
          <a href="admin_property_edit?id=${ p.id }">
            <button type="button" class="btn btn-primary btn-sm">
              <span data-feather="edit"></span>
            </button>
          </a>
        </td>
        <td>
          <a deleteLink="true" href="admin_property_delete?id=${ p.id }">
            <button type="button" class="btn btn-danger btn-sm">
              <span data-feather="trash-2"></span>
            </button>
          </a>
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>




<!-- Pagination -->
<div>
	<%@include file="../component/admin/adminPage.jsp" %>
</div>

        
	
<!-- Add New Category -->

<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Add New Property</h4>

    <form method="post" id="addForm" action="admin_property_add">
  		<table>
  			<tr>
  				<td>屬性名稱 : </td>
  				<td><input  id="name" name="name" type="text"></td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="hidden" name="cid" value="${ c.id }">
  					<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  				</td>
  			</tr>
  		</table>
  	</form>

  </div>
</div>

  
<script>
$(function(){

	$("#addForm").submit(function() {
        if (checkEmpty("name", "屬性名稱"))
            return true;
        return false;
    });
  
});
</script>
	



<%@include file="../component/admin/adminFooter.jsp"%>