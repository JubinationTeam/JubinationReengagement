

<%-- 
    Document   : adminlogin
    Created on : 22 Sep, 2014, 9:58:51 PM
    Author     : Welcome
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta name="description" content="">
            <meta name="author" content="Souvik Das">
            <title>Call API</title>
            
               <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
            
             <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />" rel="stylesheet">
          <script async type="text/javascript" src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" />"></script>
            
            <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />" rel="stylesheet">            
            
         
             <script  type="text/javascript" src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
          
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
            
              <script async type="text/javascript" src="<c:url value="/resources/js/adminmail.js" />"></script>
          
            <script async type="text/javascript" src="<c:url value="//cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.3.0/bootbox.min.js" />"></script>
           
            <%--
           
           <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
           <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
           <link type="text/css" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
            --%>
            
           
           
           <%--
            <script type="text/javascript" src="<c:url value="/resources/js/doc.js" />"></script> 
            <script type="text/javascript" src="<c:url value="/resources/js/respond.js" />"></script>
                 --%>
            
           
            
    </head>
    <body>
       <tiles:insertAttribute name="navigation"/>
       <div class="container">
       <div class="row">
           
                <div class="col-sm-5">
                    <br/>
                    <center>${message}</center>
                    <br/>
                    <div id="compose" class="form-group" >
                       <form action="${pageContext.request.contextPath}/admin/callinterface/call/beforetest" method="post">
                            <label for="comment">Add Numbers</label>

                            <textarea name="numbers" class="form-control" rows="10" placeholder="Add numbers here...(Each in new line)" id="comment"></textarea>
                            </br>
                            <button type="submit" class="btn btn-lg btn-info" id="button-submit">Test -1</button>
                       </form>                       
                    </div>
               </div>
                            
       </div>
         <div class="row">
           
                <div class="col-sm-5">
                    <br/>
                    <center>${message}</center>
                    <br/>
                    <div id="compose" class="form-group" >
                       <form action="${pageContext.request.contextPath}/admin/callinterface/call/aftertest" method="post">
                            <label for="comment">Add Numbers</label>

                            <textarea name="numbers" class="form-control" rows="10" placeholder="Add numbers here...(Each in new line)" id="comment"></textarea>
                            </br>
                            <button type="submit" class="btn btn-lg btn-info" id="button-submit">Repeat</button>
                       </form>                       
                    </div>
               </div>
                            
       </div>          
       </div>
    </body>
</html>

