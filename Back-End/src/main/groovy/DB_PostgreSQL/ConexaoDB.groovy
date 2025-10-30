package DB_PostgreSQL

import Interfaces.DataBase

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConexaoDB {


     static void initDB(DataBase bancoDeDados)  throws SQLException{
        bancoDeDados.conectarBanco()
    }

    // Precisa reconnectar ao DB devido ao Try with resources, que fechar a conexao aṕos uma ação
     static Connection getConnection(DataBase bancoDeDados) throws SQLException {
         return bancoDeDados.getConnection()
    }
}
