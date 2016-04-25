<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title><spring:message code="title.profile.create"/></title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        <spring:message code="title.profile.create"/>
    </div>
    <div class="panel-body">
        <div class="row">
            <form:form modelAttribute="profile" action="/profile/createProfile" method="post" class="form-horizontal"
                       enctype="multipart/form-data">
                <div class="form-group col-md-6 col-xs-12">
                    <label class="col-md-4 col-xs-12 control-label" for="userName">
                        <spring:message code="label.userName"/>
                    </label>
                    <div class="col-md-8 col-xs-12">
                        <form:input cssClass="form-control" path="userName"/>
                        <form:errors cssClass="text-danger" path="userName"/>
                    </div>
                </div>
                <div class="form-group col-md-6 col-xs-12">
                    <label class="col-md-4 col-xs-12 control-label" for="profileName">
                        <spring:message code="label.profileName"/>
                    </label>
                    <div class="col-md-8 col-xs-12">
                        <form:input cssClass="form-control" path="profileName"/>
                        <form:errors cssClass="text-danger" path="profileName"/>
                    </div>
                </div>
                <div class="clearfix"></div>

                <div class="form-group col-md-6 col-xs-12">
                    <label class="col-md-4 col-xs-12 control-label" for="image">
                        <spring:message code="label.image"/>
                    </label>
                    <div class="col-md-8 col-xs-12">
                        <input type="file" name="image" data-val="true" data-val-required="Resim seçilmelidir."/>
                        <form:errors cssClass="text-danger" path="image"></form:errors>
                    </div>
                </div>

                <div class="form-group col-md-6 col-xs-12 ">
                    <div class="col-md-offset-4 col-xs-12 ">
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="volunteerForSelling"/> <spring:message
                                    code="label.volunteerForSelling"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="form-group col-md-6 col-xs-12 ">
                    <div class="col-md-offset-4 col-xs-12 ">
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="volunteerForPackaging"/> <spring:message
                                    code="label.volunteerForPackaging"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-6 col-xs-12 ">
                    <div class="col-md-offset-4 col-xs-12 ">
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="isPublic"/> <spring:message code="label.isPublic"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="form-groupcol-md-6 col-xs-12">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default"><spring:message code="label.save"/></button>
                    </div>
                </div>

                <div class="clearfix"></div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>
        </div>
    </div>
</div>

</body>

<content tag="local_script">
    <script>
        $(function () {
            if ("${message}" != "") {
                if ("${status}" == "success")
                    KS.Message.Success("${message}");
                else
                    KS.Message.Error("${message}");
            }

        });
    </script>
</content>

