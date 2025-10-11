package DAO

import Classes.Cidade
import DB_PostgreSQL.ConexaoDB

import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class CidadeDAO {
    static List<Cidade> listarCidades(String city,int estado_id) throws SQLException {
        String sql = "SELECT city.nome,city.estado_id,city.codigo from Cidade as city left join estado as est on city.estado_id = est.codigo  where upper(city.nome) like upper('%"+city+"%') and est.codigo = "+estado_id.toString()
        List<Cidade> cidades = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConnection();
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                cidades.add(new Cidade(
                        rs.getString("nome"),
                        rs.getInt("estado_id"),
                        rs.getInt("codigo"),
                ));
            }
        }
        return cidades;
    }
}
