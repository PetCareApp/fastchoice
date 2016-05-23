package br.com.fastchoice.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import br.com.fastchoice.model.controller.Controller;
import br.com.fastchoice.model.dao.ConnectionFactory;
import br.com.fastchoice.model.dao.Dao;
import br.com.fastchoice.model.entities.Amigo;
import br.com.fastchoice.model.entities.Avaliacao;
import br.com.fastchoice.model.entities.Carona;
import br.com.fastchoice.model.entities.Comentario;
import br.com.fastchoice.model.entities.Conta;
import br.com.fastchoice.model.entities.Estabelecimento;
import br.com.fastchoice.model.entities.Mensagem;
import br.com.fastchoice.model.entities.Pessoa;
import br.com.fastchoice.model.entities.Reserva;

public class BDCreateTables {

    public static void main(String[] args) {
	int option = 90;
	while (option != 0) {
	    option = Integer
		    .parseInt(JOptionPane
			    .showInputDialog(
				    null,
				    "What do you want?\n\n1: Create all Tables\n2: Avaliacao\n3: Carona\n4: Estabelecimento\n5: Mensagem\n6: Pessoa\n7: Reserva\n8: Comentários\n9: Delete All Tables\n0: exit\n22: insert all",
				    "BD: Create tables!", JOptionPane.QUESTION_MESSAGE));
	    try {
		switch (option) {
		case 1:
		    createTables();
		    JOptionPane.showMessageDialog(null, "All Tables are Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 2:
		    createTableAvaliacao();
		    JOptionPane.showMessageDialog(null, "Table Avaliacao Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 3:
		    createTableCarona();
		    JOptionPane.showMessageDialog(null, "Table Carona Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 4:
		    createTableEstabelecimento();
		    JOptionPane.showMessageDialog(null, "Table Estabelecimento Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 5:
		    createTableMensagem();
		    JOptionPane.showMessageDialog(null, "Table Mensagem Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 6:
		    createTablePessoa();
		    JOptionPane.showMessageDialog(null, "Table Pessoas Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 7:
		    createTableReserva();
		    JOptionPane.showMessageDialog(null, "Table Reserva Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 8:
		    createTableComentario();
		    JOptionPane.showMessageDialog(null, "Table Comentarios Created!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		case 9:
		    if (JOptionPane.showConfirmDialog(null, "All data will deleted! Do you want this?!", "Really?",
			    JOptionPane.WARNING_MESSAGE) == 0) {
			deleteTables();
			JOptionPane.showMessageDialog(null, "All Tables was deleted!", "Done! =x",
				JOptionPane.INFORMATION_MESSAGE);
		    }
		    break;
		case 22:
		    insertAllTables();
		    JOptionPane.showMessageDialog(null, "All Table are full!", "Done! ;)",
			    JOptionPane.INFORMATION_MESSAGE);
		    break;
		}

	    } catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Error in SQL!\n\nP.S.: Your DB's url is localhost/fastchoice ?\nLook your root and password too.",
			"Done! ;)", JOptionPane.INFORMATION_MESSAGE);
		e.printStackTrace();
	    }
	}
    }

    private static void insertTableAmigo() {
	Random rd = new Random();
	int i = 0;
	while (i < 5) {
	    int id2 = rd.nextInt(7) + 1;
	    Amigo amigo = new Amigo();
	    amigo.setIdPessoa(1);
	    amigo.setIdAmigo(id2);

	    boolean jaTem = false;
	    List<Amigo> amigos = (List<Amigo>) Dao.getInstance().readWithId("amigo", 1);
	    for (Amigo a : amigos) {
		if (id2 == 1 || a.getIdAmigo() == id2) {
		    jaTem = true;
		    break;
		}
	    }
	    List<Amigo> amigos2 = (List<Amigo>) Dao.getInstance().readWithId("amigo", id2);
	    for (Amigo a : amigos2) {
		if (id2 == 1 || a.getIdAmigo() == 1) {
		    jaTem = true;
		    break;
		}
	    }
	    if (amigo.getIdAmigo() == amigo.getIdPessoa()) {
		jaTem = true;
	    }

	    if (jaTem) {
		jaTem = false;
	    } else {
		i++;
		Dao.getInstance().create(amigo);
	    }
	}
    }

    private static void insertTableConta() {
	Conta conta = new Conta();
	conta.setIdPessoa(1);
	conta.setSenha("senha");
	conta.setUsuario("admin");
	conta.setEmail("123123@uashdu");

	Conta conta2 = new Conta();
	conta2.setIdPessoa(2);
	conta2.setSenha("senha");
	conta2.setUsuario("conta2");
	conta2.setEmail("aaaa@uashdu");

	Conta conta3 = new Conta();
	conta3.setIdPessoa(3);
	conta3.setSenha("senha");
	conta3.setUsuario("conta3");
	conta3.setEmail("bbbb@uashdu");

	Dao.getInstance().create(conta);
	Dao.getInstance().create(conta2);
	Dao.getInstance().create(conta3);
    }

    private static void insertTableAvaliacao() {
	Avaliacao avaliacao = new Avaliacao();
	avaliacao.setIdPessoa(3);
	avaliacao.setIdEstabelecimento(1);
	avaliacao.setValor((byte) 5);

	Avaliacao avaliacao2 = new Avaliacao();
	avaliacao2.setIdPessoa(2);
	avaliacao2.setIdEstabelecimento(1);
	avaliacao2.setValor((byte) 1);

	Avaliacao avaliacao3 = new Avaliacao();
	avaliacao3.setIdPessoa(5);
	avaliacao3.setIdEstabelecimento(2);
	avaliacao3.setValor((byte) 4);

	Dao.getInstance().create(avaliacao);
	Dao.getInstance().create(avaliacao2);
	Dao.getInstance().create(avaliacao3);
    }

    private static void insertTableCarona() {
	Carona carona = new Carona();
	carona.setIdMotorista(1);
	carona.setIdConvidado(2);

	Dao.getInstance().create(carona);
    }

    private static void insertTableComentario() {
	Comentario comentario = new Comentario();
	comentario.setIdPessoa(3);
	comentario.setIdEstabelecimento(1);
	comentario.setComentario("Muito legal!");
	comentario.setData(new Date(Calendar.getInstance().getTime().getTime()));
	List<Avaliacao> readAvaliacao = (List<Avaliacao>) Dao.getInstance().readQuery("avaliacao",
		"SELECT * FROM avaliacao WHERE id_pessoa=3 AND id_estabelecimento=1 ");
	comentario.setAvaliacao(readAvaliacao.get(0));

	Comentario comentario2 = new Comentario();
	comentario2.setIdPessoa(2);
	comentario2.setIdEstabelecimento(1);
	comentario2.setComentario("Acheio meio tosco...");
	comentario2.setData(new Date(Calendar.getInstance().getTime().getTime()));
	List<Avaliacao> readAvaliacao2 = (List<Avaliacao>) Dao.getInstance().readQuery("avaliacao",
		"SELECT * FROM avaliacao WHERE id_pessoa=2 AND id_estabelecimento=1 ");
	comentario2.setAvaliacao(readAvaliacao2.get(0));

	Comentario comentario3 = new Comentario();
	comentario3.setIdPessoa(5);
	comentario3.setIdEstabelecimento(2);
	comentario3.setComentario("Comida boa e é bem tranquilo!");
	comentario3.setData(new Date(Calendar.getInstance().getTime().getTime()));
	List<Avaliacao> readAvaliacao3 = (List<Avaliacao>) Dao.getInstance().readQuery("avaliacao",
		"SELECT * FROM avaliacao WHERE id_pessoa=5 AND id_estabelecimento=2 ");
	comentario3.setAvaliacao(readAvaliacao3.get(0));

	Dao.getInstance().create(comentario);
	Dao.getInstance().create(comentario2);
	Dao.getInstance().create(comentario3);
    }

    private static void insertTableEstabelecimento() {
	Estabelecimento estabelecimento = new Estabelecimento();
	estabelecimento.setNome("Bar do Jimi");
	estabelecimento
		.setDescricao("Bar de rock. Temos várias cervejas e destilados, bem como um ambiente muito rock and roll!!!");
	estabelecimento.setEndBairro("Centro");
	estabelecimento.setEndRua("15 de Novembro");
	estabelecimento.setEndCidade("Caruaru");
	estabelecimento.setEndGoogle("15 de Novembro, Caruaru");
	estabelecimento.setEndNumero((short) 55);
	estabelecimento.setEndComplemento("1 e 2 andar");
	estabelecimento.setTelefone("8177889900");
	estabelecimento.setTipo((byte) 1);
	estabelecimento.setVagasTotais((byte) 5);
	estabelecimento.setVagasDisponiveis((byte) 4);
	estabelecimento.setFotoPath("WebContent/resource/estabelecimento/barrock.png");
	Path path = Paths.get("WebContent/resource/estabelecimento/barrock.png");
	byte[] foto = null;
	try {
	    foto = Files.readAllBytes(path);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	estabelecimento.setFoto(foto);
	estabelecimento.setFotoExtension("png");
//	ImageIcon imageIcon = new ImageIcon(estabelecimento.getFotoPath());
//	estabelecimento.setFoto(imageIcon.getImage());
	estabelecimento.setVisitas(120);
	// estabelecimento.setComentarios(comentarios);

	Estabelecimento estabelecimento2 = new Estabelecimento();
	estabelecimento2.setNome("Restaurante Mariano");
	estabelecimento2.setDescricao("A melhor comida de Caruaru. Pratos regionais e sobremesas diversas.");
	estabelecimento2.setEndCidade("Caruaru");
	estabelecimento2.setEndGoogle("Avenida Agamenon Madalhaes Caruaru");
	estabelecimento2.setEndBairro("Mauricio de Nassau");
	estabelecimento2.setEndRua("Agamenon Magalhães");
	estabelecimento2.setEndNumero((short) 134);
	estabelecimento2.setEndComplemento("Próximo ao Shopping Difusora");
	estabelecimento2.setTelefone("8199889900");
	estabelecimento2.setTipo((byte) 2);
	estabelecimento2.setVagasTotais((byte) 10);
	estabelecimento2.setVagasDisponiveis((byte) 5);
	estabelecimento2.setFotoPath("WebContent/resource/estabelecimento/restaurante.jpg");
	Path path2 = Paths.get("WebContent/resource/estabelecimento/restaurante.jpg");
	byte[] foto2 = null;
	try {
	    foto2 = Files.readAllBytes(path2);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	estabelecimento2.setFoto(foto2);
	estabelecimento2.setFotoExtension("png");
//	ImageIcon imageIcon2 = new ImageIcon(estabelecimento2.getFotoPath());
//	estabelecimento2.setFoto(imageIcon2.getImage());
	estabelecimento2.setVisitas(75);

	Dao.getInstance().create(estabelecimento);
	Dao.getInstance().create(estabelecimento2);
    }

    private static void insertTableMensagem() {

	List<Amigo> amigos = (List<Amigo>) Dao.getInstance().readWithId("amigo", 1);

	Mensagem mensagem = new Mensagem();
	mensagem.setIdDestinario(1);
	mensagem.setIdRemetente(amigos.get(0).getIdAmigo());
	mensagem.setMsg("Ae, cara! Vamos sair hoje?");
	mensagem.setData(new Date(Calendar.getInstance().getTime().getTime()));

	Mensagem mensagem2 = new Mensagem();
	mensagem2.setIdDestinario(1);
	mensagem2.setIdRemetente(amigos.get(1).getIdAmigo());
	mensagem2.setMsg("Aeoooooooo");
	mensagem2.setData(new Date(Calendar.getInstance().getTime().getTime()));

	Mensagem mensagem3 = new Mensagem();
	mensagem3.setIdDestinario(1);
	mensagem3.setIdRemetente(amigos.get(2).getIdAmigo());
	mensagem3.setMsg("Achei uma promoção massa!");
	mensagem3.setData(new Date(Calendar.getInstance().getTime().getTime()));

	Mensagem mensagem4 = new Mensagem();
	mensagem4.setIdDestinario(1);
	mensagem4.setIdRemetente(amigos.get(0).getIdAmigo());
	mensagem4.setMsg("Bicho, esse projeto tá foda!!!");
	mensagem4.setData(new Date(Calendar.getInstance().getTime().getTime()));

	Mensagem mensagem5 = new Mensagem();
	mensagem5.setIdDestinario(1);
	mensagem5.setIdRemetente(amigos.get(0).getIdAmigo());
	mensagem5.setMsg("Vai para a faculdade hoje?");
	mensagem5.setData(new Date(Calendar.getInstance().getTime().getTime()));

	Mensagem mensagem6 = new Mensagem();
	mensagem6.setIdDestinario(1);
	mensagem6.setIdRemetente(amigos.get(0).getIdAmigo());
	mensagem6.setMsg("Tenho uma história pra te contar... me liga, visse?!");
	mensagem6.setData(new Date(Calendar.getInstance().getTime().getTime()));

	Mensagem mensagem7 = new Mensagem();
	mensagem7.setIdDestinario(1);
	mensagem7.setIdRemetente(amigos.get(3).getIdAmigo());
	mensagem7.setMsg("Tô no bar do rock hoje, aparece lá!");
	mensagem7.setData(new Date(Calendar.getInstance().getTime().getTime()));

	Dao.getInstance().create(mensagem);
	Dao.getInstance().create(mensagem2);
	Dao.getInstance().create(mensagem3);
	Dao.getInstance().create(mensagem4);
	Dao.getInstance().create(mensagem5);
	Dao.getInstance().create(mensagem6);
	Dao.getInstance().create(mensagem7);
    }

    private static void insertTablePessoa() throws IOException {
	Pessoa pessoa = new Pessoa();
	pessoa.setNome("Alex");
	pessoa.setCpf("12345678910");
	pessoa.setEndBairro("Indianópolis");
	pessoa.setEndRua("Av Adjar da Silva Casé");
	pessoa.setEndGoogle("Av. Adjar da Silva Casé Indianópolis Caruaru");
	pessoa.setEndNumero((short) 55);
	pessoa.setEndComplemento("apartamento");
//	pessoa.setFotoFile(new File("WebContent/resource/people/alex.png"));
//	BufferedImage img = ImageIO.read(pessoa.getFotoFile());
//	ImageIcon imageIcon = new ImageIcon(img);
//	pessoa.setFoto(imageIcon.getImage());
	Path path = Paths.get("WebContent/resource/people/alex.png");
	byte[] foto = null;
	try {
	    foto = Files.readAllBytes(path);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	pessoa.setFoto(foto);
	pessoa.setFotoExtension("png");
	pessoa.setIdade((byte) 30);
	pessoa.setMasculino(true);
	pessoa.setTelefone("8199998888");

	Pessoa pessoa2 = new Pessoa();
	pessoa2.setNome("Donovam de Souza");
	pessoa2.setCpf("01987654321");
	pessoa2.setEndBairro("Nossa Sra. das Dores");
	pessoa2.setEndRua("Av Rio Branco");
	pessoa2.setEndGoogle("Av. Rio Branco Nossa Sra. das Dores Caruaru");
	pessoa2.setEndNumero((short) 177);
	pessoa2.setEndComplemento("Loja");
//	pessoa2.setFotoFile(new File("WebContent/resource/people/donovam.png"));
//	BufferedImage img2 = ImageIO.read(pessoa2.getFotoFile());
//	ImageIcon imageIcon2 = new ImageIcon(img2);
//	pessoa2.setFoto(imageIcon2.getImage());
	Path path2 = Paths.get("WebContent/resource/people/donovam.png");
	byte[] foto2 = null;
	try {
	    foto2 = Files.readAllBytes(path2);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	pessoa2.setFoto(foto2);
	pessoa2.setFotoExtension("png");
	pessoa2.setIdade((byte) 30);
	pessoa2.setMasculino(true);
	pessoa2.setTelefone("8199998888");

	Pessoa pessoa3 = new Pessoa();
	pessoa3.setNome("David Luiz");
	pessoa3.setCpf("9345776123");
	pessoa3.setEndBairro("Maurício de Nassau");
	pessoa3.setEndRua("R. Visc. de Inhaúma Maurício");
	pessoa3.setEndGoogle("R. Visc. de Inhaúma Maurício de Nassau Caruaru");
	pessoa3.setEndNumero((short) 177);
	pessoa3.setEndComplemento("Loja");
//	pessoa3.setFotoFile(new File("WebContent/resource/people/david.png"));
//	BufferedImage img3 = ImageIO.read(pessoa3.getFotoFile());
//	ImageIcon imageIcon3 = new ImageIcon(img3);
//	pessoa3.setFoto(imageIcon3.getImage());
	Path path3 = Paths.get("WebContent/resource/people/david.png");
	byte[] foto3 = null;
	try {
	    foto3 = Files.readAllBytes(path3);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	pessoa3.setFoto(foto3);
	pessoa3.setFotoExtension("png");
	pessoa3.setIdade((byte) 30);
	pessoa3.setMasculino(true);
	pessoa3.setTelefone("8199994444");

	Pessoa pessoa4 = new Pessoa();
	pessoa4.setNome("Sasha Grey");
	pessoa4.setCpf("3657834685");
	pessoa4.setEndBairro("Indianopolis");
	pessoa4.setEndRua("Av. José Rodrigues de Jesus Indianópolis Caruaru");
	pessoa4.setEndGoogle("");
	pessoa4.setEndNumero((short) 551);
	pessoa4.setEndComplemento("Casa");
//	pessoa4.setFotoFile(new File("WebContent/resource/people/sasha.png"));
//	BufferedImage img4 = ImageIO.read(pessoa4.getFotoFile());
//	ImageIcon imageIcon4 = new ImageIcon(img4);
//	pessoa4.setFoto(imageIcon4.getImage());
	Path path4 = Paths.get("WebContent/resource/people/sasha.png");
	byte[] foto4 = null;
	try {
	    foto4 = Files.readAllBytes(path4);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	pessoa4.setFoto(foto4);
	pessoa4.setFotoExtension("png");
	pessoa4.setIdade((byte) 24);
	pessoa4.setMasculino(true);
	pessoa4.setTelefone("81977774444");

	Pessoa pessoa5 = new Pessoa();
	pessoa5.setNome("Jailson Mendes");
	pessoa5.setCpf("1653681696");
	pessoa5.setEndBairro("Cedro");
	pessoa5.setEndRua("Rua Numero 8");
	pessoa5.setEndGoogle("R. R Oito Cedro Caruaru");
	pessoa5.setEndNumero((short) 33);
	pessoa5.setEndComplemento("Barraco");
//	pessoa5.setFotoFile(new File("WebContent/resource/people/jailson.png"));
//	BufferedImage img5 = ImageIO.read(pessoa5.getFotoFile());
//	ImageIcon imageIcon5 = new ImageIcon(img5);
//	pessoa5.setFoto(imageIcon5.getImage());
	Path path5 = Paths.get("WebContent/resource/people/jailson.png");
	byte[] foto5 = null;
	try {
	    foto5 = Files.readAllBytes(path5);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	pessoa5.setFoto(foto5);
	pessoa5.setFotoExtension("png");
	pessoa5.setIdade((byte) 30);
	pessoa5.setMasculino(true);
	pessoa5.setTelefone("8188776655");

	Pessoa pessoa6 = new Pessoa();
	pessoa6.setNome("Chico Bioca");
	pessoa6.setCpf("9283409237");
	pessoa6.setEndBairro("Rendeiras");
	pessoa6.setEndRua("Rua Numero 4");
	pessoa6.setEndGoogle("R. R. Dezoito Rendeiras Caruaru");
	pessoa6.setEndNumero((short) 12);
	pessoa6.setEndComplemento("Barraco");
//	pessoa6.setFotoFile(new File("WebContent/resource/people/chico.png"));
//	BufferedImage img6 = ImageIO.read(pessoa6.getFotoFile());
//	ImageIcon imageIcon6 = new ImageIcon(img6);
//	pessoa6.setFoto(imageIcon6.getImage());
	Path path6 = Paths.get("WebContent/resource/people/chico.png");
	byte[] foto6 = null;
	try {
	    foto6 = Files.readAllBytes(path6);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	pessoa6.setFoto(foto6);
	pessoa6.setFotoExtension("png");
	pessoa6.setIdade((byte) 30);
	pessoa6.setMasculino(true);
	pessoa6.setTelefone("8199881111");

	Pessoa pessoa7 = new Pessoa();
	pessoa7.setNome("Senhora Volta Aqui");
	pessoa7.setCpf("2938747111");
	pessoa7.setEndBairro("Vassoural");
	pessoa7.setEndRua("Rua Monte Urais");
	pessoa7.setEndGoogle("R. Monte Urais Santa Rosa Caruaru");
	pessoa7.setEndNumero((short) 288);
	pessoa7.setEndComplemento("Casa");
//	pessoa7.setFotoFile(new File("WebContent/resource/people/senhora.png"));
//	BufferedImage img7 = ImageIO.read(pessoa7.getFotoFile());
//	ImageIcon imageIcon7 = new ImageIcon(img7);
//	pessoa7.setFoto(imageIcon7.getImage());
	Path path7 = Paths.get("WebContent/resource/people/senhora.png");
	byte[] foto7 = null;
	try {
	    foto7 = Files.readAllBytes(path7);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	pessoa7.setFoto(foto7);
	pessoa7.setFotoExtension("png");
	pessoa7.setIdade((byte) 55);
	pessoa7.setMasculino(true);
	pessoa7.setTelefone("8188223355");

	Dao.getInstance().create(pessoa);
	Dao.getInstance().create(pessoa2);
	Dao.getInstance().create(pessoa3);
	Dao.getInstance().create(pessoa4);
	Dao.getInstance().create(pessoa5);
	Dao.getInstance().create(pessoa6);
	Dao.getInstance().create(pessoa7);
    }

    private static void insertTableReserva() {
	java.util.Date data = new java.util.Date();
	java.util.Date data2 = new java.util.Date();
	java.util.Date data3 = new java.util.Date();
	
	java.util.Calendar cal = Calendar.getInstance();
	cal.setTime(data);
	cal.set(Calendar.HOUR_OF_DAY, 20);
	cal.set(Calendar.MINUTE, 30);
	
	java.util.Calendar cal2 = Calendar.getInstance();
	cal2.setTime(data2);
	cal2.set(Calendar.HOUR_OF_DAY, 21);
	cal2.set(Calendar.MINUTE, 30);
	
	java.util.Calendar cal3 = Calendar.getInstance();
	cal3.setTime(data3);
	cal3.set(Calendar.HOUR_OF_DAY, 23);
	cal3.set(Calendar.MINUTE, 30);
	
	Reserva reserva = new Reserva();
	reserva.setIdPessoa(2);
	reserva.setIdEstabelecimento(1);
	reserva.setData(data);

	Reserva reserva2 = new Reserva();
	reserva2.setIdPessoa(3);
	reserva2.setIdEstabelecimento(2);
	reserva2.setData(data2);

	Reserva reserva3 = new Reserva();
	reserva3.setIdPessoa(4);
	reserva3.setIdEstabelecimento(2);
	reserva3.setData(data3);

	
	Dao.getInstance().create(reserva);
	Dao.getInstance().create(reserva2);
	Dao.getInstance().create(reserva3);
    }

    private static void insertAllTables() {
	try {
	    insertTablePessoa();
	} catch (IOException e) {
	    System.out.println("deu merda nas imagens de pessoa! kkk");
	    e.printStackTrace();
	}
	insertTableAmigo();
	insertTableEstabelecimento();
	insertTableMensagem();
	insertTableReserva();
	insertTableCarona();
	insertTableAvaliacao();
	insertTableComentario();
	insertTableConta();
    }

    private static void createTables() {
	createTableAvaliacao();
	createTableCarona();
	createTableEstabelecimento();
	createTableMensagem();
	createTablePessoa();
	createTableReserva();
	createTableComentario();
	createTableAmigo();
	createTableConta();
    }

    private static void createTableConta() {
	String sql = "CREATE TABLE CONTA" + "(" + "id int not null auto_increment," + "id_pessoa int not null,"
		+ "facebook varchar(100)," + "usuario varchar(16) not null," + "senha varchar(16) not null," 
		+ "email varchar(50) not null," + "PRIMARY KEY (id)" + ");";
		
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTableAvaliacao() {
	String sql = "CREATE TABLE AVALIACAO" + "(" + "id int not null auto_increment," + "id_pessoa int not null,"
		+ "id_estabelecimento int not null," + "valor int not null," + "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTableCarona() {
	String sql = "CREATE TABLE CARONA" + "(" + "id int not null auto_increment," + "id_morotista int not null,"
		+ "id_convidado int not null," + "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTableEstabelecimento() {
	String sql = "CREATE TABLE ESTABELECIMENTO" + "(" + "id int not null auto_increment,"
		+ "nome varchar(50) not null," + "tipo tinyint," + "descricao varchar(200) not null,"
		+ "end_rua varchar(50)," + "end_cidade varchar(50)," + "end_bairro varchar(100),"
		+ "end_complemento varchar(100)," + "end_numero int,"
		+ "telefone varchar(20) not null," + "foto longblob," + "vagas_totais int," + "end_google varchar(200) not null,"
		+ "foto_path varchar(200),"+ "foto_extension varchar(4)," + "vagas_disponiveis int," + "visitas int," + "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTableMensagem() {
	String sql = "CREATE TABLE MENSAGEM" + "(" + "id int not null auto_increment," + "id_remetente int not null,"
		+ "id_destinario int not null," + "mensagem tinytext not null," + "data datetime not null,"
		+ "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTablePessoa() {
	String sql = "CREATE TABLE PESSOA" + "(" + "id  int not null auto_increment," + "name varchar(50) not null,"
		+ "idade tinyint," + "sexo tinyint," + "cpf varchar(11),"
		+ "end_rua varchar(100)," + "end_bairro varchar(100),"
		+ "end_complemento varchar(100)," + "end_numero int,"
		+ "end_google varchar(200) not null,"
		+ "telefone varchar(20)," + "foto longblob, foto_extension varchar(4)," + "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTableComentario() {
	String sql = "CREATE TABLE COMENTARIO" + "(" + "id int not null auto_increment," + "id_pessoa int not null,"
		+ "id_estabelecimento int not null," + "comentario tinytext not null," + "data datetime not null,"
		+ "id_avaliacao int not null," + "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTableReserva() {
	String sql = "CREATE TABLE RESERVA" + "(" + "id int not null auto_increment," + "id_pessoa int not null,"
		+ "id_estabelecimento int not null," + "data datetime not null," + "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void createTableAmigo() {
	String sql = "CREATE TABLE AMIGO" + "(" + "id int not null auto_increment," + "id_pessoa int not null,"
		+ "id_amigo int not null," + "PRIMARY KEY (id)" + ");";
	System.out.println(sql);
	stmtExecute(sql);
    }

    private static void deleteTables() {
	deleteTableAvaliacao();
	deleteTableCarona();
	deleteTableEstabelecimento();
	deleteTableMensagem();
	deleteTablePessoa();
	deleteTableReserva();
	deleteTableComentario();
	deleteTableAmigo();
	deleteTableConta();
    }

    private static void deleteTableConta() {
	String sql = "DROP TABLE conta";
	stmtExecute(sql);
    }

    private static void deleteTableAvaliacao() {
	String sql = "DROP TABLE avaliacao";
	stmtExecute(sql);
    }

    private static void deleteTableCarona() {
	String sql = "DROP TABLE Carona";
	stmtExecute(sql);
    }

    private static void deleteTableEstabelecimento() {
	String sql = "DROP TABLE Estabelecimento";
	stmtExecute(sql);
    }

    private static void deleteTableMensagem() {
	String sql = "DROP TABLE Mensagem";
	stmtExecute(sql);
    }

    private static void deleteTablePessoa() {
	String sql = "DROP TABLE Pessoa";
	stmtExecute(sql);
    }

    private static void deleteTableReserva() {
	String sql = "DROP TABLE Reserva";
	stmtExecute(sql);
    }

    private static void deleteTableComentario() {
	String sql = "DROP TABLE Comentario";
	stmtExecute(sql);
    }

    private static void deleteTableAmigo() {
	String sql = "DROP TABLE Amigo";
	stmtExecute(sql);
    }

    private static void stmtExecute(String sql) {
	Connection con = new ConnectionFactory().getConnection();
	try {
	    PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.execute();
	    stmt.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
