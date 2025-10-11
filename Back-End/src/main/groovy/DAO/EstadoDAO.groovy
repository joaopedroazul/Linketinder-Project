package DAO

import Classes.Estado
import DB_PostgreSQL.ConexaoDB

import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class EstadoDAO {
    static List<Estado> listarEstados() throws SQLException {
        String sql = "SELECT nome from Estado"
        List<Estado> estados = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                estados.add(new Estado(
                        rs.getString("nome")
                ));
            }
        }
        return estados;
    }
}
