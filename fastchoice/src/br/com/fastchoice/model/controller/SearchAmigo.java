package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Amigo;
import br.com.fastchoice.model.entities.Pessoa;

@WebServlet("/SearchAmigo")
public class SearchAmigo extends HttpServlet {

	private static final long serialVersionUID = -30523340L;
	private static List<Pessoa> amigosBuscados;
	
	public SearchAmigo() {
		amigosBuscados = new ArrayList<Pessoa>();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String busca = request.getParameter("buscatext");
		
		long idPessoa = Controller.getPessoa().getId();
		
		amigosBuscados.clear();
		List<Pessoa> pessoas = (List<Pessoa>) Dao.getInstance().read("Pessoa");
		
		for (Iterator<Pessoa> iterator = pessoas.iterator(); iterator.hasNext();) {
			Pessoa p = iterator.next();
			if (!p.getNome().toLowerCase().contains(busca.toLowerCase())) {
				iterator.remove();
			} else {
				List<Amigo> amigosDeP = p.getAmigos(); 
				for (Amigo a: amigosDeP) {
					if ((a.getIdAmigo() == idPessoa || a.getIdPessoa() == idPessoa) && p.getId() != idPessoa) {
						amigosBuscados.add(p);
						iterator.remove();
					}
				}
			}
		}
		//ordenar por nome
		Collections.sort(amigosBuscados, new Comparator() {
		    @Override
		    public int compare(Object amigo, Object amigo2) {
		    	return ((Pessoa)amigo).getNome().compareTo(((Pessoa)amigo2).getNome());
		    }
		}); 
		    
		// atualiza na sessão    
		HttpSession session = request.getSession();
		session.setAttribute("naoAmigosBuscados", pessoas);
		session.setAttribute("amigosBuscados", amigosBuscados);

		response.sendRedirect("/fastchoice/chat.jsp");
	}

}
