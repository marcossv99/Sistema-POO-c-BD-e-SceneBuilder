package persistencia;

import dominio.EspecieRemedio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EspecieRemedioDAO {

	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM EspecieRemedio";
	private final String INCLUIR = "INSERT INTO EspecieRemedio ( nomeEspecie, nomeRemedio, marcaRemedio ) "
			+ "VALUES ( ?, ?, ? )";
	private final String ALTERAR = "UPDATE EspecieRemedio SET especie = ?, nomeRemedio = ?, marcaRemedio = ?"
			+ " WHERE especie = ? AND nomeRemedio = ? AND marcaRemedio = ?";
	private final String BUSCAR = "SELECT * FROM EspecieRemedio WHERE especie = ?  AND nomeRemedio = ? AND marcaRemedio = ?";
	private final String BUSCARAMPLO = "SELECT * FROM EspecieRemedio WHERE marcaRemedio LIKE %?% OR  nomeRemedio LIKE %?%"
			+ " OR especie LIKE %?%";
	private final String DELETAR = "DELETE FROM EspecieRemedio WHERE especie = ? AND nomeRemedio = ? AND marcaRemedio = ?";

	public EspecieRemedioDAO() {
		minhaConexao = new Conexao("postgres", "postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}

	public ArrayList<EspecieRemedio> relatorio() {
		ArrayList<EspecieRemedio> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RELATORIO);
			while (rs.next()) {
				EspecieRemedio er = new EspecieRemedio(rs.getString("especie"), rs.getString("nomeRemedio"),
						rs.getString("marcaRemedio"));
				lista.add(er);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public ArrayList<EspecieRemedio> buscarMarcaRacao(String op) {
		ArrayList<EspecieRemedio> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCARAMPLO);
			instrucao.setString(1, op);
			instrucao.setString(2, op);
			instrucao.setString(3, op);
			ResultSet rs = instrucao.executeQuery();
			while (rs.next()) {
				EspecieRemedio er = new EspecieRemedio(rs.getString("especie"), rs.getString("nomeRemedio"),
						rs.getString("marcaRacao"));
				lista.add(er);
			}
			minhaConexao.desconectar();
		} catch (SQLException ex) {
			System.out.println("Erro na busca: " + ex.getMessage());
		}
		return lista;
	}

	public EspecieRemedio buscar(String e, String mr, String nr) {
		EspecieRemedio er = null;
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setString(1, e);
			instrucao.setString(2, nr);
			instrucao.setString(3, mr);
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				er = new EspecieRemedio(rs.getString("especie"), rs.getString("nomeRemedio"),
						rs.getString("marcaRemedio"));
			}
			minhaConexao.desconectar();
		} catch (SQLException ex) {
			System.out.println("Erro na busca: " + ex.getMessage());
		}
		return er;
	}

	public void inclusao(EspecieRemedio er) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setString(1, er.getEspecie());
			instrucao.setString(2, er.getNomeRemedio());
			instrucao.setString(3, er.getMarcaRemedio());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclusão: " + e.getMessage());
		}
	}

	public void exclusao(String e, String nr, String mr) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(DELETAR);
			instrucao.setString(1, e);
			instrucao.setString(2, nr);
			instrucao.setString(3, mr);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception ex) {
			System.out.println("Erro na exclusão: " + ex.getMessage());
		}
	}

	public void alteracao(EspecieRemedio er, String e, String nr, String mr) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
			instrucao.setString(1, er.getEspecie());
			instrucao.setString(2, er.getNomeRemedio());
			instrucao.setString(3, er.getMarcaRemedio());
			instrucao.setString(5, e);
			instrucao.setString(6, nr);
			instrucao.setString(7, mr);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception ex) {
			System.out.println("Erro na alteração: " + ex.getMessage());
		}
	}
}
