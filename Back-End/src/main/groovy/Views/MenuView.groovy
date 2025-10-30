package Views

import Classes.Candidato
import Classes.Competencia
import Classes.Empresa
import Classes.Pessoa
import Classes.Vaga
import DAO.CandidatoDAO
import DAO.Competencia_CandidatoDAO
import DAO.EmpresaDAO
import DAO.VagaDAO

import java.sql.Date

class MenuView {
    static  Scanner scanner = new Scanner(System.in)
    static int option = 1

    static void mostrarMenuInicial(){
        println("seja bem vindo ao linketinder\nSistema de match entre voce e a sua vaga dos sonhos\n\n")
        loopMenu(option)
    }

    static void loopMenu(int option){

        while (option != 0) {
            //Scanner scanner = new Scanner(System.in)
            MensagensDeMenu("inicio")
            option = getOption()
            if (option == 1){
                criarConta()
            }
            else if  (option == 2){
                acessarAplicacao()

            }

        }
    }

    static void criarConta (){
        MensagensDeMenu("Criar Conta")
        int tipoDeContaCriada = getOption()
        switch (tipoDeContaCriada) {
            case 1:
                criarCandidato()
                break
            case 2:
                criarEmpresa()
                break
        }
    }

    static void acessarAplicacao (){
        MensagensDeMenu("Fazer Login")
        option = getOption()
        switch (option){
            case  1:
                String[] dadosCandidato = loginDados()
                String email = dadosCandidato[0]
                String senha = dadosCandidato[1]
                loginCandidato(email,senha)
                break
            case  2:
                String[] dadosEmpresa = loginDados()
                String email = dadosEmpresa[0]
                String senha = dadosEmpresa[1]
                loginEmpresa(email,senha)
                break
        }

    }

    static void MensagensDeMenu(String escolha){
        println("Selecione uma opção")
        println("\n")

        switch (escolha){
            case "inicio":
                println("1- Criar sua conta")
                println("2- fazer Login")
                break
            case "Criar Conta":
                println("1- Criar sua conta de Candidato")
                println("2- Criar sua conta de Empresa")
                break
            case "Fazer Login":
                println("1- Fazer login com sua conta de Candidato")
                println("2- Fazer login com sua conta de Empresa")
                break
            case "Menu Empresa":
                println("1- Visualizar Candidato")
                println("2- Criar uma nova Vaga")
                break
            case "Menu Candidato":
                println("1- Visualizar vagas")
                println("2- Fazer Logout")
                break
        }

    }

    static int getOption(){
        return entradaDeDadosProtegida()
    }

    static int entradaDeDadosProtegida(){
        try{
            Integer opcaoRecebida= scanner.nextLine().toInteger()
            return opcaoRecebida
        }
        catch(NumberFormatException ex) {
            println("Erro: Por favor, digite um número válido.")

            return -1
        }
    }

    static void criarCandidato(){
        Candidato novoCandidato = new Candidato()

        novoCandidato = CandidatoView.viewCreateCanditado();
        CandidatoDAO.criarCandidato(novoCandidato);
        List<Competencia>novasCompetencias = CompetenciaView.viewCreateCompetencias();
        novoCandidato = CandidatoDAO.listarUltimoCandidato()
        novasCompetencias.each { competencia -> Competencia_CandidatoDAO.createCompetencia_Candidato(competencia.id,novoCandidato.id)}

    }

    static void criarEmpresa(){
            Empresa novaEmpresa = new Empresa();
            EmpresaView.viewCreateEmpresa(novaEmpresa);
            EmpresaDAO.createEmpresa(novaEmpresa);

    }

    static String[] loginDados(){
        String email, senha
        println("Digite seu email: ")
        email = scanner.nextLine()
        println("Digite seu senha: ")
        senha = scanner.nextLine()
        return [email,senha]
    }

    static Candidato verificarCandidato(String email, String senha){
        try{
            Candidato candidatoCadastrado = CandidatoDAO.Login(email,senha)

            if(candidatoCadastrado.getId() > 0)
                return candidatoCadastrado

        }catch(NullPointerException exception){
            return new Candidato(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                     Date.valueOf("2025-10-09"),
                    "",
                    0
            );
        }
    }

    static void loginCandidato(String email, String senha ){
        Candidato candidatoLogado =  verificarCandidato(email,senha)

        if(candidatoLogado.getId() > 0){
            int condicaoDeEntrada = 1
            while(condicaoDeEntrada == 1) {
                MensagensDeMenu("Menu Candidato")
                int opcaoInterna = scanner.nextLine().toInteger()
                switch (opcaoInterna) {
                    case 1:
                        println("Veja a lista de empresa buscando novos trabalhadores")
                        VagaDAO.listarVaga().eachWithIndex { Vaga vaga, int indexVaga ->
                            println("__________________________________________")
                            println("\n Vaga " + (indexVaga + 1).toString())
                            println(vaga.nome)
                            println(vaga.descricao)
                            println("\n\n")
                        }
                    case 2:
                        break
                    default:
                        continue

                }
            }
        }
        else{
            println("Candidato não cadastrado, crie sua conta")

        }
    }

    static Empresa verificarEmpresa(String email, String senha){
        try{
            Empresa empressCadastrado =  EmpresaDAO.Login(email,senha)

            if(empressCadastrado.getId() > 0)
                return empressCadastrado

        }catch(NullPointerException exception){
            return new Empresa(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0,
            );
        }
    }

    static void loginEmpresa(String email, String senha){
        Empresa empresaLogada = verificarEmpresa(email,senha)
            if(empresaLogada.getId() > 0){
                int condicaoDeEntrada = 1
                while(condicaoDeEntrada >= 1 || condicaoDeEntrada <= 2) {
                    MensagensDeMenu("Menu Empresa")
                    int opcaoInterna = scanner.nextLine().toInteger()
                    if (opcaoInterna == 1) {
                        CandidatoDAO.listarCandidato().each { Candidato c ->
                            println("________________________________________")
                            println(" Candidato " + (c.id.toString()))
                            println(c.descricao)
                            println("")
                        }
                    } else if (opcaoInterna == 2) {
                        Vaga novaVaga = new Vaga()
                        novaVaga = VagaView.viewCreateVaga(empresaLogada.getId())
                        VagaDAO.createVaga(novaVaga)
                    }
                    else{break}
                    condicaoDeEntrada = opcaoInterna
                }
            }else{
                println("Empresa não cadastrada, crie sua conta")
            }
    }



}
