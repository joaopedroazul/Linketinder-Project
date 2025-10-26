package Views

import Classes.Candidato
import DAO.CandidatoDAO

import java.sql.Date
import java.util.regex.Matcher
import java.util.regex.Pattern

class CandidatoView {


    static Candidato viewCreateCanditado(){
        Candidato candidatoCriado = new Candidato();
        String[] dataValidada;
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        validandoNome(candidatoCriado)
        validandoSobrenome(candidatoCriado)
        validandoEmail(candidatoCriado)
        validandoCpf(candidatoCriado)
        validandoPais(candidatoCriado)
        validandoCep(candidatoCriado)
        validandoDescricao(candidatoCriado)
        validandoSenha(candidatoCriado)
        dataValidada = validandoDataNascimento()
        candidatoCriado.dataNascimento = Date.valueOf(dataValidada[0]);


        println("Candidato cadastrado!!!")
        return candidatoCriado;
    }

    static String[] validandoDataNascimento(){

        Scanner scanner = new Scanner(System.in)
        String dataInserida = '';
        while (dataInserida != "0"){
            println("Digite a data de nascimento: ")
            dataInserida = scanner.nextLine()
            Pattern regex = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}" );
            Matcher matcher = regex.matcher(dataInserida);
            if(matcher.matches()){
                return [dataInserida.tokenize("/")[2]+"-"+dataInserida.tokenize("/")[1]+"-"+dataInserida.tokenize("/")[0],"1"]
            }else{
                println("data de nascimento invalida, vefiricar se a data segue o padrao DD/MM/YYYY")
            }
        }
        if(dataInserida == "0")
            return ["","0"]

        return ["",""]

    }

    static Candidato validandoNome(Candidato candidatoAtual){

        Scanner scanner = new Scanner(System.in)
        String nome = ""

        while(nome == ""){
            println("Digite seu nome: ")
            nome = scanner.nextLine()
            if(nome != ""){
                candidatoAtual.nome = nome
                return candidatoAtual
            }
            else{
                println("Nome digitado invalido, digite novamente\n\n")
            }

        }

    }
    static Candidato validandoSobrenome(Candidato candidatoAtual){
        Scanner scanner = new Scanner(System.in)
        String sobrenome = ""

        while(sobrenome == ""){
            println("Digite seu sobrenome: ")
            sobrenome = scanner.nextLine()
            if(sobrenome != ""){
                candidatoAtual.sobrenome = sobrenome
                return candidatoAtual
            }
            else{
                println("Sobrenome digitado invalido, digite novamente\n\n")
            }

        }

    }
    static Candidato validandoEmail(Candidato candidatoAtual){
        Scanner scanner = new Scanner(System.in)
        String email = ""

        while(email == ""){
            println("Digite seu email: ")
            email = scanner.nextLine()
            if(email != ""){
                candidatoAtual.email = email
                return candidatoAtual
            }
            else{
                println("Email digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Candidato validandoCpf(Candidato candidatoAtual){
        Scanner scanner = new Scanner(System.in)
        String cpf = ""

        while(cpf == ""){
            println("Digite seu cpf: ")
            cpf = scanner.nextLine()
            if(cpf != ""){
                candidatoAtual.cpf = cpf
                return candidatoAtual
            }
            else{
                println("Cpf digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Candidato validandoPais(Candidato candidatoAtual){
        Scanner scanner = new Scanner(System.in)
        String pais = ""

        while(pais == ""){
            println("Digite seu país: ")
            pais = scanner.nextLine()
            if(pais != ""){
                candidatoAtual.país = pais
                return candidatoAtual
            }
            else{
                println("País digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Candidato validandoCep(Candidato candidatoAtual){
        Scanner scanner = new Scanner(System.in)
        String cep = ""

        while(cep == ""){
            println("Digite seu cep: ")
            cep = scanner.nextLine()
            if(cep != ""){
                candidatoAtual.cep = cep
                return candidatoAtual
            }
            else{
                println("Cep digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Candidato validandoDescricao(Candidato candidatoAtual){
        Scanner scanner = new Scanner(System.in)
        String descricao = ""

        while(descricao == ""){
            println("Digite seu descricao: ")
            descricao = scanner.nextLine()
            if(descricao != ""){
                candidatoAtual.descricao = descricao
                return candidatoAtual
            }
            else{
                println("Cep digitado invalido, digite novamente\n\n")
            }

        }
    }
    static Candidato validandoSenha(Candidato candidatoAtual){
        Scanner scanner = new Scanner(System.in)
        String senha = ""

        while(senha == ""){
            println("Digite sua senha: ")
            senha = scanner.nextLine()
            if(senha != ""){
                candidatoAtual.senha = senha
                return candidatoAtual
            }
            else{
                println("Senha invalida, digite novamente\n\n")
            }

        }
    }

    static Boolean  Login(String email, String senha){
        boolean possuiCadastro = false
        if(CandidatoDAO.Login(email,senha) != null)
            possuiCadastro = true

        possuiCadastro
    }




}
