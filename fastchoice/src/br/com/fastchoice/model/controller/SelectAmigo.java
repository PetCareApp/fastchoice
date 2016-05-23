package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Mensagem;
import br.com.fastchoice.model.entities.Pessoa;

@WebServlet("/SelectAmigo")
public class SelectAmigo extends HttpServlet {

	private static final long serialVersionUID = -3000197083223340L;
	
	private static SelectAmigo instance;

	public static SelectAmigo getInstance() {
		if (instance == null) {
			instance = new SelectAmigo();
		}
		return instance;
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		long amigoId = Long.parseLong(request.getParameter("amigoId"));
		
		Pessoa amigo = (Pessoa) Dao.getInstance().readWithId("Pessoa", amigoId);
		
		HttpSession session = request.getSession();
		session.setAttribute("amigoChat", amigo);
		updateMensagensAmigos(amigoId, session);

		response.sendRedirect("/fastchoice/chat.jsp");
	}
	
	public static void updateMensagensAmigos(long amigoId, HttpSession session) {
		String sql = "SELECT * FROM mensagem WHERE (id_remetente="+Controller.getPessoa().getId()+" AND id_destinario="+amigoId+") OR (id_remetente="+amigoId+" AND id_destinario="+Controller.getPessoa().getId()+")";
		List<Mensagem> mensagens = (List<Mensagem>) Dao.getInstance().readQuery("Mensagem", sql);
		session.setAttribute("mensagensAmigoChat", mensagens);
		//ordenar MSGS por data
	}

}
