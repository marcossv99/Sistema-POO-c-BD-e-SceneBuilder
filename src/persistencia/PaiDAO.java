/*package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class PaiDAO {
	private final Conexao minhaConexao;
	private final String RELATORIO = "SELECT * FROM ?";
    private final String BUSCARINT = "SELECT * FROM ? WHERE ? = ?";
    private final String BUSCARSTRING = "SELECT * FROM ? WHERE ? = '?'";
	private final String DELETARINT = "DELETE FROM ? WHERE ? = ?";
	private final String DELETARSTRING = "DELETE FROM ? WHERE ? = '?'";
	private final String getId = "SELECT * FROM ? ORDER BY ? DESC";
	
	public PaiDAO () {
		minhaConexao = new Conexao( "postgres","123", "jdbc:postgresql://localhost:5432/ProjAquicultura");
	}
	
	public Conexao getMinhaConexao() {
		return minhaConexao;
	}

	public String getRELATORIO() {
		return RELATORIO;
	}

	public String getDELETARSTRING() {
		return DELETARSTRING;
	}
	
	public String getDELETARINT() {
		return DELETARINT;
	}

	public String getGetid() {
		return getId;
	}
	
	public String getBUSCARINT() {
		return BUSCARINT;
	} 
	
	public String getBUSCARSTRING() {
		return BUSCARSTRING;
	} 
	
	public int getUltimoId( String nomeTabela, String nomePk){
    	int ultimoID = 0;
        try{
            minhaConexao.conectar();
            PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(getId);
            instrucao.setString(1, nomeTabela);
            instrucao.setString(2, nomePk);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                ultimoID = rs.getInt(nomePk);
            }
            minhaConexao.desconectar();
            
        }catch(Exception e){
            System.out.println("Erro na identificação do ultimo ID: "+e.getMessage());
        }
        return ultimoID;  
    }

	
}*/
