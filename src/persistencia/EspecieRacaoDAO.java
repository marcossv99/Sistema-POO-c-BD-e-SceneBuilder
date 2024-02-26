package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dominio.EspecieRacao;

public class EspecieRacaoDAO {
	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM EspecieRacao";
	private final String INCLUIR = "INSERT INTO EspecieRacao ( nomeEspecie, nomeRacao, marcaRacao )"
			+ " VALUES ( ?, ?, ? )";
    private final String ALTERAR = "UPDATE EspecieRacao SET especie = ?, nomeRacao = ?, marcaRacao = ?, quantidade = ?"
    		+ " WHERE especie = ? AND nomeRacao = ? AND marcaRacao = ?";
    private final String BUSCAR = "SELECT * FROM EspecieRacao WHERE especie = ?  AND nomeRacao = ? AND marcaRacao = ?";
    private final String BUSCARAMPLO = "SELECT * FROM EspecieRacao WHERE marcaRacao LIKE %?% OR nomeRacao LIKE %?% "
    		+ "OR especie LIKE %?%";
    private final String DELETAR = "DELETE FROM EspecieRacao WHERE especie = ? AND nomeRacao = ? AND marcaRacao = ?";
    
    public EspecieRacaoDAO () {
		minhaConexao = new Conexao( "postgres","postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}
    
    public ArrayList<EspecieRacao> relatorio(){
        ArrayList<EspecieRacao> lista = new ArrayList<>();
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(RELATORIO);
            instrucao.setString(1, "EspecieRacao");
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
                EspecieRacao er = new EspecieRacao( rs.getString("especie"), rs.getString("nomeRacao"),
                		rs.getString("marcaRacao"));
                lista.add(er);
            }
            minhaConexao.desconectar();
        }catch(SQLException e){
            System.out.println("Erro no relatório: "+e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<EspecieRacao> buscarAmplo( String op ){
    	ArrayList<EspecieRacao> lista = new ArrayList<>();
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCARAMPLO);
            instrucao.setString(1, op);
            instrucao.setString(2, op);
            instrucao.setString(3, op);
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
            	EspecieRacao er = new EspecieRacao( rs.getString("especie"), rs.getString("nomeRacao"),
                		rs.getString("marcaRacao"));
                lista.add(er);
            }
            minhaConexao.desconectar();
        }catch(SQLException ex){
            System.out.println("Erro na busca: "+ex.getMessage());
        }
        return lista;
    }
    
    public EspecieRacao buscar( String e, String mr, String nr ){
    	EspecieRacao er = null;
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, e);
            instrucao.setString(2, nr);
            instrucao.setString(3, mr);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                er = new EspecieRacao( rs.getString("especie"), rs.getString("nomeRacao"),
                		rs.getString("marcaRacao"));
            }
            minhaConexao.desconectar();
        }catch(SQLException ex){
            System.out.println("Erro na busca: "+ex.getMessage());
        }
        return er;
    }
    
    public void inclusao(EspecieRacao er){
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
            instrucao.setString(1, er.getEspecie());
            instrucao.setString(2, er.getNomeRacao());
            instrucao.setString(3, er.getMarcaRacao());
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception e){
            System.out.println("Erro na inclusão: "+e.getMessage());
        }
    }
     
    public void exclusao( String e, String nr, String mr ){
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(DELETAR);
            instrucao.setString(1, e);
            instrucao.setString(2, nr);
            instrucao.setString(3, mr);
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception ex){
            System.out.println("Erro na exclusão: "+ex.getMessage());
        }
    }
    
    public void alteracao( EspecieRacao er, String e, String nr, String mr ){
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
            instrucao.setString(1, er.getEspecie());
            instrucao.setString(2, er.getNomeRacao());
            instrucao.setString(3, er.getMarcaRacao());
            instrucao.setString(5, e);
            instrucao.setString(6, nr);
            instrucao.setString(7, mr);
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception ex){
            System.out.println("Erro na alteração: "+ex.getMessage());
        }
    } 
}
