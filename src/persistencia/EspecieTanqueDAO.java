package persistencia;

import dominio.EspecieTanque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspecieTanqueDAO {

  private final Conexao minhaConexao;
  private final String RELATORIO =
    "SELECT * FROM EspecieTanque WHERE registroCriadouro = ?";
  private final String INCLUIR =
    "INSERT INTO EspecieTanque ( nomeEspecie, IdTanque, registroCriadouro )" +
    " VALUES ( ?, ?, ? )";
  private final String ALTERAR =
    "UPDATE EspecieTanque SET nomeEspecie = ?, IdTanque = ? " +
    "WHERE nomeEspecie = ? AND IdTanque = ?";
  private final String BUSCAR =
    "SELECT * FROM EspecieTanque WHERE" +
    " nomeEspecie = ? AND IdTanque = ? AND registroCriadouro = ?";
  private final String BUSCARAMPLO =
    "SELECT * FROM EspecieRemedio WHERE ( nomeEspecie LIKE %?% OR idTanque LIKE %?% )" +
    " AND registroCriadouro = ?";
  private final String DELETAR =
    "DELETE FROM EspecieTanque WHERE nomeEspecie = ? AND IdTanque = ?";

  public EspecieTanqueDAO() {
    minhaConexao =
      new Conexao(
        "postgres",
        "postgres",
        "jdbc:postgresql://localhost:5432/ProjAquicultura"
      );
  }

  public ArrayList<EspecieTanque> relatorio(String r) {
    ArrayList<EspecieTanque> lista = new ArrayList<>();
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(RELATORIO);
      instrucao.setString(1, r);
      ResultSet rs = instrucao.executeQuery();
      while (rs.next()) {
        EspecieTanque et = new EspecieTanque(
          rs.getString("nomeEspecie"),
          rs.getInt("IdTanque")
        );
        lista.add(et);
      }
      minhaConexao.desconectar();
    } catch (SQLException e) {
      System.out.println("Erro no relatório: " + e.getMessage());
    }
    return lista;
  }

  public EspecieTanque buscarEspecifico(String e, int id) {
    EspecieTanque et = null;
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(BUSCAR);
      instrucao.setString(1, e);
      instrucao.setInt(2, id);
      ResultSet rs = instrucao.executeQuery();
      if (rs.next()) {
        et =
          new EspecieTanque(rs.getString("nomeEspecie"), rs.getInt("IdTanque"));
      }
      minhaConexao.desconectar();
    } catch (SQLException ex) {
      System.out.println("Erro na busca: " + ex.getMessage());
    }
    return et;
  }

  public ArrayList<EspecieTanque> buscarAmplo(String op, String rc) {
    ArrayList<EspecieTanque> lista = new ArrayList<>();
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(BUSCARAMPLO);
      instrucao.setString(1, op);
      instrucao.setString(2, op);
      instrucao.setString(3, rc);
      ResultSet rs = instrucao.executeQuery();
      while (rs.next()) {
        EspecieTanque et = new EspecieTanque(
          rs.getString("nomeEspecie"),
          rs.getInt("IdTanque")
        );
        lista.add(et);
      }
      minhaConexao.desconectar();
    } catch (SQLException ex) {
      System.out.println("Erro na busca: " + ex.getMessage());
    }
    return lista;
  }

  public void inclusao(EspecieTanque er) {
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(INCLUIR);
      instrucao.setString(1, er.getNomeEspecie());
      instrucao.setInt(2, er.getIdTanque());
      instrucao.execute();
      minhaConexao.desconectar();
    } catch (Exception e) {
      System.out.println("Erro na inclusão: " + e.getMessage());
    }
  }

  public void exclusao(String e, int t) {
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(DELETAR);
      instrucao.setString(1, e);
      instrucao.setInt(2, t);
      instrucao.execute();
      minhaConexao.desconectar();
    } catch (Exception ex) {
      System.out.println("Erro na exclusão: " + ex.getMessage());
    }
  }

  public void alteracao(EspecieTanque er, String e, int id) {
    try {
      minhaConexao.conectar();
      PreparedStatement instrucao = minhaConexao
        .getConexao()
        .prepareStatement(ALTERAR);
      instrucao.setString(1, er.getNomeEspecie());
      instrucao.setInt(2, er.getIdTanque());
      instrucao.setString(3, e);
      instrucao.setInt(4, id);
      instrucao.execute();
      minhaConexao.desconectar();
    } catch (Exception ex) {
      System.out.println("Erro na alteração: " + ex.getMessage());
    }
  }
}
