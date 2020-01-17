<%@ page language="java" contentType="text/html;" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
    <title>Spring MVC Form</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-theme.min.css" rel="stylesheet">
    <style type="text/css">
        .error {
            color: red
        }
    </style>
</head>

<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Spring MVC 5 Form</h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign Up</div>
            </div>
            <div class="panel-body">
                <form:form action="user" class="form-horizontal"
                           method="post" modelAttribute="user">

                    <div class="form-group">
                        <label for="firstName" class="col-md-3 control-label">First
                            Name</label>
                        <div class="col-md-9">
                            <form:input path="firstName" class="form-control"/>
                            <form:errors path="firstName" cssClass="error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="col-md-3 control-label">Last
                            Name</label>
                        <div class="col-md-9">
                            <form:input path="lastName" class="form-control"/>
                            <form:errors path="lastName" cssClass="error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="gender" class="col-md-3 control-label">gender</form:label>
                        <div class="col-md-9">
                            <form:radiobutton path="gender" value="M" label="Male"/>
                            <form:radiobutton path="gender" value="F" label="Female"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userName" class="col-md-3 control-label">User
                            Name </label>
                        <div class="col-md-9">
                            <form:input path="userName" class="form-control"/>
                            <form:errors path="userName" cssClass="error" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <form:password path="password" class="form-control"/>
                            <form:errors path="password" cssClass="error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-3 control-label">Email</label>
                        <div class="col-md-9">
                            <form:input path="email" class="form-control"/>
                            <form:errors path="email" cssClass="error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="country" class="col-md-3 control-label">Country</label>
                        <div class="col-md-9">
                            <form:select path = "country">
                                <form:option value = "无" label = "请选择"/>
                                <form:options items = "${countryList}" />
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="favorites" class="col-md-3 control-label">Favorites</label>
                        <div class="col-md-9">
                            <form:checkboxes class="f" items = "${favorites}" path = "favorites" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <form:button class="btn btn-primary">Submit</form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
</body>
</html>