<%@page import="br.com.fastchoice.model.dao.Dao"%>
<%@page import="br.com.fastchoice.model.entities.Estabelecimento"%>
<%@page import="br.com.fastchoice.model.entities.Pessoa"%>
<%@page import="br.com.fastchoice.model.controller.Controller"%>
<%@page import="br.com.fastchoice.model.entities.Reserva"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
      <jsp:include page="/menu.jsp">
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
            <form id="custom-search-form" class="form-search form-horizontal pull-right">
                <div class="input-append span12">
                    <input type="text" class="search-query" placeholder="Pesquisar por amigo ou estabelecimento...">
                    <button type="submit" class="btn"><i class="icon-search"></i></button>
			
					    
                </div>
            </form>
        </div>
	</div>
	

	
	
                <ul class="gallery-post-grid holder">

<%
	List<Reserva> caronaReservas = (List<Reserva>) session.getAttribute("caronaReservas");
	List<Reserva> minhasReservas = (List<Reserva>) session.getAttribute("minhasReservas");
	if (minhasReservas != null && !minhasReservas.isEmpty() && caronaReservas != null && !caronaReservas.isEmpty()) {
		for (Reserva rAmigo: caronaReservas) {
		    if (Controller.getPessoa().getId() != rAmigo.getIdPessoa()) {
			    Pessoa amigo = (Pessoa) Dao.getInstance().readWithId("pessoa", rAmigo.getIdPessoa());
			    Estabelecimento e = (Estabelecimento) Dao.getInstance().readWithId("estabelecimento", rAmigo.getIdEstabelecimento());
			    String idAmigo = rAmigo.getIdPessoa()+"";
			    String idEstab = rAmigo.getIdEstabelecimento()+"";
	%>
	<li  class="span3 gallery-item" data-id="id-1" data-type="illustration">
		<div class="foto" style="margin: 0 auto; display: table;">
			<span class="gallery-hover-4col hidden-phone hidden-tablet">
				<span class="gallery-icons">
			 </span>
			</span>
			<c:import url="display_image.jsp">
				<c:param name="entidade" value="amizades"/>
  				<c:param name="idNumber" value="<%=idAmigo%>"/>
  				<c:param name="altura" value="170"/>
				<c:param name="largura" value="170"/>
				<c:param name="classe" value="pull-left"/>
			</c:import>
			</div>
	        <span class="project-details"><a href="gallery-single.htm"><%=amigo.getNome()%></a><%=amigo.getEndGoogle()%></span>
	        <center> <br/>
	        <form role="form" action="SendCarona" method="post" class="SendCarona">
			<input type="hidden" name="amigoId" value=<%=amigo.getId()%> >
			<input type="hidden" name="amigoNome" value=<%=amigo.getNome()%> >
			<input type="hidden" name="estabId" value=<%=e.getId()%> >
			<input type="hidden" name="caronaBotao" value="oferecer" >
			<button id="botaoOferecer" name="botaoOferecer" type="submit" class="btn btn-success">Oferecer Carona</button>
		</form>
		 </center>
	        </li>
	        <li  class="span3 gallery-item" data-id="id-1" data-type="illustration">
                       <a href="gallery-single.htm"><img src="img/seta.png" alt="Gallery"></a>
						<center> <span class="project-details"><a href="gallery-single.htm"></a><%=rAmigo.getData()%></span></center>
                    </li>
                    <li  class="span3 gallery-item" data-id="id-1" data-type="illustration">
                        <span class="gallery-hover-4col hidden-phone hidden-tablet">
                            <span class="gallery-icons">
                            </span>
                            </span>
		<form role="form" action="SelectSearchEstabelecimento" method="post" class="SelectSearchEstabelecimento">
			<input type="hidden" name="estabId" value=<%=e.getId()%> >
			<div class="foto" style="margin: 0 auto; display: table;">
				<c:import url="display_image.jsp">
					<c:param name="entidade" value="estabele"/>
	  				<c:param name="idNumber" value="<%=idEstab%>"/>
	  				<c:param name="altura" value="170"/>
					<c:param name="largura" value="170"/>
					<c:param name="classe" value="pull-left"/>
				</c:import>
			</div>
	       <span class="project-details"><a href="gallery-single.htm"><%=e.getNome()%></a><%=e.getDescricao()%></span>
		</form>
	       <center>
	       <form role="form" action="SendCarona" method="post" class="SendCarona">
			<input type="hidden" name="amigoId" value=<%=amigo.getId()%> >
			<input type="hidden" name="amigoNome" value=<%=amigo.getNome()%> >
			<input type="hidden" name="estabId" value=<%=e.getId()%> >
			<input type="hidden" name="caronaBotao" value="pedir" >
			<button id="botaoPedir" name="botaoPedir" type="submit" class="btn btn-success">Pedir Carona</button>
		</form>
		</center>
		</li>
		   <br/><br/>
	<%
		    }
		} 
	} else {
	%>
	<h5>Nenhum estabelecimento encontrado.</h5>
	<%	
	}
	%>

                </ul>
            </div>

          

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
