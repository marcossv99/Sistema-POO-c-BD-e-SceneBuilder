package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Especie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EspecieDAO{
	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM Especie";
    private final String INCLUIR = "INSERT INTO Especie ( nome, diasAdulto, comprimentoIdeal, pesoIdeal,"
    		+ " phMin, phMax, tempMin, tempMax ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
    private final String ALTERAR = "UPDATE Especie SET nome = ?, diasAdulto = ?, comprimentoIdeal = ?, pesoIdeal = ?," 
            + " phMin = ?, phMax = ?, tempMin = ?, tempMax = ?";
    private final String BUSCAR = "SELECT * FROM Especie WHERE nome LIKE ?";
    private final String DELETAR = "DELETE FROM Especie WHERE nome = ?";
    
    public EspecieDAO () {
		minhaConexao = new Conexao( "postgres","postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}
    
    public ArrayList<Especie> relatorio(){
        ArrayList<Especie> lista = new ArrayList<>();
        try{
            minhaConexao.conectar();
            Statement instrucao = minhaConexao.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(RELATORIO);
            while(rs.next()){
                Especie p = new Especie( rs.getString("nome"), rs.getInt("comprimentoIdeal"),
                		rs.getFloat("pesoIdeal"), rs.getFloat("phMin") , rs.getFloat ("phMax"), 
                		rs.getInt ("tempMin"), rs.getInt ("tempMax"), rs.getInt("diasAdulto"));
                lista.add(p);
            }
            minhaConexao.desconectar();
        }catch(SQLException e){
            System.out.println("Erro no relatório: "+e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<Especie> buscar( String n ){
    	ArrayList<Especie> lista = new ArrayList<>();
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1,"%" + n + "%");
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
                Especie p = new Especie( rs.getString("nome"), rs.getInt("comprimentoIdeal"),
                		rs.getFloat("pesoIdeal"), rs.getFloat("phMin") , rs.getFloat ("phMax"), 
                		rs.getInt ("tempMin"), rs.getInt ("tempMax"), rs.getInt("diasAdulto"));
                lista.add(p);
            }
            minhaConexao.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return lista;
    }
    
    public void inclusao(Especie c){
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
            instrucao.setString(1, c.getNome());
            instrucao.setInt(2, c.getDiasAdulto());
            instrucao.setInt(3, c.getComprimentoIdeal());
            instrucao.setFloat(4, c.getPesoIdeal());
            instrucao.setFloat(5, c.getPhMin());
            instrucao.setFloat(6, c.getPhMax());
            instrucao.setInt(7, c.getTempMin());
            instrucao.setInt(8, c.getTempMax());
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception e){
            System.out.println("Erro na inclusão: "+e.getMessage());
        }
    }
     
    public void exclusao( String n ){
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(DELETAR);
            instrucao.setString(1, n);
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception e){
            System.out.println("Erro na exclusão: "+e.getMessage());
        }
    }
    
    public void alteracao(Especie c, String n) {
        try {
            minhaConexao.conectar();
            
            Statement desabilitarRestricao = minhaConexao.getConexao().createStatement();
            desabilitarRestricao.execute("SET CONSTRAINTS ALL DEFERRED");
            if (verificarReferencias(n)) {
                System.out.println("Existem registros dependentes na tabela 'producao' referenciando esta espécie.");
    
                String novaReferencia = "NovaEspecie"; // Substitua "NovaEspecie" pelo nome da nova espécie desejada
    
                PreparedStatement updateReferencia = minhaConexao.getConexao().prepareStatement(
                        "UPDATE Producao SET nomeEspecie = ? WHERE nomeEspecie = ?");
                updateReferencia.setString(1, novaReferencia);
                updateReferencia.setString(2, n);
                updateReferencia.executeUpdate();
    
                System.out.println("Registros dependentes na tabela 'producao' atualizados para a nova espécie: " + novaReferencia);
            }
    
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
            instrucao.setString(1, c.getNome());
            instrucao.setInt(2, c.getDiasAdulto());
            instrucao.setInt(3, c.getComprimentoIdeal());
            instrucao.setFloat(4, c.getPesoIdeal());
            instrucao.setFloat(5, c.getPhMin());
            instrucao.setFloat(6, c.getPhMax());
            instrucao.setInt(7, c.getTempMin());
            instrucao.setInt(8, c.getTempMax());
            instrucao.execute();
    
            System.out.println("Alteração realizada com sucesso!");
    
            minhaConexao.desconectar();
        } catch (Exception e) {
            System.out.println("Erro na alteração: " + e.getMessage());
        }
    }
    private boolean verificarReferencias(String nomeEspecie) throws SQLException {
        PreparedStatement verificarReferencias = minhaConexao.getConexao().prepareStatement(
                "SELECT COUNT(*) FROM Producao WHERE nomeEspecie = ?");
        verificarReferencias.setString(1, nomeEspecie);
        ResultSet resultado = verificarReferencias.executeQuery();
        resultado.next();
        int count = resultado.getInt(1);
        return count > 0;
    }
    
    public int verificarNome( String n ){
    	int flag = 0;
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, n);
            ResultSet rs = instrucao.executeQuery();
            while (rs.next()) {
            	flag++;
            }
            minhaConexao.desconectar();
            
        }catch(Exception e){
            System.out.println("Erro na procura do Registro: "+e.getMessage());
        } 
        return flag;
    } 
    
    public ObservableList<String> nomes() {
    	ObservableList<String> lista = FXCollections.observableArrayList();
    	String n;
        try{
        	minhaConexao.conectar();
        	Statement instrucao = minhaConexao.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(RELATORIO);
            while(rs.next()){
                n = rs.getString("nome");
                lista.add(n);
            }
            minhaConexao.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return lista;
    }
}
