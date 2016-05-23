<%@page import="br.com.fastchoice.model.entities.Mensagem"%>
<%@page import="java.util.List"%>
<%@page import="br.com.fastchoice.model.controller.Controller"%>
<%@page import="br.com.fastchoice.model.entities.Pessoa"%>
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
<link rel="stylesheet" href="css/jquery.lightbox-0.5.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/custom-styles.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="css/styleChat.css">

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
<script src="js/bootstrap.js"></script>
<script src="js/jquery.custom.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
 


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
           
         

<div class="container">

    <div class="row">
     <form id="custom-search-form" action="SearchAmigo"  method="post" class="form-search form-horizontal pull-right">
                <div class="input-append span12">
                    <input id="buscatext" name="buscatext" type="text" class="search-query" placeholder="Pesquisar...">  <!-- bootstrap.css line 1656 HEIGHT 36px -->
                    <button id="submitbusca" name="submitbusca" type="submit" class="btn"><i class="icon-search"></i></button>
<!-- 					 <button class="btn btn-success">Procurar amigo</button> -->
					    
                </div>
            </form>
	
	<div class="conversation-wrap col-lg-3">
        <%
	try {
	    Pessoa pessoa = Controller.getPessoa();
	List<Pessoa> amizades = (List<Pessoa>) session.getAttribute("amizades");
	List<Pessoa> naoAmigos = null;
	session.setAttribute("displayImage", "amigos");
	if (session.getAttribute("amigosBuscados") != null) {
		amizades = (List<Pessoa>) session.getAttribute("amigosBuscados");
		naoAmigos = (List<Pessoa>) session.getAttribute("naoAmigosBuscados");
	}
	for (Pessoa amigo: amizades) {
	    session.setAttribute("amizadesId", amigo.getId());
	    String idAmigo = ""+amigo.getId();
	%>
	<div class="media conversation">
		<form action="SelectAmigo" method="post" class="SelectAmigo">
			<input type="hidden" name="amigoId" value=<%=amigo.getId()%> >
			<div class="media conversation">
			<c:import url="display_image.jsp">
				<c:param name="entidade" value="amizades"/>
		  		<c:param name="idNumber" value="<%=idAmigo%>"/>
		  		<c:param name="altura" value="50"/>
				<c:param name="largura" value="64"/>
				<c:param name="classe" value="pull-left"/>
			</c:import>
                <div class="media-body">
                    <h5 class="media-heading"><%=amigo.getNome()%></h5>
                    <small><%=amigo.getEndGoogle()%></small>
                </div>
            </div>
		</form>
		</div>

<%	
	}
	if (session.getAttribute("amigosBuscados") != null) {
	%>
		<%
		for (Pessoa naoAmigo : naoAmigos) {
		    String idNaoAmigo = ""+naoAmigo.getId();
		%>
		<div class="media conversation">
		<form action="SelectAmigo" method="post" class="SelectAmigo">
			<input type="hidden" name="amigoId" value=<%=naoAmigo.getId()%> >
			<div class="media conversation">
			<c:import url="display_image.jsp">
				<c:param name="entidade" value="amizades"/>
		  		<c:param name="idNumber" value="<%=idNaoAmigo%>"/>
		  		<c:param name="altura" value="50"/>
				<c:param name="largura" value="64"/>
				<c:param name="classe" value="pull-left"/>
			</c:import>
                <div class="media-body">
                    <h5 class="media-heading"><%=naoAmigo.getNome()%></h5>
                    <small><%=naoAmigo.getEndGoogle()%></small>
                    <% if(naoAmigo.getId() != pessoa.getId()) { %>
		        	<br/>
		        	<a href=<%="addAmigo?id="+naoAmigo.getId()%> onClick="this.disabled=true;" id="addAmigo" name="addAmigo" type="button" class="btn btn-default" style="box-sizing: border-box; line-height: 5px; background: rgb(0,180,0); width: 40; height: 20px; color: rgb(255,255,255); font-family: 'Roboto', sans-serif; font-size: 14;">Adicionar</a>
		        	<% } %>
                </div>
            </div>
		</form>
		</div>
			<%
		}
	}
	} catch (Exception e) {
	    e.printStackTrace();
	}
	%>
		
	</div>	
		<!-- AQUI EH O CHAT EM SI -->

        <div class="message-wrap col-lg-8">
     <div class="msg-wrap">
<%
try {
	Pessoa amigoChat = (Pessoa) session.getAttribute("amigoChat");
	if (amigoChat != null) {
		List<Mensagem> mensagens = (List<Mensagem>) session.getAttribute("mensagensAmigoChat");
		for (Mensagem msg: mensagens) {
			Pessoa remetente = Controller.getInstance().getPessoa();
			if (msg.getIdRemetente() == amigoChat.getId()) {
				remetente = amigoChat;
			}
			String idRemetente = ""+remetente.getId();
%>
                <div class="media msg ">
                    <a class="pull-left" href="#">
<%--                         <img id="imagemChat" class="media-object" data-src="holder.js/64x64" alt="64x64" style="width: 32px; height: 32px;" src="${pageContext.request.contextPath}/displayImage/amigo"> --%>
                       	<c:import url="display_image.jsp">
                       		<c:param name="entidade" value="qualquer"/>
  							<c:param name="idNumber" value="<%=idRemetente%>"/>
  							<c:param name="altura" value="32"/>
  							<c:param name="largura" value="32"/>
  							<c:param name="classe" value="media-object"/>
						</c:import>
                    </a>
                    <div class="media-body">
                        <small class="pull-right time"><i class="fa fa-clock-o"></i><%=msg.getData()%></small>
                        <h5 class="media-heading"><%=remetente.getNome()%></h5>
                        <small class="col-lg-10"><%=msg.getMsg()%></small>
                    </div>
                </div>
<%
	}
%>

               

            </div>

            <div class="send-wrap ">
            	<form name="message" action="SendMessage" method="post" class="SendMessage">
					<input type="hidden" name="amigoId" value=<%=amigoChat.getId()%> >
	                <textarea id="usermsg" name="usermsg" class="form-control send-message" rows="3" placeholder="Escreva sua mensagem..."></textarea>
            		<div class="btn-panel">
                		<input type="submit" id="submitmsg"  name="submitmsg" class=" col-lg-4 text-right btn   send-message-btn pull-right" role="button"> 
            		</div>
				</form>
   
<%
	}
} catch (Throwable e) {
	e.printStackTrace();
}
%>
         </div>
        </div>
    </div>
</div>
    </div>  <!-- End Container -->

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
