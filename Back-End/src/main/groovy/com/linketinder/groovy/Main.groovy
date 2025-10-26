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
import Views.CandidatoView
import Views.MenuView


static void main(String[] args) {

    ConexaoDB.initDB();
    MenuView.mostrarMenuInicial()

}