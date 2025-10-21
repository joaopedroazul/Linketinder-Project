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
        List<Estado> estadosDisponiveis = new ArrayList<>();

        try (Connection conectado = ConexaoDB.getConnection();
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {
            while (resultadoQuery.next()) {
                estadosDisponiveis.add(new Estado(
                        resultadoQuery.getString("nome")
                ));
            }
        }
        return estadosDisponiveis;
    }
}
