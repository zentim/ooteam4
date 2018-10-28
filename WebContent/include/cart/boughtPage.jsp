<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 

     
<div style="max-width: 1013px; margin: 130px auto;">
	<div class="btn-group mt-4" role="group" aria-label="Basic example">
	  <a orderState="all" href="#nowhere">
		  <button type="button" class="btn btn-secondary">
		  	All Orders
		  </button>
	  </a>
	  <a orderState="waitPay" href="#nowhere">
		  <button type="button" class="btn btn-secondary">
		  	Wait Pay
		  </button>
	  </a>
	  <a orderState="waitDelivery" href="#nowhere">
		  <button type="button" class="btn btn-secondary">
		  	Wait Delivery
		  </button>
	  </a>
	</div>
    
    <div style="clear:both"></div>
    
    <div>
        <table style="border: 1px solid #E8E8E8; width: 100%; margin: 20px 0px; background-color: #F5F5F5; text-align: center;">
            <tr>
                <td width="680px">Object</td>
                <td width="100px">Price</td>
                <td width="100px">All Qty</td>
                <td width="120px">Payment</td>
                <td width="100px">Operation</td>
            </tr>
        </table>
     
    </div>
     
    <div class="orderListItem">
        <c:forEach items="${os}" var="o">
            <table class="orderListItemTable table table-striped table-bordered table-sm" orderState="${o.state}" oid="${o.id}" style="border: 2px solid #ECECEC; width: 100%; margin: 20px 0px;">
                <tr style="background-color: #F1F1F1; font-size:12px;">
                    <td colspan="2" align="left">
	                    <b><fmt:formatDate value="${o.dateOrdered}" pattern="yyyy-MM-dd HH:mm:ss"/></b> 
	                    <span>Order ID: ${o.id} 
	                    </span>
                    </td>
                    <td  colspan="2"></td>
                    <td colspan="1">
                        <a class="wangwanglink" href="#nowhere">
                            <div class="orderItemWangWangGif"></div>
                        </a>
                         
                    </td>
                    <td class="orderItemDeleteTD" style="text-align: center;">
                        <a class="deleteOrderLink" oid="${o.id}" href="#nowhere">
                            <i class="fas fa-trash-alt fa-lg orderListItemDelete"></i>
                        </a>
                         
                    </td>
                </tr>
                
                <c:forEach items="${o.orderItems}" var="oi" varStatus="st">
                    <tr class="orderItemProductInfoPartTR" >
                        <td width="80px">
                        	<img 
                        		width="80" 
                        		height="80" 
                        		src="img/productSingle/${ oi.product.firstProductImage.id }.jpg">
                        </td>
                        <td width="600px">
                            
                            <a href="foreproduct?pid=${ oi.product.id }">${ oi.product.name }</a>
                            
                        </td>
                        <td width="100px" style="text-align: right;">
                            <div style="color: #3C3C3C; font-size: 14px">
                            	$<fmt:formatNumber type="number" value="${oi.product.price}" minFractionDigits="1"/>
                            </div>
                        </td>
                        <c:if test="${st.count == 1}">
                          
                            <td 
                            	valign="center" 
                            	rowspan="${fn:length(o.orderItems)}" 
                            	width="100px"
                            	style="text-align: center;">
                                <span class="orderListItemNumber">${ o.totalQuantity }</span>
                            </td>
                            <td 
                            	valign="center" 
                            	rowspan="${fn:length(o.orderItems)}" 
                            	width="120px"
                            	style="text-align: right;">
                                <div class="orderListItemProductRealPrice">
                                	$<fmt:formatNumber  minFractionDigits="1"  maxFractionDigits="2" type="number" value="${o.total}"/>
                                </div>
                            </td>
                            <td 
                            	valign="top" 
                            	rowspan="${fn:length(o.orderItems)}" 
                            	width="100px"
                            	style="text-align: center;">
                                <c:if test="${o.state == 'waitPay' }">
                                    <a href="pay.jsp?oid=${ o.id }&total=${ o.total }">
                                        <button class="btn btn-primary orderListItemConfirm">Pay</button>
                                    </a>                              
                                </c:if>
                                 
                            </td>                     
                        </c:if>
                    </tr>
                </c:forEach>      
                 
            </table>
        </c:forEach>
         
    </div>
     
</div>




<script>
var deleteOrder = false;
var deleteOrderid = 0;
 
$(function(){
    $("a[orderState]").click(function(){
        var orderState = $(this).attr("orderState");
        if('all'==orderState){
            $("table[orderState]").show(); 
        }
        else{
            $("table[orderState]").hide();
            $("table[orderState="+orderState+"]").show();         
        }
    });
     
    $("a.deleteOrderLink").click(function(){
        deleteOrderid = $(this).attr("oid");
        deleteOrder = false;
        $("#deleteConfirmModal").modal("show");
    });
     
    $("button.deleteConfirmButton").click(function(){
        deleteOrder = true;
        $("#deleteConfirmModal").modal('hide');
    }); 
     
    $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
        if(deleteOrder){
            var page = "foredeleteOrder";
            $.post(
                    page,
                    {"oid":deleteOrderid},
                    function(result){
                        if("success"==result){
                            $("table.orderListItemTable[oid="+deleteOrderid+"]").hide();
                        }
                        else{
                            location.href="login.jsp";
                        }
                    }
                );
             
        }
    })      
});
 
</script>