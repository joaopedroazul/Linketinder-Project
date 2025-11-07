package Views

import Controllers.CandidatoController
import Controllers.EmpresaController
import Services.CandidatoService
import Services.EmpresaService


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
                CandidatoController.createCandidato()
                break
            case 2:
                EmpresaController.criarEmpresa()
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
                CandidatoController.loginCandidato(email, senha)
                break
            case  2:
                String[] dadosEmpresa = loginDados()
                String email = dadosEmpresa[0]
                String senha = dadosEmpresa[1]
                EmpresaController.loginEmpresa(email, senha)
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

    static String[] loginDados(){
        String email, senha
        println("Digite seu email: ")
        email = scanner.nextLine()
        println("Digite seu senha: ")
        senha = scanner.nextLine()
        return [email,senha]
    }



}
