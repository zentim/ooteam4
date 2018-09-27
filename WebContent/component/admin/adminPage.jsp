<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<nav class="Page navigation example">
  <ul class="pagination">
    <li class="page-item <c:if test="${!page.hasPrevious}">disabled</c:if>">
      <a  href="?page.start=0${page.param}" aria-label="Previous" class="page-link">
        <span aria-hidden="true">«</span>
      </a>
    </li>
 
    <li class="page-item <c:if test="${!page.hasPrevious}">disabled</c:if>"">
      <a  href="?page.start=${page.start-page.count}${page.param}" aria-label="Previous" class="page-link">
        <span aria-hidden="true">‹</span>
      </a>
    </li>    
 
    <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
        <c:if test="${status.count*page.count-page.start<=20 && status.count*page.count-page.start>=-10}">
            <li class="page-item <c:if test="${status.index*page.count==page.start}">active</c:if>">
                <a href="?page.start=${status.index*page.count}${page.param}"
                   class="page-link">
                   ${status.count}
                   <c:if test="${status.index*page.count==page.start}">
                     <span class="sr-only">(current)</span>
                   </c:if>
                </a>
            </li>
        </c:if>
    </c:forEach>
 
    <li class="page-item <c:if test="${!page.hasNext}">disabled</c:if>">
      <a href="?page.start=${page.start+page.count}${page.param}" aria-label="Next" class="page-link">
        <span aria-hidden="true">›</span>
      </a>
    </li>
    
    <li class="page-item <c:if test="${!page.hasNext}">disabled</c:if>">
      <a href="?page.start=${page.last}${page.param}" aria-label="Next" class="page-link">
        <span aria-hidden="true">»</span>
      </a>
    </li>
  </ul>
</nav>


<script>
$(function(){
    $("ul.pagination li.disabled a").click(function(){
        return false;
    });
});
</script>