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
            <title>Home</title>
            
               <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
            
             <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />" rel="stylesheet">
          <script async type="text/javascript" src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" />"></script>
            
            <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />" rel="stylesheet">            
            
         
             <script  type="text/javascript" src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
          
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
            
            
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
        <br/>
        <center>
            <p><b>Pending calls of the day</b></p>
        </center>
        <br/>
        <div class="container">
       <div class="table-responsive">
               <table class="table table-striped table-condensed table-hover table-bordered">
              <thead>
                <tr>
                                 
                                <th>CallFrom</th>  
                                  <th>CallTo</th>
				  <th>Status</th>
				  <th>TrackStatus</th>
				  <th>CallType</th>
				  <th>DailWhomNumber</th>
				  <th>DailCallDuration</th>
				  <th>Message</th>
				  <th>DateCreated</th>
				  <th>AnsweredBy</th> 
				  <th>StartTime</th>   
                   <th>EndTime</th>  
				   <th>DateUpdated</th> 
				   <th>Duration</th> 
                   <th>Price</th>
                   <th>Direction</th> 
				   <th>Digits</th>
                    <th>Sid</th>				   
					<th>Uri</th> 				   
                    <th>RecordingUrl</th>   
                   <th>PhoneNumberSid</th>
					<th>AccountSid</th> 				   
                   <th>ForwardedFrom</th>   
                   <th>CallerName</th>
				   <th>ParentCallSid</th>
                      
                      
                   
                   
                   
                   
                   
                   
     
                </tr>
              </thead>
              <tbody>
                    <c:forEach items="${callrecords}" var="item">
                    
                    <tr>
                        		<td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.callFrom}"/></p>
                        </td>
						<td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.callTo}"/></p>
                         </td>
						 <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.status}"/></p>
                        </td>
						<td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.trackStatus}"/></p>
                         </td>
						 <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.callType}"/></p>
                         </td>
						 <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.dailWhomNumber}"/></p>
                        </td>
						<td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.dailCallDuration}"/></p>
                        </td>
						 <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.message}"/></p>
                        </td>
						<td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.dateCreated}"/></p>
                        </td>
						 <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.answeredBy}"/></p>
                        </td>
						 <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.startTime}"/></p>
                         </td>
                         <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.endTime}"/></p>
                        </td>
						<td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.dateUpdated}"/></p>
                         </td>
						 <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.duration}"/></p>
                         </td>
						 <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.price}"/></p>
                        </td>
						
                         <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.direction}"/></p>
                         </td>
						  <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.digits}"/></p>
                         </td>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.sid}"/></p>
                        </td>
						 <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.uri}"/></p>
                         </td>
						 
                         <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.recordingUrl}"/></p>
                        </td>
					
						<td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.phoneNumberSid}"/></p>
                         </td>
                          <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.accountSid}"/></p>
                        </td>
                         <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.forwardedFrom}"/></p>
                         </td>
                         <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.callerName}"/></p>
                        </td>
                         <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.parentCallSid}"/></p>
                         </td>
                         
                         
                        
                         
                         
                      
                         
                         
                
                    </tr>
                                              
                     </c:forEach>
              </tbody>
           </table>
               </div>
        </div>
    </body>
</html>
