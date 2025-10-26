package Classes

import DAO.CidadeDAO
import DAO.EstadoDAO

class Vaga {
    String nome, descricao
    int empresa_id, estado_id,cidade_id

    Vaga(){
        this.nome = "";
        this.descricao = "";
    }
    Vaga(String nome, int empresa,int estado,int cidade, String descricaoVaga){
        this.nome = nome;
        this.empresa_id = empresa;
        this.estado_id = estado;
        this.cidade_id = cidade;
        this.descricao = descricaoVaga;
    }



}
