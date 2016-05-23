package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Pessoa;
import br.com.fastchoice.model.entities.Reserva;

@WebServlet("/LoadCaronas")
public class LoadCaronas extends HttpServlet {

    private static final long serialVersionUID = -5574115392121L;

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Pessoa pessoa = Controller.getPessoa();
	String sql = "SELECT * FROM reserva WHERE id_pessoa="+pessoa.getId();
	List<Reserva> minhasReservas = (List<Reserva>) Dao.getInstance().readQuery("reserva", sql);
	List<Reserva> outrosReservas = (List<Reserva>) Dao.getInstance().read("reserva");
	List<Pessoa> amigos = Controller.getAmizades();
	List<Reserva> caronaReservas = new ArrayList<Reserva>();
	
	for (Iterator<Reserva> it = outrosReservas.iterator(); it.hasNext();) {
	    Reserva r = it.next();
	    for (Iterator<Reserva> itMinha = minhasReservas.iterator(); itMinha.hasNext();) {
		Reserva rMinha = itMinha.next();
		// 1:SE não é a mesma; 2:SE é de amigos; 3:SE minhas tem o msm estabelecimento; 4:SE está na data/horário próximo
		if (r.getId() != rMinha.getId()) { //1
		    for (Pessoa amigo : amigos) {
			if (r.getIdPessoa() == amigo.getId() && r.getIdEstabelecimento() == rMinha.getIdEstabelecimento()) {//2 && 3
			    if (dataAproximada(r.getData(), rMinha.getData())) { //4
				caronaReservas.add(r); // add carona do outro
			    }			    
			}
		    }
		}
	    }

	}
	System.out.println("caronaReservas size:"+caronaReservas.size());
	request.getSession().setAttribute("caronaReservas", caronaReservas);
	request.getSession().setAttribute("minhasReservas", minhasReservas);
	response.sendRedirect("/fastchoice/caronas.jsp");
    }

    @SuppressWarnings("deprecation")
    public boolean dataAproximada (Date date1, Date date2) {
	
	if (date1.getYear() == date2.getYear() && 
		date1.getMonth() == date2.getMonth() &&
		date1.getDate() == date2.getDate()) {
	    int data1hora = date1.getHours();
	    int data2hora = date2.getHours();
	    int distanceHour = data1hora - data2hora;
	    int data1min = date1.getMinutes();
	    int data2min = date2.getMinutes();
	    int distanceMinute = data1min - data2min;
	    if (distanceHour == 0 || ((distanceHour == 1 || distanceHour == -1) && (distanceMinute <= 30 && distanceMinute >= -30))) {
		return true;
	    }
	}
	return false;
    }
    
}
