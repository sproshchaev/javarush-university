package javarush.live;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import static java.nio.charset.StandardCharsets.UTF_8;

final class DemoHttpServer implements AutoCloseable {
    private final HttpServer server;

    DemoHttpServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/posts/1", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            reply(exchange, 200, "{\"id\":1,\"title\":\"HttpClient\"}");
        });
        server.createContext("/echo", exchange -> {
            byte[] body = exchange.getRequestBody().readAllBytes();
            String text = new String(body, UTF_8);
            reply(exchange, 200, exchange.getRequestMethod() + " | " + body.length + " bytes | "
                    + (text.isEmpty() ? "(empty)" : text));
        });
        server.createContext("/redirect", exchange -> {
            exchange.getResponseHeaders().set("Location", "/posts/1");
            reply(exchange, 302, "");
        });
        server.createContext("/slow", exchange -> {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                exchange.close();
                return;
            }
            reply(exchange, 200, "ready");
        });
        server.createContext("/cookies/set", exchange -> {
            exchange.getResponseHeaders().set("Set-Cookie", "demo=java; Path=/cookies");
            reply(exchange, 200, "cookie set");
        });
        server.createContext("/cookies/check", exchange -> {
            String cookie = exchange.getRequestHeaders().getFirst("Cookie");
            reply(exchange, 200, cookie == null ? "(no Cookie header)" : cookie);
        });
        server.start();
    }

    URI uri(String path) {
        return URI.create("http://127.0.0.1:" + server.getAddress().getPort() + path);
    }

    private static void reply(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(UTF_8);
        try (exchange) {
            exchange.sendResponseHeaders(status, bytes.length == 0 ? -1 : bytes.length);
            if (bytes.length > 0) {
                exchange.getResponseBody().write(bytes);
            }
        }
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
