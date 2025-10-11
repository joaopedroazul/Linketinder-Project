package DAO

import Classes.Empresa
import DB_PostgreSQL.ConexaoDB

import java.sql.*

class EmpresaDAO {

    static Empresa Login(String email,String senha) throws SQLException {
        String sql = "SELECT * FROM Empresa where email = '"+email+"' and senha = '"+senha+"';";

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return new Empresa(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        rs.getInt("codigo"),

                );
            }
        }
        return null;
    }

    static boolean createEmpresa(Empresa e){
        String sql = """
            INSERT INTO Empresa (NOME,EMAIL,CNPJ,PAIS,CEP,DESCRICAO,SENHA) 
            VALUES (?,?,?,?,?,?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection();
            PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setString(1,e.getNome());
            preparando.setString(2,e.getEmail());
            preparando.setString(3,e.getCnpj());
            preparando.setString(4,e.getPais());
            preparando.setString(5,e.getCep());
            preparando.setString(6,e.getDescricao());
            preparando.setString(7,e.getSenha());

            int resultado = preparando.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            return resultado > 0;
        }catch (SQLException exp){
            System.out.println(System.err);
            exp.printStackTrace();
        }
        return false;
    }

    static List<Empresa> listarEmpresa() throws SQLException {
        String sql = "SELECT * FROM Empresa" ;
        List<Empresa> empresas = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                empresas.add(new Empresa(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cnpj"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getString("descricao"),
                    rs.getString("senha")
                ));

            }
        }
        return empresas;
    }

    static Empresa listarEmpresa(int index) throws SQLException {
        String sql = "SELECT * FROM Empresa where codigo = "+Integer.toString(index)+";";

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return new Empresa(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cnpj"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getString("descricao"),
                    rs.getString("senha")
                );
            }
        }
        return null;
    }

    static boolean updateEmpresa(Empresa e,int index) throws SQLException {
        String sql = ""+
                "UPDATE Empresa "+
                "set nome = ?,"+
                "email = ? ,"+
                "cnpj = ?,"+
                "pais = ? ,"+
                "cep = ?,"+
                "descricao = ?,"+
                "senha = ? "+
                "where codigo = ?"+
                "";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement preparando = conn.prepareStatement(sql)) {

            preparando.setString(1,e.getNome());
            preparando.setString(2,e.getEmail());
            preparando.setString(3,e.getCnpj());
            preparando.setString(4,e.getPais());
            preparando.setString(5,e.getCep());
            preparando.setString(6,e.getDescricao());
            preparando.setString(7,e.getSenha());
            preparando.setInt(8, index);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("✅ Candidato id: "+ index+ " atualizado com sucesso!");
            } else {
                System.out.println("❌ Nenhuma candidato encontrado com ID " + index);
            }
            return result > 0;

        }
    }

    static boolean removerEmpresa(int id) throws SQLException {

        String sql = "DELETE FROM Empresa WHERE codigo = ?; ";


        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement preparando = conn.prepareStatement(sql)) {
            preparando.setInt(1, id);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("Empresa removido com sucesso!");
                return true;
            }
        }
        return  false;

    }
}
