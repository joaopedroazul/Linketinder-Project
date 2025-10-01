import type { Candidato } from "./Candidato";

export class Competencias {
    id_user: string;
    listaCompetencias: string[];

    constructor(id: string, listaCompetencias = [""]) {
        this.id_user = id;
        this.listaCompetencias = listaCompetencias;
    }

    static getCompetenciasById(id: string): string[] {
        const competenciasData = localStorage.getItem('competencias');
        if (competenciasData) {
            const competenciasList: Competencias[] = JSON.parse(competenciasData);
            const competencias = competenciasList.find(comp => comp.id_user === id);
            return competencias?.listaCompetencias || [""];
        }
        return [""];
    }
    static deleteCompetenciasById(id: string): void {
        const competenciasData = localStorage.getItem('competencias');
        if (competenciasData) {
            const competenciasList: Competencias[] = JSON.parse(competenciasData);
            const competencias = competenciasList.filter(comp => comp.id_user !== id);
            localStorage.setItem("competencias",JSON.stringify(competencias))
        }
    }

    static saveCompetencias(competencias: Competencias): void {
        const competenciasData = localStorage.getItem('competencias');
        let competenciasList: Competencias[] = competenciasData ? JSON.parse(competenciasData) : [];

        const index = competenciasList.findIndex(comp => comp.id_user === competencias.id_user);
        if (index !== -1) {
            competenciasList[index] = competencias; // Atualiza se já existir
        } else {
            competenciasList.push(competencias); // Adiciona novo
        }

        localStorage.setItem('competencias', JSON.stringify(competenciasList));
    }

    static getDataCompetencias(): void {
        let competenciasPadrao =  ["Java","Python","JavaScript","C#","Ruby","PHP","C++","Swift","Go","Kotlin"]
        var result0 = 0
        var result1 = 0
        var result2 = 0
        var result3 = 0
        var result4 = 0
        var result5 = 0
        var result6 = 0
        var result7 = 0
        var result8 = 0
        var result9 = 0



        var candidatos: Candidato[] = JSON.parse(localStorage.getItem('candidatos') || '[]');
        var tamanho = candidatos.length
        
        for(let c in candidatos){
            const candidato = candidatos[c];
            
            if (candidato) {
                const list: string[]  = Competencias.getCompetenciasById(candidato.nome + candidato.email + candidato.cpf);
                let count :number = 0 
                for(let stock in competenciasPadrao){
                    let competenciaPadrao = competenciasPadrao[stock];
                    
                    if(competenciaPadrao && list.includes(competenciaPadrao))
                        switch(count){
                            case 0:
                                result0++
                                break;
                            case 1:
                                result1++
                                break;
                            case 2:
                                result2++
                                break;
                            case 3:
                                result3++
                                break;
                            case 4:
                                result4++
                                break;
                            case 5:
                                result5++
                                break;
                            case 6:
                                result6++
                                break;
                            case 7:
                                result7++
                                break;
                            case 8:
                                result8++
                                break;
                            case 9:
                                result9++
                                break;
                    }
                        
                    // for(let c = 0 ; c< list?.length;c++){
                    //     let comp = list[c];
                    //     console.log(competenciaPadrao,c)
                        
                    // }]
                    count++
                }
                
            }
        }
        console.log([(result0/tamanho)*100,(result1/tamanho)*100,(result2/tamanho)*100,(result3/tamanho)*100,(result4/tamanho)*100,(result5/tamanho)*100,(result6/tamanho)*100,(result7/tamanho)*100,(result8/tamanho)*100,(result9/tamanho)*100])
        localStorage.setItem("DataCompetencias",JSON.stringify([(result0/tamanho)*100,(result1/tamanho)*100,(result2/tamanho)*100,(result3/tamanho)*100,(result4/tamanho)*100,(result5/tamanho)*100,(result6/tamanho)*100,(result7/tamanho)*100,(result8/tamanho)*100,(result9/tamanho)*100]));
        localStorage.setItem("DataCompetenciasCount",JSON.stringify([result0,result1,result2,result3,result4,result5,result6,result7,result8,result9]));
    }
   
}
