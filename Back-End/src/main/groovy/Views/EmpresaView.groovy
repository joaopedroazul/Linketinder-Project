package Views

import Classes.Empresa
import Classes.Empresa

class EmpresaView {


    static Empresa viewCreateEmpresa(Empresa empresaCriada){
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        println("Digite uma breve descrição sobre voce: ")
        validandoNome(empresaCriada)
        validandoEmail(empresaCriada)
        validandoCnpj(empresaCriada)
        validandoPais(empresaCriada)
        validandoCep(empresaCriada)
        validandoDescricao(empresaCriada)
        validandoSenha(empresaCriada)
        return empresaCriada
    }


    static Empresa validandoNome(Empresa empresaAtual){

        Scanner scanner = new Scanner(System.in)
        String nome = ""

        while(nome == ""){
            println("Digite o nome da sua empresa: ")
            nome = scanner.nextLine()
            if(nome != ""){
                empresaAtual.nome = nome
                return empresaAtual
            }
            else{
                println("Nome digitado invalido, digite novamente\n\n")
            }

        }

    }
    static Empresa validandoEmail(Empresa empresaAtual){
        Scanner scanner = new Scanner(System.in)
        String email = ""

        while(email == ""){
            println("Digite seu email corporativo: ")
            email = scanner.nextLine()
            if(email != ""){
                empresaAtual.email = email
                return empresaAtual
            }
            else{
                println("Email digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Empresa validandoCnpj(Empresa empresaAtual){
        Scanner scanner = new Scanner(System.in)
        String cnpj = ""

        while(cnpj == ""){
            println("Digite seu cnpj: ")
            cnpj = scanner.nextLine()
            if(cnpj != ""){
                empresaAtual.cnpj = cnpj
                return empresaAtual
            }
            else{
                println("Cnpj digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Empresa validandoPais(Empresa empresaAtual){
        Scanner scanner = new Scanner(System.in)
        String pais = ""

        while(pais == ""){
            println("Digite seu país: ")
            pais = scanner.nextLine()
            if(pais != ""){
                empresaAtual.pais = pais
                return empresaAtual
            }
            else{
                println("País digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Empresa validandoCep(Empresa empresaAtual){
        Scanner scanner = new Scanner(System.in)
        String cep = ""

        while(cep == ""){
            println("Digite seu CEP: ")
            cep = scanner.nextLine()
            if(cep != ""){
                empresaAtual.cep = cep
                return empresaAtual
            }
            else{
                println("Cep digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Empresa validandoDescricao(Empresa empresaAtual){
        Scanner scanner = new Scanner(System.in)
        String descricao = ""

        while(descricao == ""){
            println("Digite seu descricao: ")
            descricao = scanner.nextLine()
            if(descricao != ""){
                empresaAtual.descricao = descricao
                return empresaAtual
            }
            else{
                println("Cep digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Empresa validandoSenha(Empresa empresaAtual){
        Scanner scanner = new Scanner(System.in)
        String senha = ""

        while(senha == ""){
            println("Digite sua senha: ")
            senha = scanner.nextLine()
            if(senha != ""){
                empresaAtual.senha = senha
                return empresaAtual
            }
            else{
                println("Senha invalida, digite novamente\n\n")
            }

        }
    }


}
