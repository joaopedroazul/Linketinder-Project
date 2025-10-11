package DAO

import Classes.Competencia
import DB_PostgreSQL.ConexaoDB

import java.sql.*

class CompetenciaDAO {


    static boolean createCompetencia(Competencia e){
        String sql = """
            INSERT INTO Competencia (NOME) 
            VALUES (?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection();
            PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setString(1,e.getNome());

            int resultado = preparando.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            return resultado > 0;
        }catch (SQLException exp){
            System.out.println(System.err);
            exp.printStackTrace();
        }
        return false;
    }

    static List<Competencia> listarCompetencia() throws SQLException {
        String sql = "SELECT * FROM Competencia" ;
        List<Competencia> competencias = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                competencias.add(new Competencia(
                        rs.getString("nome"),
                        rs.getInt("codigo")

                ));

            }
        }
        return competencias;
    }

//    static Competencia listarCompetencia(int index) throws SQLException {
//        String sql = "SELECT * FROM Competencia where codigo = "+Integer.toString(index)+";";
//
//        try (Connection conn = ConexaoDB.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                return new Competencia(
//                        rs.getString("nome")
//                );
//            }
//        }
//        return null;
//    }

//    static boolean updateCompetencia(Competencia c,int index) throws SQLException {
//        String sql = ""+
//                "UPDATE Competencia "+
//                "set nome = ?"+
//                "where codigo = ?"+
//                "";
//        try (Connection conn = ConexaoDB.getConnection();
//             PreparedStatement preparando = conn.prepareStatement(sql)) {
//
//            preparando.setString(1,c.getNome());
//            preparando.setInt(2, index);
//            int result = preparando.executeUpdate();
//            if (result > 0) {
//                System.out.println("✅ Competencia id: "+ index+ " atualizado com sucesso!");
//            } else {
//                System.out.println("❌ Nenhuma Competencia encontrado com ID " + index);
//            }
//            return result > 0;
//
//        }
//    }

    static boolean removerCompetencia(int id) throws SQLException {

        String sql = "DELETE FROM Competencia WHERE codigo = ?; ";


        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement preparando = conn.prepareStatement(sql)) {
            preparando.setInt(1, id);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("Competencia removido com sucesso!");
                return true;
            }
        }
        return  false;

    }
}
