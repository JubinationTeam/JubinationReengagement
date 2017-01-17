<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tisesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<tisesx:useAttribute name="current"/>   

                            
          <div class="masthead clearfix" >
            <div class="inner">
              <h3 class="masthead-brand"><img   src="${pageContext.request.contextPath}/resources/css/images/Jubination_logo3_300x100.png"  alt="Jubination">
                             </h3>
              <nav >
                <ul class="nav masthead-nav">
                  <li class="${current=='welcome'?'active':'inactive'}" style=" text-shadow: 1px 1px #555555;"><a href="${pageContext.request.contextPath}/">Home</a></li>
              <%--    <li class="${current=='products'?'active':'inactive'}" style=" text-shadow: 1px 1px #555555;"><a href="${pageContext.request.contextPath}/products">Products</a></li>
                  --%><li class="${current=='about'?'active':'inactive'}" style=" text-shadow: 1px 1px #555555;"><a href="${pageContext.request.contextPath}/about">What We Do</a></li>
                  <li class="${current=='contact'?'active':'inactive'}" style=" text-shadow: 1px 1px #555555;"><a href="${pageContext.request.contextPath}/contact">Reach Us</a></li>
                  
                  <li class="${current=='careers'?'active':'inactive'}" style=" text-shadow: 1px 1px #555555;"><a href="${pageContext.request.contextPath}/careers">Careers</a></li>
                </ul>
              </nav>
            </div>
          </div>