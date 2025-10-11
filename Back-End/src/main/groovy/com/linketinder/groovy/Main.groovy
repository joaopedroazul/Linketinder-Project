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



static void main(String[] args) {

    ConexaoDB.initDB();

    println("seja bem vindo ao linketinder\nSistema de match entre voce e a sua vaga dos sonhos\n\n")
    Integer opcao = 9
    while (opcao != 0){
        Scanner scanner = new Scanner(System.in)
        println("Selecione uma opção")
        println("\n\n")
        println("1- Criar sua conta")
        println("2- fazer Login")
        opcao = scanner.nextLine().toInteger()
        if (opcao == 0){
            break;
        }else if  (opcao != 1 && opcao != 2){
            continue
        }else if (opcao == 1){
            println("Selecione uma opção")
            println("\n\n")
            println("1- Criar sua conta de Candidato")
            println("2- Criar sua conta de Empresa")
            opcao = scanner.nextLine().toInteger()
            if (opcao == 1){
                Candidato c = new Candidato()
                c = Candidato.viewCreateCanditado();
                List<Competencia> competencias = Pessoa.viewCreateCompetencias();
                CandidatoDAO.createCandidato(c);
                c = CandidatoDAO.listarUltimoCandidato()
                competencias.each {Competencia comp -> Competencia_CandidatoDAO.createCompetencia_Candidato(comp.id,c.id)}

            }else if (opcao == 2){
                Empresa e = new Empresa();
                Empresa.viewCreateEmpresa(e);
                EmpresaDAO.createEmpresa(e);

            }else{
                continue;
            }


        }else if (opcao == 2){
            println("Selecione uma opção")
            println("\n\n")
            println("1- Fazer login com sua conta de Candidato")
            println("2- Fazer login com sua conta de Empresa")
            opcao = scanner.nextLine().toInteger()
            if (opcao == 1){
                String email, senha
                println("Digite seu email: ")
                email = scanner.nextLine()
                println("Digite seu senha: ")
                senha = scanner.nextLine()
                if(Candidato.Login(email,senha)){
                    println("Veja a lista de empresa buscando novos trabalhadores")
                    VagaDAO.listarVaga().eachWithIndex {Vaga v, int index ->
                        println("__________________________________________")
                        println("\n Vaga "+(index+1).toString())
                        println(v.nome)
                        println(v.descricao)
                        println("\n\n")
                    }

                }else{
                    println("Candidato não cadastrado, crie sua conta")
                    continue
                }
            }else{
                String email, cnpj
                println("Digite seu email: ")
                email = scanner.nextLine()
                println("Digite sua senha: ")
                cnpj = scanner.nextLine()
                Empresa logada = EmpresaDAO.Login(email,cnpj)
                if(logada.id > 0){
                    println("Selecione uma opção")
                    println("\n\n")
                    println("1- Visualizar Candidato")
                    println("2- Criar uma nova Vaga")
                    opcao = scanner.nextLine().toInteger()
                    if (opcao == 1){
                        CandidatoDAO.listarCandidato().each {Candidato c ->
                            println("________________________________________")
                            println(" Candidato "+(c.id.toString()))
                            println(c.descricao)
                            println("")
                        }
                    }
                    else if( opcao == 2){
                        Vaga novaVaga = new Vaga()
                        novaVaga = Vaga.viewCreateVaga(2)
                        VagaDAO.createVaga(novaVaga)
                    }

                }else{
                    println("Empresa não cadastrada, crie sua conta")
                    continue
                }
            }
        }

    }


}