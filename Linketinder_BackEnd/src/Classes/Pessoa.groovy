package Classes
import Interfaces.IPessoa


class Pessoa implements IPessoa{

    String nome, estado, cep, descricao;
    ArrayList<String> competencias;

    @Override
    ArrayList<Pessoa>  listarCandidato() {
        return null
    }

    @Override
    Pessoa listarEmpresa() {
        return null
    }
}
