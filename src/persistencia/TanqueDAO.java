package persistencia;

import dominio.Tanque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TanqueDAO {

	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM Tanque WHERE registroCriadouro = ?";
	private final String INCLUIR = "INSERT INTO Tanque ( idTanque, registroCriadouro, capacidade )" + " VALUES ( ?, ?, CAST( ? AS integer) )";
	private final String ALTERAR = "UPDATE Tanque SET id = ?, registroCriadouro = ?, capacidade = ?"
			+ " WHERE idTanque = ? AND registroCriadouro = ?";
	private final String BUSCAR = "SELECT * FROM Tanque WHERE registroCriadouro = ? AND idTanque = ?";
	private final String DELETAR = "DELETE FROM Tanque WHERE idTanque = ? AND registroCriadouro = ?";

	public TanqueDAO() {
		minhaConexao = new Conexao("postgres", "postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}

	public ArrayList<Tanque> relatorio(String registro) {
		ArrayList<Tanque> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(RELATORIO);
			instrucao.setString(1, registro);
			ResultSet rs = instrucao.executeQuery();
			while (rs.next()) {
				Tanque t = new Tanque(rs.getInt("idTanque"), rs.getInt("capacidade"));
				lista.add(t);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	/*public Tanque buscar(int id, String registro) {
		Tanque c = null;
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setString(1, registro);
			instrucao.setInt(2, id);
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				c = new Tanque(rs.getInt("idTanque"), rs.getInt("capacidade"));
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro na busca: " + e.getMessage());
		}
		return c;
	}*/

	/*
	 * public ArrayList<Tanque> relatorioCapacidadeMaiores( int capacidade ){
	 * ArrayList<Tanque> lista = new ArrayList<>(); try{ minhaConexao.conectar();
	 * PreparedStatement instrucao =
	 * minhaConexao.getConexao().prepareStatement(getRELATORIO());
	 * instrucao.setString(1, "Tanque"); ResultSet rs = instrucao.executeQuery();
	 * while(rs.next()){ if ( rs.getInt("capacidade") >= capacidade ) { Tanque t =
	 * new Tanque(rs.getInt("idTanque"), rs.getInt("capacidade")); lista.add(t); } }
	 * minhaConexao.desconectar(); }catch(SQLException e){
	 * System.out.println("Erro no relatório: "+e.getMessage()); } return lista; }
	 * 
	 * public ArrayList<Tanque> relatorioCapacidadeMenores( int capacidade ){
	 * ArrayList<Tanque> lista = new ArrayList<>(); try{ minhaConexao.conectar();
	 * PreparedStatement instrucao =
	 * minhaConexao.getConexao().prepareStatement(getRELATORIO());
	 * instrucao.setString(1, "Tanque"); ResultSet rs = instrucao.executeQuery();
	 * while(rs.next()){ if ( rs.getInt("capacidade") <= capacidade ) { Tanque t =
	 * new Tanque(rs.getInt("idTanque"), rs.getInt("capacidade")); lista.add(t); } }
	 * minhaConexao.desconectar(); }catch(SQLException e){
	 * System.out.println("Erro no relatório: "+e.getMessage()); } return lista; }
	 */

	public void inclusao(Tanque c) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setInt(1, c.getIdTanque());
			instrucao.setString(2, c.getCriadouro());
			instrucao.setInt(3, c.getCapacidade());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclusão: " + e.getMessage());
		}
	}

	public void exclusao(int id, String criadouro) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(DELETAR);
			instrucao.setInt(1, id);
			instrucao.setString(2, criadouro);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclusão: " + e.getMessage());
		}
	}

	public void alteracao(Tanque c, int id, String criadouro) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
			instrucao.setInt(1, c.getCapacidade());
			instrucao.setString(2, c.getCriadouro());
			instrucao.setInt(3, c.getCapacidade());
			instrucao.setInt(4, id);
			instrucao.setString(5, criadouro);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na alteração: " + e.getMessage());
		}
	}
	
	public boolean buscarId( String n, int id ){
    	boolean flag = false;
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
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
	
	public ObservableList<String> ids( String r ) {
    	ObservableList<String> lista = FXCollections.observableArrayList();
    	String n;
        try{
        	minhaConexao.conectar();
        	PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(RELATORIO);
			instrucao.setString(1, r);
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
                n = rs.getString("idTanque");
                lista.add(n);
            }
            minhaConexao.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return lista;
    }

}
