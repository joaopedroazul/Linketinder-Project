package DAO

import Classes.Competencia
import Classes.Competencia_Vaga
import DB_PostgreSQL.ConexaoDB

import java.sql.*

class Competencia_VagaDAO {

    static boolean createVaga(Competencia_Vaga competenciaVaga){
        String sql = """
            INSERT INTO Competencia_Vaga (VAGA_ID, COMPETENCIA_ID) 
            VALUES (?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection();
            PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setInt(1,competenciaVaga.getVaga_id());
            preparando.setInt(2,competenciaVaga.getCompetencia_id());

            int resultado = preparando.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            return resultado > 0;
        }catch (SQLException exp){
            System.out.println(System.err);
            exp.printStackTrace();
        }
        return false;
    }

    static List<Competencia> listarCompetencia_Vaga() throws SQLException {
        String sql = "SELECT c.nome as competencia FROM Competencia_Vaga as cc left join Competencia as c  on cc.competencia_id = c.codigo left join Vaga as v on v.codigo = c.vaga_id" ;
        List<Competencia> competencias = new ArrayList<>();

        try (Connection conectado = ConexaoDB.getConnection();
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

    static boolean removerCompetencia_Vaga(int id_vaga, int id_competencia) throws SQLException {

        String sql = "DELETE FROM Competencia_Vaga WHERE competencia_id = ? and vaga_id = ?; ";


        try (Connection conectado = ConexaoDB.getConnection();
             PreparedStatement preparando = conectado.prepareStatement(sql)) {
            preparando.setInt(1, id_competencia);
            preparando.setInt(2, id_vaga);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("Competencia removido com sucesso!");
                return true;
            }
        }
        return  false;

    }
}
