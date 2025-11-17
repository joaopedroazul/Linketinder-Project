package com.linketinder.groovy

import Api.HttpServer
import DbPostgreSQL.ConexaoDB
import DbPostgreSQL.FactoryDB
import DbPostgreSQL.ProductPostgreSQL
import Interfaces.DataBase
import Views.MenuView


static void main(String[] args) {
    try {
    FactoryDB fabrica = new ProductPostgreSQL()
    DataBase servidorDeBancoDeDados = fabrica.iniciandoProducao()
    ConexaoDB.initDB(servidorDeBancoDeDados)
    //MenuView.mostrarMenuInicial()

    // Inicia o servidor HTTP
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "5000"));
        HttpServer server = new HttpServer(port);
    server.start();
    } catch (Exception e) {
        e.printStackTrace();
    }

}