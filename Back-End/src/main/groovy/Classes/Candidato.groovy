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


}
