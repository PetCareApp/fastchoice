package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Estabelecimento;

@WebServlet("/SendSearch")
public class SendSearch extends HttpServlet {

	private static final long serialVersionUID = -3000197083223340L;
	
	private static SendSearch instance;
	private static List<Estabelecimento> estabelecimentos;

	public static SendSearch getInstance() {
		if (instance == null) {
			instance = new SendSearch();
		}
		return instance;
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	    	String novo = request.getParameter("novo");
	    	if (novo != null && novo.equalsIgnoreCase("novo") && Controller.getPessoa().getId() == 1) {
	    	    response.sendRedirect("/fastchoice/cadastro_estabelecimento.jsp");
	    	}
	    
		if (estabelecimentos != null) {
			estabelecimentos.clear();
		}
		
		String busca = request.getParameter("buscatext");

		estabelecimentos = (List<Estabelecimento>) Dao.getInstance().read("estabelecimento");
		
		for (Iterator<Estabelecimento> iterator = estabelecimentos.iterator(); iterator.hasNext();) {
			Estabelecimento e = iterator.next();
			if (!e.getNome().toLowerCase().contains(busca.toLowerCase()) && !e.getEndRua().toLowerCase().contains(busca.toLowerCase()) && !e.getEndBairro().toLowerCase().contains(busca.toLowerCase())) {
				iterator.remove();
			}
		}
		//ordenar ESTAB por nome

		response.sendRedirect("/fastchoice/estabelecimento.jsp");
	}

	public static List<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}

	public static void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
		SendSearch.estabelecimentos = estabelecimentos;
	}

}
