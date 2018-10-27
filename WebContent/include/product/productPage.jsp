<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    


    <!-- ##### Single Product Details Area Start ##### -->
    <section class="single_product_details_area d-flex align-items-center">

        <!-- Single Product Thumb -->
        <div class="single_product_thumb clearfix">
            <div class="product_thumbnail_slides owl-carousel">
            	<div style="height: 650px; display: flex; justify-content: center; align-items: center;">
            		<img src="img/productSingle/${p.firstProductImage.id}.jpg" alt="productId ${p.firstProductImage.id}" style="width: 100%; height: auto; max-width: 100%; max-height: 100%; vertical-align: middle;">
            	</div>
            	<div style="height: 650px; display: flex; justify-content: center; align-items: center;">
            		<img src="img/productSingle/${p.firstProductImage.id}.jpg" alt="productId ${p.firstProductImage.id}" style="width: 100%; height: auto; max-width: 100%; max-height: 100%; vertical-align: middle;">
            	</div>
            	<div style="height: 650px; display: flex; justify-content: center; align-items: center;">
            		<img src="img/productSingle/${p.firstProductImage.id}.jpg" alt="productId ${p.firstProductImage.id}" style="width: 100%; height: auto; max-width: 100%; max-height: 100%; vertical-align: middle;">
            	</div>
            </div>
        </div>

        <!-- Single Product Description -->
        <div class="single_product_desc clearfix">
               
			<c:if test="${!empty p.promotionName }">
            	<span style="color: red;">${ p.promotionName }</span>
            </c:if>
            
            <a href="cart.html">
                <h2>${ p.name }</h2>
            </a>
            <p class="product-price">
<!--             
            	<span class="old-price">$65.00</span>
-->            	 
            	$${ p.price }
            </p>
            
            

            <!-- Form -->
            <form class="cart-form clearfix" method="post">
                <!-- Select Box -->
<!--                 
                <div class="select-box d-flex mt-50 mb-30">
                    <select name="select" id="productSize" class="mr-5">
                        <option value="value">Size: XL</option>
                        <option value="value">Size: X</option>
                        <option value="value">Size: M</option>
                        <option value="value">Size: S</option>
                    </select>
                    <select name="select" id="productColor">
                        <option value="value">Color: Black</option>
                        <option value="value">Color: White</option>
                        <option value="value">Color: Red</option>
                        <option value="value">Color: Purple</option>
                    </select>
                </div>
 -->                
                <!-- Cart & Favourite Box -->
<!--                 
                <div class="cart-fav-box d-flex align-items-center">
-->                
                    <!-- Cart -->
<!--                    
                    <button type="submit" name="addtocart" value="5" class="btn essence-btn">Add to cart</button>
-->                    
                    <!-- Favourite -->
<!--                    
                    <div class="product-favourite ml-4">
                        <a href="#" class="favme fa fa-heart"></a>
                    </div>
                </div>
-->
                
                <c:if test="${ p.inventory > 0 }">
					<p>Inventory: ${p.inventory} </p>
					
					Qty:
					<div class="form-group" style="width:30%">
						<input class="form-control productNumberSetting" value="1" type="number" name="quantity" min="1" max="${p.inventory}"></br>
					</div>
					
					
					
					<a href="#nowhere" class="addCartLink">
						<button class="btn btn-primary col-sm-9 addCartButton" type="button" id="addToCart">
							<i class="fa fa-shopping-cart"></i> Add To Cart
						</button>
					</a>
				</c:if>
				
				<c:if test="${ p.inventory == 0 }">
					<p>Out of Stock</p>
					
					<button onclick="subscribe(${p.id})" class="btn btn-warning col-sm-9" type="button" id="subscribe_btn">
						Notify me when product is available
					</button>
				</c:if>
                
            </form>
        </div>
    </section>
    <!-- ##### Single Product Details Area End ##### -->
    

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
