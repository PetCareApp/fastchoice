package br.com.fastchoice.model.amigos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.controller.Controller;
import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Amigo;
import br.com.fastchoice.model.entities.Pessoa;

@WebServlet("/Amigos")
public class Amigos extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<Pessoa> amizades;

	public Amigos() {
		super();
		amizades = new ArrayList<Pessoa>();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		amizades.clear();
		List<Amigo> amigos = (List<Amigo>) Dao.getInstance().readWithId("Amigo", Controller.getPessoa().getId());
		System.out.println("amigos size: "+amigos.size());
		for (Amigo a: amigos) {
			Pessoa p =  (Pessoa) Dao.getInstance().readWithId("Pessoa", a.getIdAmigo());
			amizades.add(p);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("amizades", amizades);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
