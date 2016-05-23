<%@page import="java.util.Date"%>
<%@page import="br.com.fastchoice.model.entities.Reserva"%>
<%@page import="br.com.fastchoice.model.controller.SelectSearchEstabelecimento"%>
<%@page import="br.com.fastchoice.model.controller.SendSearch"%>
<%@page import="br.com.fastchoice.model.entities.Avaliacao"%>
<%@page import="br.com.fastchoice.model.controller.Controller"%>
<%@page import="br.com.fastchoice.model.entities.Pessoa"%>
<%@page import="br.com.fastchoice.model.entities.Comentario"%>
<%@page import="java.util.List"%>
<%@page import="br.com.fastchoice.model.dao.Dao"%>
<%@page import="br.com.fastchoice.model.entities.Estabelecimento"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Piccolo Theme</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/bootstrapEstab.css">
<link rel="stylesheet" href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/jquery.lightbox-0.5.css">
<link rel="stylesheet" href="css/custom-styles.css">
    <link href="assets/css/star.css" rel="stylesheet"> 


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
     
     <%
try {
	Estabelecimento estabelecimento = (Estabelecimento) session.getAttribute("estabelecimentoBuscado");
	session.setAttribute("imagemEstabelecimento", estabelecimento);
	List<Reserva> reservas = estabelecimento.getReservas();
	int avaliacao = 0;
	Pessoa pessoa = Controller.getInstance().getPessoa();
	String avaliacaoEscrita = ""+estabelecimento.getAvaliacao();
	avaliacaoEscrita = avaliacaoEscrita.substring(0, 3);
	String idEstab = ""+estabelecimento.getId();
%>
    <!-- Blog Content
    ================================================== --> 
    <div class="row"><!--Container row-->

        <!-- Blog Full Post
        ================================================== --> 
        <div class="span8 blog">

            <!-- Blog Post 1 -->
            <article>
                <h3 class="title-bg"><a href="#"><%=estabelecimento.getNome()%></a></h3>
                <div class="post-content">
                   <c:import url="display_image.jsp">
                	<c:param name="entidade" value="estabele"/>
  					<c:param name="idNumber" value="<%=idEstab%>"/>
  					<c:param name="altura" value="600"/>
					<c:param name="largura" value="600"/>
					<c:param name="classe" value="img-responsive"/>
				</c:import>
<% if (pessoa.getId() == 1) {%>
	 <a href="editar_estabelecimento.jsp" onClick="this.disabled=true;" id="estabEdit" name="estabEdit" type="button" class="btn btn-default" style="box-sizing: border-box; line-height: 5px; background: rgb(0,180,0); width: 10%; height: 20px; color: rgb(255,255,255); font-family: 'Roboto', sans-serif; font-size: 14;">Editar</a>
<% } %>

                    <div class="post-body">
                         <p><%=estabelecimento.getDescricao()%></p>

                  <div class="ratings">
                       <%
                        byte estrelas = (byte) estabelecimento.getAvaliacao();
                        for(int i=0; i < estrelas; i++) {
                        %>
                            <span class="glyphicon glyphicon-star"></span>
                        <%
                        } 
                        byte estrelasOFF = (byte)(5 - estrelas);
                        for(int i=0; i < estrelasOFF; i++) {
                        %>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        <%} %>
                            <%=avaliacaoEscrita%> estrelas
                        <br>    
                    <p class="pull-left">Vagas: <%=estabelecimento.getVagasDisponiveis()+" / "+estabelecimento.getVagasTotais()+" ("+estabelecimento.getVisitas()+" visitas)"%> </p>
                    <%
                    String rdate = null;
                    String rtime = null;
                    String nomeBotao = "Reservar";
                    if (reservas == null || reservas.isEmpty()) {
                    	session.setAttribute("reservaFeita", null);
                    	rdate = "";
                    	rtime = "";
                    }
                    for (Reserva r : reservas) {
                    	if (r.getIdPessoa() == pessoa.getId()) {
                    		nomeBotao = "Cancelar Reserva";
                    		session.setAttribute("reservaFeita", r);
                    		rdate = (1900+r.getData().getYear())+"-"+r.getData().getMonth()+"-"+r.getData().getDate();
                    		rtime = r.getData().getHours()+":"+r.getData().getMinutes();
                    		System.out.println("data "+rdate);
                    	}
                    }
                    %>
                    </div>
                    <div class="text-right">
	                    <form action="SendReserva" method="post" class="SendReserva">
	                    <%if (nomeBotao.equalsIgnoreCase("Reservar")) { %>
	                    	<input type="date" style="vertical-align:baseline; box-sizing: border-box; line-height: 12px;" id="rdate" name="rdate" value="<%=rdate%>" />
							<input type="time" style="vertical-align:baseline; box-sizing: border-box; line-height: 12px;" id="rtime" name="rtime" value="<%=rtime%>" />
							<%} else { %>
							<input type="date" style="vertical-align:baseline; box-sizing: border-box; line-height: 12px;" id="rdate" name="rdate" disabled value="<%=rdate%>" />
							<input type="time" style="vertical-align:baseline; box-sizing: border-box; line-height: 12px;" id="rtime" name="rtime" disabled value="<%=rtime%>" />
							<% } %>
                        	<br/><button style="margin-top: 10px;" id="nomeBotao" name="nomeBotao" type="submit" class="btn btn-success"><%=nomeBotao%></button>
                    	</form>
                    </div>
                </div>

                 
                    </div>
            </article>

          

        <!-- Post Comments
        ================================================== --> 
            <section class="comments">
                <!-- Comment Form -->
                <div class="comment-form-container">
                    <h6>Vote e comente:</h6>
                    <form role="form" action="SendComentario" method="post" class="SendComentario">
                    <input type="hidden" name="estabId" value=<%=estabelecimento.getId()%> >
                    <p>
				      <span class="starRating">
                   	<%
            		List<Avaliacao> readAvaliacao = (List<Avaliacao>) Dao.getInstance().readQuery("avaliacao", "SELECT * FROM avaliacao WHERE id_pessoa="+pessoa.getId()+" AND id_estabelecimento="+estabelecimento.getId()+" ");
                   	byte nota = 0;
					if (readAvaliacao.size() > 0) {
	                   	Avaliacao avaliacaoPessoa = readAvaliacao.get(0);
	                   	nota = avaliacaoPessoa.getValor();
					}
					for(int i=5; i>0; i--) {
                   	%>
				        <input id=<%="rating"+i%> type="radio" name="rating" value=<%=i%> <%if(nota==i){%>checked<%}%>>
				        <label for=<%="rating"+i%>><%=i%></label>
				    <%
					}
				    %>    
				      </span>
				      </p>
                    <textarea id="comment" name="comment" class="span6"></textarea>
                        <div class="row">
                            <div class="span2">
                                <input type="submit" role="button" class="btn btn-inverse" value="Postar meu comentário">
                            </div>
                        </div>
                    </form>
                </div>
               
                        <ul>
                        <%
					for (Comentario c: estabelecimento.getComentarios()) {
						Pessoa pessoaComentou = (Pessoa) Dao.getInstance().readWithId("Pessoa", c.getIdPessoa());
						String idPessoaComentou = pessoaComentou.getId()+"";
					%>
                            <li>
                            	<%
		                        byte estrelasC = c.getAvaliacao().getValor();
		                        for(int i=0; i < estrelasC; i++) {
		                        %>
		                            <span class="glyphicon glyphicon-star"></span>
		                        <%
		                        } 
		                        int estrelasOFFC = (int) 5 - estrelasC;
		                        for(int i=0; i < estrelasOFFC; i++) {
		                        %>
		                        <span class="glyphicon glyphicon-star-empty"></span>
		                        <%} %>
		                        	 <span class="comment-name"><%=pessoaComentou.getNome()%></span>
		                        	 <br>
		                            <span class="comment-date"><%=c.getData()%></span>
									<div class="comment-content"><%=c.getComentario()%></div>
		                        <div class="foto" style="width: 100px; height: 100px">   
                                <c:import url="display_image.jsp">
				                	<c:param name="entidade" value="naoAmigo"/>
				  					<c:param name="idNumber" value="<%=idPessoaComentou%>"/>
				  					<c:param name="altura" value="64"/>
									<c:param name="largura" value="64"/>
									<c:param name="classe" value="img-responsive"/>
								</c:import>
								</div>
                            </li>
							<%
					}
					%>
                         </ul>
                   
            
                
        </section><!-- Close comments section-->

        </div><!--Close container row-->

        <!-- Blog Sidebar
        ================================================== --> 

    </div>
    
    </div> <!-- End Container -->
        <%
} catch (Throwable e) {
	e.printStackTrace();
}
%>

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

