<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_category_list">所有分類</a></li>
    <li class="breadcrumb-item"><a href="admin_property_list?cid=${p.category.id}">${p.category.name}</a></li>
    <li class="breadcrumb-item active" aria-current="page">編輯屬性</li>
  </ol>
</nav>

<!-- Edit Property -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Edit Property</h4>

    <form method="post" id="addForm" action="admin_property_update">
  		<table class="addTable">
  			<tr>
  				<td>屬性名稱 : </td>
  				<td><input  id="name" name="name" value="${ p.name }" type="text" class="form-control"></td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="hidden" name="id" value="${ p.id }">
  					<input type="hidden" name="cid" value="${ p.category.id }">
  					<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  				</td>
  			</tr>
  		</table>
  	</form>

  </div>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>