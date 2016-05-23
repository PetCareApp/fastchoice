package br.com.fastchoice.model.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import com.mysql.jdbc.Blob;

import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Estabelecimento;
import br.com.fastchoice.model.entities.Pessoa;

@WebServlet("/displayImage/*")
public class DisplayImage extends HttpServlet {

    private static final long serialVersionUID = -5574115392265969581L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String entidadeDisplay = request.getPathInfo().substring(1, 9);
	long id = Long.parseLong(request.getPathInfo().substring(9));
	
	if (entidadeDisplay.equalsIgnoreCase("estabele")) {

	    List<Estabelecimento> estabelecimentos = (List<Estabelecimento>) Dao.getInstance().read("estabelecimento");
	    
	    for (Estabelecimento e : estabelecimentos) {
		if (e.getId() == id) {
        	    byte[] content = e.getFoto();
        	    response.setContentType("image/png");
        	    response.setContentLength(content.length);
        	    response.getOutputStream().write(content);
		}
	    }
	} else {
	    List<Pessoa> pessoas = (List<Pessoa>) Dao.getInstance().read("pessoa");
	    for (Pessoa p : pessoas) {
		if (p.getId() == id) {
        	    byte[] content = p.getFoto();
        	    response.setContentType("image/"+p.getFotoExtension());
        	    response.setContentLength(content.length);
        	    response.getOutputStream().write(content);
		}
	    }
	}
    }

}
