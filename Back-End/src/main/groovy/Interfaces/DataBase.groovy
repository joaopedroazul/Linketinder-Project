package Interfaces

import java.sql.Connection

interface DataBase {
    void conectarBanco();
    Connection getConnection();
}