<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="description" content="Health Packages at affordable rates"/>
        <meta name="author" content="Souvik Das"/>
        <title>Jubination - Health Packages at affordable rates</title>
        <link href="<c:url value="${pageContext.request.contextPath}/resources/css/images/logo.png" />" rel="shortcut icon">
        <link type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.10.4.css" rel="stylesheet">
        <link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" rel="stylesheet">            
        <link type="text/css" href="${pageContext.request.contextPath}/resources/css/welcome.css" rel="stylesheet">
 <link type="text/css" href="${pageContext.request.contextPath}/resources/css/c.css" rel="stylesheet">
        
        <link href='http://fonts.googleapis.com/css?family=Covered+By+Your+Grace' rel='stylesheet' type='text/css'>
    <script  type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script  type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
        <script async type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.3.0/bootbox.min.js"></script>
        <script async type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    
        
    </head>
    <body>
        



 
        <!--
        Slideshow
        =====================================================================
               --> 
        
     
           <div class="container top-item-blur" > 
    <div class="container">
    <div class="row"  style="height:100%">
                  
                  
        <div class="col-sm-12"   style="height:100%">
            
   <tiles:insertAttribute name="header"/>
        </div>
        </div>
    </div>
</div>
   <div class="container" >
      <div class="well-sm" style="background-color: white;border-radius: 10px">
                     
<h2 style="font: 500 30px/1em 'Raleway'" class="indent">Dreams come true. At work.
                    </h2>     
          <hr/>             
                 <div style="font: 500 20px/1em 'Raleway';color:#5a5a5a;padding-left: 10px;padding-right: 10px">
                <c:if test="${(empty jobs)}">    <br/>  <p style="text-align: justify;line-height: 1.3em">
No job postings as of now.</p>
                </c:if>
               
             
              
               
             
                <c:if test="${(not empty jobs)}">
                 <c:forEach items="${jobs}" var="entity">
                     
                         <c:if test="${not empty entity.subject}"/>
                          <h4><c:out value="${entity.subject}" /></h4><br/>
                          <c:if test="${not empty entity.body}"/>
                          <h5><c:out value="${entity.body}" /></h5>
                            <p><a onmouseout="this.style.backgroundColor='#ed5d0a'" onmouseover="this.style.backgroundColor='#fe6e1b'"  style="font-size:0.9em;background-color:#ed5d0a;color:#ffffff" class="btn btn-mini" href="${pageContext.request.contextPath}/contact" role="button">Apply</a></p>
                          
                         <hr/>
                     
                 </c:forEach>
                 </div>
                </c:if>
                </div>
        
        <br/>
   </div><br/>
   <!-- /.container -->
 <tiles:insertAttribute name="footer"/>
                      
                 
                                    
                                    
                                </body>
</html>
