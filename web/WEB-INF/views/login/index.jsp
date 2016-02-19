<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>
    Login
</title>
<body>
<div id="login-form" class="row">
    <div class="col-lg-push-4 col-xs-12 col-lg-3">
        <form:form modelAttribute="login" action="login" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label"><spring:message code="label.email"/></label>
                <div class="col-sm-10">
                    <form:input cssClass="form-control" type="email" path="email"/>
                    <form:errors path="email"/>

                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label"><spring:message
                        code="label.password"/></label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3">
                    <form:errors path="password"></form:errors>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> <spring:message code="label.rememberMe"/>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default"><spring:message code="label.signIn"/></button>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
<content tag="local_script">
    <script>
        $(document).ready(function () {
            if ("${message}" != "") {
                KS.Message.Error("", "${message}");
            }
        });
    </script>
</content>
