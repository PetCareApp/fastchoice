package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Conta;
import br.com.fastchoice.model.entities.Pessoa;

import com.restfb.types.User;

@WebServlet("/SendPerfil")
public class SendPerfil extends HttpServlet {

    private static final long serialVersionUID = 61505185956292653L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	    IOException {

	HttpSession session = request.getSession();

	String isFacebook = (String) request.getSession().getAttribute("isFacebook");

	String nome = null;
	User user = null;
	
	if (isFacebook != null && isFacebook.equalsIgnoreCase("yes")) {
	    user = (User) request.getSession().getAttribute("facebookUser");
	    nome = user.getName();
	} else {
	    nome = request.getParameter("nome");
	}
	String idade = request.getParameter("idade");
	String cpf = request.getParameter("cpf");
	String sexo = request.getParameter("sexo");
	String telefone = request.getParameter("telefone");
	String usuario = request.getParameter("usuario");
	String senha = request.getParameter("senha");
	String bairro = request.getParameter("bairro");
	String rua = request.getParameter("rua");
	String complemento = request.getParameter("complemento");
	String numero = request.getParameter("numero");
	String endGoogle = request.getParameter("endGoogleHidden");
	String email = request.getParameter("email");
	String butao = request.getParameter("butao");

	List<Conta> contas = (List<Conta>) Dao.getInstance().read("conta");
	for (Conta c : contas) {
	    if (c.getUsuario().equalsIgnoreCase(usuario)) {
		session.setAttribute("loginOk", false);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("O usuário<h3>" + usuario + " " + "</h3> já está em uso. Por favor, escolha outro.");
		out.close();
		response.sendRedirect("/fastchoice/login.jsp");
		return;
	    }
	}

	Pessoa pessoa = new Pessoa();

	// foto
	// process only if its multipart content
	if (ServletFileUpload.isMultipartContent(request)) {
	    System.out.println("entrou no ServletFileUpload");
	    try {
		List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

		for (FileItem item : multiparts) {
		    if (!item.isFormField()) {
			System.out.println("vai escrever os bytes da foto");
			byte[] bytes = item.get();
			pessoa.setFoto(bytes);
			pessoa.setFotoExtension(FilenameUtils.getExtension(item.getName()));
		    } else {
			if (item != null && item.getFieldName() != null) {
			    switch (item.getFieldName()) {
			    case "nome":
				nome = item.getString();
				break;
			    case "idade":
				idade = item.getString();
				break;
			    case "cpf":
				cpf = item.getString();
				break;
			    case "telefone":
				telefone = item.getString();
				break;
			    case "sexo":
				sexo = item.getString();
				break;
			    case "usuario":
				usuario = item.getString();
				break;
			    case "senha":
				senha = item.getString();
				break;
			    case "bairro":
				bairro = item.getString();
				break;
			    case "rua":
				rua = item.getString();
				break;
			    case "complemento":
				complemento = item.getString();
				break;
			    case "numero":
				numero = item.getString();
				break;
			    case "endGoogle":
				endGoogle = item.getString();
				System.out.println("endGoogle switch: "+endGoogle);
				break;
			    case "endGoogleHidden":
				endGoogle = item.getString();
				System.out.println("endGoogleHidden switch: "+endGoogle);
				break;
			    case "email":
				email = item.getString();
				break;
			    case "butao":
				butao = item.getString();
				if (butao != null && butao.equalsIgnoreCase("voltar")) {
				    session.invalidate();
					response.sendRedirect("/fastchoice/login.jsp");
				}
				break;
			    }
			}
		    }
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }

	} else {
	    System.out.println("leu porra nenhuma kkkk");
	}

	pessoa.setNome(nome);
//	pessoa.setIdade(Byte.parseByte(idade));
	pessoa.setCpf(cpf);
	pessoa.setTelefone(telefone);
	pessoa.setEndBairro(bairro);
	pessoa.setEndRua(rua);
	pessoa.setEndComplemento(complemento);
//	pessoa.setEndNumero(Short.parseShort(numero));
	pessoa.setEndGoogle(endGoogle);

//	if (sexo.equalsIgnoreCase("masculino")) {
//	    pessoa.setMasculino(true);
//	} else {
//	    pessoa.setMasculino(false);
//	}

	Dao.getInstance().create(pessoa);

	String sql = "SELECT * FROM pessoa WHERE cpf=" + cpf;
	List<Pessoa> busca = (List<Pessoa>) Dao.getInstance().readQuery("pessoa", sql);
	pessoa = busca.get(0);

	Conta conta = new Conta();
	conta.setIdPessoa(pessoa.getId());
	conta.setUsuario(usuario);
	conta.setSenha(senha);
	conta.setEmail(email);
	if (user != null) {
	    conta.setFacebook(user.getId());
	    Dao.getInstance().create(conta);

	    response.sendRedirect("/fastchoice/Controller");
	} else {
	    Dao.getInstance().create(conta);
	    response.sendRedirect("/fastchoice/login.jsp");
	}
    }

}
