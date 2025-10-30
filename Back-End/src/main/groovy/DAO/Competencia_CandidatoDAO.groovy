package DAO

import Classes.Competencia
import DB_PostgreSQL.ConexaoDB
import DB_PostgreSQL.PostgreSQL

import java.sql.*

class Competencia_CandidatoDAO {

    static boolean createCompetencia_Candidato(int id_competencia , int id_candidato ){
        String sql = """
            INSERT INTO Competencia_Candidato (CANDIDATO_ID, COMPETENCIA_ID) 
            VALUES (?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection(PostgreSQL.getDB()); ;
            PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setInt(1,id_candidato);
            preparando.setInt(2,id_competencia);

            int resultado = preparando.executeUpdate();
            return resultado > 0;
        }catch (SQLException exp){
            System.out.println(System.err);
            exp.printStackTrace();
        }
        return false;
    }

    static List<Competencia> listarCompetencia_Candidato() throws SQLException {
        String sql = "SELECT c.nome as competencia FROM Competencia_Candidato as cc left join Competencia as c  on cc.competencia_id = c.codigo left join Candidato as can on can.codigo = c.candidato_id" ;
        List<Competencia> competencias = new ArrayList<>();

        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {
            while (resultadoQuery.next()) {
                competencias.add(new Competencia(
                        resultadoQuery.getString("competencia"),
                ));
            }
        }
        return competencias;
    }

    static boolean removerCompetencia_Candidato(int id_candidato, int id_competencia) throws SQLException {

        String sql = "DELETE FROM Competencia_Candidato WHERE competencia_id = ? and candidato_id = ?; ";


        try (Connection conectado= ConexaoDB.getConnection(PostgreSQL.getDB());;
             PreparedStatement preparando = conectado.prepareStatement(sql)) {
            preparando.setInt(1, id_competencia);
            preparando.setInt(2, id_candidato);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("Competencia removido com sucesso!");
                return true;
            }
        }
        return  false;

    }
}
