import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

/**
 * Minimal Cosmic SMS send example (Java 17+, no dependencies).
 * Run: java SmsApi.java USERNAME API_PASSWORD 447700900701
 */
public class SmsApi {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage: java SmsApi.java USERNAME API_PASSWORD TARGET_MOBILE");
            System.exit(1);
        }
        String username = args[0], apiPassword = args[1], target = args[2];

        String xml = """
        <?xml version="1.0" encoding="UTF-8"?>
        <sendsms>
          <sender>CosmicSMS</sender>
          <message>Hello from Cosmic SMS!</message>
          <mobiles>
            <mobile>%s</mobile>
          </mobiles>
        </sendsms>""".formatted(target);

        String auth = Base64.getEncoder()
            .encodeToString((username + ":" + apiPassword).getBytes());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.cosmicsms.com/prod/rest/sms/send"))
            .header("Content-Type", "application/xml")
            .header("Authorization", "Basic " + auth)
            .POST(HttpRequest.BodyPublishers.ofString(xml))
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
