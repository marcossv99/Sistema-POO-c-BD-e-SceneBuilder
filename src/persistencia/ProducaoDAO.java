package persistencia;

import dominio.Producao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProducaoDAO {

	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM Producao WHERE registroCriadouro = ?";
	private final String INCLUIR = "INSERT INTO Producao ( idProducao, quantidade, nomeEspecie, idTanque, dataProducao, registroCriadouro )"
			+ " VALUES ( ?, ?, ?, ?, ?, ?)";
	private final String ALTERAR = "UPDATE Producao SET idProducao = ?, quantidade = ?, nomeEspecie = ?, idTanque = ?,"
			+ "dataProducao = ? WHERE idProducao = ? AND registroCriadouro = ? ";
	private final String DELETAR = "DELETE FROM Producao WHERE registroCriadouro = ? AND idProducao = ?";
	private final String BUSCAR = "SELECT * FROM Producao WHERE registroCriadouro = ? AND ( nomeEspecie LIKE ? OR idTanque::TEXT ~~ ? )";
	private final String BUSQUINHA = "SELECT * FROM Producao WHERE registroCriadouro = ? AND idProducao = ?";
	
	public ProducaoDAO() {
		minhaConexao = new Conexao("postgres", "postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}

	public ArrayList<Producao> relatorio(String rc) {
		ArrayList<Producao> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(RELATORIO);
			instrucao.setString(1, rc);
			ResultSet rs = instrucao.executeQuery();
			while (rs.next()) {
				Producao c = new Producao(rs.getInt("idProducao"), rs.getInt("quantidade"), rs.getString("nomeEspecie"),
						rs.getInt("idTanque"), rs.getString("dataProducao"), rs.getString("registroCriadouro"));
				lista.add(c);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public ArrayList<Producao> buscar(String rc, String p) {
		ArrayList<Producao> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setString(1, rc);
			instrucao.setString(2,"%" + p + "%");
			instrucao.setString(3,"'" + p + "'");
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				Producao c = new Producao(rs.getInt("idProducao"), rs.getInt("quantidade"), rs.getString("nomeEspecie"),
						rs.getInt("idTanque"), rs.getString("dataProducao"), rs.getString("registroCriadouro"));
				lista.add(c);
			}
			minhaConexao.desconectar();
		} catch (SQLException ex) {
			System.out.println("Erro na busca: " + ex.getMessage());
		}
		return lista;
	}

	public void inclusao(Producao c) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setInt(1, c.getIdProducao());
			instrucao.setInt(2, c.getQuantidade());
			instrucao.setString(3, c.getNomeEspecie());
			instrucao.setInt(4, c.getIdTanque());
			instrucao.setString(5, c.getDataProducao());
			instrucao.setString(6, c.getRegistroCriadouro());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclusão: " + e.getMessage());
		}
	}

	public void exclusao(int id, String rc) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(DELETAR);
			instrucao.setString(1, rc);
			instrucao.setInt(2, id);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclusão: " + e.getMessage());
		}
	}

	public void alteracao(Producao c, int id) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
			instrucao.setInt(1, c.getQuantidade());
			instrucao.setString(2, c.getNomeEspecie());
			instrucao.setInt(3, c.getIdTanque());
			instrucao.setString(4, c.getDataProducao());
			instrucao.setInt(5, id);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na alteração: " + e.getMessage());
		}
	}
	
	public boolean verificar( String n, int id ){
    	boolean flag = false;
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSQUINHA);
            instrucao.setString(1, n);
            instrucao.setInt(2, id);
            ResultSet rs = instrucao.executeQuery();
            flag = rs.next();
            minhaConexao.desconectar();
            
        }catch(Exception e){
            System.out.println("Erro na procura do Registro: "+e.getMessage());
        } 
        return flag;
    }
}
