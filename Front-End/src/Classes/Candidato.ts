export class Candidato {
    nome: string;
    email: string;
    idade: number;
    cpf: string;
    estado: string;
    cep: string;
    descricao: string;

    constructor(nome: string, email: string, idade: number, cpf: string, estado: string, cep: string, descricao: string) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.cpf = cpf;
        this.estado = estado;
        this.cep = cep;
        this.descricao = descricao;
    }
}
