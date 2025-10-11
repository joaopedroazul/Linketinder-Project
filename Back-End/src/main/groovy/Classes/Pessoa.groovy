package Classes

import DAO.CompetenciaDAO
import Interfaces.IPessoa

class Pessoa implements IPessoa{

    String nome, cep, descricao;
    ArrayList<String> competencias;

    @Override
    ArrayList<Pessoa>  listarCandidato() {
        return null
    }

    @Override
    ArrayList<Pessoa>   listarEmpresa() {
        return null
    }

    static ArrayList<Competencia> viewCreateCompetencias(){
        Scanner scanner = new Scanner(System.in)
        ArrayList<Competencia> lista = new ArrayList<>()
        ArrayList<Competencia> result = new ArrayList<>()

        CompetenciaDAO.listarCompetencia().each {Competencia c -> lista.add(c)}

        println("\n\nIremos selecionar Algumas Competencias para o seu perfil\n")
        println("Para isso selecione a competencia disponivel na lista a seguir: ")
        println("\n\n")
        lista.each {item ->print((item.getId()).toString()+": "+item.getNome()+"\n")}
        Integer flag = 0
        while(flag < 1){

            println("\nSelecione o numero da competencia ou digite 0 para sair ")
            int option = scanner.nextLine().toInteger()
            if (option != 0) {
                result.add(lista.get(option-1))
                lista.remove(option-1)
            }else{
                break
            }
            print("\033[H\033[2J")
            System.out.flush()
            lista.eachWithIndex {item,index ->print((index+1).toString()+": "+item.getNome()+"\n")}
        }
        result
    }

}
