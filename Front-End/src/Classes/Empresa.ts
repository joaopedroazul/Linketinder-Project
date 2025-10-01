export class Empresa {
    nome: string;
    email: string;
    pais: string;
    cnpj: string;
    estado: string;
    cep: string;
    descricao: string;

    constructor(nome: string, email: string, pais: string, cnpj: string, estado: string, cep: string, descricao: string) {
        this.nome = nome;
        this.email = email;
        this.pais = pais;
        this.cnpj = cnpj;
        this.estado = estado;
        this.cep = cep;
        this.descricao = descricao;
    }
}
