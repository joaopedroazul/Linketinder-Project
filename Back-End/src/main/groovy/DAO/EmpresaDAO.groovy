package DAO

import Classes.Empresa
import DB_PostgreSQL.ConexaoDB

import java.sql.*

class EmpresaDAO {

    static Empresa Login(String email,String senha) throws SQLException {
        String sql = "SELECT * FROM Empresa where email = '"+email+"' and senha = '"+senha+"';";

        try (Connection conectado = ConexaoDB.getConnection();
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {

            while (resultadoQuery.next()) {
                return new Empresa(
                        resultadoQuery.getString("nome"),
                        resultadoQuery.getString("email"),
                        resultadoQuery.getString("cnpj"),
                        resultadoQuery.getString("pais"),
                        resultadoQuery.getString("cep"),
                        resultadoQuery.getString("descricao"),
                        resultadoQuery.getInt("codigo"),

                );
            }
        }
        return null;
    }

    static boolean createEmpresa(Empresa empresaCriada){
        String sql = """
            INSERT INTO Empresa (NOME,EMAIL,CNPJ,PAIS,CEP,DESCRICAO,SENHA) 
            VALUES (?,?,?,?,?,?,?)
            """;

        try(Connection  conectado = ConexaoDB.getConnection();
            PreparedStatement preparando = conectado.prepareStatement(sql)){
            preparando.setString(1,empresaCriada.getNome());
            preparando.setString(2,empresaCriada.getEmail());
            preparando.setString(3,empresaCriada.getCnpj());
            preparando.setString(4,empresaCriada.getPais());
            preparando.setString(5,empresaCriada.getCep());
            preparando.setString(6,empresaCriada.getDescricao());
            preparando.setString(7,empresaCriada.getSenha());

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
        List<Empresa> empresasCriadas = new ArrayList<>();

        try (Connection conectado = ConexaoDB.getConnection();
             Statement s = conectado.createStatement();
             ResultSet resultadoQuery = s.executeQuery(sql)) {
            while (resultadoQuery.next()) {
                empresasCriadas.add(new Empresa(
                    resultadoQuery.getString("nome"),
                    resultadoQuery.getString("email"),
                    resultadoQuery.getString("cnpj"),
                    resultadoQuery.getString("pais"),
                    resultadoQuery.getString("cep"),
                    resultadoQuery.getString("descricao"),
                    resultadoQuery.getString("senha")
                ));

            }
        }
        return empresasCriadas;
    }

    static Empresa listarEmpresa(int id_empresa) throws SQLException {
        String sql = "SELECT * FROM Empresa where codigo = "+Integer.toString(id_empresa)+";";

        try (Connection conectado = ConexaoDB.getConnection();
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {

            while (resultadoQuery.next()) {
                return new Empresa(
                    resultadoQuery.getString("nome"),
                    resultadoQuery.getString("email"),
                    resultadoQuery.getString("cnpj"),
                    resultadoQuery.getString("pais"),
                    resultadoQuery.getString("cep"),
                    resultadoQuery.getString("descricao"),
                    resultadoQuery.getString("senha")
                );
            }
        }
        return null;
    }

    static boolean updateEmpresa(Empresa empresaAtualizada,int id_empresa) throws SQLException {
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
        try (Connection conectado = ConexaoDB.getConnection();
             PreparedStatement preparando = conectado.prepareStatement(sql)) {

            preparando.setString(1,empresaAtualizada.getNome());
            preparando.setString(2,empresaAtualizada.getEmail());
            preparando.setString(3,empresaAtualizada.getCnpj());
            preparando.setString(4,empresaAtualizada.getPais());
            preparando.setString(5,empresaAtualizada.getCep());
            preparando.setString(6,empresaAtualizada.getDescricao());
            preparando.setString(7,empresaAtualizada.getSenha());
            preparando.setInt(8, id_empresa);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("✅ Candidato id: "+ id_empresa+ " atualizado com sucesso!");
            } else {
                System.out.println("❌ Nenhuma candidato encontrado com ID " + id_empresa);
            }
            return result > 0;

        }
    }

    static boolean removerEmpresa(int id_empresa) throws SQLException {

        String sql = "DELETE FROM Empresa WHERE codigo = ?; ";


        try (Connection conectado = ConexaoDB.getConnection();
             PreparedStatement preparando = conectado.prepareStatement(sql)) {
            preparando.setInt(1, id_empresa);
            int result = preparando.executeUpdate();
            if (result > 0) {
                System.out.println("Empresa removido com sucesso!");
                return true;
            }
        }
        return  false;

    }
}
