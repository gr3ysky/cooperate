<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<title>
    ${profile.profileName}
</title>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        ${profile.profileName}

        <c:if test="${profile.owned==true}">
            <a class="btn btn-circle btn-xs btn-primary  pull-right" href="/profile/update"><i
                    class="glyphicon glyphicon-pencil"></i></a>
        </c:if>
    </div>
    <div class="panel-body">
        <div class="row">

            <c:if test="${not empty profile.picture}">
                <div class="col-xs-12 text-align-center">
                    <img src="${profile.picture}" alt="${profile.profileName}"
                         style="max-width: 200px; max-height: 200px"/></div>
            </c:if>
            <div class="col-xs-2"><spring:message code="label.profileName"/></div>
            <div class="col-xs-10">${profile.profileName} </div>
            <div class="col-xs-2"><spring:message code="label.userName"/></div>
            <div class="col-xs-10">${profile.userName} </div>
            <div class="col-xs-2"></div>
            <div class="col-xs-10">
                <c:if test="${profile.volunteerForPackaging==true}">
                    <spring:message code="label.volunteerForPackaging"/>
                </c:if>
            </div>
            <div class="col-xs-2"></div>
            <div class="col-xs-10">
                <c:if test="${profile.volunteerForSelling==true}">
                    <spring:message code="label.volunteerForSelling"/>
                </c:if>
            </div>

        </div>
    </div>
</div>


</body>

<content tag="local_script">
    <script>
        $(function () {
        });
    </script>
</content>
