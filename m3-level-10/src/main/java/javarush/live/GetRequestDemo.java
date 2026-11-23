package javarush.live;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class GetRequestDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        try (DemoHttpServer server = new DemoHttpServer()) {

            HttpRequest request = HttpRequest.newBuilder(server.uri("/posts/1"))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            System.out.println(request.method() + " " + request.uri().getPath() +
                    " -> " + response.statusCode());
            System.out.println("Requested: " + request.version().orElseThrow()
            + ", actual: " + response.version());
            System.out.println(response.body());

        }

    }

}
