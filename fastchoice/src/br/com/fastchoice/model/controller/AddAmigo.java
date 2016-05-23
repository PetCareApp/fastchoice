package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Amigo;
import br.com.fastchoice.model.entities.Pessoa;

@WebServlet("/AddAmigo")
public class AddAmigo extends HttpServlet {

    private static final long serialVersionUID = -5574115392121L;

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	try {
	    long id = Long.parseLong(request.getParameter("id"));
	    Pessoa pessoa = Controller.getPessoa();
	    Amigo amigo = new Amigo();
	    amigo.setIdAmigo(pessoa.getId());
	    amigo.setIdPessoa(id);
	    Dao.getInstance().create(amigo);
	    Controller.getInstance().loadAmigos(request, response);
	    
	    List<Pessoa> amigosBuscados = (List<Pessoa>) request.getSession().getAttribute("amigosBuscados");
	    List<Pessoa> naoAmigosBuscados = (List<Pessoa>) request.getSession().getAttribute("naoAmigosBuscados");
	    for (Iterator<Pessoa> it = naoAmigosBuscados.iterator(); it.hasNext();) {
		Pessoa a = it.next();
		if (a.getId() == id) {
		    amigosBuscados.add(a);
		    it.remove();
		}
	    }
	    
	    //ordenar por nome : amigos
	    Collections.sort(amigosBuscados, new Comparator() {
		@Override
	        public int compare(Object amigo, Object amigo2) {
		    return ((Pessoa)amigo).getNome().compareTo(((Pessoa)amigo2).getNome());
	    	}
	    }); 
	  //ordenar por nome: nao amigos
	    Collections.sort(naoAmigosBuscados, new Comparator() {
		@Override
	        public int compare(Object amigo, Object amigo2) {
		    return ((Pessoa)amigo).getNome().compareTo(((Pessoa)amigo2).getNome());
	    	}
	    }); 
	    
	    //atualiza na sessão
	    request.getSession().setAttribute("amigosBuscados", amigosBuscados);
	    request.getSession().setAttribute("naoAmigosBuscados", naoAmigosBuscados);
	    
	    response.sendRedirect("/fastchoice/chat.jsp");
//	    request.getRequestDispatcher("chat.jsp").forward(request, response);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
    }

}
