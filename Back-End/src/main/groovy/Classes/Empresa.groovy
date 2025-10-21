package Classes

class Empresa extends Pessoa{
    //String nome, estado, cep, descricao;
    String email, cnpj, pais, senha
    int id;

    Empresa(String NOME,String EMAIL,String CNPJ,String PAIS,String CEP,String DESCRICAO, int id){
        this.nome = NOME;
        this.email = EMAIL;
        this.cnpj = CNPJ;
        this.pais = PAIS;
        this.cep = CEP;
        this.descricao = DESCRICAO;
        this.id = id
    }

    Empresa(){
        this.nome = "";
        this.email = "";
        this.cnpj = "";
        this.pais = "";
        this.cep = "";
        this.descricao = "";
        this.senha = "";
    }


    static Empresa viewCreateEmpresa(Empresa empresaCriada){
        Scanner scanner = new Scanner(System.in)
        println("Seja bem vindo!\n\n Iremos criar o seu cadastro no Linketinder agora\n")
        println("Para isso insira suas informações de acordo com as perguntas")
        println("\n\n")
        println("Digite o nome da sua empresa: ")
        empresaCriada.nome = scanner.nextLine()
        println("Digite seu email corporativo: ")
        empresaCriada.email = scanner.nextLine()
        println("Digite seu cnpj: ")
        empresaCriada.cnpj = scanner.nextLine()
        println("Digite seu pais: ")
        empresaCriada.pais = scanner.nextLine()
        println("Digite o seu CEP: ")
        empresaCriada.cep = scanner.nextLine()
        println("Digite uma breve descrição sobre voce: ")
        empresaCriada.descricao = scanner.nextLine()
        println("Digite sua senha: ")
        empresaCriada.senha = scanner.nextLine()
        println("Empresa cadastrada!!!")
        return empresaCriada
    }




}
