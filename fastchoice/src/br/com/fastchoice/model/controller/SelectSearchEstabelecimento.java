package br.com.fastchoice.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Estabelecimento;

@WebServlet("/SelectSearchEstabelecimento")
public class SelectSearchEstabelecimento extends HttpServlet {

	private static final long serialVersionUID = -300019723340L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		long estabId = Long.parseLong(request.getParameter("estabId"));
		Estabelecimento estabelecimento = (Estabelecimento) Dao.getInstance().readWithId("estabelecimento", estabId);
		HttpSession session = request.getSession();
		session.setAttribute("estabelecimentoBuscado", estabelecimento);
		
		response.sendRedirect("/fastchoice/estabelecimento.jsp");
	}

}
