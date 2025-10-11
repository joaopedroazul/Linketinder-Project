package DB_PostgreSQL


import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConexaoDB {

    private static Connection conectado;



     static void initDB() throws SQLException {
        String url =  "jdbc:postgresql://localhost:5432/Linketinder?user=postgres&password=postgres";
        conectado = DriverManager.getConnection(url);
    }

    // Precisa reconnectar ao DB devido ao Try with resources, que fechar a conexao aṕos uma ação
     static Connection getConnection() throws SQLException {
        if (conectado == null || conectado.isClosed()) {
            initDB();
        }
        return conectado;
    }
}
