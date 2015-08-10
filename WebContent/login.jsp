<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html lang="en">

<head>
 <% if(session.getAttribute("loginStatus")!=null && session.getAttribute("loginStatus").equals("true"))
{
   response.sendRedirect("dashboard.jsp");
}%> 
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>BookHub Admin </title>

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
 <%
                    if(response.getHeader("LogoutStatus")!=null){
                    if(response.getHeader("LogoutStatus").equals("true"))
                    {
                    	out.println("You have successfully logged out");
                    	
                    }
                    }
                    %>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                   
                        <h3 class="panel-title">Login</h3>
                        <%//session.removeAttribute("loginStatus");
		if(session.getAttribute("loginStatus")!=null){
			if(session.getAttribute("loginStatus").equals("fail"))
		     out.println("Email/Password is incorrect");
		}
		%>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="SessionController" method="POST" id="loginfrm" name="loginfrm">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                              <!--  <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div> commented  for now -->  
                                <!-- Change this to a button or input when using this as a form -->
                                <!--<a href="/BookHub/doLogin" class="btn btn-lg btn-success btn-block">Login</a>-->
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="jquery/dist/jquery.min.js"></script>
    <!--  jquery validate -->
	<script src="jquery/dist/jquery.validate.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>
  	
  	<script type='text/javascript'>
	 
	 $().ready(function() {
	 
	// alert('hi');
	 //validate signup form on keyup and submit
		$("#loginfrm").validate({
			rules: {

				password: {
					required: true,
					minlength: 2
				},
				email: {
					required: true,
					email: true
				}
			},
			messages: {
				
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 2 characters long"
				},
				email: "Please enter a valid email address"
				
			}
		});
	});	
</script>
</body>

</html>
