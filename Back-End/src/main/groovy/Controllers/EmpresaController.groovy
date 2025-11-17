package Controllers

import DAO.EmpresaDAO
import Models.Candidato
import Models.Empresa
import Services.CandidatoService
import Services.EmpresaService

import java.util.regex.Matcher
import java.util.regex.Pattern

class EmpresaController {

    static void createEmpresa(){
        Empresa possivelEmpresaCriada = EmpresaService.criarEmpresa()
        try{
            if(possivelEmpresaCriada.getId() > 0)
                println("Empresa Cadastrada com sucesso")
        }catch (Exception excecao){
            println("Ocorreu um erro durante o cadastro")
        }

    }

    static void loginEmpresa(String email,String senha){
        EmpresaService.loginEmpresa(email, senha)
    }

    static String processRequest(String method, String path, String body) {
        try {

            //GET READ ALL
            if (path.equals("/empresas") && method.equals("GET")) {
                List<Empresa> empresas = EmpresaDAO.listarEmpresa();
                return toJsonArray(empresas);
            }



            // POST CREATE
            if (path.equals("/empresas") && method.equals("POST")) {
                Empresa novaEmpresa = fromJson(body)
                boolean create = EmpresaDAO.createEmpresa(novaEmpresa);
                if (create) {
                    return "{Empresa criada com sucesso!}" ;
                }else {
                    return "{Ocorreu um erro am criar uma Empresa!}" ;
                }


            }

            Pattern pattern = Pattern.compile("^/empresas/(\\d+)");
            Matcher matcher = pattern.matcher(path);

            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group(1));

                if (method.equals("GET")) {
                    Empresa empresaProcurada = EmpresaDAO.listarEmpresa(id);
                    if (empresaProcurada != null) {
                        return toJson(empresaProcurada);
                    } else {
                        return "{\"error\": \"Empresa not found\"}";
                    }
                }

                if (method.equals("PUT")) {
                    Empresa empresaAtualizado = fromJson(body);
                    boolean updated = EmpresaDAO.updateEmpresa(empresaAtualizado,id);
                    return "{\"success\": " + updated + "}";
                }

                if (method.equals("DELETE")) {
                    boolean deleted = EmpresaDAO.removerEmpresa(id);
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


    protected static String toJson(Empresa novaEmpresa) {

        return String.format(
                "{\"id\": %d, \"nome\": \"%s\", \"email\": \"%s\", \"cnpj\": \"%s\", \"país\": \"%s\", \"cep\": \"%s\", \"descrição\": \"%s\"}",
                novaEmpresa.getId(), novaEmpresa.getNome(),
                novaEmpresa.getEmail(),novaEmpresa.getCnpj(),novaEmpresa.getPais(),novaEmpresa.getCep(),
                novaEmpresa.getDescricao()
        );
    }

    static String toJsonArray(List<Empresa> empresasListadas) {
        StringBuilder bufferString = new StringBuilder("[");
        for (int i = 0; i < empresasListadas.size(); i++) {
            bufferString.append(toJson(empresasListadas.get(i)));
            if (i < empresasListadas.size() - 1) {
                bufferString.append(",");
            }
        }
        bufferString.append("]");
        return bufferString.toString();
    }

    static Empresa fromJson(String json) {
        // Remove chaves e aspas, então parseia
        json = json.replaceAll("[{}\"]", "");
        String[] pairs = json.split(",");
        Empresa empresaConvertidaJson = new Empresa();

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) continue;

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            try {
                switch (key) {
                    case "id":
                        empresaConvertidaJson.setId(Integer.parseInt(value));
                        break;
                    case "nome":
                        empresaConvertidaJson.setNome(value);
                        break;
                    case "email":
                        empresaConvertidaJson.setEmail(value);
                        break;
                    case "cnpj":
                        empresaConvertidaJson.setCnpj(value)
                        break
                    case "país":
                        empresaConvertidaJson.setPais(value);
                        break;
                    case "cep":
                        empresaConvertidaJson.setCep(value);
                        break;
                    case "descrição":
                        empresaConvertidaJson.setDescricao(value);
                        break;
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar campo " + key + ": " + e.getMessage());
            }
        }
        return empresaConvertidaJson;
    }

}
