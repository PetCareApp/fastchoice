package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Estabelecimento;
import br.com.fastchoice.model.entities.Reserva;

@WebServlet("/SendReserva")
public class SendReserva extends HttpServlet {

	private static final long serialVersionUID = -3000L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Estabelecimento estabelecimento = (Estabelecimento) session.getAttribute("estabelecimentoBuscado");
		System.out.println("sendreserva mexendo em "+estabelecimento.getNome());
		Reserva reservaFeita = (Reserva) session.getAttribute("reservaFeita");
		
		if (reservaFeita == null) {
			if (estabelecimento.getVagasDisponiveis() > 0) {
				estabelecimento.subVagasDisponiveis();
			}
			
			Reserva reserva = new Reserva();
			reserva.setIdEstabelecimento(estabelecimento.getId());
			reserva.setIdPessoa(Controller.getPessoa().getId());
			String rdate = request.getParameter("rdate");
			String rtime = request.getParameter("rtime");
			System.out.println("dia: "+rdate);
			System.out.println("horario: "+rtime);
			int dia = Integer.parseInt(rdate.substring(8, 10));
			int mes = Integer.parseInt(rdate.substring(5, 7));
			mes -= 1;
			int ano = Integer.parseInt(rdate.substring(0, 4));
			ano -= 1900;
			int hora = Integer.parseInt(rtime.substring(0, 2));
			int minuto = Integer.parseInt(rtime.substring(3, 5));
			Date data = new Date(ano, mes, dia, hora, minuto);
			
			java.util.Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			cal.set(Calendar.HOUR_OF_DAY, hora);
			cal.set(Calendar.MINUTE, minuto);
			
			reserva.setData(data);
			
			estabelecimento.getReservas().add(reserva);
	
			Dao.getInstance().create(reserva);
			Dao.getInstance().update(estabelecimento);
		} else {
			estabelecimento.removeReserva(reservaFeita);
			Dao.getInstance().update(estabelecimento);
			Dao.getInstance().delete(reservaFeita);
			session.setAttribute("reservaFeita", null);
		}
		estabelecimento = (Estabelecimento) Dao.getInstance().readWithId("estabelecimento", estabelecimento.getId());
		session.setAttribute("estabelecimentoBuscado", estabelecimento);
		
		response.sendRedirect("/fastchoice/estabelecimento.jsp");
	}

}
