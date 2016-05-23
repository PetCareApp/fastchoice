package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Estabelecimento;
import br.com.fastchoice.model.entities.Reserva;

@WebServlet("/SearchReserva")
public class SearchReserva extends HttpServlet {

	private static final long serialVersionUID = -223340L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String busca = request.getParameter("buscatext");
		
		String sql = "SELECT * FROM reserva WHERE id_pessoa="+Controller.getPessoa().getId();
		List<Reserva> reservas = (List<Reserva>) Dao.getInstance().readQuery("reserva", sql);

		List<Estabelecimento> estabelecimentosReservados = new ArrayList<Estabelecimento>();
		for (Reserva r: reservas) {
			Estabelecimento e = (Estabelecimento) Dao.getInstance().readWithId("estabelecimento", r.getIdEstabelecimento());
			estabelecimentosReservados.add(e);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("estabelecimentosReservados", estabelecimentosReservados);

		//ordenar ESTAB por nome

		response.sendRedirect("/fastchoice/reservas.jsp");
	}

}
