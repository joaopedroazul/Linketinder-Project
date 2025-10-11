package DAO

import Classes.Candidato
import DB_PostgreSQL.ConexaoDB

import java.sql.*

class CandidatoDAO {

    static boolean createCandidato(Candidato c){
        String sql = """
            INSERT INTO CANDIDATO (NOME,SOBRENOME,DATA_NASCIMENTO,EMAIL,CPF,PAIS,CEP,DESCRICAO,SENHA) 
            VALUES (?,?,?,?,?,?,?,?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection();
        PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setString(1,c.getNome());
            preparando.setString(2, c.getSobrenome());
            preparando.setDate(3, c.getDataNascimento());
            preparando.setString(4,c.getEmail());
            preparando.setString(5,c.getCpf());
            preparando.setString(6,c.getPaís());
            preparando.setString(7,c.getCep());
            preparando.setString(8,c.getDescricao());
            preparando.setString(9,c.getSenha());

            int resultado = preparando.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            return resultado > 0;
        }catch (SQLException e){
            System.out.println(System.err);
            e.printStackTrace();
        }
        return false;
    }

    static List<Candidato> listarCandidato() throws SQLException {
        String sql = "SELECT * FROM CANDIDATO" ;
        List<Candidato> candidatos = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                candidatos.add(new Candidato(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getDate("data_nascimento"),
                    rs.getString("descricao"),
                    rs.getInt("codigo")

                ));

            }
        }
        return candidatos;
    }

    static Candidato listarCandidato(int index) throws SQLException {
        String sql = "SELECT * FROM Candidato where codigo = "+Integer.toString(index)+";";

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return new Candidato(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getDate("data_nascimento"),
                    rs.getString("descricao"),
                    rs.getInt("codigo")


                );
            }
        }
        return null;
    }
    static Candidato listarUltimoCandidato() throws SQLException {
        String sql = "SELECT * FROM Candidato order by codigo desc limit 1";

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return new Candidato(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getDate("data_nascimento"),
                        rs.getString("descricao"),
                        rs.getInt("codigo")


                );
            }
        }
        return null;
    }

    static Candidato Login(String email,String senha) throws SQLException {
        String sql = "SELECT * FROM Candidato where email = '"+email+"' and senha = '"+senha+"';";

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return new Candidato(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getDate("data_nascimento"),
                        rs.getString("descricao"),
                        rs.getInt("codigo")

                );
            }
        }
        return null;
    }

    static boolean updateCandidato(Candidato c,int index) throws SQLException {
        String sql = ""+
                "UPDATE Candidato "+
                "set nome = ?,"+
                "sobrenome = ? ,"+
                "data_nascimento = ? ,"+
                "email = ? ,"+
                "cpf = ?,"+
                "pais = ? ,"+
                "cep = ?,"+
                "descricao = ?,"+
                "senha = ? "+
                "where codigo = ?"+
                "";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement preparando = conn.prepareStatement(sql)) {

            preparando.setString(1,c.getNome());
            preparando.setString(2, c.getSobrenome());
            preparando.setDate(3, c.getDataNascimento());
            preparando.setString(4,c.getEmail());
            preparando.setString(5,c.getCpf());
            preparando.setString(6,c.getPaís());
            preparando.setString(7,c.getCep());
            preparando.setString(8,c.getDescricao());
            preparando.setString(9,c.getSenha());
            preparando.setInt(10, index);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("✅ Candidato id: "+ index+ " atualizado com sucesso!");
            } else {
                System.out.println("❌ Nenhuma candidato encontrado com ID " + index);
            }
            return result > 0;

        }
    }

    static boolean removerCandidato(int id) throws SQLException {

        String sql = "DELETE FROM Candidato WHERE codigo = ?; ";


        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement preparando = conn.prepareStatement(sql)) {
            preparando.setInt(1, id);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("Candidato removido com sucesso!");
                return true;
            }
        }
        return  false;

    }


}
