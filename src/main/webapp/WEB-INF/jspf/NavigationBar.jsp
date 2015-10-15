<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>E-commerce Admin</title>
        <!-- Bootstrap Core CSS -->
        <link href="<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">
        <!-- MetisMenu CSS -->
        <link href="<c:url value="/resources/bower_components/metisMenu/dist/metisMenu.min.css"/>" rel="stylesheet">
        <!-- DataTables CSS -->
        <link href="<c:url value="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">
        <!-- DataTables Responsive CSS -->
        <link href="<c:url value="/resources/bower_components/datatables-responsive/css/dataTables.responsive.css"/>" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="<c:url value="/resources/dist/css/sb-admin-2.css"/>" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="wrapper">

            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="Dashboard">E-commerce Admin</a>
                </div>

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li><a href="Profile"><i class="fa fa-user fa-fw"></i> Profil</a></li>
                            <li class="divider"></li>
                            <li><a href="Logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
                        </ul>
                    </li>
                </ul>

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li><a href="Dashboard"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
                            <li><a href="Book"><i class="fa fa-book fa-fw"></i> Gestion des livres</a></li>
                            <li><a href="Author"><i class="fa fa-users fa-fw"></i> Gestion des auteurs</a></li>
                            <li><a href="Category"><i class="fa fa-sitemap fa-fw"></i> Gestion des categories</a></li>
                            <li><a href="Editor"><i class="fa fa-pencil fa-fw"></i> Gestion des editeurs</a></li>
                            <li><a href="Event"><i class="fa fa-calendar fa-fw"></i> Gestion des événements</a></li>
                            <li><a href="Customer"><i class="fa fa-user fa-fw"></i> Gestion des clients</a></li>
                            <li><a href="Tax"><i class="fa fa-money fa-fw"></i> Gestion des taxes</a></li>
                        </ul>
                    </div>
                </div>

            </nav>