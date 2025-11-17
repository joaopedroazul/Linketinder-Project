package Api

import Controllers.VagaController
import DAO.CandidatoDAO
import Models.Candidato
import Controllers.CandidatoController
import Controllers.EmpresaController
import Controllers.VagaController
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class Routes implements Runnable {
    private final Socket clientSocket;

    public Routes(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Ler a requisição
            String requestLine = in.readLine();
            if (requestLine == null) return;

            System.out.println("Requisição: " + requestLine);

            String[] requestParts = requestLine.split(" ");
            String method = requestParts[0];
            String path = requestParts[1];

            // Ler headers
            String line;
            int contentLength = 0;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.substring("Content-Length:".length()).trim());
                }
            }

            // Ler body se existir
            StringBuilder body = new StringBuilder();
            if (contentLength > 0) {
                char[] buffer = new char[contentLength];
                int bytesRead = in.read(buffer, 0, contentLength);
                if (bytesRead > 0) {
                    body.append(buffer, 0, bytesRead);
                }
            }
            // Processar a requisição
            String response

            switch (path){
                case "/candidatos":
                    response = CandidatoController.processRequest(method, path, body.toString());
                    break
                case "/empresas":
                    response = EmpresaController.processRequest(method, path, body.toString());
                    break
                case "/vagas":
                    response = VagaController.processRequest(method, path, body.toString());
                    break
                default:
                    switch (path[1..-1].tokenize('/')[0]){
                        case "candidatos":
                            response = CandidatoController.processRequest(method, path, body.toString());
                            break
                        case "empresas":
                            response = EmpresaController.processRequest(method, path, body.toString());
                            break
                        case "vagas":
                            response = VagaController.processRequest(method, path, body.toString());
                            break
                    }
            }

            // Enviar resposta
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json");
            out.println("Access-Control-Allow-Origin: *");
            out.println("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
            out.println("Access-Control-Allow-Headers: Content-Type");
            out.println();
            out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
