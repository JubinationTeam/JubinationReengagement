<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<center>
    
        <h2><a href="${pageContext.request.contextPath}/admin" >
                <img   src="${pageContext.request.contextPath}/resources/css/images/logo.png"  alt="Jubination">
                             HRMS</a></h2>

        <h3>${admin.name}, ${admin.work}</h3>
        <br/>
        <br/>
        
        <a class="btn btn-small btn-default" type="button" href="${pageContext.request.contextPath}/admin" >Home &nbsp; <span class="glyphicon glyphicon-home">&nbsp;</span> </a>
       <a class="btn btn-small btn-default" type="button" href="${pageContext.request.contextPath}/admin/mail" >Mail &nbsp; <span class="glyphicon glyphicon-envelope">&nbsp;</span></a>
         <c:if test="${admin.power<3||admin.work=='HR'}">  
            <a class="btn btn-small btn-default" type="button" href="${pageContext.request.contextPath}/admin/hr" >Employees &nbsp; <span class="glyphicon glyphicon-user">&nbsp;</span></a>
         <%--   <a class="btn btn-small btn-default" href="${pageContext.request.contextPath}/admin/career" >Careers &nbsp; <span class="glyphicon glyphicon-briefcase">&nbsp;</span></a>
         --%>
         </c:if>  
        <a class="btn btn-small btn-default" href="${pageContext.request.contextPath}/admin/callinterface" >Call Interface &nbsp; <span class="glyphicon glyphicon-phone">&nbsp;</span></a>
        <a class="btn btn-small btn-default" href="${pageContext.request.contextPath}/admin/callrecords" >Call Records &nbsp; <span class="glyphicon glyphicon-phone-alt">&nbsp;</span></a>
      
        <a class="btn btn-small btn-default" href="${pageContext.request.contextPath}/admin/settings" >Settings &nbsp; <span class="glyphicon glyphicon-cog">&nbsp;</span></a>
      
        <a class="btn btn-small btn-danger" href="${pageContext.request.contextPath}/logout" >Log out &nbsp; <span class="glyphicon glyphicon-log-out">&nbsp;</span></a>


</center>