<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%> 

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_category_list">所有分類</a></li>
    <li class="breadcrumb-item"><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
    <li class="breadcrumb-item active" aria-current="page">${ p.name }</li>
    <li class="breadcrumb-item active" aria-current="page">產品圖片管理</li>
  </ol>
</nav>

<div class="container">
  <div class="row">
    <div class="col-sm">
      <!-- Add New ProductImage -->
      <div class="card">
        <div class="card-body">
          <h4>新增產品<b class="text-primary"> 單個 </b>圖片</h4>

          <form method="post" id="addFormSingle" action="admin_productImage_add" enctype="multipart/form-data">
            <table class="addTable">
              <tr>
                <td>請選擇本地圖片 尺寸 400 X 400 為佳</td>
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
              <th>產品單個圖片縮略圖</th>
              <th>刪除</th>
            </tr>
          </thead>
          
          <tbody>
            <c:forEach items="${pisSingle}" var="pi">
            <tr>
              <td>${ pi.id }</td>
              <td>
                <a title="點擊查看原圖" href="img/productSingle/${pi.id}.jpg"><img height="50px" src="img/productSingle/${pi.id}.jpg"></a>  
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

    <div class="col-sm">
      <!-- Add New ProductImage -->
      <div class="card">
        <div class="card-body">
          <h4>新增產品<b class="text-primary"> 詳情 </b>圖片</h4>

          <form method="post" id="addFormDetail" action="admin_productImage_add" enctype="multipart/form-data">
            <table class="addTable">
              <tr>
                <td>請選擇本地圖片 寬度 790 為佳</td>
              </tr>
              <tr>
                <td>
                	<input id="filepathDetail" type="file" name="filepath" />
                </td>
              </tr>
              <tr>
                <td colspan="2" align="center">
                  <input type="hidden" name="type" value="type_detail" />
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
              <th>產品詳情圖片縮略圖</th>
              <th>刪除</th>
            </tr>
          </thead>
          
          <tbody>
            <c:forEach items="${pisDetail}" var="pi">
            <tr>
              <td>${ pi.id }</td>
              <td>
                <a title="點擊查看原圖" href="img/productDetail/${pi.id}.jpg"><img height="50px" src="img/productDetail/${pi.id}.jpg"></a>  
              </td>
              <td>
                <a deleteLink="true" href="admin_productImage_delete?id=${ p.id }">
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
    if(checkEmpty("filepathSingle","圖片文件")){
      $("#filepathSingle").value("");
      return true;
    }
    return false;
  });
  $(".addFormDetail").submit(function(){
    if(checkEmpty("filepathDetail","圖片文件"))
      return true;
    return false;
  });
  
});
</script>
  



<%@include file="../include/admin/adminFooter.jsp"%>