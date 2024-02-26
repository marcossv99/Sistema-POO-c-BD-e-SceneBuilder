package persistencia;

import dominio.EspecieCriadouro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspecieCriadouroDAO {

  private final Conexao minhaConexao;
  private final String RELATORIO =
    "SELECT * FROM EspecieCriadouro WHERE criadouro = ?";
  private final String INCLUIR =
    "INSERT INTO EspecieCriadouro ( especie, criadouro, possui ) VALUES ( ?, ?, ?)";
  private final String ALTERAR =
    "UPDATE EspecieCriadouro SET especie = ?, criadouro = ?, possui = ? WHERE especie = ?" +
    "AND criadouro = ?";
  private final String BUSCAR =
    "SELECT * FROM EspecieCriadouro WHERE especie = ? AND criadouro = ?";
  private final String DELETAR =
    "DELETE FROM EspecieCriadouro WHERE especie = ? AND criadouro = ?";
  private final String BUSCARSTRING =
    "SELECT * FROM EspecieCriadouro WHERE criadouro = ? AND especie LIKE %?%";

  public EspecieCriadouroDAO() {
    minhaConexao =
      new Conexao(
        "postgres",
        "postgres",
        "jdbc:postgresql://localhost:5432/ProjAquicultura"
      );
  }

  public ArrayList<EspecieCriadouro> relatorio(String c) {
    ArrayList<EspecieCriadouro> lista = new ArrayList<>();
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(BUSCAR);
      instrucao.setString(1, c);
      ResultSet rs = instrucao.executeQuery(RELATORIO);
      while (rs.next()) {
        EspecieCriadouro ep = new EspecieCriadouro(
          rs.getString("especie"),
          rs.getString("criadouro"),
          rs.getBoolean("possui")
        );
        lista.add(ep);
      }
      minhaConexao.desconectar();
    } catch (SQLException e) {
      System.out.println("Erro no relatório: " + e.getMessage());
    }
    return lista;
  }

  public EspecieCriadouro buscar(String c, String e) {
    EspecieCriadouro ep = null;
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(BUSCAR);
      instrucao.setString(1, e);
      instrucao.setString(2, c);
      ResultSet rs = instrucao.executeQuery();
      if (rs.next()) {
        ep =
          new EspecieCriadouro(
            rs.getString("especie"),
            rs.getString("criadouro"),
            rs.getBoolean("possui")
          );
      }
      minhaConexao.desconectar();
    } catch (SQLException ex) {
      System.out.println("Erro na busca: " + ex.getMessage());
    }
    return ep;
  }

  public ArrayList<EspecieCriadouro> buscarAmplo(String e, String c) {
    ArrayList<EspecieCriadouro> lista = new ArrayList<>();
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(BUSCARSTRING);
      instrucao.setString(1, c);
      instrucao.setString(2, e);
      ResultSet rs = instrucao.executeQuery();
      while (rs.next()) {
        EspecieCriadouro ep = new EspecieCriadouro(
          rs.getString("especie"),
          rs.getString("criadouro"),
          rs.getBoolean("possui")
        );
        lista.add(ep);
      }
      minhaConexao.desconectar();
    } catch (SQLException ex) {
      System.out.println("Erro na busca: " + ex.getMessage());
    }
    return lista;
  }

  public void inclusao(EspecieCriadouro ec) {
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(INCLUIR);
      instrucao.setString(1, ec.getEspecie());
      instrucao.setString(2, ec.getCriadouro());
      instrucao.setBoolean(3, ec.isPossui());
      instrucao.execute();
      minhaConexao.desconectar();
    } catch (Exception e) {
      System.out.println("Erro na inclusão: " + e.getMessage());
    }
  }

  public void exclusao(String e, String c) {
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(DELETAR);
      instrucao.setString(1, e);
      instrucao.setString(2, c);
      instrucao.execute();
      minhaConexao.desconectar();
    } catch (Exception ex) {
      System.out.println("Erro na exclusão: " + ex.getMessage());
    }
  }

  public void alteracao(EspecieCriadouro ec, String e, String c) {
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(ALTERAR);
      instrucao.setString(1, ec.getEspecie());
      instrucao.setString(2, ec.getCriadouro());
      instrucao.setBoolean(3, ec.isPossui());
      instrucao.setString(4, e);
      instrucao.setString(5, c);
      instrucao.execute();
      minhaConexao.desconectar();
    } catch (Exception ex) {
      System.out.println("Erro na alteração: " + ex.getMessage());
    }
  }
}
