package Classes

import DAO.CandidatoDAO

import java.sql.Date
import java.util.regex.Matcher
import java.util.regex.Pattern

class Candidato extends Pessoa{
    //String nome, cep, descricao;
    int id
    String email, cpf, sobrenome,país,senha
    Date dataNascimento

    Candidato(){
        this.nome = "";
        this.sobrenome =  "";
        this.dataNascimento = Date.valueOf("2025-10-09");
        this.email = "";
        this.cpf = "";
        this.país = "";
        this.cep = "";
        this.descricao = "";
        this.senha = "";
    }

    Candidato(String nome,String sobrenome,String email,String cpf,String pais,String CEP ,Date dataN,String desc,int id) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.dataNascimento = dataN
        this.email = email
        this.cpf = cpf
        this.país = pais
        this.cep = CEP
        this.descricao = desc
        this.id = id;
    }



    static Candidato viewCreateCanditado(){
        Scanner scanner = new Scanner(System.in)
        Candidato candidatoCriado = new Candidato();
        String[] dataValidada;
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        println("Digite seu nome: ")
        candidatoCriado.nome = scanner.nextLine()
        println("Digite seu sobrenome: ")
        candidatoCriado.sobrenome = scanner.nextLine()
        println("Digite seu email: ")
        candidatoCriado.email = scanner.nextLine()
        println("Digite seu cpf: ")
        candidatoCriado.cpf = scanner.nextLine()
        println("Digite o Pais onde mora: ")
        candidatoCriado.país = scanner.nextLine()
        dataValidada = validandoDataNascimento()
        if(dataValidada[1] == "0")
            return null;
        else
           candidatoCriado.dataNascimento = Date.valueOf(dataValidada[0]);
        println("Digite o seu CEP: ")
        candidatoCriado.cep = scanner.nextLine()
        println("Digite uma breve descrição sobre voce: ")
        candidatoCriado.descricao = scanner.nextLine()
        println("Digite sua senha de acesso: ")
        candidatoCriado.senha = scanner.nextLine()

        println("Candidato cadastrado!!!")
        return candidatoCriado;
    }

    static String[] validandoDataNascimento(){
        Scanner scanner = new Scanner(System.in)
        String dataInserida = '';
        while (dataInserida == "0"){
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

    static Boolean  Login(String email, String senha){
        boolean possuiCadastro = false
        if(CandidatoDAO.Login(email,senha) != null)
            possuiCadastro = true

        possuiCadastro
    }




}
