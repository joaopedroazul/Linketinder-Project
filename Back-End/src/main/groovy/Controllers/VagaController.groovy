package Controllers

import DAO.VagaDAO
import Models.Candidato
import Models.Vaga

import java.util.regex.Matcher
import java.util.regex.Pattern

class VagaController {

    static String processRequest(String method, String path, String body) {
        try {

            //GET READ ALL
            if (path.equals("/vagas") && method.equals("GET")) {
                List<Vaga> vagas = VagaDAO.listarVaga();
                return toJsonArray(vagas);
            }

            // POST CREATE
            if (path.equals("/vagas") && method.equals("POST")) {
                Vaga novaVaga = fromJson(body)
                boolean create = VagaDAO.createVaga(novaVaga);
                if (create) {
                    return "{Vaga criado com sucesso!}" ;
                }else {
                    return "{Ocorreu um erro am criar uma vaga!}" ;
                }


            }

            Pattern pattern = Pattern.compile("^/vagas/(\\d+)");
            Matcher matcher = pattern.matcher(path);

            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group(1));

                if (method.equals("GET")) {
                    Vaga vagaProcurada = VagaDAO.listarVaga(id);
                    if (vagaProcurada != null) {
                        return toJson(vagaProcurada);
                    } else {
                        return "{\"error\": \"Vaga not found\"}";
                    }
                }

                if (method.equals("PUT")) {
                    Vaga vagaAtualizada = fromJson(body);
                    boolean updated = VagaDAO.updateVaga(vagaAtualizada,id);
                    return "{\"success\": " + updated + "}";
                }

                if (method.equals("DELETE")) {
                    boolean deleted = VagaDAO.removerVaga(id);
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


    protected static String toJson(Vaga novaVaga) {

        return String.format(
                "{\"id\": %d, \"nome\": \"%s\", \"cidade\": \"%s\", \"estado\": \"%s\", \"empresa\": \"%s\", \"descrição\": \"%s\"}",
                novaVaga.getId(), novaVaga.getNome(), novaVaga.getCidade_id(),
                novaVaga.getEstado_id(),novaVaga.getEmpresa_id(),novaVaga.getDescricao()
        );
    }

    static String toJsonArray(List<Vaga> vagasListadas) {
        StringBuilder bufferString = new StringBuilder("[");
        for (int i = 0; i < vagasListadas.size(); i++) {
            bufferString.append(toJson(vagasListadas.get(i)));
            if (i < vagasListadas.size() - 1) {
                bufferString.append(",");
            }
        }
        bufferString.append("]");
        return bufferString.toString();
    }

    static Vaga fromJson(String json) {
        // Remove chaves e aspas, então parseia
        json = json.replaceAll("[{}\"]", "");
        String[] pairs = json.split(",");
        Vaga vagaConvertidaJson = new Vaga();

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) continue;

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            try {
                switch (key) {
                    case "id":
                        vagaConvertidaJson.setId(Integer.parseInt(value));
                        break;
                    case "nome":
                        vagaConvertidaJson.setNome(value);
                        break;
                    case "cidade":
                        vagaConvertidaJson.setCidade_id(Integer.parseInt(value));
                        break;
                    case "estado":
                        vagaConvertidaJson.setEstado_id(Integer.parseInt(value));
                        break;
                    case "empresa":
                        vagaConvertidaJson.setEmpresa_id(Integer.parseInt(value))
                        break
                    case "descrição":
                        vagaConvertidaJson.setDescricao(value);
                        break;
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar campo " + key + ": " + e.getMessage());
            }
        }
        return vagaConvertidaJson;
    }
}
