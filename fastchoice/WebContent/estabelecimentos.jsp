<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="br.com.fastchoice.model.dao.Dao"%>
<%@page import="br.com.fastchoice.model.controller.SendSearch"%>
<%@page import="br.com.fastchoice.model.entities.Estabelecimento"%>
<%@page import="java.util.List"%>
<%@page import="br.com.fastchoice.model.controller.Controller"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>FastChoice - Simples, rápido e prático.</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/prettyPhoto.css" />
<link rel="stylesheet" href="css/custom-styles.css">

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link rel="stylesheet" href="css/style-ie.css"/>
<![endif]--> 

<!-- Favicons
================================================== -->
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="apple-touch-icon" href="img/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72" href="img/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114" href="img/apple-touch-icon-114x114.png">

<!-- JS
================================================== -->
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/jquery.quicksand.js"></script>
<script src="js/jquery.custom.js"></script>

</head>

<body>
	<div class="color-bar-1"></div>
    <div class="color-bar-2 color-bg"></div>
    
    <div class="container main-container">
    
      <div class="row header"><!-- Begin Header -->
      
        <!-- Logo
        ================================================== -->
        <div class="span5 logo">
        	<a href="index.htm"><img src="img/piccolo-logo.png" alt="" /></a>
            <h5>Simples, rápido e prático.</h5>
        </div>
        
        <!-- Main Navigation
        ================================================== -->
       <jsp:include page="menu.jsp">
       		<jsp:param value="asd" name="asd"/>
       </jsp:include>

      </div><!-- End Header -->
     
    <!-- Page Content
    ================================================== --> 
    <div class="row">

        <!-- Gallery Items
        ================================================== --> 
        <div class="span12 gallery">

            <div class="row clearfix">
			
			<div class="row">
			<div class="span16">
            <form action="SendSearch" method="post" id="custom-search-form" class="form-search form-horizontal pull-right">
                <div class="input-append span12">
                    <input id="buscatext" name="buscatext" type="text" class="search-query" placeholder="Pesquisar...">
                    <button name="submitbusca" type="submit"  id="submitbusca" class="btn"><i class="icon-search"></i></button>
                </div>
            </form>
        </div>
	</div>

                <ul class="gallery-post-grid holder">

	<%
	List<Estabelecimento> estabelecimentos = SendSearch.getEstabelecimentos();
	if (estabelecimentos == null || estabelecimentos.isEmpty()) {
	    estabelecimentos = (List<Estabelecimento>) Dao.getInstance().read("estabelecimento");
	}
	if (estabelecimentos != null && estabelecimentos.size() > 0) {
	    Collections.sort(estabelecimentos, new Comparator() {
	            @Override
	            public int compare(Object e1, Object e2) {
	                return ((Estabelecimento)e1).getNome().compareTo(((Estabelecimento)e2).getNome());
	            }
	        }); 
		for (Estabelecimento e : estabelecimentos) {
			session.setAttribute("imagemEstabelecimento", e);
			String idEstab = ""+e.getId();
	%>
	
<li  class="span3 gallery-item" data-id="id-1" data-type="illustration">
	<form role="form" action="SelectSearchEstabelecimento" method="post" class="SelectSearchEstabelecimento">
		<span class="gallery-hover-4col hidden-phone hidden-tablet">
		<span class="gallery-icons">
		</span>
				 </span>
			<input type="hidden" name="estabId" value=<%=e.getId()%> >
			<div class="foto" style="margin: 0 auto; display: table;">
			<c:import url="display_image.jsp">
				<c:param name="entidade" value="estabele"/>
  				<c:param name="idNumber" value="<%=idEstab%>"/>
  				<c:param name="altura" value="170"/>
				<c:param name="largura" value="170"/>
				<c:param name="classe" value="item-details-link"/>
			</c:import>
			</div>
			<span class="project-details"><a href="gallery-single.htm"><%=e.getNome()%></a><%=e.getDescricao()%></span>
			</form>
		 </li>
			<%}
		}%>
                   

                </ul>
            </div>

            <!-- Pagination -->
<!--             <div class="pagination"> -->
<!--                 <ul> -->
<!--                 <li class="active"><a href="#">Prev</a></li> -->
<!--                 <li class="active"><a href="#">1</a></li> -->
<!--                 <li><a href="#">2</a></li> -->
<!--                 <li><a href="#">3</a></li> -->
<!--                 <li><a href="#">4</a></li> -->
<!--                 <li><a href="#">Next</a></li> -->
<!--                 </ul> -->
<!--             </div> -->

        </div><!-- End gallery list-->

    </div><!-- End container row -->
    
    </div> <!-- End Container -->

    <!-- Footer Area
        ================================================== -->
	<div class="footer-container"><!-- Begin Footer -->
    	<div class="container">
        	<div class="row footer-row">
                
              </div>

            <div class="row"><!-- Begin Sub Footer -->
                <div class="span12 footer-col footer-sub">
                    <div class="row no-margin">
                        <div class="span6"><span class="left">Copyright 2015 FastChoice Theme. Todos os direitos reservados.</span></div>
                        <div class="span6">
                            <span class="right">
                            <a href="#">Home</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="#">Estabelecimentos</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="#">Amigos</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="#">Caronas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="#">Contact</a>
                            </span>
                        </div>
                    </div>
                </div>
            </div><!-- End Sub Footer -->

        </div>
    </div><!-- End Footer --> 
   

    <!-- Scroll to Top -->  
    <div id="toTop" class="hidden-phone hidden-tablet">Back to Top</div>
    
</body>
</html>
