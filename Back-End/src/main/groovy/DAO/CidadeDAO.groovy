package DAO

import Classes.Cidade
import DB_PostgreSQL.ConexaoDB

import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class CidadeDAO {
    static List<Cidade> listarCidades(String cidade,int estado_id) throws SQLException {
        String sql = "SELECT city.nome,city.estado_id,city.codigo from Cidade as city left join estado as est on city.estado_id = est.codigo  where upper(city.nome) like upper('%"+cidade+"%') and est.codigo = "+estado_id.toString()
        List<Cidade> cidadesCadastradas = new ArrayList<>();

        try (Connection conectado = ConexaoDB.getConnection();
             Statement estado = conectado.createStatement();
             ResultSet resultadoQuery = estado.executeQuery(sql)) {
            while (resultadoQuery.next()) {
                cidadesCadastradas.add(new Cidade(
                        resultadoQuery.getString("nome"),
                        resultadoQuery.getInt("estado_id"),
                        resultadoQuery.getInt("codigo"),
                ));
            }
        }
        return cidadesCadastradas;
    }
}
