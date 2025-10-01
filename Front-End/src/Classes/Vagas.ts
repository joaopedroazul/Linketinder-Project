export class Vagas {
    titulo: string;
    atributos: string[];
    id_empresa: string;

    constructor(titulo: string, att: string[], id: string) {
        this.titulo = titulo;
        this.atributos = att;
        this.id_empresa = id;
    }
}
