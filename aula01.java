import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class aula01{
    public static void main(String[] args) throws IOException, InterruptedException{

        // FAZER A CONEXÃO HTTP E BUSCAR O TOP 250 FILMES

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";   //crio uma string com a url que quero pegar os dados
        URI endereço = URI.create(url);     //crio um objeto do tipo URI que transforma o meu texto em URI 
        HttpClient client = HttpClient.newHttpClient();     //crio um objeto do tipo HttpClient 
        HttpRequest request = HttpRequest.newBuilder(endereço).GET().build();   //crio um objeto do tipo request para pegar o endereço e la de dentro dar um get(pegar) as informações (build)
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());  //crio um response no formato string e usando client.send passo os paramentros para ele ele pegar as informações da web e entender elas como string
        String body = response.body();    //crio uma variavel de texto e passo os parametros da web pra ela 


        // EXTRAIR OS DADOS DE INTERESSE (TITULO, IMAGEM,  CLASSIFICAÇÃO)

        JsonParse Parse = new JsonParse();
        List<Map<String, String>> listaDeFilmes = Parse.parse(body);
        

        //EXIBIR E MANIPULAR OS DADOS
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.print("\033[34mNome do filme:  \033[m");
            System.out.println(filme.get("title"));

            System.out.print("\033[4mlink da imagem:\033[m ");
            System.out.println(filme.get("image"));

            System.out.print("\033[35mClassificação: \033[m");
            System.out.println(filme.get("imDbRating"));

            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrilinha = (int) classificacao;

            System.out.print("\033[1mEstelas: \033[m");

            for(int n=1; n <= numeroEstrilinha; n++){
                System.out.print("\033[32;4;1m + \033[m");
            }

            System.out.println();
            System.out.println();
        }
    }
}