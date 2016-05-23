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

@WebServlet("/EditPerfil")
public class EditPerfil extends HttpServlet {

    private static final long serialVersionUID = 6150516292653L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	    IOException {

	System.out.println("edit perfil");
	
	HttpSession session = request.getSession();
	String nome = request.getParameter("nome");
	String idade = request.getParameter("idade");
	String cpf = request.getParameter("cpf");
	String sexo = request.getParameter("sexo");
	String telefone = request.getParameter("telefone");
	String senha = request.getParameter("senha");
	String senhaConfirma = request.getParameter("senhaConfirma");
	String bairro = request.getParameter("bairro");
	String rua = request.getParameter("rua");
	String complemento = request.getParameter("complemento");
	String numero = request.getParameter("numero");
	String endGoogle = request.getParameter("endGoogleHidden");
	String email = request.getParameter("email");
	
	String butao = request.getParameter("butao");

	// foto
	// process only if its multipart content
	if (ServletFileUpload.isMultipartContent(request)) {
	    System.out.println("entrou no ServletFileUpload");
	    try {
		List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

		for (FileItem item : multiparts) {
		    if (!item.isFormField() && item != null) {
			System.out.println("vai escrever os bytes da foto");
			byte[] bytes = item.get();
			Pessoa pessoa = Controller.getPessoa();
			pessoa.setFoto(bytes);
			pessoa.setFotoExtension(FilenameUtils.getExtension(item.getName()));
			Dao.getInstance().update(pessoa);
		    } else {

			if (item != null && item.getFieldName() != null) {
			    switch (item.getFieldName()) {
			    case "butao":
				butao = item.getString();
				break;
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
			    case "senha":
				senha = item.getString();
				break;
			    case "senhaConfirma":
				senhaConfirma = item.getString();
				break;
			    case "bairro":
				bairro = item.getString();
				break;
			    case "rua":
				rua = item.getString();
				break;
			    case "endGoogleHidden":
				endGoogle = item.getString();
				break;
			    case "complemento":
				complemento = item.getString();
				break;
			    case "numero":
				numero = item.getString();
				break;
			    case "email":
				email = item.getString();
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

	if (butao.equalsIgnoreCase("Voltar")) {
	    response.sendRedirect("/fastchoice/chat.jsp");
	} else if (butao.equalsIgnoreCase("Excluir")) {
	    Dao.getInstance().delete(Controller.getPessoa());
	    session.invalidate();
	    response.sendRedirect("/fastchoice/login.jsp");
	} else if (butao.equalsIgnoreCase("Atualizar")) {

	    if (!senha.equals(senhaConfirma)) {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("A <h3>senha</h3> e sua <h3>confirmação</h3> não estão iguais. Por favor, digite novamente.");
		out.close();
		response.sendRedirect("/fastchoice/editar_perfil.jsp");
		return;
	    }

	    Pessoa pessoa = Controller.getPessoa();

	    pessoa.setNome(nome);
//	    pessoa.setIdade(Byte.parseByte(idade));
	    pessoa.setCpf(cpf);
	    pessoa.setTelefone(telefone);
	    pessoa.setEndBairro(bairro);
	    pessoa.setEndRua(rua);
	    pessoa.setEndComplemento(complemento);
//	    pessoa.setEndNumero(Short.parseShort(numero));
	    pessoa.setEndGoogle(endGoogle);
	    
	    Conta conta = Controller.getConta();
	    conta.setEmail(email);
	    conta.setSenha(senha);
	    Dao.getInstance().update(conta);

	    
//	    if (sexo.equalsIgnoreCase("masculino")) {
//		pessoa.setMasculino(true);
//	    } else {
//		pessoa.setMasculino(false);
//	    }

	    Dao.getInstance().update(pessoa);


	    response.sendRedirect("/fastchoice/editar_perfil.jsp");
	}
    }

}
