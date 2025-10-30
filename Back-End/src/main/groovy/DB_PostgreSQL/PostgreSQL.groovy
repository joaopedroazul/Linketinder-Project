package DB_PostgreSQL

import Interfaces.DataBase

import java.sql.Connection
import java.sql.DriverManager

class PostgreSQL implements DataBase{
    private static Connection conectado;
    private static  PostgreSQL conexao;

    private PostgreSQL(){
        println("utilizando o PostgreSQL como Banco de dados")
    }

    static PostgreSQL getDB(){
        if(!conexao){
           conexao =  new PostgreSQL()
        }
        conexao.conectarBanco()
        return conexao
    }

    @Override
    void conectarBanco() {
        String url =  "jdbc:postgresql://localhost:5432/Linketinder?user=postgres&password=postgres";
        conectado =  DriverManager.getConnection(url);
    }


    @Override
    Connection getConnection() {
        if (conectado == null || conectado.isClosed()) {
            conectado = conectarBanco()
        }
        return conectado;
    }
}


