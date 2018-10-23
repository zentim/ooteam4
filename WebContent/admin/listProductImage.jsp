<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%> 

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_brand_list">All Brand</a></li>
    <li class="breadcrumb-item"><a href="admin_product_list?cid=${p.brand.id}">${p.brand.name}</a></li>
    <li class="breadcrumb-item active" aria-current="page">${ p.name }</li>
    <li class="breadcrumb-item active" aria-current="page">Manage Product Image</li>
  </ol>
</nav>

<div class="container">
  <div class="row">
    <div class="col-sm">
      <!-- Add New ProductImage -->
      <div class="card">
        <div class="card-body">
          <h4>New Proudct<b class="text-primary"> Single </b>Image</h4>

          <form method="post" id="addFormSingle" action="admin_productImage_add" enctype="multipart/form-data">
            <table class="addTable">
              <tr>
                <td>Image Size 400 X 400 is better</td>
              </tr>
              <tr>
                <td>
                	<input id="filepathSingle" type="file" name="filepath" />
                </td>
              </tr>
              <tr>
                <td colspan="2" align="center">
                  <input type="hidden" name="type" value="type_single" />
                  <input type="hidden" name="pid" value="${ p.id }" />
                  <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                </td>
              </tr>
            </table>
          </form>

        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>#</th>
              <th>Product single image thumbnail</th>
              <th>Delete</th>
            </tr>
          </thead>
          
          <tbody>
            <c:forEach items="${pisSingle}" var="pi">
            <tr>
              <td>${ pi.id }</td>
              <td>
                <a title="click to see the original image" href="img/productSingle/${pi.id}.jpg"><img height="50px" src="img/productSingle/${pi.id}.jpg"></a>  
              </td>
              <td>
                <a deleteLink="true" href="admin_productImage_delete?id=${ pi.id }">
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
    </div>
      
  </div>
</div>


  
<script>
$(function(){

  $(".addFormSingle").submit(function(){
    if(checkEmpty("filepathSingle","Image File")){
      $("#filepathSingle").value("");
      return true;
    }
    return false;
  });
  $(".addFormDetail").submit(function(){
    if(checkEmpty("filepathDetail","Image File"))
      return true;
    return false;
  });
  
});
</script>
  



<%@include file="../include/admin/adminFooter.jsp"%>