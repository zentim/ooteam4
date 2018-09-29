<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 

     
<div style="max-width: 1013px; margin: 10px auto;">
	<div class="btn-group" role="group" aria-label="Basic example">
	  <a orderStatus="all" href="#nowhere">
		  <button type="button" class="btn btn-secondary">
		  	所有訂單
		  </button>
	  </a>
	  <a orderStatus="waitPay" href="#nowhere">
		  <button type="button" class="btn btn-secondary">
		  	待付款
		  </button>
	  </a>
	  <a orderStatus="waitDelivery" href="#nowhere">
		  <button type="button" class="btn btn-secondary">
		  	待發貨
		  </button>
	  </a>
	</div>
    
    <div style="clear:both"></div>
    
    <div>
        <table style="border: 1px solid #E8E8E8; width: 100%; margin: 20px 0px; background-color: #F5F5F5; text-align: center;">
            <tr>
                <td>物品</td>
                <td width="100px">單價</td>
                <td width="100px">數量</td>
                <td width="120px">實付款</td>
                <td width="100px">交易操作</td>
            </tr>
        </table>
     
    </div>
     
    <div class="orderListItem">
        <c:forEach items="${os}" var="o">
            <table class="orderListItemTable table table-striped table-bordered table-sm" orderStatus="${o.status}" oid="${o.id}" style="border: 2px solid #ECECEC; width: 100%; margin: 20px 0px;">
                <tr style="background-color: #F1F1F1; font-size:12px;">
                    <td colspan="2">
                    <b><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></b> 
                    <span>訂單號: ${o.orderCode} 
                    </span>
                    </td>
                    <td  colspan="2"></td>
                    <td colspan="1">
                        <a class="wangwanglink" href="#nowhere">
                            <div class="orderItemWangWangGif"></div>
                        </a>
                         
                    </td>
                    <td class="orderItemDeleteTD">
                        <a class="deleteOrderLink" oid="${o.id}" href="#nowhere">
                            <span  class="orderListItemDelete glyphicon glyphicon-trash"></span>
                        </a>
                         
                    </td>
                </tr>
                
                <c:forEach items="${o.orderItems}" var="oi" varStatus="st">
                    <tr class="orderItemProductInfoPartTR" >
                        <td>
                        	<img 
                        		width="80" 
                        		height="80" 
                        		src="img/productSingle_middle/${ oi.product.firstProductImage.id }.jpg">
                        </td>
                        <td>
                            <div>
                                <a href="foreproduct?pid=${ oi.product.id }">${ oi.product.name }</a>
                            </div>
                        </td>
                        <td width="100px">
                            <div style="color: #999; font-size: 14px">
                            	$<fmt:formatNumber type="number" value="${oi.product.originalPrice}" minFractionDigits="2"/>
                            </div>
                            <div style="color: #3C3C3C; font-size: 14px">
                            	$<fmt:formatNumber type="number" value="${oi.product.promotePrice}" minFractionDigits="2"/>
                            </div>
                        </td>
                        <c:if test="${st.count==1}">
                          
                            <td valign="top" rowspan="${fn:length(o.orderItems)}" width="100px">
                                <span class="orderListItemNumber">${o.totalNumber}</span>
                            </td>
                            <td valign="top" rowspan="${fn:length(o.orderItems)}" width="120px">
                                <div class="orderListItemProductRealPrice">
                                	$<fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${o.total}"/>
                                </div>
                                <div class="orderListItemPriceWithTransport">
                                	(含運費 : $0.00)
                                </div>
                            </td>
                            <td valign="top" rowspan="${fn:length(o.orderItems)}" width="100px">
                                <c:if test="${o.status=='waitPay' }">
                                    <a href="alipay.jsp?oid=${ o.id }&total=${ o.total }">
                                        <button class="orderListItemConfirm">付款</button>
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
    $("a[orderStatus]").click(function(){
        var orderStatus = $(this).attr("orderStatus");
        if('all'==orderStatus){
            $("table[orderStatus]").show(); 
        }
        else{
            $("table[orderStatus]").hide();
            $("table[orderStatus="+orderStatus+"]").show();         
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
            var page="foredeleteOrder";
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
     
    $(".ask2delivery").click(function(){
        var link = $(this).attr("link");
        $(this).hide();
        page = link;
        $.ajax({
               url: page,
               success: function(result){
                alert("卖家已秒发，刷新当前页面，即可进行确认收货")
               }
            });
         
    });
});
 
</script>