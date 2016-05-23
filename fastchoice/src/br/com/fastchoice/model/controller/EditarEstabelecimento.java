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

@WebServlet("/EditarEstabelecimento")
public class EditarEstabelecimento extends HttpServlet {

    private static final long serialVersionUID = 61505121113L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	    IOException {

	Estabelecimento e = (Estabelecimento) request.getSession().getAttribute("estabelecimentoBuscado");
	String butao = request.getParameter("butao");
	String nome = request.getParameter("nome");
	String descricao = request.getParameter("descricao");
	String tipo = request.getParameter("tipo");
	String telefone = request.getParameter("telefone");
	String bairro = request.getParameter("bairro");
	String rua = request.getParameter("rua");
	String cidade = request.getParameter("cidade");
	String complemento = request.getParameter("complemento");
	String numero = request.getParameter("numero");
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
			    case "bairro":
				bairro = item.getString();
				break;
			    case "rua":
				rua = item.getString();
				break;
			    case "endGoogleHidden":
				endGoogle = item.getString();
				break;
			    case "cidade":
				cidade = item.getString();
				break;
			    case "complemento":
				complemento = item.getString();
				break;
			    case "numero":
				numero = item.getString();
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

	if (butao.equals("Voltar")) {
	    response.sendRedirect("/fastchoice/chat.jsp");
	} else if (butao.equalsIgnoreCase("Excluir")) {
	    Dao.getInstance().delete(e);
	    request.getSession().setAttribute("estabelecimentoBuscado", null);
	    response.sendRedirect("/fastchoice/estabelecimentos.jsp");
	} else if (butao.equalsIgnoreCase("Atualizar")) {

	    e.setDescricao(descricao);
	    e.setNome(nome);
	    e.setEndBairro(bairro);
	    e.setEndComplemento(complemento);
//	    e.setEndNumero(Short.parseShort(numero));
	    e.setEndRua(rua);
	    e.setEndCidade(cidade);
	    e.setTelefone(telefone);
	    e.setEndGoogle(endGoogle);

	    Dao.getInstance().update(e);
	    response.sendRedirect("/fastchoice/editar_estabelecimento.jsp");
	}
    }

}
