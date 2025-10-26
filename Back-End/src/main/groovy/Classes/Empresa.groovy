package Classes

class Empresa extends Pessoa{
    //String nome, estado, cep, descricao;
    String email, cnpj, pais, senha
    int id;

    Empresa(String NOME,String EMAIL,String CNPJ,String PAIS,String CEP,String DESCRICAO, int id){
        this.nome = NOME;
        this.email = EMAIL;
        this.cnpj = CNPJ;
        this.pais = PAIS;
        this.cep = CEP;
        this.descricao = DESCRICAO;
        this.id = id
    }

    Empresa(){
        this.nome = "";
        this.email = "";
        this.cnpj = "";
        this.pais = "";
        this.cep = "";
        this.descricao = "";
        this.senha = "";
    }




}
