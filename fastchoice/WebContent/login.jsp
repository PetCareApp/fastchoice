<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>	
<head>
<title>Login - FastChoice</title>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords"/>
<link href="css/style35.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='//fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Lato:100,300,400,700' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script src="js/jquery.min.js"></script>
</head>
<body>
<script>$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
 <!--SIGN UP-->
<div class="login-form">
	<div class="close"> </div>
		<h1>Login usuário</h1>
		<h2>Use sua rede social para entrar no site</h2>
				<ul class="bottom-sc-icons">
					<li><a href="loginFacebook" type="button" class="facebook"><img src="img/fb.png" />Logar via facebook</a></li>
					
					<div class="strip">OU</div>
				</ul>
			<form action="Controller" method="post" class="Controller">
				<input type="text" class="email" id="form-username" name="form-username" placeholder="Usuário" required/>
				<input type="password" class="password" id="form-password" name="form-password" placeholder="Senha" required/>
					<div class="but-bottom">
						<div class="p-container">
							
						</div>
															
					</div><br>
							<input type="submit" value="Entrar">
			</form>
						<p>Não é membro?<a href="cadastro_perfil.jsp" class="sign">Cadastre-se!</a></p>
</div>
 	<div class="copyrights">
			<p>&copy; 2015 Fastchoice Login. Todos os direitos reservados | Template by Fastchoice
		</div>
</body>
</html>