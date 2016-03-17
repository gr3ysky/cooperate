<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

        <c:if test="${not empty Menu}">

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
                                    <spring:message code="${item.resourceKey}"/>   <span
                                        class="caret"></span></a>

                            </c:if>
                            <c:if test="${ empty item.children}">
                                <a href="${item.href}">
                                    <c:if test="${not empty item.icon}">
                                        <i class="glyphicon glyphicon-${item.icon}"></i>
                                    </c:if>
                                    <spring:message code="${item.resourceKey}"/>  </a>
                            </c:if>
                            <c:if test="${not empty item.children}">
                                <ul class="dropdown-menu">
                                    <c:forEach var="child" items="${item.children}">
                                        <li>
                                            <a href="${child.href}">
                                                <c:if test="${not empty child.icon}">
                                                    <i class="glyphicon glyphicon-${child.icon}"></i>
                                                </c:if>
                                                <spring:message code="${child.resourceKey}"/>  </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>

                        </li>
                    </c:forEach>
                </ul>

        </c:if>
