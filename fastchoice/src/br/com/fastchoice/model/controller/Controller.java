package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restfb.types.User;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Amigo;
import br.com.fastchoice.model.entities.Conta;
import br.com.fastchoice.model.entities.Pessoa;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Controller instance;
	private static boolean loginStatus = false;
	private static Pessoa pessoa;
	private static List<Pessoa> amizades;
	private static Conta conta;

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public Controller() {
		amizades = new ArrayList<Pessoa>();
	}
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String login = request.getParameter("form-username");
		String senha = request.getParameter("form-password");
		
		//facebook
		String isFacebook = (String) request.getSession().getAttribute("isFacebook");
		Conta contaAdmin = (Conta) Dao.getInstance().readWithId("conta", 1);
		
		if (isFacebook != null && isFacebook.equalsIgnoreCase("yes")) {
		    User user = (User) request.getSession().getAttribute("facebookUser");
		    String facebookId = user.getId(); 
		    String sql = "SELECT * FROM conta WHERE facebook="+facebookId;
		    List<Conta> contas = (List<Conta>) Dao.getInstance().readQuery("conta", sql);
		    System.out.println("contas: "+contas.size());
		    if (contas != null && !contas.isEmpty()) {
			System.out.println("user FB ja tem conta");
			conta = contas.get(0);
			Controller.pessoa = (Pessoa) Dao.getInstance().readWithId("Pessoa", conta.getIdPessoa());
			Controller.loginStatus = true;
			loadAmigos(request, response);
			response.sendRedirect("/fastchoice/chat.jsp");
		    } else {
			System.out.println("user FB tem que cadastrar!");
			response.sendRedirect("/fastchoice/cadastro_perfil.jsp");
		    }
		} else if (login.equalsIgnoreCase("admin") && senha.equals(contaAdmin.getSenha())) {
			Controller.loginStatus = true;
			Controller.pessoa = (Pessoa) Dao.getInstance().readWithId("Pessoa", 1);
			Controller.conta = contaAdmin;
			loadAmigos(request, response);
			response.sendRedirect("/fastchoice/chat.jsp");
		} else {
			List<Conta> contas = (List<Conta>) Dao.getInstance().read("conta");
			for (Conta c : contas) {
				if (c.getUsuario().equalsIgnoreCase(login) && c.getSenha().equals(senha)) {
					Controller.setPessoa((Pessoa) Dao.getInstance().readWithId("pessoa", c.getIdPessoa()));
					Controller.conta = c;
					session.setAttribute("loginOk", true);
					loadAmigos(request, response);
					response.sendRedirect("/fastchoice/chat.jsp");
				}
			}
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 out.println("O <h3>usuário</h3> ou a <h3>senha</h3> estão incorretos. Por favor, tente novamente.");
				 out.close();
				 return;
		}

	}
	
	public void loadAmigos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (amizades != null) {
			amizades.clear();
		}

		List<Amigo> amigos = (List<Amigo>) Dao.getInstance().readWithId("Amigo", pessoa.getId());
		for (Amigo a : amigos) {
			long idPraBuscar;
			if (a.getIdPessoa() != pessoa.getId()) {
				idPraBuscar = a.getIdPessoa();
			} else {
				idPraBuscar = a.getIdAmigo();
			}
			amizades.add((Pessoa) Dao.getInstance().readWithId("Pessoa", idPraBuscar));
		}
		//ordenar por nome
		ordenarAmigos();
		
		HttpSession session = request.getSession();
		session.setAttribute("amizades", amizades);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void ordenarAmigos() {
	    Collections.sort(amizades, new Comparator() {
	            @Override
	            public int compare(Object amigo, Object amigo2) {
	                return ((Pessoa)amigo).getNome().compareTo(((Pessoa)amigo2).getNome());
	            }
	        }); 
	}
	
	public static boolean isLoginStatus() {
		return loginStatus;
	}

	public static void setLoginStatus(boolean loginStatus) {
		Controller.loginStatus = loginStatus;
	}

	public static Pessoa getPessoa() {
		return pessoa;
	}

	public static void setPessoa(Pessoa pessoa) {
		Controller.pessoa = pessoa;
	}

	public static List<Pessoa> getAmizades() {
		return amizades;
	}

	public static void setAmizades(List<Pessoa> amizades) {
		Controller.amizades = amizades;
	}

	public static Conta getConta() {
		return conta;
	}

	public static void setConta(Conta conta) {
		Controller.conta = conta;
	}

}
