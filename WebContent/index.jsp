<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="bookhub.entity.Book" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.util.List" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>BookHub  admin panel</title>

    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">
		<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">BOOK HUB</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <!--  
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form> -->
      <ul class="nav navbar-nav navbar-right">
      <%if(request.getSession().getAttribute("loginStatus")==null || request.getSession().getAttribute("loginStatus").equals(false) ){ %>
        <li><a href="PageRedirect">Login</a></li>
		<li><a href="Register.html">Register</a></li>
		<%} %>
		<%if(request.getSession().getAttribute("loginStatus")!=null && request.getSession().getAttribute("loginStatus").equals(true) ){ %>
        <li class="dropdown">
          <h4>Logged in as :</h4><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%out.print(request.getSession().getAttribute("username"));%> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <!-- <li><a href="#">Edit Profile</a></li> -->
            <li><a href="SessionController">Logout</a></li>
          </ul>
        </li>
        <%} %>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
      <!-- /.navbar-top-links -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
				  <span class="navbar-brand"> Search Books</span></div>
				  <form action="SearchController">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" name="title" class="form-control" placeholder="Title...">
                           
                            </div>
                            <!-- /input-group -->
                        </li>

                        <li class="sidebar-search">
                          <div class="input-group custom-search-form">
                                <input type="text" name="author" class="form-control" placeholder="authour...">
                           
                            </div>  
                        </li>
						<li class="sidebar-search">
                          <div class="input-group custom-search-form">
                                <input type="text" name="isbn" class="form-control" placeholder="ISBN No...">
                             
                            </div>  
                        </li>
                      <li class="sidebar-search">
                          <div class="input-group custom-search-form">
                               <input class="btn btn-lg btn-success btn-block" type="submit" value="Search">
                             
                            </div>  
                        </li>
                    </ul>
                   </form>
                </div>
                <!-- /.sidebar-collapse -->
           
            <!-- /.navbar-static-side -->
</nav>
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Welcome to BookHub </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-10">
                    <!-- /.panel -->
					 <div class="panel panel-default">
                        <div class="panel-heading">
                            Your Search results
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            
                             <%List<Book> books = new ArrayList<Book>();
                            		books=(List<Book>)request.getAttribute("books");
                            		%>
                            		<%if(books==null||books.size()==0){ %>
                            		<h4>No Books to be displayed</h4>
                            		<%} %>
                            		<d	iv class="list-group">
                            <c:forEach items="${books}" var="book"> 
							<a href="#" class="list-group-item">
							
                                    <c:out value="${book.getTitle()}"/>
                                     <!-- <span class="pull-right text-muted small"> -->
                                     <%if(session.getAttribute("loginStatus").equals(true)){ %>
                                     <a href="DownloadController?id=${book.getId()}">Download
                                   </a> <%} %><!-- </span> -->
                                </a>
								</c:forEach>
                            </div>
                            <!-- /.list-group -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <% if(request.getAttribute("message")!=null) {out.write(request.getAttribute("message").toString());} %>
                </div>
            
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../bower_components/raphael/raphael-min.js"></script>
    <script src="../bower_components/morrisjs/morris.min.js"></script>
    <script src="js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>

</body>

</html>
