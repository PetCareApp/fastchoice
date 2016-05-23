<%@page import="com.restfb.types.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		    	 document.getElementById("usuario").removeAttribute('required');
		    	 document.getElementById("email").removeAttribute('required');
		    	 document.getElementById("endGoogle").removeAttribute('required');
		    	 document.getElementById("cpf").removeAttribute('required');
		    	 document.getElementById("senha").removeAttribute('required');
		    	 document.getElementById("senhaConfirma").removeAttribute('required');
		}
		</script>
</head>
<body>
	<div class="main">
		<div class="header" >
			<h1>Criar conta!</h1>
		</div>
		<p>Cadastre-se para poder usar o FastChoice</p>
			<form action="SendPerfil" method="post" enctype="multipart/form-data">
				<ul class="left-form">
					<h2>Nova conta:</h2>
					<li>
			            <label for="foto">Foto</label>
          				<input type="file" id="fotoFile" name="fotoFile">
          			</li>
					<li>
						<input type="text" id="usuario" name="usuario"  placeholder="Usuário" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text"  id="email" name="email" placeholder="Email" required/>
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
					<%
					User user = (User) request.getSession().getAttribute("facebookUser");
					if (user != null) { %>
						<input type="text" id="nome" name="nome" readonly="readonly" value="<%=user.getName()%>"/>
					<% } else { %>
						<input type="text" id="nome" name="nome"  placeholder="Nome" required/>
					<% } %>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						<input type="text" id="cpf" name="cpf"  placeholder="CPF" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>  
					<li>
						<input type="text" id="endGoogle" name="endGoogle"  placeholder="Confirme no mapa ao lado..." required/>
						<input id="endGoogleHidden" name="endGoogleHidden" type="hidden" value="">
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<input type="submit" role="button" class="btnproprio" value="Criar conta">
					<input id="butao" name="butao" type="submit" role="button" class="btnproprio" onclick="liberarRequerimento()" value="Voltar">
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