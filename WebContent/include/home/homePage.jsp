<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>
 
<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>

                   
<div class="col-sm-8 text-left" style="padding:10px">  
	  
	<c:forEach items="${ cs }" var="c" varStatus="stc">
		<c:if test="${ stc.count <= categorycount }">
		
			<div style="float: left; width: 100%;">
				<a href="forecategory?cid=${ c.id }">
					<h2 id="${ c.id }">${ c.name }</h2>
				</a>
			
				<c:forEach items="${ c.products }" var="p" varStatus="st">
					<c:if test="${ st.count <= 8 }">
						
						<div class="w3-col s6 l3">
							<a href="foreproduct?pid=${p.id}">
							    <div class="w3-container" style="border: 1px solid black; margin: 5px;">
							        <div class="w3-display-container" >
							        	
							        	<div style="position:relative; width:170px; height: 225px;">
								        	<img style="position:absolute; max-width: 100%; max-height: 100%; top:0; bottom: 0; left: 0; right: 0; margin: auto; " src="img/productSingle/${p.firstProductImage.id}.jpg">
							        	</div>
							        	
							        	<c:if test="${!empty p.promotionName }">
							            <span class="w3-tag w3-display-topleft">${ p.promotionName } - ${ p.discountTypeName }</span>
							            </c:if>
							            
							            <!-- 
							            <div class="w3-display-middle w3-display-hover">
							                <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button>
							            </div>
							             -->
							            
							        </div>
							        <p style="text-align: right;">${fn:substring(p.name, 0, 20)}<br>
							        	<b>$<fmt:formatNumber type="number" value="${p.price}" minFractionDigits="2"/></b>
							        </p>
							    </div>
						    </a>
						</div>
						
					</c:if>
				</c:forEach>
			</div>
			<div style="clear: both;"></div>
		</c:if>
	</c:forEach>
    
</div>           
            