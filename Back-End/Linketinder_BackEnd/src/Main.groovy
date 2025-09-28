import Classes.*
import DataBase.*
static void main(String[] args) {

    ArrayList<Empresa> listEmpresas = new ArrayList<>()
    ArrayList<Candidato> listCandidatos = new ArrayList<>()
    ArrayList<String> listEmpresasCompetencias = new ArrayList<>()
    ArrayList<String> listCandidatosCompetencias = new ArrayList<>()
    listEmpresasCompetencias = Empresa.listarCompetencias()
    listCandidatosCompetencias = Candidato.listarCompetencias()
    println("seja bem vindo ao linketinder\nSistema de match entre voce e a sua vaga dos sonhos\n\n")
    Integer opcao = 9
    while (opcao != 0){
        Scanner scanner = new Scanner(System.in)
        println("Selecione uma opção")
        println("\n\n")
        println("1- Criar sua conta")
        println("2- fazer Login")
        opcao = scanner.nextLine().toInteger()
        if (opcao == 0){
            break;
        }else if  (opcao != 1 && opcao != 2){
            continue
        }else if (opcao == 1){
            println("Selecione uma opção")
            println("\n\n")
            println("1- Criar sua conta de Candidato")
            println("2- Criar sua conta de Empresa")
            opcao = scanner.nextLine().toInteger()
            if (opcao == 1){
                Candidato c = new Candidato()
                listCandidatos = Candidato.viewCreateCanditado(c,listCandidatos)
                Candidato.updateLista(listCandidatos)
                listCandidatos = Candidato.listarCandidato()
            }else{
                Empresa e = new Empresa()
                listEmpresas = Empresa.viewCreateEmpresa(e,listEmpresas)
                Empresa.updateLista(listEmpresas)
                listEmpresas = Empresa.listarEmpresa()
            }


        }else if (opcao == 2){
            println("Selecione uma opção")
            println("\n\n")
            println("1- Fazer login com sua conta de Candidato")
            println("2- Fazer login com sua conta de Empresa")
            opcao = scanner.nextLine().toInteger()
            if (opcao == 1){
                String email, cpf
                println("Digite seu email: ")
                email = scanner.nextLine()
                println("Digite seu cpf: ")
                cpf = scanner.nextLine()
                if(Candidato.Login(email,cpf)){
                    println("Veja a lista de empresa buscando novos trabalhadores")
                    listEmpresas = Empresa.listarEmpresa()
                    listEmpresas.eachWithIndex{l,id->
                        println(l.nome)
                        println(l.email)
                        println(l.cnpj)
                        println(l.pais)
                        println(l.estado)
                        println(l.cep)
                        println(l.descricao)
                        listEmpresasCompetencias[id].each { c ->
                            print(c+" ")
                        }
                        println("\n\n")

                    }

                }else{
                    println("Candidato não cadastrado, crie sua conta")
                    continue
                }
            }else{
                String email, cnpj
                println("Digite seu email: ")
                email = scanner.nextLine()
                println("Digite seu cnpj: ")
                cnpj = scanner.nextLine()
                if(Empresa.Login(email,cnpj)){
                    println("Veja a lista de candidatos buscando novas oportunidades")
                    listCandidatos = Candidato.listarCandidato()
                    listCandidatos.eachWithIndex{l,id->
                        println(l.nome)
                        println(l.email)
                        println(l.cpf)
                        println(l.idade)
                        println(l.estado)
                        println(l.cep)
                        println(l.descricao)
                        listEmpresasCompetencias[id].each { c ->
                            print(c+" ")
                        }
                        println("\n\n")

                    }

                }else{
                    println("Empresa não cadastrada, crie sua conta")
                    continue
                }
            }
        }

    }
//    Empresa e = new Empresa()
//    listEmpresas = Empresa.listarEmpresa()
//
//    listEmpresas.eachWithIndex{l,id->
//            println(l.nome)
//            println(l.email)
//            println(l.cnpj)
//            println(l.pais)
//            println(l.estado)
//            println(l.cep)
//            println(l.descricao)
//            listEmpresasCompetencias[id].each { c ->
//                print(c+" ")
//            }
//            println("\n\n")
//    }
    //Empresa.viewCreateEmpresa(e)



}