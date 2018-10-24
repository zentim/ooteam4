<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>


<div class="col-sm-2 sidenav" style="padding:10px 0px 10px 0px;">
    	
    <!-- show segment -->
    <div class="accordion" id="accordionExample">
    
      <c:forEach items="${ segments }" var="segment" varStatus="stSegment">
      
		  <div class="card">
		    <div class="card-header" id="heading${ segment.id }">
		      <h5 class="mb-0">
		        <button 
		        	class="btn btn-link" 
		        	style="font-size: 24px; font-weight: bold;"
		        	type="button" 
		        	data-toggle="collapse" 
		        	data-target="#collapse${ segment.id }" 
		        	aria-expanded="true" 
		        	aria-controls="collapse${ segment.id }"
		        	onclick="toggleCategory(this)">
		          ${ segment.name }
		        </button>
		      </h5>
		    </div>
		
		    <div id="collapse${ segment.id }" class="collapse" aria-labelledby="heading${ segment.id }" data-parent="#accordionExample">
		      <div class="card-body">
		        
		        <!-- show category -->
		        <ul class="nav navbar-nav">
		        
			    	<c:forEach items="${ segment.categorys }" var="c" varStatus="st">
						<li><a href="#${ c.id }">${ c.name } <span class="badge">${ c.brands.size() }</span></a></li>
			    	</c:forEach>
			    	
			    </ul>
		        
		      </div>
		    </div>
		  </div>
		  
	  </c:forEach>
	  
	</div>
</div>


<script type="text/javascript">
function toggleCategory(item) {
    // alert($(item).parent().parent().parent().find("ul").attr('id'));
	var display = $(item).parent().parent().parent().find("ul").parent().parent().css("display");
	var isDisplay = $(item).parent().parent().parent().find("ul").parent().parent().hasClass("in");
	if (isDisplay) {
		$(item).parent().parent().parent().find("ul").css("display", "none");
	} else {
		$(item).parent().parent().parent().find("ul").css("display", "block");
	}
}

$(function(){

});
</script>





