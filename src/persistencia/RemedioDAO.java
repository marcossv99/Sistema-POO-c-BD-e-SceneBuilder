package persistencia;

import dominio.Remedio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RemedioDAO {

	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM Remedio";
	private final String INCLUIR = "INSERT INTO Remedio ( nome, marca, dosagem ) VALUES ( ?, ?, ? )";
	private final String ALTERAR = "UPDATE Remedio SET nome = ?, marca = ?, dosagem = ? WHERE nome = ? AND marca = ?";
	private final String BUSCAR = "SELECT * FROM Remedio WHERE nome LIKE ? OR marca LIKE ? OR dosagem::TEXT ~~ ?";
	private final String BUSQUINHA = "SELECT * FROM Remedio WHERE nome = ? AND marca = ?";
	private final String DELETAR = "DELETE FROM Remedio WHERE nome = ? AND marca = ?";

	public RemedioDAO() {
		minhaConexao = new Conexao("postgres", "postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}

	public ArrayList<Remedio> relatorio() {
		ArrayList<Remedio> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RELATORIO);
			while (rs.next()) {
				Remedio r = new Remedio(rs.getString("nome"), rs.getString("marca"), rs.getDouble("dosagem"));
				lista.add(r);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public ArrayList<Remedio> buscar(String op) {
		ArrayList<Remedio> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setString(1,"%" + op + "%");
			instrucao.setString(2,"%" + op + "%");
			instrucao.setString(3,"'" + op + "'");
			ResultSet rs = instrucao.executeQuery();
			while (rs.next()) {
				Remedio r = new Remedio(rs.getString("nome"), rs.getString("marca"), rs.getDouble("dosagem"));
				lista.add(r);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public void inclusao(Remedio c) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setString(1, c.getNome());
			instrucao.setString(2, c.getMarca());
			instrucao.setDouble(3, c.getDosagem());
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

	public void alteracao(Remedio c, String n, String m) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
			instrucao.setString(1, c.getNome());
			instrucao.setString(2, c.getMarca());
			instrucao.setDouble(3, c.getDosagem());
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
