<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<h2>Discount</h2>
<div class="table-responsive">
  <table class="table table-striped table-bordered table-sm" >
    <thead>
      <tr align="center">
        <th>#</th>
        <th>ActivityName</th>
        <th>DiscountType</th>
        <th>From</th>
        <th>To</th>
        <th>State</th>
        <th>Operation</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${ps}" var="p">

      <tr align="center">
        <td>${ p.id }</td>
        <td>${ p.name }</td>
        <td>${ p.discountTypeDesc }</td>
		<td>
          <fmt:formatDate value="${p.dateFrom}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
        <td>
          <fmt:formatDate value="${p.dateTo}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
        <td>${ p.state }</td>
        <td>
        	<a href="admin_promotion_edit?id=${ p.id }">
	            <button type="button" class="btn btn-primary btn-sm">
	              <span data-feather="edit"></span>
	            </button>
	        </a>
            <button
              promotionId=${p.id}
              class="promotionPageCheckPromotionItems btn btn-primary btn-sm">
              MoreDetail
            </button>
            <a deleteLink="true" href="admin_promotion_delete?id=${p.id}">
	            <button
	              type="button"
	              class="btn btn-danger btn-sm">
	              <span data-feather="trash-2"></span>
	            </button>
	          </a>
        </td>
      </tr>
      
      <tr class="orderPageOrderItemTR"  promotionId=${p.id} style="display: none">
	      <td colspan="10" align="center">

	          <div  class="orderPageOrderItem">
                <table width="800px" align="center" class="orderPageOrderItemTable">

	                  <c:forEach items="${p.promotionItems }" var="pi">

	                      <tr>
	                          <td align="left">
	                          	<div style="width: 40px; height: 40px; max-width: 100%; display: flex; align-items: center; justify-content: center;">
						  	  		<img src="img/productSingle/${pi.product.firstProductImage.id}.jpg" style="height: 100%;">
						  	  	</div>
	                          </td>
	                          <td>
	                              <a href="admin_productImage_list?pid=${pi.product.id}">
	                                 <span>${ pi.product.name }</span>
	                              </a>
	                          </td>
	                          <td align="right">
	                              <span class="text-muted">Price : $${ pi.product.price }</span>
	                          </td>
	                          <td align="right">
	                              <span class="text-muted">minQuantity: ${ pi.minQuantity }</span>
	                          </td>
	                          <td align="right">
	                              <span class="text-muted">discountOf: ${ pi.discountOf }%</span>
	                          </td>
	                      </tr>

	                  </c:forEach>

                </table>
	           </div>

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


<!-- Add New Discount -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    		<h4>Promotion Name</h4>

  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroup-sizing-default">DiscountName</span>
			  </div>
			  <input 
			  	id="discount_name"
			  	name="discount_name"
			  	type="text" 
			  	class="form-control" 
			  	aria-label="Sizing example input" 
			  	aria-describedby="inputGroup-sizing-default">
			</div>



			
			<h4>Promotion Time</h4>
  			
  			<div class="container">
			    <div class="row">
			        <div>
			        	From: 
			            <div class="form-group">
			                <div class='input-group date' id='datetimepicker1'>
			                    <input type='text' class="form-control" name="dateFrom" id="date_from"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
			        </div>
			        <script type="text/javascript">
			            $(function () {
			                $('#datetimepicker1').datetimepicker({
			                	autoclose: true,
			                	todayBtn: true
			                });
			            });
			        </script>
			    </div>
			    <div class="row">
			        <div>
			        	To: 
			            <div class="form-group">
			                <div class='input-group date' id='datetimepicker2'>
			                    <input type='text' class="form-control" name="date_to" id="date_to"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
			        </div>
			        <script type="text/javascript">
			            $(function () {
			                $('#datetimepicker2').datetimepicker({
			                	autoclose: true,
			                	todayBtn: true
			                });
			            });
			        </script>
			    </div>
			</div>
  			
  			
  			
  			
  			
  			
  			<h4>Discount Rule</h4>
  			
  			<div class="form-group">
            <div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">DiscountType</label>
			  </div>
			  <select class="custom-select" id="discount_type">
			    <option value="0" selected>Choose...</option>
			    <option value="1">productSet</option>
			    <option value="2">eachGroupOfN</option>
			    <option value="3">spendMoreThanInLastYear</option>
			    <option value="4">buyXGetYFree</option>
			  </select>
			</div>
		   </div>
  			
<!--   			
  			<div class="mb-3">
  				<select id="promotion_item" multiple="multiple">
  					<c:forEach items="${products}" var="product">
				    <option value="${ product.id }">${ product.name }</option>
				    </c:forEach>
				</select>
				<script type="text/javascript">
				    $(document).ready(function() {
				        $('#promotion_item').multiselect();
				    });
				</script>		
	  			
  			</div>
 -->			
  			
  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">State</label>
			  </div>
			  <select class="custom-select" id="state">
			    <option value="0" selected>inactive</option>
				<option value="1">active</option>
			  </select>
			</div>
  			
  				
			<button class="btn btn-lg btn-primary btn-block" id="create_promotion_btn">Submit</button>
  			
  		
  	

  </div>
</div>


<script>
$(function(){
    $("button.promotionPageCheckPromotionItems").click(function(){
        var promotionId = $(this).attr("promotionId");
        $("tr.orderPageOrderItemTR[promotionId="+promotionId+"]").toggle();
    });
});
</script>


<script>
$(document).ready(function(){
    $("#create_promotion_btn").click(function(){
        var discountType = $("#discount_type").val();
        var discountName = $("#discount_name").val();
        var dateFrom = $("#date_from").val();
        var dateTo = $("#date_to").val();
        var state = $("#state").val();
        
        /*
        var selectedProducts = new Array();
        $("#promotion_item option:selected").each(function() {
        		
        	selectedProducts.push($(this).val().toString());
        	
        })
        */
        
		
        if( discountType !== "Choose..." && discountType !== "" && discountName != "" ){
	        	  $.ajax({
	                  url: 'admin_promotion_add',
	                  type: 'post',
	                  async: false,
	                  data: {
	                	  discountType: discountType, 
	                	  discountName: discountName,
	                	  dateFrom: dateFrom,
	                	  dateTo: dateTo,
	                	  state: state
	                  },
	                  success: function(result){
	                     /* if(res == 'General customer'||res == 'VIP customer'){
	                      	alert("Login successful!");
	                      	location.reload();
	                      }else if(res == 'admin'||res == 'manager'){
	                      	location.href = './BackEndIndex.jsp';
	                      
	                      */
	                      console.log("success!!!")
	                      if("success" == result){
	                              
	                    	  location.reload();
	                         
	                      }else{                    	
	                      	$("#login_password").val('');
	                      	$('#login_notice').html('Invalid email or password!');
	                      	$("#login_notice").css("color", "#ed2553");
	                      }
	                   }
	              })
	        } 
	       	return false;
	    });
});

</script>


<%@include file="../include/admin/adminFooter.jsp"%>
