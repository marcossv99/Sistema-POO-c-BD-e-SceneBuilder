package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Criadouro;

public class CriadouroDAO {
	private final Conexao minhaConexao;
    private final String INCLUIR = "INSERT INTO Criadouro ( cpfProprietario, endereco, senha, celular, numeroRegistro )"
    		+ " VALUES ( ?, ?, ?, ?, ?)";
    private final String ALTERAR = "UPDATE Criadouro SET cpfProprietario = ?, endereco = ?, celular = ?, numeroRegistro = ?"
    		+ " WHERE numeroRegistro = ?";
    private final String BUSCAR = "SELECT * FROM Criadouro WHERE numeroRegistro = ?";
    private final String DELETAR = "DELETE FROM Criadouro WHERE numeroRegistro = ?";
    private final String ALTERARSENHA = "UPDATE Criadouro SET senha = ? WHERE numeroRegistro = ?";
    
    public CriadouroDAO () {
		minhaConexao = new Conexao( "postgres","postgres", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}
    
    public void inclusao(Criadouro c){
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
            instrucao.setString(1, c.getCpfProprietario());
            instrucao.setString(2, c.getEndereco());
            instrucao.setString(3, c.getSenha());
            instrucao.setString(4, c.getCel());
            instrucao.setString(5, c.getNumeroRegistro());
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception e){
            System.out.println("Erro na inclusão: "+e.getMessage());
        }
    }
    
    public Criadouro buscar( String n ){
    	Criadouro c = null;
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, n);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                c = new Criadouro( rs.getString("cpfProprietario"), rs.getString("endereco"), rs.getString("senha"),
                		rs.getString("celular"), rs.getString("numeroRegistro"));
            }
            minhaConexao.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return c;
    }
     
    public void exclusao(String n){
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
    
    public void alteracao( Criadouro c, String r ){
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERAR);
            instrucao.setString(1, c.getCpfProprietario());
            instrucao.setString(2, c.getEndereco());
            instrucao.setString(3, c.getCel());
            instrucao.setString(4, c.getNumeroRegistro());
            instrucao.setString(5, r);
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception e){
            System.out.println("Erro na alteração: "+e.getMessage());
        }
    }  
    
    public void alterarSenha( String c, String s ){
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERARSENHA);
            instrucao.setString(1, s);
            instrucao.setString(2, c);
            instrucao.execute();
            minhaConexao.desconectar();
        }catch(Exception e){
            System.out.println("Erro na alteração: "+e.getMessage());
        }
    } 
    
    public int verificarRegistro( String n ){
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
    
    public String buscarCelular( String n ){
    	String c = null;
        try{
        	minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
            instrucao.setString(1, n);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                c = rs.getString("celular");	
            }
            minhaConexao.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return c;
    }
}
