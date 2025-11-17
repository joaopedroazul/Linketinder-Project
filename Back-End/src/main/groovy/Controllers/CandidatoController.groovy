package Controllers

import DAO.CandidatoDAO
import Models.Candidato
import Services.CandidatoService


import java.util.regex.Matcher;
import java.util.regex.Pattern;


class CandidatoController {
    static void createCandidato(){
        Candidato possivelCandidatoCriado = CandidatoService.criarCandidato()
        try{
            if(possivelCandidatoCriado.getId() > 0)
                println("Candidato Cadastrado com sucesso")
        }catch (Exception excecao){
            println("Ocorreu um erro durante o cadastro")
        }
    }

    static void loginCandidato(String email,String senha){
        CandidatoService.loginCandidato(email, senha)
    }

     static String processRequest(String method, String path, String body) {
        try {

            //GET READ ALL
            if (path.equals("/candidatos") && method.equals("GET")) {
                List<Candidato> candidatos = CandidatoDAO.listarCandidato();
                return toJsonArray(candidatos);
            }



            // POST CREATE
            if (path.equals("/candidatos") && method.equals("POST")) {
                Candidato novoCandidato = fromJson(body)
                boolean create = CandidatoDAO.criarCandidato(novoCandidato);
                if (create) {
                    return "{Candidato criado com sucesso!}" ;
                }else {
                    return "{Ocorreu um erro am criar um candidato!}" ;
                }


            }

            Pattern pattern = Pattern.compile("^/candidatos/(\\d+)");
            Matcher matcher = pattern.matcher(path);

            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group(1));

                if (method.equals("GET")) {
                    Candidato candidatoProcurado = CandidatoDAO.listarCandidato(id);
                    if (candidatoProcurado != null) {
                        return toJson(candidatoProcurado);
                    } else {
                        return "{\"error\": \"Candidato not found\"}";
                    }
                }

                if (method.equals("PUT")) {
                    Candidato candidatoAtualizado = fromJson(body);
                    boolean updated = CandidatoDAO.atualizarCandidato(candidatoAtualizado,id);
                    return "{\"success\": " + updated + "}";
                }

                if (method.equals("DELETE")) {
                    boolean deleted = CandidatoDAO.removerCandidato(id);
                    return "{\"success\": " + deleted + "}";
                }
            }

            if (method.equals("OPTIONS")) {
                return ""; // Resposta vazia para preflight requests
            }

            return "{\"error\": \"Endpoint not found\"}";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }


    protected static String toJson(Candidato novoCandidato) {

        return String.format(
                "{\"id\": %d, \"nome\": \"%s\", \"sobrenome\": \"%s\", \"email\": \"%s\", \"cpf\": \"%s\", \"país\": \"%s\", \"cep\": \"%s\", \"data de nascimento\": \"%s\", \"descrição\": \"%s\"}",
                novoCandidato.getId(), novoCandidato.getNome(), novoCandidato.getSobrenome(),
                novoCandidato.getEmail(),novoCandidato.getCpf(),novoCandidato.getPaís(),novoCandidato.getCep(),
                novoCandidato.getDataNascimento(),novoCandidato.getDescricao()
        );
    }

    static String toJsonArray(List<Candidato> candidatosListados) {
        StringBuilder bufferString = new StringBuilder("[");
        for (int i = 0; i < candidatosListados.size(); i++) {
            bufferString.append(toJson(candidatosListados.get(i)));
            if (i < candidatosListados.size() - 1) {
                bufferString.append(",");
            }
        }
        bufferString.append("]");
        return bufferString.toString();
    }

    static Candidato fromJson(String json) {
        // Remove chaves e aspas, então parseia
        json = json.replaceAll("[{}\"]", "");
        String[] pairs = json.split(",");
        Candidato candidatoConvertidoJson = new Candidato();

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) continue;

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            try {
                switch (key) {
                    case "id":
                        candidatoConvertidoJson.setId(Integer.parseInt(value));
                        break;
                    case "nome":
                        candidatoConvertidoJson.setNome(value);
                        break;
                    case "sobrenome":
                        candidatoConvertidoJson.setSobrenome(value);
                        break;
                    case "email":
                        candidatoConvertidoJson.setEmail(value);
                        break;
                    case "cpf":
                        candidatoConvertidoJson.setCpf(value)
                        break
                    case "país":
                        candidatoConvertidoJson.setPaís(value);
                        break;
                    case "cep":
                        candidatoConvertidoJson.setCep(value);
                        break;
                    case "data de nascimento":
                        candidatoConvertidoJson.setDataNascimento(java.sql.Date.valueOf(value.replace("T", " ").split(" ")[0]));
                        break;
                    case "descrição":
                        candidatoConvertidoJson.setDescricao(value);
                        break;
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar campo " + key + ": " + e.getMessage());
            }
        }
        return candidatoConvertidoJson;
    }


}
