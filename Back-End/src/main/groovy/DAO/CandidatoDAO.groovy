package DAO

import Classes.Candidato
import DB_PostgreSQL.ConexaoDB
import DB_PostgreSQL.PostgreSQL

import java.sql.*

class CandidatoDAO {

    static boolean criarCandidato(Candidato consultaCandidato){
        String sql = """
            INSERT INTO CANDIDATO (NOME,SOBRENOME,DATA_NASCIMENTO,EMAIL,CPF,PAIS,CEP,DESCRICAO,SENHA) 
            VALUES (?,?,?,?,?,?,?,?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection(PostgreSQL.getDB());;
        PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setString(1,consultaCandidato.getNome());
            preparando.setString(2,consultaCandidato.getSobrenome());
            preparando.setDate(3, consultaCandidato.getDataNascimento());
            preparando.setString(4,consultaCandidato.getEmail());
            preparando.setString(5,consultaCandidato.getCpf());
            preparando.setString(6,consultaCandidato.getPaís());
            preparando.setString(7,consultaCandidato.getCep());
            preparando.setString(8,consultaCandidato.getDescricao());
            preparando.setString(9,consultaCandidato.getSenha());

            int resultado = preparando.executeUpdate();
            return resultado > 0;
        }catch (SQLException e){
            System.out.println(System.err);
            e.printStackTrace();
        }
        return false;
    }

    static List<Candidato> listarCandidato() throws SQLException {
        String sql = "SELECT * FROM CANDIDATO" ;
        List<Candidato> candidatosCriados = new ArrayList<>();

        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {
            while (resultadoQuery.next()) {
                candidatosCriados.add(new Candidato(
                    resultadoQuery.getString("nome"),
                    resultadoQuery.getString("sobrenome"),
                    resultadoQuery.getString("email"),
                    resultadoQuery.getString("cpf"),
                    resultadoQuery.getString("pais"),
                    resultadoQuery.getString("cep"),
                    resultadoQuery.getDate("data_nascimento"),
                    resultadoQuery.getString("descricao"),
                    resultadoQuery.getInt("codigo")

                ));

            }
        }
        return candidatosCriados;
    }

    static Candidato listarCandidato(int id_candidato) throws SQLException {
        String sql = "SELECT * FROM Candidato where codigo = "+Integer.toString(id_candidato)+";";

        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {

            while (resultadoQuery.next()) {
                return new Candidato(
                    resultadoQuery.getString("nome"),
                    resultadoQuery.getString("sobrenome"),
                    resultadoQuery.getString("email"),
                    resultadoQuery.getString("cpf"),
                    resultadoQuery.getString("pais"),
                    resultadoQuery.getString("cep"),
                    resultadoQuery.getDate("data_nascimento"),
                    resultadoQuery.getString("descricao"),
                    resultadoQuery.getInt("codigo")


                );
            }
        }
        return null;
    }
    
    static Candidato listarUltimoCandidato() throws SQLException {
        String sql = "SELECT * FROM Candidato order by codigo desc limit 1";

        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {

            while (resultadoQuery.next()) {
                return new Candidato(
                        resultadoQuery.getString("nome"),
                        resultadoQuery.getString("sobrenome"),
                        resultadoQuery.getString("email"),
                        resultadoQuery.getString("cpf"),
                        resultadoQuery.getString("pais"),
                        resultadoQuery.getString("cep"),
                        resultadoQuery.getDate("data_nascimento"),
                        resultadoQuery.getString("descricao"),
                        resultadoQuery.getInt("codigo")


                );
            }
        }
        return null;
    }

    static Candidato Login(String email,String senha) throws SQLException {
        String sql = "SELECT * FROM Candidato where email = '"+email+"' and senha = '"+senha+"';";

        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {

            while (resultadoQuery.next()) {
                return new Candidato(
                        resultadoQuery.getString("nome"),
                        resultadoQuery.getString("sobrenome"),
                        resultadoQuery.getString("email"),
                        resultadoQuery.getString("cpf"),
                        resultadoQuery.getString("pais"),
                        resultadoQuery.getString("cep"),
                        resultadoQuery.getDate("data_nascimento"),
                        resultadoQuery.getString("descricao"),
                        resultadoQuery.getInt("codigo")

                );
            }
        }
        return null;
    }

    static boolean atualizarCandidato(Candidato candidatoAtualizado,int id_candidato) throws SQLException {
        String sql = ""+
                "UPDATE Candidato "+
                "set nome = ?,"+
                "sobrenome = ? ,"+
                "data_nascimento = ? ,"+
                "email = ? ,"+
                "cpf = ?,"+
                "pais = ? ,"+
                "cep = ?,"+
                "descricao = ?"+
                "where codigo = ?"+
                "";
        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             PreparedStatement preparando = conectado.prepareStatement(sql)) {

            preparando.setString(1, candidatoAtualizado.getNome());
            preparando.setString(2, candidatoAtualizado.getSobrenome());
            preparando.setDate(3, candidatoAtualizado.getDataNascimento());
            preparando.setString(4, candidatoAtualizado.getEmail());
            preparando.setString(5, candidatoAtualizado.getCpf());
            preparando.setString(6, candidatoAtualizado.getPaís());
            preparando.setString(7, candidatoAtualizado.getCep());
            preparando.setString(8, candidatoAtualizado.getDescricao());
            preparando.setInt(9, id_candidato);
            int resultadoQuery= preparando.executeUpdate();
            if (resultadoQuery> 0) {
                System.out.println("✅ Candidato id: "+ id_candidato+ " atualizado com sucesso!");
            } else {
                System.out.println("❌ Nenhuma candidato encontrado com ID " + id_candidato);
            }
            return resultadoQuery> 0;

        }
    }

    static boolean removerCandidato(int id_candidato) throws SQLException {

        String sql = "DELETE FROM Candidato WHERE codigo = ?; ";


        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             PreparedStatement preparando = conectado.prepareStatement(sql)) {
            preparando.setInt(1, id_candidato);
            int resultadoQuery= preparando.executeUpdate();
            if (resultadoQuery> 0) {
                System.out.println("Candidato removido com sucesso!");
                return true;
            }
        }
        return  false;

    }

    static String listarSenhaCandidato(int id_candidato) throws SQLException {
        String sql = "SELECT senha FROM Candidato where codigo = "+Integer.toString(id_candidato)+"";

        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {

            while (resultadoQuery.next()) {
                return resultadoQuery.getString("senha")

            }
        }
        return "";
    }


}