package Classes

class Empresa extends Pessoa{
    //String nome, estado, cep, descricao;
    String email, cnpj, pais


    void createEmpresa() {
        File file  = new File($/./DataBase/Empresa.txt/$)
        if(file.readLines().size() == 0 ){
            file.write("${this.nome}_${this.email}_${this.cnpj}_${this.pais}_${this.estado}_${this.cep}_${this.descricao}\n")
        }else{
            file.append("${this.nome}_${this.email}_${this.cnpj}_${this.pais}_${this.estado}_${this.cep}_${this.descricao}\n")
        }


        File file2  = new File($/./DataBase/Empresas_competencias.txt/$)
        Integer tamanho = this.competencias.size()
        this.competencias.eachWithIndex {comp, id ->
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


    static void viewCreateEmpresa(Empresa e){
        Scanner scanner = new Scanner(System.in)
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        println("Digite o nome da sua empresa: ")
        e.nome = scanner.nextLine()
        println("Digite seu email corporativo: ")
        e.email = scanner.nextLine()
        println("Digite seu cnpj: ")
        e.cnpj = scanner.nextLine()
        println("Digite seu pais: ")
        e.pais = scanner.nextLine()
        println("Digite a sigla do estado onde reside: ")
        e.estado = scanner.nextLine()
        println("Digite o seu CEP: ")
        e.cep = scanner.nextLine()
        println("Digite uma breve descrição sobre voce: ")
        e.descricao = scanner.nextLine()
        e.competencias = viewCreateCompetencias()
        e.createEmpresa()
        println("Empresa cadastrada!!!")
    }



    @Override
    static ArrayList<Empresa>  listarEmpresa(){
        File file  = new File($/./DataBase/Empresa.txt/$)
        List<String> lines = file.readLines()
        ArrayList<Empresa> lista = new ArrayList<>()
        lines.each {line->
            //line.split('_').eachWithIndex{ l,id -> println(id+": "+l)}
            Empresa e = new Empresa()
            e.nome = line.split('_')[0]
            e.email = line.split('_')[1]
            e.cnpj = line.split('_')[2]
            e.pais = line.split('_')[3]
            e.estado = line.split('_')[4]
            e.cep = line.split('_')[5]
            e.descricao = line.split('_')[6]
            lista.add(e)
        }
        lista
    }

    static ArrayList<Empresa>  listarCompetencias(){
        File file  = new File($/./DataBase/Empresas_competencias.txt/$)
        List<String> lines = file.readLines()
        ArrayList<String> lista = new ArrayList<>()
        lines.each {line->
            if (line.find("_")){
                lista.add(line.split("_"))
            }else{
                lista.add(line)
            }
        }
        lista
    }

    static Boolean  Login(String email, String cnpj){
        File file  = new File($/./DataBase/Empresa.txt/$)
        List<String> lines = file.readLines()
        ArrayList<String> lista = new ArrayList<>()
        boolean possuiCadastro = false;
        lines.each {line->
            if ( line.split('_')[1] == email && line.split('_')[2] == cnpj )
                possuiCadastro = true
        }
        possuiCadastro
    }

}
