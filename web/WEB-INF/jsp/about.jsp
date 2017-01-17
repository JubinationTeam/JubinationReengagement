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
                     
<h2 style="font: 500 25px/1em 'Raleway'" class="indent">What We Do
                    </h2>                <br/>
                 <h4 style="font: 500 18px/1em 'Raleway';color:#5a5a5a;padding-left: 10px;padding-right: 10px">
                    <p style="text-align: justify;line-height: 1.3em">
Jubination Software Solutions, an integrated technology solutions
company functioning in the Software and Information Technology domains
and it is poised to become a major player in technology distribution,
Jubination Software Solutions pan India presence, combined with its
acknowledged capability to optimize its core capabilities across
diverse technology platforms, fine human resource compliances and
commitment to quality and support facilitates the delivery of best in
class solutions. In an age of convergence Jubination Software
Solutions paces the future with an excellent performance paradigm.
                    <br/><br/>
                    <b>Business Strategy</b><br/>
                    We solve business problems, identify opportunities, refine product ideas and global strategies. Our strategic counsel and Innovation Workshops zoom out before we zoom in to build you web/mobile app.
                    <br/>
                    <br/>
                    <b>Development</b><br/>
                    We think fast, and code even faster. We build on any language (native or cross-platform), with any integrations/customizations you want, 'cuz we can.
                  <br/>
                    <br/>
                    <b>Lifecycle Management</b><br/>
                    Jubination delivers a holistic approach to product management. We’ll help bring your product to life, then nurture and grow it.
                    </p>
                    
                </h4>
                    
                    
                    
                <hr/>
                <br/>
               
                </div>
        
        <br/>
   <tiles:insertAttribute name="footer"/>
     </div><!-- /.container -->

                      
                                    
                                    
                                    
                                </body>
</html>
