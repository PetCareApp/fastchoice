package br.com.fastchoice.model.dao;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.fastchoice.model.entities.Amigo;
import br.com.fastchoice.model.entities.Avaliacao;
import br.com.fastchoice.model.entities.Carona;
import br.com.fastchoice.model.entities.Comentario;
import br.com.fastchoice.model.entities.Conta;
import br.com.fastchoice.model.entities.Estabelecimento;
import br.com.fastchoice.model.entities.Mensagem;
import br.com.fastchoice.model.entities.Pessoa;
import br.com.fastchoice.model.entities.Reserva;

public class Dao implements IDao {

	Connection con = new ConnectionFactory().getConnection();

	private static Dao instance;

	public static Dao getInstance() {
		if (instance == null) {
			instance = new Dao();
		}
		return instance;
	}

	@Override
	public void create(Object obj) {
		if (obj instanceof Conta) {
			Conta conta = (Conta) obj;
			String sql = "INSERT INTO conta"
					+ " (id_pessoa, usuario, senha, facebook, email) "
					+ " VALUES (?,?,?,?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) conta.getIdPessoa());
				stmt.setString(2, conta.getUsuario());
				stmt.setString(3, conta.getSenha());
				stmt.setString(4, conta.getFacebook());
				stmt.setString(5, conta.getEmail());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Avaliacao) {
			Avaliacao avaliacao = (Avaliacao) obj;
			String sql = "INSERT INTO avaliacao "
					+ " (id_pessoa, id_estabelecimento, valor) "
					+ " VALUES (?,?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				// stmt.setInt(1, (int) avaliacao.getId());
				stmt.setInt(1, (int) avaliacao.getIdPessoa());
				stmt.setInt(2, (int) avaliacao.getIdEstabelecimento());
				stmt.setByte(3, avaliacao.getValor());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Carona) {
			Carona carona = (Carona) obj;
			String sql = "INSERT INTO carona "
					+ " (id_morotista, id_convidado) " + " VALUES (?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				// stmt.setInt(1, (int) carona.getId());
				stmt.setInt(1, (int) carona.getIdMotorista());
				stmt.setInt(2, (int) carona.getIdConvidado());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Estabelecimento) {
			Estabelecimento estabelecimento = (Estabelecimento) obj;
			String sql = "INSERT INTO estabelecimento "
					+ " (nome, tipo, end_rua, end_bairro, end_complemento, end_numero, telefone, vagas_totais, vagas_disponiveis, descricao, visitas, foto, foto_extension, end_cidade, end_google) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			try {
//				InputStream inputImage = new FileInputStream(estabelecimento.);
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, estabelecimento.getNome());
				stmt.setByte(2, estabelecimento.getTipo());
				stmt.setString(3, estabelecimento.getEndRua());
				stmt.setString(4, estabelecimento.getEndBairro());
				stmt.setString(5, estabelecimento.getEndComplemento());
				stmt.setShort(6, estabelecimento.getEndNumero());
				stmt.setString(7, estabelecimento.getTelefone());
//				stmt.setBlob(8, inputImage, (int) file.length());
				stmt.setInt(8, (int) estabelecimento.getVagasTotais());
				stmt.setInt(9, (int) estabelecimento.getVagasDisponiveis());
				stmt.setString(10, estabelecimento.getDescricao());
//				stmt.setString(12, estabelecimento.getFotoPath());
				stmt.setInt(11, (int) estabelecimento.getVisitas());
				stmt.setBytes(12, estabelecimento.getFoto());
				stmt.setString(13, estabelecimento.getFotoExtension());
				stmt.setString(14, estabelecimento.getEndCidade());
				stmt.setString(15, estabelecimento.getEndGoogle());
				
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Mensagem) {
			Mensagem mensagem = (Mensagem) obj;
			String sql = "INSERT INTO mensagem "
					+ " (id_remetente, id_destinario, mensagem, data) "
					+ " VALUES (?,?,?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				// stmt.setInt(1, (int) mensagem.getId());
				stmt.setInt(1, (int) mensagem.getIdRemetente());
				stmt.setInt(2, (int) mensagem.getIdDestinario());
				stmt.setString(3, mensagem.getMsg());
				stmt.setDate(4, mensagem.getData());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Pessoa) {
			Pessoa pessoa = (Pessoa) obj;
			String sql = "INSERT INTO pessoa "
					+ " (name, idade, sexo, cpf, end_rua, end_bairro, end_complemento, end_numero, telefone, foto, foto_extension, end_google) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, pessoa.getNome());
				stmt.setInt(2, (int) pessoa.getIdade());
				stmt.setBoolean(3, pessoa.isMasculino());
				stmt.setString(4, pessoa.getCpf());
				stmt.setString(5, pessoa.getEndRua());
				stmt.setString(6, pessoa.getEndBairro());
				stmt.setString(7, pessoa.getEndComplemento());
				stmt.setInt(8, pessoa.getEndNumero());
				stmt.setString(9, pessoa.getTelefone());
				stmt.setBytes(10, pessoa.getFoto());
				stmt.setString(11, pessoa.getFotoExtension());
				stmt.setString(12, pessoa.getEndGoogle());

				stmt.execute();
				stmt.close();
			} catch (SQLException  e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Reserva) {
			Reserva reserva = (Reserva) obj;
			String sql = "INSERT INTO reserva "
					+ " (id_pessoa, id_estabelecimento, data) "
					+ " VALUES (?,?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				// stmt.setInt(1, (int) reserva.getId());
				stmt.setInt(1, (int) reserva.getIdPessoa());
				stmt.setInt(2, (int) reserva.getIdEstabelecimento());
				stmt.setTimestamp(3, new Timestamp(reserva.getData().getYear(), reserva.getData().getMonth(), reserva.getData().getDate(), reserva.getData().getHours(), reserva.getData().getMinutes(), 0, 0));
//				stmt.setDate(3, reserva.getData());
//				stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Comentario) { // tabela auxiliar
			Comentario comentario = (Comentario) obj;
			String sql = "INSERT INTO comentario "
					+ " (id_pessoa, id_estabelecimento, comentario, data, id_avaliacao) "
					+ " VALUES (?,?,?,?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				// stmt.setInt(1, (int) comentario.getId());
				stmt.setInt(1, (int) comentario.getIdPessoa());
				stmt.setInt(2, (int) comentario.getIdEstabelecimento());
				stmt.setString(3, comentario.getComentario());
				stmt.setDate(4, comentario.getData());
				stmt.setInt(5, (int) comentario.getAvaliacao().getId());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Amigo) { // tabela auxiliar
			Amigo amigo = (Amigo) obj;
			String sql = "INSERT INTO amigo " + " (id_pessoa, id_amigo) "
					+ " VALUES (?,?) ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				// stmt.setInt(1, (int) amigo.getId());
				stmt.setInt(1, (int) amigo.getIdPessoa());
				stmt.setInt(2, (int) amigo.getIdAmigo());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<?> read(String obj) {
		if (obj.equalsIgnoreCase("Conta")) {
			String sql = "SELECT * FROM conta ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Conta> lista = new ArrayList<Conta>();
				while (rs.next()) {
					Conta conta = new Conta();
					conta.setId(rs.getInt("id"));
					conta.setIdPessoa(rs.getInt("id_pessoa"));
					conta.setUsuario(rs.getString("usuario"));
					conta.setSenha(rs.getString("senha"));
					conta.setFacebook(rs.getString("facebook"));
					conta.setEmail(rs.getString("email"));
					
					lista.add(conta);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Avaliacao")) {
			String sql = "SELECT * FROM avaliacao ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
				while (rs.next()) {
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setId(rs.getInt("id"));
					avaliacao.setIdPessoa(rs.getInt("id_pessoa"));
					avaliacao.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					avaliacao.setValor(rs.getByte("valor"));

					lista.add(avaliacao);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Carona")) {
			String sql = "SELECT * FROM carona ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Carona> lista = new ArrayList<Carona>();
				while (rs.next()) {
					Carona carona = new Carona();
					carona.setId(rs.getInt("id"));
					carona.setIdMotorista(rs.getInt("id_motorista"));
					carona.setIdConvidado(rs.getInt("id_convidado"));

					lista.add(carona);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Comentario")) {
			String sql = "SELECT * FROM comentario ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Comentario> lista = new ArrayList<Comentario>();
				while (rs.next()) {
					Comentario comentario = new Comentario();
					comentario.setId(rs.getInt("id"));
					comentario.setIdPessoa(rs.getInt("id_pessoa"));
					comentario.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					comentario.setComentario(rs.getString("comentario"));
					comentario.setData(rs.getDate("data"));
					Avaliacao avaliacao = (Avaliacao) readWithId("avaliacao", rs.getInt("id_avaliacao"));
					comentario.setAvaliacao(avaliacao);

					lista.add(comentario);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Estabelecimento")) {
			String sql = "SELECT * FROM estabelecimento ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Estabelecimento> lista = new ArrayList<Estabelecimento>();
				while (rs.next()) {
					Estabelecimento estabelecimento = new Estabelecimento();
					estabelecimento.setId(rs.getInt("id"));
					estabelecimento.setNome(rs.getString("nome"));
					estabelecimento.setDescricao(rs.getString("descricao"));
					estabelecimento.setEndRua(rs.getString("end_rua"));
					estabelecimento.setEndCidade(rs.getString("end_cidade"));
					estabelecimento.setEndGoogle(rs.getString("end_google"));
					estabelecimento.setEndBairro(rs.getString("end_bairro"));
					estabelecimento.setEndComplemento(rs
							.getString("end_complemento"));
					estabelecimento.setEndNumero(rs.getShort("end_numero"));
					estabelecimento.setTelefone(rs.getString("telefone"));
					estabelecimento.setVagasTotais(rs.getByte("vagas_totais"));
					estabelecimento.setVagasDisponiveis(rs
							.getByte("vagas_disponiveis")); 
//					estabelecimento.setFotoPath(rs.getString("foto_path").replaceFirst("WebContent/", ""));
//					Blob blob = rs.getBlob("foto");
//					ImageIcon imageIcon = new ImageIcon(blob.getBytes(1,
//							(int) blob.length()));
//					Image foto = imageIcon.getImage();
//					estabelecimento.setFoto(foto);

					// FOTO
					estabelecimento.setFoto(rs.getBytes("foto"));
					estabelecimento.setFotoExtension(rs.getString("foto_extension"));
					
					// AVALIACAO
					List<Avaliacao> avaliacoes = (List<Avaliacao>) read("avaliacao");
					double avaliacaoFinal = 0;
					if (avaliacoes != null && !avaliacoes.isEmpty()) {
						int contador = 0;
						for (Avaliacao a: avaliacoes) {
							if (a.getIdEstabelecimento() == estabelecimento.getId()) {
								avaliacaoFinal += a.getValor();
								contador++;
							}
						}
						if (avaliacaoFinal > 0) {
							avaliacaoFinal = avaliacaoFinal / contador;
						}
					}
					estabelecimento.setAvaliacao(avaliacaoFinal);
					List<Comentario> comentarios = (List<Comentario>) read("Comentario");
					for (Comentario c: comentarios) {
						if (c.getIdEstabelecimento() == estabelecimento.getId()) {
							estabelecimento.getComentarios().add(c);
						}
					}
					estabelecimento.setVisitas(rs.getInt("visitas"));
					
					List<Reserva> reservas = (List<Reserva>) readWithId("reserva", estabelecimento.getId());
					estabelecimento.setReservas(reservas);

					lista.add(estabelecimento);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Mensagem")) {
			String sql = "SELECT * FROM mensagem ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Mensagem> lista = new ArrayList<Mensagem>();
				while (rs.next()) {
					Mensagem mensagem = new Mensagem();
					mensagem.setId(rs.getInt("id"));
					mensagem.setIdRemetente(rs.getInt("id_remetente"));
					mensagem.setIdDestinario(rs.getInt("id_destinario"));
					mensagem.setMsg(rs.getString("mensagem"));
					mensagem.setData(rs.getDate("data"));

					lista.add(mensagem);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Pessoa")) {
			String sql = "SELECT * FROM pessoa ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
				while (rs.next()) {
					Pessoa pessoa = new Pessoa();
					pessoa.setId(rs.getInt("id"));
					pessoa.setNome(rs.getString("name"));
					pessoa.setCpf(rs.getString("cpf"));
					pessoa.setEndRua(rs.getString("end_rua"));
					pessoa.setEndBairro(rs.getString("end_bairro"));
					pessoa.setEndComplemento(rs.getString("end_complemento"));
					pessoa.setEndNumero(rs.getShort("end_numero"));
					pessoa.setTelefone(rs.getString("telefone"));
					pessoa.setIdade(rs.getByte("idade"));
					pessoa.setMasculino(rs.getBoolean("sexo"));
					pessoa.setEndGoogle(rs.getString("end_google"));
					
//					Blob blob = rs.getBlob("foto");
//					byte [] array = blob.getBytes( 1, ( int ) blob.length() );
//					File file = File.createTempFile(pessoa.getId()+pessoa.getCpf(), ".jpg", new File(""));
//					FileOutputStream out = new FileOutputStream( file );
//					out.write( array );
//					out.close();
//					ImageIcon imageIcon = new ImageIcon(blob.getBytes(1,
//							(int) blob.length()));
//					Image foto = imageIcon.getImage();
//					
//					pessoa.setFoto(foto);
//					pessoa.setFotoFile(file);
					
					// FOTO
					pessoa.setFoto(rs.getBytes("foto"));
					pessoa.setFotoExtension(rs.getString("foto_extension"));
					
					// AVALIACAO
					List<Amigo> amigosBD = (ArrayList<Amigo>) read("Amigo");
					List<Amigo> amigosPessoa = new ArrayList<Amigo>();
					for (Amigo a: amigosBD) {
						if (a.getIdAmigo() == pessoa.getId() || a.getIdPessoa() == pessoa.getId()) {
							amigosPessoa.add(a);
						}
					}
					pessoa.setAmigos(amigosPessoa);

					lista.add(pessoa);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Reserva")) {
			String sql = "SELECT * FROM reserva ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Reserva> lista = new ArrayList<Reserva>();
				while (rs.next()) {
					Reserva reserva = new Reserva();
					reserva.setId(rs.getInt("id"));
					reserva.setIdPessoa(rs.getInt("id_pessoa"));
					reserva.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					Date data = rs.getTimestamp("data");
					System.out.println("reserva person "+reserva.getIdPessoa()+" - "+data.getHours());
					reserva.setData(data);
//					reserva.setData(rs.getDate("data"));

					lista.add(reserva);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Amigo")) {
			String sql = "SELECT * FROM amigo ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Amigo> lista = new ArrayList<Amigo>();
				while (rs.next()) {
					Amigo amigo = new Amigo();
					amigo.setId(rs.getInt("id"));
					amigo.setIdPessoa(rs.getInt("id_pessoa"));
					amigo.setIdAmigo(rs.getInt("id_amigo"));

					lista.add(amigo);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	@Override
	public Object readWithId(String obj, long id) {
		if (obj.equalsIgnoreCase("Conta")) {
			String sql = "SELECT * FROM conta WHERE id= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				Conta conta = null;
				if (rs.next()) {
					conta = new Conta();
					conta.setId(id);
					conta.setIdPessoa(rs.getInt("id_pessoa"));
					conta.setUsuario(rs.getString("usuario"));
					conta.setSenha(rs.getString("senha"));
					conta.setFacebook(rs.getString("facebook"));
					conta.setEmail(rs.getString("email"));
				}
				rs.close();
				stmt.close();

				return conta;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Avaliacao")) {
			String sql = "SELECT * FROM avaliacao WHERE id= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				Avaliacao avaliacao = null;
				if (rs.next()) {
					avaliacao = new Avaliacao();
					avaliacao.setIdPessoa(rs.getInt("id_pessoa"));
					avaliacao.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					avaliacao.setValor(rs.getByte("valor"));
				}
				rs.close();
				stmt.close();

				return avaliacao;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Carona")) {
			String sql = "SELECT * FROM carona  WHERE id= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

					rs.next();
					Carona carona = new Carona();
					carona.setId(rs.getInt("id"));
					carona.setIdMotorista(rs.getInt("id_motorista"));
					carona.setIdConvidado(rs.getInt("id_convidado"));

				rs.close();
				stmt.close();

				return carona;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Comentario")) {
			String sql = "SELECT * FROM comentario  WHERE id= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

					rs.next();
					Comentario comentario = new Comentario();
					comentario.setId(rs.getInt("id"));
					comentario.setIdPessoa(rs.getInt("id_pessoa"));
					comentario.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					comentario.setComentario(rs.getString("comentario"));
					comentario.setData(rs.getDate("data"));
					Avaliacao avaliacao = (Avaliacao) readWithId("avaliacao", rs.getInt("id_avaliacao"));
					comentario.setAvaliacao(avaliacao);

				rs.close();
				stmt.close();

				return comentario;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Estabelecimento")) {
			String sql = "SELECT * FROM estabelecimento  WHERE id= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

					rs.next();
					Estabelecimento estabelecimento = new Estabelecimento();
					estabelecimento.setId(rs.getInt("id"));
					estabelecimento.setNome(rs.getString("nome"));
					estabelecimento.setDescricao(rs.getString("descricao"));
					estabelecimento.setEndRua(rs.getString("end_rua"));
					estabelecimento.setEndCidade(rs.getString("end_cidade"));
					estabelecimento.setEndGoogle(rs.getString("end_google"));
					estabelecimento.setEndBairro(rs.getString("end_bairro"));
					estabelecimento.setEndComplemento(rs
							.getString("end_complemento"));
					estabelecimento.setEndNumero(rs.getShort("end_numero"));
					estabelecimento.setTelefone(rs.getString("telefone"));
					estabelecimento.setVagasTotais(rs.getByte("vagas_totais"));
					estabelecimento.setVagasDisponiveis(rs
							.getByte("vagas_disponiveis"));
//					estabelecimento.setFotoPath(rs.getString("foto_path").replaceFirst("WebContent/", ""));
//					Blob blob = rs.getBlob("foto");
//					ImageIcon imageIcon = new ImageIcon(blob.getBytes(1,
//							(int) blob.length()));
//					Image foto = imageIcon.getImage();
//					estabelecimento.setFoto(foto);
					
					// FOTO
					estabelecimento.setFoto(rs.getBytes("foto"));
					estabelecimento.setFotoExtension(rs.getString("foto_extension"));
					
					// AVALIACAO
					List<Avaliacao> avaliacoes = (List<Avaliacao>) read("avaliacao");
					double avaliacaoFinal = 0;
					if (avaliacoes != null && !avaliacoes.isEmpty()) {
						int contador = 0;
						for (Avaliacao a: avaliacoes) {
							if (a.getIdEstabelecimento() == estabelecimento.getId()) {
								avaliacaoFinal += a.getValor();
								contador++;
							}
						}
						if (avaliacaoFinal > 0) {
							avaliacaoFinal = avaliacaoFinal / contador;
						}
					}
					estabelecimento.setAvaliacao(avaliacaoFinal);
					
					List<Comentario> comentarios = (List<Comentario>) read("Comentario");
					for (Comentario c: comentarios) {
						if (c.getIdEstabelecimento() == estabelecimento.getId()) {
							estabelecimento.getComentarios().add(c);
						}
					}
					estabelecimento.setVisitas(rs.getInt("visitas"));
					
					List<Reserva> reservas = (List<Reserva>) readWithId("reserva", estabelecimento.getId());
					estabelecimento.setReservas(reservas);

				rs.close();
				stmt.close();

				return estabelecimento;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Mensagem")) {
			String sql = "SELECT * FROM mensagem  WHERE id_remetente= "+id+" OR id_destinario="+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
				while(rs.next()) {
					Mensagem mensagem = new Mensagem();
					mensagem.setId(rs.getInt("id"));
					mensagem.setIdRemetente(id);
					mensagem.setIdDestinario(rs.getInt("id_destinario"));
					mensagem.setMsg(rs.getString("mensagem"));
					mensagem.setData(rs.getDate("data"));
					
					mensagens.add(mensagem);
				}

				rs.close();
				stmt.close();

				return mensagens;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Pessoa")) {
			String sql = "SELECT * FROM pessoa  WHERE id= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

					rs.next();
					Pessoa pessoa = new Pessoa();
					pessoa.setId(rs.getInt("id"));
					pessoa.setNome(rs.getString("name"));
					pessoa.setCpf(rs.getString("cpf"));
					pessoa.setEndRua(rs.getString("end_rua"));
					pessoa.setEndBairro(rs.getString("end_bairro"));
					pessoa.setEndComplemento(rs.getString("end_complemento"));
					pessoa.setEndNumero(rs.getShort("end_numero"));
					pessoa.setTelefone(rs.getString("telefone"));
					pessoa.setIdade(rs.getByte("idade"));
					pessoa.setMasculino(rs.getBoolean("sexo"));
					pessoa.setEndGoogle(rs.getString("end_google"));

//					Blob blob = rs.getBlob("foto");
//					byte [] array = blob.getBytes( 1, ( int ) blob.length() );
//					File file = File.createTempFile(pessoa.getId()+pessoa.getCpf(), ".jpg", new File(""));
//					FileOutputStream out = new FileOutputStream( file );
//					out.write( array );
//					out.close();
//					ImageIcon imageIcon = new ImageIcon(blob.getBytes(1,
//							(int) blob.length()));
//					Image foto = imageIcon.getImage();
//					
//					pessoa.setFoto(foto);
//					pessoa.setFotoFile(file);
					
					// FOTO
					pessoa.setFoto(rs.getBytes("foto"));
					pessoa.setFotoExtension(rs.getString("foto_extension"));
					
					// AVALIACAO
					ArrayList<Amigo> amigosBD = (ArrayList<Amigo>) read("Amigo");
					List<Amigo> amigosPessoa = new ArrayList<Amigo>();
					for (Amigo a: amigosBD) {
						if ((a.getIdAmigo() | a.getIdPessoa()) == pessoa.getId()) {
							amigosPessoa.add(a);
						}
					}
					pessoa.setAmigos(amigosPessoa);

				rs.close();
				stmt.close();

				return pessoa;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Reserva")) {
			String sql = "SELECT * FROM reserva  WHERE id_estabelecimento= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				List<Reserva> reservas = new ArrayList<Reserva>();
				while (rs.next()) {
					Reserva reserva = new Reserva();
					reserva.setId(rs.getInt("id"));
					reserva.setIdPessoa(rs.getInt("id_pessoa"));
					reserva.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					Date data = rs.getTimestamp("data");
					System.out.println("reserva person "+reserva.getIdPessoa()+" - "+data.getHours());
					reserva.setData(data);
					
					reservas.add(reserva);
				}
				rs.close();
				stmt.close();

				return reservas;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Amigo")) {
			String sql = "SELECT * FROM amigo  WHERE id_pessoa= "+id+" OR id_amigo= "+id+" ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Amigo> amigos = new ArrayList<Amigo>();
				while(rs.next()) {
					Amigo amigo = new Amigo();
					amigo.setId(rs.getInt("id"));
					amigo.setIdPessoa(rs.getInt("id_pessoa"));
					amigo.setIdAmigo(rs.getInt("id_amigo"));
					amigos.add(amigo);
				}
				rs.close();
				stmt.close();

				return amigos;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public void update(Object obj) {
		if (obj instanceof Conta) {
			Conta conta = (Conta) obj;
				String sql = "UPDATE conta SET "
						+ " senha=?, email=? "
						+ " WHERE id_pessoa=? AND usuario=?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, conta.getSenha());
					stmt.setString(2, conta.getEmail());
					stmt.setInt(3, (int) conta.getIdPessoa());
					stmt.setString(4, conta.getUsuario());

					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	} else if (obj instanceof Avaliacao) {
			Avaliacao avaliacao = (Avaliacao) obj;
				String sql = "UPDATE avaliacao SET "
						+ " valor=? "
						+ " WHERE id_pessoa=? AND id_estabelecimento=?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setByte(1, avaliacao.getValor());
					stmt.setInt(2, (int) avaliacao.getIdPessoa());
					stmt.setInt(3, (int) avaliacao.getIdEstabelecimento());

					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else if (obj instanceof Carona) {
			Carona carona = (Carona) obj;
			if (carona.getId() > 0) {
				String sql = "UPDATE carona SET "
						+ " id_morotista=?, id_convidado=? " + " WHERE id=? ";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, (int) carona.getIdMotorista());
					stmt.setInt(2, (int) carona.getIdConvidado());
					stmt.setInt(3, (int) carona.getId());

					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Can't save: Carona ID < 1", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj instanceof Estabelecimento) {
			Estabelecimento estabelecimento = (Estabelecimento) obj;
			if (estabelecimento.getId() > 0) {
				String sql = "UPDATE estabelecimento SET "
						+ " nome=?, tipo=?, end_rua=?, end_bairro=?, end_complemento=?, end_numero=?, telefone=?, vagas_totais=?, vagas_disponiveis=?, visitas=?, foto=?, foto_extension=?, end_cidade=?, end_google=? WHERE id=? ";
				try {
					//String path = estabelecimento.getFotoPath();
					//File file = new File(path);
					//InputStream inputImage = new FileInputStream(file);
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, estabelecimento.getNome());
					stmt.setByte(2, estabelecimento.getTipo());
					stmt.setString(3, estabelecimento.getEndRua());
					stmt.setString(4, estabelecimento.getEndBairro());
					stmt.setString(5, estabelecimento.getEndComplemento());
					stmt.setShort(6, estabelecimento.getEndNumero());
					stmt.setString(7, estabelecimento.getTelefone());
					stmt.setInt(8, (int) estabelecimento.getVagasTotais());
					stmt.setInt(9, (int) estabelecimento.getVagasDisponiveis());
					stmt.setInt(10, (int) estabelecimento.getVisitas());
					stmt.setBytes(11, estabelecimento.getFoto());
					stmt.setString(12, estabelecimento.getFotoExtension());
					stmt.setString(13, estabelecimento.getEndCidade());
					stmt.setString(14, estabelecimento.getEndGoogle());
					stmt.setInt(15, (int) estabelecimento.getId());

					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Can't save: Estabelecimento ID < 1", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj instanceof Mensagem) {
			Mensagem mensagem = (Mensagem) obj;
			if (mensagem.getId() > 0) {
				String sql = "UPDATE mensagem SET "
						+ " id_remetente=?, id_destinario=?, mensagem=?, data=? "
						+ " WHERE id=? ";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, (int) mensagem.getIdRemetente());
					stmt.setInt(2, (int) mensagem.getIdDestinario());
					stmt.setString(3, mensagem.getMsg());
					stmt.setDate(4, mensagem.getData());
					stmt.setInt(5, (int) mensagem.getId());

					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Can't save: Mensagem ID < 1", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj instanceof Pessoa) {
			Pessoa pessoa = (Pessoa) obj;
			if (pessoa.getId() > 0) {
				String sql = "UPDATE pessoa SET "
						+ " name=?, idade=?, sexo=?, cpf=?, end_rua=?, end_bairro=?, end_complemento=?, end_numero=?, telefone=?, foto=?, foto_extension=?, end_google=?"
						+ " WHERE id=? ";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, pessoa.getNome());
					stmt.setInt(2, (int) pessoa.getIdade());
					stmt.setBoolean(3, pessoa.isMasculino());
					stmt.setString(4, pessoa.getCpf());
					stmt.setString(5, pessoa.getEndRua());
					stmt.setString(6, pessoa.getEndBairro());
					stmt.setString(7, pessoa.getEndComplemento());
					stmt.setInt(8, pessoa.getEndNumero());
					stmt.setString(9, pessoa.getTelefone());
					stmt.setBytes(10, pessoa.getFoto());
					stmt.setString(11, pessoa.getFotoExtension());
					stmt.setString(12, pessoa.getEndGoogle());
					stmt.setInt(13, (int) pessoa.getId());

					stmt.execute();
					stmt.close();
				} catch (SQLException  e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Can't save: Pessoa ID < 1", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj instanceof Reserva) {
			Reserva reserva = (Reserva) obj;
			if (reserva.getId() > 0) {
				String sql = "UPDATE reserva SET "
						+ " id_pessoa=?, id_estabelecimento=?, data=? "
						+ " WHERE id=? ";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, (int) reserva.getIdPessoa());
					stmt.setInt(2, (int) reserva.getIdEstabelecimento());
					stmt.setTimestamp(3, new Timestamp(reserva.getData().getYear(), reserva.getData().getMonth(), reserva.getData().getDate(), reserva.getData().getHours(), reserva.getData().getMinutes(), 0, 0));
//					stmt.setDate(3, reserva.getData());
					stmt.setInt(4, (int) reserva.getId());

					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Can't save: Reserva ID < 1", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj instanceof Comentario) { // tabela auxiliar
			Comentario comentario = (Comentario) obj;
			if (comentario.getId() > 0) {
				String sql = "UPDATE reserva SET "
						+ " id_pessoa=?, id_estabelecimento=?, comentario=?, data=? "
						+ " WHERE id=? ";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, (int) comentario.getIdPessoa());
					stmt.setInt(2, (int) comentario.getIdEstabelecimento());
					stmt.setString(3, comentario.getComentario());
					stmt.setDate(4, comentario.getData());
					stmt.setInt(5, (int) comentario.getId());

					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Can't save: Comentario ID < 1", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj instanceof Amigo) {
			String sql = "UPDATE amigo SET "
					+ "id_pessoa=?, id_amigo=? WHERE id=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				Amigo amigo = (Amigo) obj;
				stmt.setInt(1, (int) amigo.getIdPessoa());
				stmt.setInt(2, (int) amigo.getIdAmigo());
				stmt.setInt(3, (int) amigo.getId());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Object obj) {
		if (obj instanceof Amigo) {
			Amigo amigo = (Amigo) obj;
			String sql = "DELETE FROM amigo WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) amigo.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Avaliacao) {
			Avaliacao avaliacao = (Avaliacao) obj;
			String sql = "DELETE FROM avaliacao WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) avaliacao.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Carona) {
			Carona carona = (Carona) obj;
			String sql = "DELETE FROM carona WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) carona.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Comentario) {
			Comentario comentario = (Comentario) obj;
			String sql = "DELETE FROM comentario WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) comentario.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Estabelecimento) {
			Estabelecimento estabelecimento = (Estabelecimento) obj;
			String sql = "DELETE FROM estabelecimento WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) estabelecimento.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM reserva WHERE id_estabelecimento=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) estabelecimento.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM avaliacao WHERE id_estabelecimento=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) estabelecimento.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM comentario WHERE id_estabelecimento=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) estabelecimento.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM carona WHERE id_estabelecimento=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) estabelecimento.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Mensagem) {
			Mensagem mensagem = (Mensagem) obj;
			String sql = "DELETE FROM mensagem WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) mensagem.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Pessoa) {
			Pessoa pessoa = (Pessoa) obj;
			String sql = "DELETE FROM pessoa WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM conta WHERE id_pessoa=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM amigo WHERE id_pessoa=? OR id_amigo=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.setInt(2, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM reserva WHERE id_pessoa=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM avaliacao WHERE id_pessoa=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM comentario WHERE id_pessoa=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM mensagem WHERE id_pessoa=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sql = "DELETE FROM carona WHERE id_convidado=? OR id_motorista";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) pessoa.getId());
				stmt.setInt(2, (int) pessoa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		} else if (obj instanceof Reserva) {
			Reserva reserva = (Reserva) obj;
			String sql = "DELETE FROM reserva WHERE id=?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, (int) reserva.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<?> readQuery(String obj, String sql) {
	    if (obj.equalsIgnoreCase("Conta")) {
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Conta> lista = new ArrayList<Conta>();
			while (rs.next()) {
				Conta conta = new Conta();
				conta.setId(rs.getInt("id"));
				conta.setIdPessoa(rs.getInt("id_pessoa"));
				conta.setUsuario(rs.getString("usuario"));
				conta.setSenha(rs.getString("senha"));
				conta.setFacebook(rs.getString("facebook"));
				conta.setEmail(rs.getString("email"));

				lista.add(conta);
			}

			rs.close();
			stmt.close();

			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    } else if (obj.equalsIgnoreCase("Avaliacao")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
				while (rs.next()) {
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setId(rs.getInt("id"));
					avaliacao.setIdPessoa(rs.getInt("id_pessoa"));
					avaliacao.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					avaliacao.setValor(rs.getByte("valor"));

					lista.add(avaliacao);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Carona")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Carona> lista = new ArrayList<Carona>();
				while (rs.next()) {
					Carona carona = new Carona();
					carona.setId(rs.getInt("id"));
					carona.setIdMotorista(rs.getInt("id_motorista"));
					carona.setIdConvidado(rs.getInt("id_convidado"));

					lista.add(carona);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Comentario")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Comentario> lista = new ArrayList<Comentario>();
				while (rs.next()) {
					Comentario comentario = new Comentario();
					comentario.setId(rs.getInt("id"));
					comentario.setIdPessoa(rs.getInt("id_pessoa"));
					comentario.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					comentario.setComentario(rs.getString("comentario"));
					comentario.setData(rs.getDate("data"));
					Avaliacao avaliacao = (Avaliacao) readWithId("avaliacao", rs.getInt("id_avaliacao"));
					comentario.setAvaliacao(avaliacao);

					lista.add(comentario);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Estabelecimento")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Estabelecimento> lista = new ArrayList<Estabelecimento>();
				while (rs.next()) {
					Estabelecimento estabelecimento = new Estabelecimento();
					estabelecimento.setId(rs.getInt("id"));
					estabelecimento.setNome(rs.getString("nome"));
					estabelecimento.setDescricao(rs.getString("descricao"));
					estabelecimento.setEndRua(rs.getString("end_rua"));
					estabelecimento.setEndCidade(rs.getString("end_cidade"));
					estabelecimento.setEndGoogle(rs.getString("end_google"));
					estabelecimento.setEndBairro(rs.getString("end_bairro"));
					estabelecimento.setEndComplemento(rs
							.getString("end_complemento"));
					estabelecimento.setEndNumero(rs.getShort("end_numero"));
					estabelecimento.setTelefone(rs.getString("telefone"));
					estabelecimento.setVagasTotais(rs.getByte("vagas_totais"));
					estabelecimento.setVagasDisponiveis(rs
							.getByte("vagas_disponiveis"));
					// FOTO
					estabelecimento.setFoto(rs.getBytes("foto"));
					estabelecimento.setFotoExtension(rs.getString("foto_extension"));
					
					// AVALIACAO
					List<Avaliacao> avaliacoes = (List<Avaliacao>) read("avaliacao");
					double avaliacaoFinal = 0;
					if (avaliacoes != null && !avaliacoes.isEmpty()) {
						int contador = 0;
						for (Avaliacao a: avaliacoes) {
							if (a.getIdEstabelecimento() == estabelecimento.getId()) {
								avaliacaoFinal += a.getValor();
								contador++;
							}
						}
						if (avaliacaoFinal > 0) {
							avaliacaoFinal = avaliacaoFinal / contador;
						}
					}
					estabelecimento.setAvaliacao(avaliacaoFinal);
					List<Comentario> comentarios = (List<Comentario>) read("Comentario");
					for (Comentario c: comentarios) {
						if (c.getIdEstabelecimento() == estabelecimento.getId()) {
							estabelecimento.getComentarios().add(c);
						}
					}
					estabelecimento.setVisitas(rs.getInt("visitas"));

					lista.add(estabelecimento);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Mensagem")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Mensagem> lista = new ArrayList<Mensagem>();
				while (rs.next()) {
					Mensagem mensagem = new Mensagem();
					mensagem.setId(rs.getInt("id"));
					mensagem.setIdRemetente(rs.getInt("id_remetente"));
					mensagem.setIdDestinario(rs.getInt("id_destinario"));
					mensagem.setMsg(rs.getString("mensagem"));
					mensagem.setData(rs.getDate("data"));

					lista.add(mensagem);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Pessoa")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
				while (rs.next()) {
					Pessoa pessoa = new Pessoa();
					pessoa.setId(rs.getInt("id"));
					pessoa.setNome(rs.getString("name"));
					pessoa.setCpf(rs.getString("cpf"));
					pessoa.setEndRua(rs.getString("end_rua"));
					pessoa.setEndBairro(rs.getString("end_bairro"));
					pessoa.setEndComplemento(rs.getString("end_complemento"));
					pessoa.setEndNumero(rs.getShort("end_numero"));
					pessoa.setTelefone(rs.getString("telefone"));
					pessoa.setIdade(rs.getByte("idade"));
					pessoa.setMasculino(rs.getBoolean("sexo"));
					// FOTO
					pessoa.setFoto(rs.getBytes("foto"));
					pessoa.setFotoExtension(rs.getString("foto_extension"));
					pessoa.setEndGoogle(rs.getString("end_google"));
					
					// AVALIACAO
					ArrayList<Amigo> amigosBD = (ArrayList<Amigo>) read("Amigo");
					List<Amigo> amigosPessoa = new ArrayList<Amigo>();
					for (Amigo a: amigosBD) {
						if ((a.getIdAmigo() | a.getIdPessoa()) == pessoa.getId()) {
							amigosPessoa.add(a);
						}
					}
					pessoa.setAmigos(amigosPessoa);

					lista.add(pessoa);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Reserva")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Reserva> lista = new ArrayList<Reserva>();
				while (rs.next()) {
					Reserva reserva = new Reserva();
					reserva.setId(rs.getInt("id"));
					reserva.setIdPessoa(rs.getInt("id_pessoa"));
					reserva.setIdEstabelecimento(rs
							.getInt("id_estabelecimento"));
					Date data = rs.getTimestamp("data");
					System.out.println("reserva person "+reserva.getIdPessoa()+" - "+data.getHours());
					reserva.setData(data);

					lista.add(reserva);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj.equalsIgnoreCase("Amigo")) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				ArrayList<Amigo> lista = new ArrayList<Amigo>();
				while (rs.next()) {
					Amigo amigo = new Amigo();
					amigo.setId(rs.getInt("id"));
					amigo.setIdPessoa(rs.getInt("id_pessoa"));
					amigo.setIdAmigo(rs.getInt("id_amigo"));

					lista.add(amigo);
				}

				rs.close();
				stmt.close();

				return lista;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	
	}

}
