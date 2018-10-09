<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<h2>Category</h2>
<div class="table-responsive table-bordered table-sm">
  <table class="table table-striped table-sm">
    <thead>
      <tr>
        <th>#</th>
        <th>分類名稱</th>
        <th>產品管理</th>
        <th>編輯</th>
        <th>刪除</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${thecs}" var="c">

      <tr>
        <td>${ c.id }</td>
        <td>${ c.name }</td>
        <td>
          <a href="admin_product_list?cid=${ c.id }">
            <button
              type="button"
              class="btn btn-primary btn-sm">
              <span data-feather="shopping-cart"></span>
            </button>
          </a>
        </td>
        <td>
          <a href="admin_category_edit?id=${ c.id }">
            <button
              type="button"
              class="btn btn-primary btn-sm">
              <span data-feather="edit"></span>
            </button>
          </a>
        </td>
        <td>
          <a deleteLink="true" href="admin_category_delete?id=${c.id}">
            <button
              type="button"
              class="btn btn-danger btn-sm">
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
	<%@include file="../include/admin/adminPage.jsp" %>
</div>



<!-- Add New Category -->

<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Add New Category</h4>

    <form
      method="post"
      id="addForm"
      action="admin_category_add">
  		<table>
  			<tr>
  				<td>分類名稱 : </td>
  				<td>
            <input
              id="name"
              name="name"
              type="text">
          </td>
  			</tr>
  			<tr>
  				<td
            colspan="2"
            align="center">
  					<button
              class="btn btn-lg btn-primary btn-block"
              type="submit">Submit</button>
  				</td>
  			</tr>
  		</table>
  	</form>

  </div>
</div>


<script>
$(function(){

  $("#addForm").submit(function(){
    if(!checkEmpty("name", "分類名稱"))
      return false;
    return true;
  });

});
</script>




<%@include file="../include/admin/adminFooter.jsp"%>
