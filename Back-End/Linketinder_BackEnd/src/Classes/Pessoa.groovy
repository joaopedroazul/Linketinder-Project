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
    ArrayList<Pessoa>   listarEmpresa() {
        return null
    }

    static ArrayList<String> viewCreateCompetencias(){
        Scanner scanner = new Scanner(System.in)
        ArrayList<String> lista = new ArrayList<>()
        ArrayList<String> result = new ArrayList<>()
        lista.add("Java")
        lista.add("Python")
        lista.add("JavaScript")
        lista.add("Cybersecurity")
        lista.add("Linux")
        lista.add("DevOps")
        lista.add("Spring Framework")
        lista.add("Angular")
        lista.add("Groovy")
        lista.add("HTML/CSS")

        println("\n\nIremos selecionar Algumas Competencias para o seu perfil\n")
        println("Para isso selecione a competencia disponivel na lista a seguir: ")
        println("\n\n")
        lista.eachWithIndex {item,index ->print((index+1).toString()+": "+item+"\n")}
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
            lista.eachWithIndex {item,index ->print((index+1).toString()+": "+item+"\n")}
        }
        result
    }

}
