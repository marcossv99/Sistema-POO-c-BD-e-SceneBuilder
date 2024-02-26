package persistencia;

import dominio.Racao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RacaoDAO {

	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM Racao";
	private final String INCLUIR = "INSERT INTO Racao ( nome, marca, precoKg ) VALUES ( ?, ?, ? )";
	private final String ALTERAR = "UPDATE Racao SET nome = ?, marca = ?, precoKg = ? WHERE nome = ? AND marca = ?";
	private final String BUSCAR = "SELECT * FROM Racao WHERE nome LIKE ? OR marca LIKE ? OR precoKg::TEXT ~~ ?";
	private final String BUSQUINHA = "SELECT * FROM Racao WHERE nome = ? AND marca = ?";
	private final String DELETAR = "DELETE FROM Racao WHERE nome = ? AND marca = ?";

	public RacaoDAO() {
		minhaConexao = new Conexao("postgres", "postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}

	public ArrayList<Racao> relatorio() {
		ArrayList<Racao> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RELATORIO);
			while (rs.next()) {
				Racao r = new Racao(rs.getString("nome"), rs.getString("marca"), rs.getDouble("precoKg"));
				lista.add(r);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public ArrayList<Racao> buscar(String op) {
		ArrayList<Racao> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setString(1,"%" + op + "%");
			instrucao.setString(2,"%" + op + "%");
			instrucao.setString(3,"'" + op + "'");
			ResultSet rs = instrucao.executeQuery();
			while (rs.next()) {
				Racao r = new Racao(rs.getString("nome"), rs.getString("marca"), rs.getDouble("precoKg"));
				lista.add(r);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public void inclusao(Racao c) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setString(1, c.getNome());
			instrucao.setString(2, c.getMarca());
			instrucao.setDouble(3, c.getPrecoKg());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclusão: " + e.getMessage());
		}
	}

	public void exclusao(String n, String m) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(DELETAR);
			instrucao.setString(1, n);
			instrucao.setString(2, m);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclusão: " + e.getMessage());
		}
	}

	public void alteracao(Racao c, String n, String m) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
			instrucao.setString(1, c.getNome());
			instrucao.setString(2, c.getMarca());
			instrucao.setDouble(3, c.getPrecoKg());
			instrucao.setString(4, n);
			instrucao.setString(5, m);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na alteração: " + e.getMessage());
		}
	}
	
	public boolean verificar( String n, String m ){
    	boolean flag = false;
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSQUINHA);
            instrucao.setString(1, n);
            instrucao.setString(2, m);
            ResultSet rs = instrucao.executeQuery();
            flag = rs.next();
            minhaConexao.desconectar();
            
        }catch(Exception e){
            System.out.println("Erro na procura do Registro: "+e.getMessage());
        } 
        return flag;
    }
}
