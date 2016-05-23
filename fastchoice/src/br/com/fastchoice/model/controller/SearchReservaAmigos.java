package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle.Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Estabelecimento;
import br.com.fastchoice.model.entities.Pessoa;
import br.com.fastchoice.model.entities.Reserva;

@WebServlet("/SearchReservaAmigos")
public class SearchReservaAmigos extends HttpServlet {

	private static final long serialVersionUID = -2230L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String busca = request.getParameter("buscatext");
		
		List<Reserva> reservas = (List<Reserva>) Dao.getInstance().read("reserva");
		List<Reserva> reservaAmigos = new ArrayList<Reserva>();
		
		for (Pessoa a : Controller.getAmizades()) {
			if (a.getNome().toLowerCase().contains(busca.toLowerCase())) {
				for (Reserva r: reservas) {
					if (r.getIdPessoa() == a.getId()) {
						reservaAmigos.add(r);
					}
				}
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("reservaAmigos", reservaAmigos);

		//ordenar ESTAB por nome

		response.sendRedirect("/fastchoice/caronas.jsp");
	}
	
}

