package Classes

class Cidade {

    String nome;
    int estado_id,cidade_id;

    Cidade(){
        this.nome = "";
    }

    Cidade(String nome, int id_est, int id_city){
        this.nome = nome;
        this.estado_id = id_est;
        this.cidade_id = id_city
    }
}
