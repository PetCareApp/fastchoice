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

@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {

	private static final long serialVersionUID = -3000197083253523340L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String msg = request.getParameter("usermsg");
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
		
		response.sendRedirect("/fastchoice/chat.jsp");
	}

}
