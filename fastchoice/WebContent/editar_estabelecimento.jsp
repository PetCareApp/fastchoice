<%@page import="br.com.fastchoice.model.entities.Estabelecimento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
		<title>Fastchoice cadastro</title>
		<meta charset="utf-8">
		<link href="css/style2.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!--webfonts-->
		<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text.css'/>
		<!--//webfonts-->
				<script type="text/javascript">
		function liberarRequerimento() {
		    	 document.getElementById("nome").removeAttribute('required');
		    	 document.getElementById("fotoFile").removeAttribute('required');
		    	 document.getElementById("descricao").removeAttribute('required');
		    	 document.getElementById("telefone").removeAttribute('required');
		    	 document.getElementById("endGoogle").removeAttribute('required');
		}
		</script>
</head>
<body>
<% Estabelecimento estabelecimento = (Estabelecimento) session.getAttribute("estabelecimentoBuscado");%>
	<div class="main">
		<div class="header" >
			<h1>Cadastrar estabelecimento!</h1>
		</div>
		<p>Dados necessários para cadastramento de estabelecimento no FastChoice</p>
			<form action="EditarEstabelecimento" method="post" enctype="multipart/form-data">
				<ul class="left-form">
					<h2>Atualizar Estabelecimento:</h2>
					<% String extension = estabelecimento.getFotoExtension();
			        String idEstab = estabelecimento.getId()+"";
			        if (extension != null && !extension.isEmpty()) { %>
			        <c:import url="display_image.jsp">
						<c:param name="entidade" value="estabele"/>
		  				<c:param name="idNumber" value="<%=idEstab%>"/>
		  				<c:param name="altura" value="100"/>
						<c:param name="largura" value="100"/>
						<c:param name="classe" value="avatar img-circle"/>
					</c:import>
			        <% } %>
					<li>
			            <label for="foto">Foto</label>
          				<input type="file" id="fotoFile" name="fotoFile" required>
          				<a href="#" class="icon ticker"> </a>
          			</li>
					<li>
						<input type="text" id="nome" name="nome"  placeholder="Nome" value="<%=estabelecimento.getNome()%>" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
<!-- 						<input type="textArea"  id="descricao" name="descricao" placeholder="Descrição" required/> -->
						<textarea class="form-control" id="descricao" name="descricao"  rows="5" cols="75%" style="border: none;outline: none; border-color: Transparent; overflow: auto;" required><%=estabelecimento.getDescricao()%></textarea>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="telefone" name="telefone" value="<%=estabelecimento.getTelefone()%>" placeholder="Telefone" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="endGoogle" name="endGoogle"  placeholder="Confirme no mapa ao lado..." value="<%=estabelecimento.getEndGoogle()%>" required />
						<input id="endGoogleHidden" name="endGoogleHidden" type="hidden" value="<%=estabelecimento.getEndGoogle()%>" required>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<input type="submit" id="butao" name="butao" role="button" class="btnproprio" value="Atualizar">
					<input type="submit" id="butao" name="butao" role="button" class="btnproprio" onclick="liberarRequerimento()" value="Voltar">
					<input type="submit" id="butao" name="butao" role="button" class="btnproprio" onclick="liberarRequerimento()" value="Excluir">
						<div class="clear"> </div>
				</ul>
				<ul class="right-form">
				<br><br>
					<h1> Escolha um dos endereços sugeridos </h1>
					<jsp:include page="/procurar_mapa.jsp">
						<jsp:param value="nada" name="nada"/>
					</jsp:include>
				</ul>
				<div class="clear"> </div>
					
			</form>
			
			<p class="copy_right">© Fastchoice 2015 Todos os direitos reservados | Template por FastChoice</a> </p>
			
		</div>
</body>
</html>