package br.com.fastchoice.model.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Avaliacao;
import br.com.fastchoice.model.entities.Comentario;
import br.com.fastchoice.model.entities.Estabelecimento;

@WebServlet("/SendComentario")
public class SendComentario extends HttpServlet {

	private static final long serialVersionUID = -300019723423523340L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Estabelecimento estabelecimento = (Estabelecimento) session.getAttribute("estabelecimentoBuscado");
		
		String estrela = request.getParameter("rating");
		String comment = request.getParameter("comment");
		long idPessoa = Controller.getPessoa().getId();
		
		Date data = new Date(Calendar.getInstance().getTime().getTime());
		
		Avaliacao a = new Avaliacao();
		a.setIdPessoa(idPessoa);
		a.setIdEstabelecimento(estabelecimento.getId());
		a.setValor(Byte.parseByte(estrela));
		List<Avaliacao> avaliacoesIguais = (List<Avaliacao>) Dao.getInstance().readQuery("avaliacao", "SELECT * FROM avaliacao WHERE id_pessoa="+idPessoa+" AND id_estabelecimento="+estabelecimento.getId()+" ");
		if (avaliacoesIguais == null || avaliacoesIguais.isEmpty()) {
			Dao.getInstance().create(a);
		} else {
			Dao.getInstance().update(a);
		}
		
		List<Avaliacao> readAvaliacao = (List<Avaliacao>) Dao.getInstance().readQuery("avaliacao", "SELECT * FROM avaliacao WHERE id_pessoa="+idPessoa+" AND id_estabelecimento="+estabelecimento.getId()+" ");
		Avaliacao avaliacao = readAvaliacao.get(0);
		Comentario comentario = new Comentario();
		comentario.setIdPessoa(idPessoa);
		comentario.setIdEstabelecimento(estabelecimento.getId());
		comentario.setComentario(comment);
		comentario.setData(data);
		comentario.setAvaliacao(avaliacao);
		
		Dao.getInstance().create(comentario);
		
		//update estabelecimento in session
		estabelecimento = (Estabelecimento) Dao.getInstance().readWithId("estabelecimento", estabelecimento.getId());
		session.setAttribute("estabelecimentoBuscado", estabelecimento);
		
		response.sendRedirect("/fastchoice/estabelecimento.jsp");
	}

}
