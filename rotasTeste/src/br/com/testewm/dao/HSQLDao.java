package br.com.testewm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.testewm.Rota;

public class HSQLDao {
	protected static final Logger LOGGER = Logger.getLogger(HSQLDao.class);

	/**
	 *
	 */
	public Connection getConnection() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:rotas", "SA", "");
			return c;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public void criarTabela() {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			
			c = getConnection();
			stmt = c.prepareStatement("CREATE TABLE ROTAS(ID IDENTITY, DESCRICAO VARCHAR, PONTOI VARCHAR, PONTOF VARCHAR, DISTANCIA DOUBLE)");
			stmt.execute();
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}finally{
			try{
				stmt.close();
				c.close();
			}catch(Exception e){
				LOGGER.error(e.getMessage());
			}
			
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Rota> listarRotas() {
		List<Rota> rotas = new ArrayList<Rota>();
		Connection c = null;
		PreparedStatement stmt = null;
		
		try {
			criarTabela();
			insereDados();
			c = getConnection();
			stmt = c.prepareStatement("select * from ROTAS");
			ResultSet rs = stmt.executeQuery();
			Rota rota = null;
			while (rs.next()) {
				rota = new Rota();
				rota.setDistancia(rs.getDouble("DISTANCIA"));
				rota.setPontoInicio(rs.getString("PONTOI"));
				rota.setPontoFim(rs.getString("PONTOF"));
				rota.setDescricao(rs.getString("DESCRICAO"));
				rotas.add(rota);
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				c.close();
			}catch(Exception e){
				LOGGER.error(e.getMessage());
			}
			
		}
		return rotas;
	}

	private void insereDados() {
		adicionaRota("A para B", "A", "B", 10);
		adicionaRota("B para D", "B", "D", 15);
		adicionaRota("A para C", "A", "C", 20);
		adicionaRota("C para D", "C", "D", 30);
		adicionaRota("B para E", "B", "E", 50);
		adicionaRota("D para E", "D", "E", 30);
	}

	public void adicionaRota(String descricao, String pontoInicial, String pontoFinal, double distanciaKm) {
		PreparedStatement stmt = null;
		Connection c = null;
		try {
			c = getConnection();
			stmt = c.prepareStatement("INSERT INTO ROTAS(DESCRICAO, PONTOI, PONTOF, DISTANCIA) " + "VALUES (?, ?, ?, ?)");
			stmt.setString(1, descricao);
			stmt.setString(2, pontoInicial);
			stmt.setString(3, pontoFinal);
			stmt.setDouble(4, distanciaKm);
			stmt.execute();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}finally{
			try{
				stmt.close();
				c.close();
			}catch(Exception e){
				LOGGER.error(e.getMessage());
			}
			
		}
	}

	public void adicionaRota(Rota r) throws Exception {
		PreparedStatement stmt = null;
		Connection c = null;
		try {
			c = getConnection();
			stmt = c.prepareStatement("INSERT INTO ROTAS(DESCRICAO, PONTOI, PONTOF, DISTANCIA) " + "VALUES (?, ?, ?, ?)");
			stmt.setString(1, r.getDescricao());
			stmt.setString(2, r.getPontoInicio());
			stmt.setString(3, r.getPontoFim());
			stmt.setDouble(4, r.getDistancia());
			stmt.execute();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw e;
		}finally{
			try{
				stmt.close();
				c.close();
			}catch(Exception e){
				LOGGER.error(e.getMessage());
			}
			
		}
	}
}