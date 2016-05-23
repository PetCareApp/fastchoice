package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Mensagem;

@WebServlet("/SendCarona")
public class SendCarona extends HttpServlet {

	private static final long serialVersionUID = -300L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String botao = request.getParameter("caronaBotao");
		String amigo = request.getParameter("amigoNome");
		String estabelecimento = request.getParameter("estabelecimento");
		String msg = null;
		if(botao.equalsIgnoreCase("oferecer")) {
			msg = "Oi "+amigo+"! Também irei para o "+estabelecimento+" hoje!  Quer carona?";
		} else {
			msg = "Oi "+amigo+"! Também irei para o "+estabelecimento+" hoje! Pode me dar carona?";
		}
		
		long idRemetente = Controller.getPessoa().getId();
		long idDestinario = Long.parseLong(request.getParameter("amigoId"));
		Date data = new Date(Calendar.getInstance().getTime().getTime());
		
		Mensagem mensagem = new Mensagem();
		mensagem.setMsg(msg);
		mensagem.setData(data);
		mensagem.setIdDestinario(idDestinario);
		mensagem.setIdRemetente(idRemetente);
		
		Dao.getInstance().create(mensagem);
		
		HttpSession session = request.getSession();
		SelectAmigo.getInstance().updateMensagensAmigos(idDestinario, session);
		
		response.sendRedirect("/fastchoice/caronas.jsp");
	}

}
