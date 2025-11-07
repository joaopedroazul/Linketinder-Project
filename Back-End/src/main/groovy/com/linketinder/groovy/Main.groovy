package com.linketinder.groovy


import DbPostgreSQL.ConexaoDB
import DbPostgreSQL.FactoryDB
import DbPostgreSQL.ProductPostgreSQL
import Interfaces.DataBase
import Views.MenuView


static void main(String[] args) {

    FactoryDB fabrica = new ProductPostgreSQL()
    DataBase servidorDeBancoDeDados = fabrica.iniciandoProducao()
    ConexaoDB.initDB(servidorDeBancoDeDados)
    MenuView.mostrarMenuInicial()

}