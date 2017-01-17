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
      <link type="text/css" href="${pageContext.request.contextPath}/resources/css/google.css" rel="stylesheet">  
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
   <div class="container ">
            <div class="row well-sm" style="background-color: white;border-radius: 10px">
                <c:url value="/googlelocate" var="url">
                        <c:param name="longitude" value="77.607036"/>
                        <c:param name="latitude" value="12.912539"/>
                    
                     </c:url>   
                <div class="col-md-6" style="color:#000000">
                    <h2 style="font: 500 38px/1em 'Raleway'" class="r-text-black">Contact Us
                    </h2>
                    <div style="text-align: left">
                    <iframe id="googleviewmap"  src="<c:out value='${url}'/>"></iframe>
                    <h3 style="font: 500 25px/1em 'Raleway'" >Office</h3>  <h4 style="font: 500 20px/1em 'Raleway';color:#eb9316" >1st Floor, 855, 10th Cross, BTM II Stage, In front of Mico Layout Police Station, Bangalore 560076</h4>
                    <h3 style="font: 500 25px/1em 'Raleway'">Ring Us</h3>  <h4 style="font: 500 20px/1em 'Raleway';color:#eb9316" >080 41689221</h4>
                    <h3 style="font: 500 25px/1em 'Raleway'" >Email Us</h3> <h4 style="font: 500 20px/1em 'Raleway';color:#eb9316">info@suemans.com</h4>
                    
                    <br/>
                    <br/>
                    
                    
                    </div>
                    <div class="clearfix"></div>
                  
                       
                </div>
                      <div class="col-md-6" style="color:#000000;text-align: left">
                    <h2 style="font: 500 38px/1em 'Raleway'" class="r-text-black">Message Us
                    </h2>
                          <form id="message-form" class="form-message" action="${pageContext.request.contextPath}/sendEmail" method="post" >                                                               
                              <label style="color:#eb9316">Name</label>                  
                              <input id="name-id" type="text" name='name' class="form-control" placeholder="eg. Ricky Patrick" />
                              <label style="color:#eb9316">Email Id&nbsp;* </label>                 
                              <input id="email-id" type="text" name='email' class="form-control" placeholder="eg. ricky@example.com" required="true"/>
                              <label  style="color:#eb9316">Mobile Number&nbsp;*</label>                  
                              <input id="mobile-id" type="text" name='mobile' class="form-control" placeholder="eg. +91-9876543210" required="true"/>
                              
                              <label   style="color:#eb9316">Message&nbsp;*</label>  
                               <textarea name="message" class="form-control" rows="5" id="message-id" placeholder="Type your message here..." required="true"></textarea><br/>
                           <button type="submit" name="submit" id="send-message" class="btn btn-large btn-primary" >Send Message</button>
                           </form>
                          <center>
                        <br/><br/>
                     <a href="www.facebook.com/Jubination11" target="_blank"><img src="${pageContext.request.contextPath}/resources/css/images/facebook.png"  alt="Facebook"/></a>
                      <a href="#google"><img src="${pageContext.request.contextPath}/resources/css/images/google.png"  alt="Google"/></a>
                   <a href="twitter.com/suemans11" target="_blank"><img src="${pageContext.request.contextPath}/resources/css/images/twitter.png"  alt="Twitter"/></a>
                    
                    </center>
                </div>  
            </div>
        <br/>
        
                       
                        
                        <br/>
   <tiles:insertAttribute name="footer"/>
    </div><!-- /.container -->

                      
                                    
                                    
                                    
                                </body>
</html>
