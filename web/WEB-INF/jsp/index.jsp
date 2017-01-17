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
        <link href="<c:url value="${pageContext.request.contextPath}/resources/css/images/logo" />" rel="shortcut icon">
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
    
               
               <div class="container top-item-blur hidden-lg hidden-md hidden-sm" > 
    <div class="container">
    <div class="row"  style="height:100%">
                  
                  
        <div class="col-sm-12"   style="height:100%">
            
   <tiles:insertAttribute name="header"/>
        </div>
        </div>
    </div>
</div>
<div class="container top-item hidden-xs" > 
    <div class="container">
    <div class="row"  style="height:100%">
                  
                  
        <div class="col-sm-12"   style="height:100%">
            
   <tiles:insertAttribute name="header"/>
        </div>
        </div>
    </div>
</div>
   <div class="container item hidden-xs" >
              <div class="row"  style="height:100%">
                  
                  
                <div class="col-sm-12"  style="height:100%;margin-top: 80px">
                    <center>
                                   <img   src="/resources/css/images/logo.png"  alt="Jubination">
                             <p class="r-text" >
                              
                               <br/><br/>
                            <%--   <span onmouseout="this.style.color='#ffffff'" onmouseover="this.style.color='#fe6e1b'"  class="glyphicon glyphicon-cog" ></span>&nbsp;&nbsp;
                               <span onmouseout="this.style.color='#ffffff'" onmouseover="this.style.color='#fe6e1b'" class="glyphicon glyphicon-pencil" ></span>&nbsp;&nbsp;
                               <span onmouseout="this.style.color='#ffffff'" onmouseover="this.style.color='#fe6e1b'" class="glyphicon glyphicon-briefcase" ></span></p><br/>
                                 <p><a onmouseout="this.style.backgroundColor='#ed5d0a'" onmouseover="this.style.backgroundColor='#fe6e1b'"  style="font-size:1.2em;background-color:#ed5d0a;color:#ffffff" class="btn btn-mini" href="${pageContext.request.contextPath}/about" role="button">Learn More </a></p>
                          
                           <br/>
                           
        
                           <p style="color:#ffffff"> Java | Android | PHP | Cloud | Hibernate | Spring</p>
  

                        --%>
                                  
                                     
                              </center>
                    
                    
</div>
</div>   
    </div>
               
          
    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

	
      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="${pageContext.request.contextPath}/resources/css/images/plan.jpg" alt="Generic placeholder image" width="140" height="140">
          <h2 >Brainstorming</h2>
          <p>We dive deep into your business needs to discover your target audience, their behaviors, your objectives, and the barriers for success. Our design brainstorming process is part discovery, part innovation, part refinement and part strategy between your company and our team.</p>
               <p><a onmouseout="this.style.backgroundColor='#ed5d0a'" onmouseover="this.style.backgroundColor='#fe6e1b'"  style="font-size:1.2em;background-color:#ed5d0a;color:#ffffff" class="btn btn-mini" href="${pageContext.request.contextPath}/about" role="button">Learn More </a></p>
          </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="${pageContext.request.contextPath}/resources/css/images/code.jpg" alt="Generic placeholder image" width="140" height="140">
          <h2 >Implementation</h2>
          <p>We love designing interactive prototypes, but to make them we first need user experience maps. They help us build a strong foundation to scope out the app’s functionality, consider its main features and understand the relationships between component to create an engaging user experience.</p>
               <p><a onmouseout="this.style.backgroundColor='#ed5d0a'" onmouseover="this.style.backgroundColor='#fe6e1b'"  style="font-size:1.2em;background-color:#ed5d0a;color:#ffffff" class="btn btn-mini" href="${pageContext.request.contextPath}/about" role="button">Learn More </a></p>
           </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="${pageContext.request.contextPath}/resources/css/images/solution.jpg" alt="Generic placeholder image" width="140" height="140">
          <h2 >Solution</h2>
          <p>Once we have the fundamentals down, the real fun begins. We start to build stuff, good stuff. Structures, connect screens, animations, responsive buttons — the ingredients for mobile app design magic. We make quick-and-dirty prototypes early in the process and never stop iterating and user testing. </p>
                 <p><a onmouseout="this.style.backgroundColor='#ed5d0a'" onmouseover="this.style.backgroundColor='#fe6e1b'"  style="font-size:1.2em;background-color:#ed5d0a;color:#ffffff" class="btn btn-mini" href="${pageContext.request.contextPath}/about" role="button">Learn More </a></p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->


      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider"/>

      <div class="row featurette well" style="background-color: white">
        <div class="col-md-7  ">
          <h2 class="featurette-heading" >
              Software Development for Scaling Large Websites
              <%--
              <span style="color:#28556e">It'll blow your mind.</span>--%>
          </h2>
          <p class="lead" >
              
              We have dedicated teams for various technologies such as Java, Spring, Hibernate etc. as well as a team handling areas such as Business Intelligence. This dedicated team enables us to give focused attention and customized application development and maintenance services to each and every client. 
              
             </div>
        <div class="col-md-5">
               <%--<img class="featurette-image img-responsive center-block" src="${pageContext.request.contextPath}/resources/css/images/cleancode.jpg" data-src="holder.js/500x500/auto" alt="Generic placeholder image"/>
        --%>
        <div class="code"></div>
        </div>
       
      </div>

      <hr class="featurette-divider">

      <div class="row featurette well">
          
        <div class="col-md-7 col-md-push-5">
            <h2 class="featurette-heading" >Web/Mobile Apps That Delight People</h2>
          <p class="lead" > Start doing the right math. Engaged users = the right ROI. A successful user experience will drive engagement, improve retention and increase revenue. When it comes to website or mobile design, we're the best. </p>
        </div>
        <div class="col-md-5 col-md-pull-7">
            <div class="mobile"></div>
        </div>
      </div>

      <hr class="featurette-divider">



      <div class="row featurette well" style="background-color: white">
        <div class="col-md-7  ">
          <h2 class="featurette-heading" >
              Database Administration
              <%--
              <span style="color:#28556e">It'll blow your mind.</span>--%>
          </h2>
          <p class="lead" >
              
            Our Database Administration delivers optimum quality across all key metrics that the client's desire viz. flexibility, reliability, security and performance. We provide best-of-the breed database management services for various database environments owing to our vast experience and expertise. </div>
        <div class="col-md-5">
               <%--<img class="featurette-image img-responsive center-block" src="${pageContext.request.contextPath}/resources/css/images/cleancode.jpg" data-src="holder.js/500x500/auto" alt="Generic placeholder image"/>
        --%>
        <div class="code"></div>
        </div>
       
      </div>

      <!-- /END THE FEATURETTES -->


      <!-- FOOTER -->
      
   <tiles:insertAttribute name="footer"/>
    </div><!-- /.container -->

                      
                                    
                                    
                                    
                                </body>
</html>
