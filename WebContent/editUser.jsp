<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bookhub.entity.User" %>
 <% if(session.getAttribute("loginStatus")==null)
{
	response.sendError ( HttpServletResponse.SC_UNAUTHORIZED,
            "You don't have enough privileges" );
} else{%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Edit User </title>

    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Edit User Details</h3>
                    </div>
                    <div class="panel-body">
                    <% User u=(User)request.getAttribute("user"); %>
                        <form role="form" action="UserController" method="POST">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="id" value="<%=u.getUserId()%>"/>
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Email" name="email" type="email" value="<%=u.getEmailId()%>"/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Phone Number" name="phonenumber" value="<%=u.getPhoneNo() %>"/> 
                                </div>
                               <div class="form-group">
                                    <input class="form-control" placeholder="password" name="password" type="password" value="" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="confirm password" name="confirm password" type="password" value="" required>
                                </div>
                                <!-- <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div> commented  for now  -->
                                <!-- Change this to a button or input when using this as a form -->
                                <!--<a href="/BookHub/doLogin" class="btn btn-lg btn-success btn-block">Login</a>-->
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="Update">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<%}%>