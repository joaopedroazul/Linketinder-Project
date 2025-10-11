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

    static Vaga viewCreateVaga(int id_empresa){
        Vaga v = new Vaga();
        Scanner scanner = new Scanner(System.in)
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        println("Digite o titulo da Vaga: ")
        v.nome = scanner.nextLine()
        v.empresa_id = id_empresa
        v.estado_id = validandoEstado()
        println("Digite a cidade, para fazer a validação: ")
        String cidade= scanner.nextLine()
        v.cidade_id = validandoCidade(cidade,v.estado_id)
        println("Digite uma breve descrição sobre voce: ")
        v.descricao = scanner.nextLine()
        return v
    }


    static int validandoEstado(){
        Scanner scanner = new Scanner(System.in)
        ArrayList<Estado> lista = new ArrayList<>()

        EstadoDAO.listarEstados().each { Estado e -> lista.add(e)}

        println("\n\nEscolha o estado onde a Vaga será localizada:\n")
        println("\n\n")
        lista.eachWithIndex {Estado item, int index ->print((index+1).toString()+": "+item.getNome()+"\n")}
        Integer escolha = -1
        while(escolha < 0 || escolha > lista.size()){

            println("\nSelecione o numero do estado correspondente")
            escolha = scanner.nextLine().toInteger()
            if (escolha >= 0 || escolha <= lista.size()) {
                break
            }else{
                continue
            }
            print("\033[H\033[2J")
            System.out.flush()
            lista.eachWithIndex {item,index ->print((index+1).toString()+": "+item.getNome()+"\n")}
        }
        escolha

    }

    static int validandoCidade(String city, int estado_id){
        Scanner scanner = new Scanner(System.in)
        ArrayList<Cidade> lista = new ArrayList<>()

        CidadeDAO.listarCidades(city,estado_id).each { Cidade e -> lista.add(e)}

        if (lista.size() == 1){
            return lista.pop().getId()
        }
        println("\n\nEscolha a cidade onde a Vaga será localizada:\n")
        println("\n\n")
        lista.eachWithIndex {Cidade item, int index ->print((index+1).toString()+": "+item.getNome()+"\n")}
        Integer escolha = -1
        while(escolha < 0 || escolha > lista.size()){

            println("\nSelecione o numero do estado correspondente")
            escolha = scanner.nextLine().toInteger()
            if (escolha >= 0 || escolha <= lista.size()) {
                break
            }else{
                continue
            }
            print("\033[H\033[2J")
            System.out.flush()
            lista.eachWithIndex {item,index ->print((index+1).toString()+": "+item.getNome()+"\n")}
        }
        escolha

    }

}
