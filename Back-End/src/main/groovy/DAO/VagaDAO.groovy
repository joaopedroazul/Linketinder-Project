package DAO

import Classes.Vaga
import DB_PostgreSQL.ConexaoDB

import java.sql.*

class VagaDAO {



    static boolean createVaga(Vaga e){
        String sql = """
            INSERT INTO VAGA (NOME,EMPRESA_ID,ESTADO_ID,CIDADE_ID,DESCRICAO) 
            VALUES (?,?,?,?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection();
            PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setString(1,e.getNome());
            preparando.setInt(2,e.getEmpresa_id());
            preparando.setInt(3,e.getEstado_id());
            preparando.setInt(4,e.getCidade_id());
            preparando.setString(5,e.getDescricao());

            int resultado = preparando.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            return resultado > 0;
        }catch (SQLException exp){
            System.out.println(System.err);
            exp.printStackTrace();
        }
        return false;
    }

    static List<Vaga> listarVaga() throws SQLException {
        String sql = "SELECT * FROM Vaga" ;
        List<Vaga> vagas = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                vagas.add(new Vaga(
                        rs.getString("nome"),
                        rs.getInt("empresa_id"),
                        rs.getInt("estado_id"),
                        rs.getInt("cidade_id"),
                        rs.getString("descricao"),
                ));
            }
        }
        return vagas;
    }

    static Vaga listarVaga(int index) throws SQLException {
        String sql = "SELECT * FROM Vaga where codigo = "+Integer.toString(index)+";";

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return new Vaga(
                        rs.getString("nome"),
                        rs.getInt("empresa_id"),
                        rs.getInt("estado_id"),
                        rs.getInt("cidade_id"),
                        rs.getString("descricao"),
                );
            }
        }
        return null;
    }

    static boolean updateVaga(Vaga c,int index) throws SQLException {
        String sql = ""+
                "UPDATE Vaga "+
                "set nome = ?"+
                "empresa_id = ? ,"+
                "estado_id = ?,"+
                "cidade_id = ? ,"+
                "descricao = ? "+
                "where codigo  =?"+
                "";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement preparando = conn.prepareStatement(sql)) {

            preparando.setString(1,c.getNome());
            preparando.setInt(2, index);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("✅ Vaga id: "+ index+ " atualizado com sucesso!");
            } else {
                System.out.println("❌ Nenhuma Vaga encontrado com ID " + index);
            }
            return result > 0;

        }
    }

    static boolean removerVaga(int id) throws SQLException {

        String sql = "DELETE FROM Vaga WHERE codigo = ?; ";


        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement preparando = conn.prepareStatement(sql)) {
            preparando.setInt(1, id);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("Vaga removido com sucesso!");
                return true;
            }
        }
        return  false;

    }
}
