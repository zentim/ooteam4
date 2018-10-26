<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
 	
<div class="col-sm-4" style="text-align:left;">
    <div style="position:relative; width:225px; height: 225px; margin: auto;">
      	<img style="position:absolute; max-width: 100%; max-height: 100%; top:0; bottom: 0; left: 0; right: 0; margin: auto; " src="img/productSingle/${p.firstProductImage.id}.jpg">
    </div>

	<h1>${p.name}</h1>
	<c:if test="${!empty p.promotionName }">
     	<span class="w3-tag w3-display-topleft">${ p.promotionName } - ${ p.discountTypeName }</span>
    </c:if>
    
    <p>
    	<Span><i class="glyphicon glyphicon-info-sign"></i> Product Information</Span>
    </p>
    
	<h3>Price: $<fmt:formatNumber type="number" value="${p.price}" minFractionDigits="2"/></h3>
	
    <c:if test="${ p.inventory > 0 }">
		<p>Inventory ${p.inventory} </p>
		
		Qty:
		<div class="form-group" style="width:30%">
			<input class="form-control productNumberSetting" value="1" type="number" name="quantity" min="1" max="${p.inventory}"></br>
		</div>
		
		
		
		<a href="#nowhere" class="addCartLink">
			<button class="btn btn-warning col-sm-9 addCartButton" type="button" id="addToCart">
				<i class="fa fa-shopping-cart"></i> Add To Cart
			</button>
		</a>
	</c:if>
	
	<c:if test="${ p.inventory == 0 }">
		<p>Out of Stock</p>
		
		<button onclick="subscribe(${p.id})" class="btn btn-primary col-sm-9" type="button" id="subscribe_btn">
			Notify me when product is available
		</button>
	</c:if>
	
	
	
</div> 

 
<script>
$(function(){
    var inventory = ${p.inventory};
    
    $(".productNumberSetting").keyup(function(){
        var num= $(".productNumberSetting").val();
        num = parseInt(num);
        if(isNaN(num))
            num= 1;
        if(num<=0)
            num = 1;
        if(num>inventory)
            num = inventory;
        $(".productNumberSetting").val(num);
    });
     
     
    $(".addCartLink").click(function(){
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    if("success" == result){
                        var pid = ${p.id};
                        var num = $(".productNumberSetting").val();
                        var addCartpage = "foreaddCart";
                        $.get(
                                addCartpage,
                                {"pid":pid, "num":num},
                                function(result){
                                    if("success" == result){
                                        $(".addCartButton").html("Already added to shopping cart");
                                        $(".addCartButton").attr("disabled","disabled");
                                        $(".addCartButton").css("background-color","lightgray")
                                        $(".addCartButton").css("border-color","lightgray")
                                        $(".addCartButton").css("color","black")
                                         
                                    }
                                    else{
                                         
                                    }
                                }
                        );                          
                    }
                    else{       
                        document.getElementById('myModal').style.display = "block";
	              		  $('.register-div').css("display","none");
	              		  $('.login-div').css("display","block");
                    }
                }
        );      
        return false;
    });
    $(".buyLink").click(function(){
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    if("success"==result){
                        var num = $(".productNumberSetting").val();
                        location.href= $(".buyLink").attr("href")+"&num="+num;
                    }
                    else{
                        $("#loginModal").modal('show');                     
                    }
                }
        );      
        return false;
    });
     
});

function subscribe(pid){
	 var page = "forecreatesubscribe";
	 var returnpage = window.location.href.split("/").pop();
	 console.log(returnpage);
	 $.get(
           page,
           {"pid":pid, "returnpage":returnpage},
           function(result){
               if("success" == result){
              	 $("#subscribe_btn").html("Subscribed");
                   $("#subscribe_btn").attr("disabled","disabled");
              
               }else{
              	 alert("Please try again, the subscription failed!");
               }
           });
}
 
</script>

