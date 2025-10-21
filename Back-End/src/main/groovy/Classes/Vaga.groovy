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
        Vaga vagaCriada = new Vaga();
        Scanner scanner = new Scanner(System.in)
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        println("Digite o titulo da Vaga: ")
        vagaCriada.nome = scanner.nextLine()
        vagaCriada.empresa_id = id_empresa
        vagaCriada.estado_id = validandoEstado()
        println("Digite a cidade, para fazer a validação: ")
        String cidade= scanner.nextLine()
        vagaCriada.cidade_id = validandoCidade(cidade,vagaCriada.estado_id)
        println("Digite uma breve descrição sobre voce: ")
        vagaCriada.descricao = scanner.nextLine()
        return vagaCriada
    }


    static int validandoEstado(){
        Scanner scanner = new Scanner(System.in)
        ArrayList<Estado> estadosDisponiveis = new ArrayList<>()

        EstadoDAO.listarEstados().each { Estado e -> estadosDisponiveis.add(e)}

        println("\n\nEscolha o estado onde a Vaga será localizada:\n")
        println("\n\n")
        estadosDisponiveis.eachWithIndex {Estado estado, int indexEstado ->print((indexEstado+1).toString()+": "+estado.getNome()+"\n")}
        Integer estadoSelecionado = -1
        while(estadoSelecionado < 0 || estadoSelecionado > estadosDisponiveis.size()){

            println("\nSelecione o numero do estado correspondente")
            estadoSelecionado = scanner.nextLine().toInteger()
            if (estadoSelecionado >= 0 || estadoSelecionado <= estadosDisponiveis.size()) {
                break
            }else{
                continue
            }
            print("\033[H\033[2J")
            System.out.flush()
            estadosDisponiveis.eachWithIndex {estado,indexEstado ->print((indexEstado+1).toString()+": "+estado.getNome()+"\n")}
        }
        estadoSelecionado

    }

    static int validandoCidade(String city, int estado_id){
        Scanner scanner = new Scanner(System.in)
        ArrayList<Cidade> cidadesDisponiveis = new ArrayList<>()

        CidadeDAO.listarCidades(city,estado_id).each { Cidade e -> lista.add(e)}

        if (cidadesDisponiveis.size() == 1){
            return cidadesDisponiveis.pop().getId()
        }
        println("\n\nEscolha a cidade onde a Vaga será localizada:\n")
        println("\n\n")
        cidadesDisponiveis.eachWithIndex {Cidade item, int index ->print((index+1).toString()+": "+item.getNome()+"\n")}
        Integer cidadeSelecionada = -1
        while(cidadeSelecionada < 0 || cidadeSelecionada > cidadesDisponiveis.size()){

            println("\nSelecione o numero do estado correspondente")
            cidadeSelecionada = scanner.nextLine().toInteger()
            if (cidadeSelecionada >= 0 || cidadeSelecionada <= cidadesDisponiveis.size()) {
                break
            }else{
                continue
            }
            print("\033[H\033[2J")
            System.out.flush()
            cidadesDisponiveis.eachWithIndex {item,index ->print((index+1).toString()+": "+item.getNome()+"\n")}
        }
        cidadeSelecionada

    }

}
