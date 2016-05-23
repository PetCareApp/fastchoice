<%@page import="br.com.fastchoice.model.entities.Conta"%>
<%@page import="br.com.fastchoice.model.entities.Pessoa"%>
<%@page import="br.com.fastchoice.model.controller.Controller"%>
<%@page import="com.restfb.types.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
		<title>Fastchoice perfil</title>
		<meta charset="utf-8">
		<link href="css/style2.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!--webfonts-->
		<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text.css'/>
		<!--//webfonts-->
		<script type="text/javascript">
		function liberarRequerimento() {
		    	 document.getElementById("cpf").removeAttribute('required');
		    	 document.getElementById("nome").removeAttribute('required');
		    	 document.getElementById("senha").removeAttribute('required');
		    	 document.getElementById("senhaConfirma").removeAttribute('required');
		    	 document.getElementById("email").removeAttribute('required');
		    	 document.getElementById("fotoFile").removeAttribute('required');
		    	 document.getElementById("endGoogle").removeAttribute('required');
		}
		</script>
</head>
<body>

	<div class="main">
		<div class="header" >
			<h1>Editar Perfil</h1>
		</div>
		 <% Pessoa pessoa = Controller.getPessoa(); 
		 Conta conta = Controller.getConta();%>
		 
		<p>Atualize os dados do seu perfil para um melhor uso do FastChoice</p>
			<form action="EditPerfil" method="post" enctype="multipart/form-data">
				<ul class="left-form">
					<h2>Atualização:</h2>
					<% String extension = pessoa.getFotoExtension();
			        String idPessoa = pessoa.getId()+"";
			        if (extension != null && !extension.isEmpty()) { %>
			        <c:import url="display_image.jsp">
						<c:param name="entidade" value="naoAmigo"/>
		  				<c:param name="idNumber" value="<%=idPessoa%>"/>
		  				<c:param name="altura" value="100"/>
						<c:param name="largura" value="100"/>
						<c:param name="classe" value="avatar img-circle"/>
					</c:import>
			        <% } %>
					<li>
			            <label for="foto">Foto</label>
          				<input type="file" id="fotoFile" name="fotoFile"/>
          			</li>
					<li>
						<input type="text"  id="email" name="email" placeholder="Email" value="<%=conta.getEmail()%>" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password" id="senha" name="senha"  placeholder="Senha" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password"  id="senhaConfirma" name="senhaConfirma" placeholder="Confirmação da senha" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="nome" name="nome"  placeholder="Nome" value="<%=pessoa.getNome()%>" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						<input type="text" id="cpf" name="cpf"  placeholder="CPF" value="<%=pessoa.getCpf()%>" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>  
					<li>
						<input type="text" id="endGoogle" name="endGoogle"  placeholder="Confirme no mapa ao lado..." value="<%=pessoa.getEndGoogle()%>" required/>
						<input id="endGoogleHidden" name="endGoogleHidden" type="hidden" value="<%=pessoa.getEndGoogle()%>">
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