package com.linketinder.groovy

import Classes.Candidato
import Classes.Competencia
import Classes.Empresa
import Classes.Pessoa
import Classes.Vaga
import DAO.CandidatoDAO
import DAO.Competencia_CandidatoDAO
import DAO.EmpresaDAO
import DAO.VagaDAO
import DB_PostgreSQL.ConexaoDB
import DB_PostgreSQL.FactoryDB
import DB_PostgreSQL.PostgreSQL
import DB_PostgreSQL.ProductPostgreSQL
import Interfaces.DataBase
import Views.CandidatoView
import Views.MenuView


static void main(String[] args) {

    FactoryDB fabrica = new ProductPostgreSQL()
    DataBase servidorDeBancoDeDados = fabrica.iniciandoProducao()
    ConexaoDB.initDB(servidorDeBancoDeDados)
    MenuView.mostrarMenuInicial()

}