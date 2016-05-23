<%@page import="br.com.fastchoice.model.controller.Controller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="span6 navigation">
	<div class="navbar hidden-phone">

		<ul class="nav">
			<li><a href="editar_perfil.jsp">Perfil</a></li>
			<li><a href="estabelecimentos.jsp">Estabelecimentos</a></li>
			<%if (Controller.getPessoa() != null && Controller.getPessoa().getId() == 1) {%>
			<li><a href="cadastro_estabelecimento.jsp">Add Estabelecimento</a></li>
			<%} %>
			<li><a href="chat.jsp">Amigos</a></li>

			<li><a href="reservas.jsp">Reservas</a></li>

			<li><a href="LoadCaronas">Caronas</a></li>


			<li><a href="SendSair">Sair</a></li>
	</ul>

</div>



</div>
