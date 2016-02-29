<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu-content">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><spring:message code="label.siteName"/></a>
        </div>
        <c:if test="${not empty Menu}">
            <div class="collapse navbar-collapse" id="menu-content">
                <ul class="nav navbar-nav">
                    <c:forEach var="item" items="${Menu}">
                        <li>
                            <c:if test="${not empty item.children}">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true"
                                   aria-expanded="false">
                                    <c:if test="${not empty item.icon}">
                                        <i class="glyphicon glyphicon-${item.icon}"></i>
                                    </c:if>
                                        ${item.name} <span
                                        class="caret"></span></a>

                            </c:if>
                            <c:if test="${ empty item.children}">
                                <a href="${item.href}">
                                    <c:if test="${not empty item.icon}">
                                        <i class="glyphicon glyphicon-${item.icon}"></i>
                                    </c:if>
                                        ${item.name} </a>
                            </c:if>
                            <c:if test="${not empty item.children}">
                                <ul class="dropdown-menu">
                                    <c:forEach var="child" items="${item.children}">
                                        <li>
                                            <a href="${child.href}">
                                                <c:if test="${not empty child.icon}">
                                                    <i class="glyphicon glyphicon-${child.icon}"></i>
                                                </c:if>
                                                    ${child.name} </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>

                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </div>
</nav>