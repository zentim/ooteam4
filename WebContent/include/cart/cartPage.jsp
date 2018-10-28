<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div style="width: 80%; margin: 130px auto;">

<h2 style="padding-top: 20px;">Shopping Cart</h2>

<div style="text-align: right; font-size: 30px;">
	<span id="undoBtn" class="fa fa-undo fa-icon" style="cursor: pointer;"></span>
</div>

<div class="table-responsive">

  <table class="table table-bordered table-sm">

    <thead>
      <tr>

        <th class="selectAndImage" style="width: 100px;">
          <img 
            selectit="false" 
            class="selectAllItem" 
            src="img/site/cartNotSelected.png" 
            style="cursor: pointer"> Select All
        </th>
        <th style="text-align: center">Product Image</th>
        <th style="text-align: center">Product Name</th>
        <th style="text-align: center">Price</th>
        <th style="text-align: center">Qty</th>
        <th style="text-align: center">Subtotal</th>
        <th style="text-align: center">Operation</th>

      </tr>
    </thead>

    <tbody>
      <c:forEach items="${ ois }" var="oi">
      	<c:if test="${ oi.state == 1 }">
      
        <tr oiid="${oi.id}" class="cartProductItemTR">

            <td align="left" style="width: 100px;">
            
                <c:if test="${ oi.quantity > 0 }">
	                <img 
	                  selectit="false" 
	                  oiid="${oi.id}" 
	                  class="cartProductItemIfSelected" 
	                  src="img/site/cartNotSelected.png" 
	                  style="cursor: pointer; display: block;">
                </c:if>
                
                <c:if test="${ oi.quantity <= 0 }">
                    <img 
                      selectit="false" 
                      oiid="${oi.id}" 
                      class="cartProductItemIfSelected" 
                      src="img/site/cartNotSelected.png" 
                      style="cursor: pointer; display: none;">
                </c:if>
                  
                <a style="display: none" href="#nowhere">
                  <img src="img/site/cartSelected.png" style="cursor: pointer">
                </a>
            </td>

            <td align="center">
              <div style="position:relative; width: 80px; height: 80px;">
                <img 
                  class="cartProductImg"  
                  src="img/productSingle/${oi.product.firstProductImage.id}.jpg" 
                  style="position:absolute; max-width: 100%; max-height: 100%; top:0; bottom: 0; left: 0; right: 0; margin: auto; ">
              </div>
            </td>

            <td align="center">
              <div class="cartProductLinkOutDiv">
                <a 
                  href="foreproduct?pid=${oi.product.id}" 
                  class="cartProductLink">${oi.product.name}</a>
              </div>
            </td>

            <td align="right">
              <span  
                class="cartProductItemPromotionPrice" 
                style="color:#c40000">
                $${oi.product.price}
              </span>
            </td>

            <td align="center">
              <div 
                class="cartProductChangeNumberDiv" 
                style="border: solid 1px #E5E5E5; width: 120px; font-size: 18px; text-align: center;">
                  <span 
                    class="hidden orderItemStock " 
                    pid="${ oi.product.id }">
                    ${ oi.product.inventory }
                  </span>
                  <span 
                    class="hidden orderItemPromotePrice" 
                    pid="${oi.product.id}">
                    ${ oi.product.price }
                  </span>
                  <a  
                    pid="${ oi.product.id }" 
                    oiid="${ oi.id }" 
                    class="numberMinus" 
                    href="#nowhere" 
                    style="text-decoration: none; font-size: 18px; padding: 8px;">-</a>
                  <input 
                    pid="${ oi.product.id }" 
                    oiid="${ oi.id }" 
                    class="orderItemNumberSetting" 
                    autocomplete="off" 
                    value="${ oi.quantity }" 
                    style="border: solid 1px #AAAAAA; width: 45px; display: inline-block; text-align: center">
                  <a  
                    inventory="${ oi.product.inventory }" 
                    pid="${ oi.product.id }" 
                    oiid="${ oi.id }" 
                    class="numberPlus" 
                    href="#nowhere" 
                    style="text-decoration: none ;font-size: 18px; padding: 8px;">+</a>
              </div>
            </td>

            <td align="right">
              <span 
                class="cartProductItemSmallSumPrice" 
                oiid="${ oi.id }" 
                pid="${ oi.product.id }">
                $<fmt:formatNumber type="number" 
                  value="${ oi.product.price * oi.quantity}" 
                  minFractionDigits="2"/>
              </span>
            </td>

            <td align="center">
              <a 
                class="deleteOrderItem" 
                oiid="${ oi.id }"  
                href="#nowhere">Delete</a>
            </td>
        </tr>
        
        </c:if>
      </c:forEach>
    </tbody>

  </table>
</div>


<div 
  class="cartFoot" 
  style="height: 50px; line-height: 50px; margin: 20px 0px; color: black; padding-left: 20px;">

    <div class="pull-right" style="font-size: 20px;">
        <span>Selected <span class="cartSumNumber" style="color: red;">0</span> Item, </span>
        <span>Total : </span>
        <span class="cartSumPrice" style="color: red; padding: 0 10px;">$0.00</span>
        <button 
        	type="submit" 
        	class="btn btn-lg createOrderButton" 
        	style="font-weight: bold; background-color: rgb(170, 170, 170); color: white;"
        	disabled="disabled">Check Out</button>
    </div>

</div>


</div>


<script>
var deleteOrderItem = false;
var deleteOrderItemid = 0;

$(function(){
	$("span#undoBtn").click(function() {
		$.post(
                "foreundoCart",
                function(result){
                    if("success" == result){
                    	location.href="forecart";
                    }
                    else{
                    	console.log("undo cart fail!");
                    }
                }
            );
	});

    $("a.deleteOrderItem").click(function(){
        deleteOrderItem = false;
        var oiid = $(this).attr("oiid");
        deleteOrderItemid = oiid;
        $("#deleteConfirmModal").modal('show');
    });

    $("button.deleteConfirmButton").click(function(){
        deleteOrderItem = true;
        $("#deleteConfirmModal").modal('hide');
    });

    $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
        if(deleteOrderItem){
            var page="foredeleteOrderItem";
            $.post(
                    page,
                    {"oiid":deleteOrderItemid},
                    function(result){
                        if("success" == result){
                            $("tr.cartProductItemTR[oiid="+deleteOrderItemid+"]").hide();
                        }
                        else{
                            location.href="login.jsp";
                        }
                    }
                );
        }
    })

    $("img.cartProductItemIfSelected").click(function(){
        var selectit = $(this).attr("selectit")
        if("selectit"==selectit){
            $(this).attr("src","img/site/cartNotSelected.png");
            $(this).attr("selectit","false")
            $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
        }
        else{
            $(this).attr("src","img/site/cartSelected.png");
            $(this).attr("selectit","selectit")
            $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
        }
        syncSelect();
        syncCreateOrderButton();
        calcCartSumPriceAndNumber();
    });

    $("img.selectAllItem").click(function(){
        var selectit = $(this).attr("selectit")
        if("selectit"==selectit){
            $("img.selectAllItem").attr("src","img/site/cartNotSelected.png");
            $("img.selectAllItem").attr("selectit","false")
            $(".cartProductItemIfSelected").each(function(){
                $(this).attr("src","img/site/cartNotSelected.png");
                $(this).attr("selectit","false");
                $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
            });
        }
        else{
            $("img.selectAllItem").attr("src","img/site/cartSelected.png");
            $("img.selectAllItem").attr("selectit","selectit")
            $(".cartProductItemIfSelected").each(function(){
                $(this).attr("src","img/site/cartSelected.png");
                $(this).attr("selectit","selectit");
                $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
            });
        }
        
        syncCreateOrderButton();
        calcCartSumPriceAndNumber();
    });

    $(".orderItemNumberSetting").keyup(function(){
        var pid = parseInt($(this).attr("pid"));
        var oiid = parseInt($(this).attr("oiid"));
        var inventory = parseInt($("span.orderItemStock[pid="+pid+"]").text());
        var price = parseFloat($("span.orderItemPromotePrice[pid="+pid+"]").text());
        var num = parseInt($(".orderItemNumberSetting[pid=" + pid + "]").val());
        
        num = parseInt(num);
        if(isNaN(num)){
        	num = 1;
        }
            
        if(num <= 0){
        	num = 1;
        }
            
        if(num > inventory){
        	num = inventory;
        }
    
        syncPrice(pid, num, price, oiid);
    });

    $(".numberPlus").click(function(){
        var pid = parseInt($(this).attr("pid"));
        var oiid = parseInt($(this).attr("oiid"));
        var inventory = parseInt($("span.orderItemStock[pid="+pid+"]").text());
        var price = parseFloat($("span.orderItemPromotePrice[pid="+pid+"]").text());
        var num = parseInt($(".orderItemNumberSetting[pid="+pid+"]").val());
        
        pid = parseInt(pid);
        oiid = parseInt(oiid);
        inventory = parseInt(inventory);
        price = parseFloat(price);
     	num = parseInt(num);
        
        num++;
        if(num > inventory){
        	num = inventory;
        }
            
        syncPrice(pid, num, price, oiid);
    });
    $(".numberMinus").click(function(){
        var pid = parseInt($(this).attr("pid"));
        var inventory = parseInt($("span.orderItemStock[pid="+pid+"]").text());
        var price= parseFloat($("span.orderItemPromotePrice[pid="+pid+"]").text());
        var oiid = parseInt($(this).attr("oiid"));
        var num = parseInt($(".orderItemNumberSetting[pid="+pid+"]").val());
        
        pid = parseInt(pid);
        oiid = parseInt(oiid);
        inventory = parseInt(inventory);
        price = parseFloat(price);
     	num = parseInt(num);
        
        --num;
        if(num <= 0) {
        	num = 0;
        }
            
        syncPrice(pid, num, price, oiid);
    });

    $("button.createOrderButton").click(function(){
        var params = "";
        $(".cartProductItemIfSelected").each(function(){
            if("selectit" == $(this).attr("selectit")){
                var oiid = $(this).attr("oiid");
                params += "&oiid="+oiid;
            }
        });
        params = params.substring(1);
        location.href = "forebuy?" + params;
    });

})

function syncCreateOrderButton(){
    var selectAny = false;
    $(".cartProductItemIfSelected").each(function(){
        if("selectit"==$(this).attr("selectit")){
            selectAny = true;
        }
    });

    if(selectAny){
        $("button.createOrderButton").css("background-color","#C40000");
        $("button.createOrderButton").removeAttr("disabled");
    }
    else{
        $("button.createOrderButton").css("background-color","#AAAAAA");
        $("button.createOrderButton").attr("disabled","disabled");
    }

}
function syncSelect(){
    var selectAll = true;
    $(".cartProductItemIfSelected").each(function(){
        if("false" == $(this).attr("selectit")){
            selectAll = false;
        }
    });

    if(selectAll)
        $("img.selectAllItem").attr("src","img/site/cartSelected.png");
    else
        $("img.selectAllItem").attr("src","img/site/cartNotSelected.png");

}

function calcCartSumPriceAndNumber(){
    var sum = 0;
    var totalNumber = 0;
    
	    $("img.cartProductItemIfSelected[selectit='selectit']").each(
		      function (){
		        var oiid = $(this).attr("oiid");
		        var num = $(".orderItemNumberSetting[oiid=" + oiid + "]").val();
		        
		        if (parseInt(num) === 0) {
		            var pid = $(".orderItemNumberSetting[oiid=" + oiid + "]").attr("pid")
		            verifySelected(pid);
		        }
		      }
	      );
	    
	    
	
	    $("img.cartProductItemIfSelected[selectit='selectit']").each(
   	      function (){
   	        var oiid = $(this).attr("oiid");
   	        var price = $(".cartProductItemSmallSumPrice[oiid=" + oiid + "]").text();
   	        price = price.replace(/,/g, "");    
   	        price = price.replace(/\\$/g, "");
   	        sum += new Number(price);
   	        
   	        var num = $(".orderItemNumberSetting[oiid=" + oiid + "]").val();
   	        totalNumber += new Number(num);
   	      }
   	    );

    $("span.cartSumPrice").html("$" + formatMoney(sum));
    $("span.cartSumNumber").html(totalNumber);
}

function syncPrice(pid, num, price, oiid){
    $(".orderItemNumberSetting[pid=" + pid + "]").val(num);
    var cartProductItemSmallSumPrice = formatMoney(num * price);
    $(".cartProductItemSmallSumPrice[pid=" + pid + "]").html("$" + cartProductItemSmallSumPrice);
    calcCartSumPriceAndNumber();
    
    verifySelected(pid);
    
    var page = "forechangeOrderItem";
 	pid = parseInt(pid);
 	num = parseInt(num);
 	price = parseFloat(price);
 	oiid = parseInt(oiid);
    $.post(
        page,
        {"pid": pid, "num": num, "oiid": oiid, "state": 1},
        function(result){
          if("success" != result){
            location.href="login.jsp";
          }
        }
      );
}

function verifySelected(pid) {
	
	var num = parseInt($(".orderItemNumberSetting[pid="+pid+"]").val());
	
	if (num === 0) {
		$(".orderItemNumberSetting[pid="+pid+"]")
	       .parents("tr.cartProductItemTR")
	       .find(".cartProductItemIfSelected")
	       .css("display", "none");
	} else {
		$(".orderItemNumberSetting[pid="+pid+"]")
        .parents("tr.cartProductItemTR")
        .find(".cartProductItemIfSelected")
        .css("display", "block");
	}
	
	$(".cartProductItemIfSelected").each(function(){
        if (parseInt($(this).parents("tr.cartProductItemTR").find(".orderItemNumberSetting").val()) === 0) {
            $(this).css("display", "none");
            $(this).attr("src","img/site/cartNotSelected.png");
            $(this).attr("selectit","false");
            $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
        }
    });
	
	syncCreateOrderButton();
}

</script>
