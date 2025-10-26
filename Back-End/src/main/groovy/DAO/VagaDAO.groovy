package DAO

import Classes.Vaga
import DB_PostgreSQL.ConexaoDB

import java.sql.*

class VagaDAO {



    static boolean createVaga(Vaga vagaCriada){
        String sql = """
            INSERT INTO VAGA (NOME,EMPRESA_ID,ESTADO_ID,CIDADE_ID,DESCRICAO) 
            VALUES (?,?,?,?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection();
            PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setString(1,vagaCriada.getNome());
            preparando.setInt(2,vagaCriada.getEmpresa_id());
            preparando.setInt(3,vagaCriada.getEstado_id());
            preparando.setInt(4,vagaCriada.getCidade_id());
            preparando.setString(5,vagaCriada.getDescricao());

            int resultadoQuery = preparando.executeUpdate();
            return resultadoQuery > 0;
        }catch (SQLException exp){
            System.out.println(System.err);
            exp.printStackTrace();
        }
        return false;
    }

    static List<Vaga> listarVaga() throws SQLException {
        String sql = "SELECT * FROM Vaga" ;
        List<Vaga> vagasCriadas = new ArrayList<>();

        try (Connection conectado = ConexaoDB.getConnection();
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {
            while (resultadoQuery.next()) {
                vagasCriadas.add(new Vaga(
                        resultadoQuery.getString("nome"),
                        resultadoQuery.getInt("empresa_id"),
                        resultadoQuery.getInt("estado_id"),
                        resultadoQuery.getInt("cidade_id"),
                        resultadoQuery.getString("descricao"),
                ));
            }
        }
        return vagasCriadas;
    }

    static Vaga listarVaga(int id_vaga) throws SQLException {
        String sql = "SELECT * FROM Vaga where codigo = "+Integer.toString(id_vaga)+";";

        try (Connection conectado = ConexaoDB.getConnection();
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {

            while (resultadoQuerynext()) {
                return new Vaga(
                        resultadoQuery.getString("nome"),
                        resultadoQuery.getInt("empresa_id"),
                        resultadoQuery.getInt("estado_id"),
                        resultadoQuery.getInt("cidade_id"),
                        resultadoQuery.getString("descricao"),
                );
            }
        }
        return null;
    }

    static boolean updateVaga(Vaga vagaAtualizada,int id_vaga) throws SQLException {
        String sql = ""+
                "UPDATE Vaga "+
                "set nome = ?"+
                "empresa_id = ? ,"+
                "estado_id = ?,"+
                "cidade_id = ? ,"+
                "descricao = ? "+
                "where codigo  =?"+
                "";
        try (Connection conectado = ConexaoDB.getConnection();
             PreparedStatement preparando = conectado.prepareStatement(sql)) {

            preparando.setString(1,vagaAtualizada.getNome());
            preparando.setInt(2, id_vaga);
            int resultadoQuery = preparando.executeUpdate();
            if (resultadoQuery > 0) {
                System.out.println("✅ Vaga id: "+ id_vaga+ " atualizado com sucesso!");
            } else {
                System.out.println("❌ Nenhuma Vaga encontrado com ID " + id_vaga);
            }
            return resultadoQuery > 0;

        }
    }

    static boolean removerVaga(int id) throws SQLException {

        String sql = "DELETE FROM Vaga WHERE codigo = ?; ";


        try (Connection conectado = ConexaoDB.getConnection();
             PreparedStatement preparando = conectado.prepareStatement(sql)) {
            preparando.setInt(1, id);
            int resultadoQuery = preparando.executeUpdate();
            if (resultadoQuery > 0) {
                System.out.println("Vaga removido com sucesso!");
                return true;
            }
        }
        return  false;

    }
}
