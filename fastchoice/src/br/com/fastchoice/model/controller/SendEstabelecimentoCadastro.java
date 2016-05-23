package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Estabelecimento;

@WebServlet("/SendEstabelecimentoCadastro")
public class SendEstabelecimentoCadastro extends HttpServlet {

    private static final long serialVersionUID = 6L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	    IOException {

	String butao = request.getParameter("butao");
	Estabelecimento e = new Estabelecimento();
	String nome = request.getParameter("nome");
	String descricao = request.getParameter("descricao");
	String tipo = request.getParameter("tipo");
	String telefone = request.getParameter("telefone");
	String endGoogle = request.getParameter("endGoogleHidden");

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
			e.setFoto(bytes);
			e.setFotoExtension(FilenameUtils.getExtension(item.getName()));
		    } else {
			if (item != null && item.getFieldName() != null) {
			    switch (item.getFieldName()) {
			    case "nome":
				nome = item.getString();
				break;
			    case "descricao":
				descricao = item.getString();
				break;
			    case "tipo":
				tipo = item.getString();
				break;
			    case "telefone":
				telefone = item.getString();
				break;
			    case "endGoogle":
				endGoogle = item.getString();
				System.out.println("endGoogle switch: "+endGoogle);
				break;
			    case "endGoogleHidden":
				endGoogle = item.getString();
				System.out.println("endGoogleHidden switch: "+endGoogle);
				break;
			    case "butao":
				butao = item.getString();
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

	if (butao.equals("Cancelar")) {
	    response.sendRedirect("/fastchoice/chat.jsp");
	}

	System.out.println("endGoogle: "+endGoogle);
	e.setDescricao(descricao);
	e.setNome(nome);
	e.setEndGoogle(endGoogle);
	e.setTelefone(telefone);

	Dao.getInstance().create(e);
	response.sendRedirect("/fastchoice/estabelecimento_cadastro.jsp");
    }
}
