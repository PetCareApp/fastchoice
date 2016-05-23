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
		    	 document.getElementById("descricao").removeAttribute('required');
		    	 document.getElementById("telefone").removeAttribute('required');
		    	 document.getElementById("endGoogle").removeAttribute('required');
		}
		</script>
</head>
<body>
	<div class="main">
		<div class="header" >
			<h1>Cadastrar estabelecimento!</h1>
		</div>
		<p>Dados necessários para cadastramento de estabelecimento no FastChoice</p>
			<form action="SendEstabelecimentoCadastro" method="post" enctype="multipart/form-data">
				<ul class="left-form">
					<h2>Novo estabelecimento:</h2>
					<li>
			            <label for="foto">Foto</label>
          				<input type="file" id="fotoFile" name="fotoFile" required>
          				<a href="#" class="icon ticker"> </a>
          			</li>
					<li>
						<input type="text" id="nome" name="nome"  placeholder="Nome" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
<!-- 						<input type="textArea"  id="descricao" name="descricao" placeholder="Descrição" required/> -->
						<textarea class="form-control" id="descricao" name="descricao"  rows="5" cols="75%" style="border: none;outline: none; border-color: Transparent; overflow: auto;" required></textarea>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="telefone" name="telefone"  placeholder="Telefone" required/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="endGoogle" name="endGoogle"  placeholder="Confirme no mapa ao lado..." value="" required />
						<input id="endGoogleHidden" name="endGoogleHidden" type="hidden" value="" required>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li> 
					<input type="submit" id="butao" name="butao" role="button" class="btnproprio" value="Criar conta">
					<input type="submit" id="butao" name="butao" role="button" class="btnproprio" onclick="liberarRequerimento()" value="Cancelar">
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