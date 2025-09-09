package Classes

class Candidato extends Pessoa{
    //String nome, estado, cep, descricao;
    String email, cpf
    Integer idade


    void createCanditado() {
        File file  = new File($/./DataBase/Candidatos.txt/$)
        if(file.readLines().size() == 0 ){
            file.write("${this.nome}_${this.email}_${this.cpf}_${this.idade.toString()}_${this.estado}_${this.cep}_${this.descricao}\n")
        }else{
            file.append("${this.nome}_${this.email}_${this.cpf}_${this.idade.toString()}_${this.estado}_${this.cep}_${this.descricao}\n")
        }


        File file2  = new File($/./DataBase/Candidatos_competencias.txt/$)
        Integer tamanho = this.competencias.size()
        println(tamanho)
        this.competencias.each {println(it)}
        this.competencias.eachWithIndex {comp, id ->
            println(id)
            if (id != tamanho-1) {
                if (file2.readLines().size() == 0){
                    file2.write("${comp}_")
                }else{
                    file2.append("${comp}_")
                }

            }else{
                if (file2.readLines().size() == 0){
                    file2.write("${comp}\n")
                }else{
                    file2.append("${comp}\n")
                }
            }
        }
        //file.write("${this.competencias}\n")

    }

    static void viewCreateCanditado(Candidato c){
        Scanner scanner = new Scanner(System.in)
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        println("Digite seu usuário: ")
        c.nome = scanner.nextLine()
        println("Digite seu email: ")
        c.email = scanner.nextLine()
        println("Digite seu cpf: ")
        c.cpf = scanner.nextLine()
        println("Digite sua idade: ")
        c.idade = scanner.nextLine().toInteger()
        println("Digite a sigla do estado onde reside: ")
        c.estado = scanner.nextLine()
        println("Digite o seu CEP: ")
        c.cep = scanner.nextLine()
        println("Digite uma breve descrição sobre voce: ")
        c.descricao = scanner.nextLine()
        c.competencias = viewCreateCompetencias()
        c.createCanditado()
        println("Candidato cadastrado!!!")
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



    @Override
    static ArrayList<Candidato>  listarCandidato(){
        File file  = new File($/./DataBase/Candidatos.txt/$)
        List<String> lines = file.readLines()
        ArrayList<Candidato> lista = new ArrayList<>()
        lines.each {line->
            Candidato c = new Candidato()
            c.nome = line.split('_')[0]
            //println(c.nome)
            c.email = line.split('_')[1]
            //println(c.email)
            c.cpf = line.split('_')[2]
            //println(c.cpf)
            c.idade = line.split('_')[3].toInteger()
            //println(c.idade)
            c.estado = line.split('_')[4]
            c.cep = line.split('_')[5]
            //println(c.cep)
            c.descricao = line.split('_')[6]
            //println(c.descricao)
            lista.add(c)
        }
        lista
    }



}
