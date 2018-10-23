<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>


<div class="col-sm-2 sidenav" style="padding:10px 0px 10px 0px;">

    <ul class="nav navbar-nav" >
    	<c:forEach items="${ cs }" var="c" varStatus="stc">
			<li><a href="#${ c.id }">${ c.name } <span class="badge">${ c.products.size() }</span></a></li>
    	</c:forEach>
    </ul>
</div>
