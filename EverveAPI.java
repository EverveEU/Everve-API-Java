import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EverveAPI {
    private String apiKey;
    private String baseUrl = "https://api.everve.net/v3/";

    public EverveAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public String makeRequest(String endpoint, String params) throws Exception {
        URL url = new URL(baseUrl + endpoint + "?api_key=" + apiKey + "&format=json" + params);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public String getUser() throws Exception {
        return makeRequest("user", "");
    }

    public String getSocials() throws Exception {
        return makeRequest("socials", "");
    }

    public String getCategories(String id) throws Exception {
        String endpoint = (id != null) ? "categories/" + id : "categories";
        return makeRequest(endpoint, "");
    }

    public String createOrder(String params) throws Exception {
        return makeRequest("orders", params);
    }

    public String getOrders(String id) throws Exception {
        String endpoint = (id != null) ? "orders/" + id : "orders";
        return makeRequest(endpoint, "");
    }

    public String updateOrder(String id, String params) throws Exception {
        return makeRequest("orders/" + id, params);
    }

    public String deleteOrder(String id) throws Exception {
        return makeRequest("orders/" + id, "&_method=DELETE");
    }

// EXAMPLE:
    public static void main(String[] args) {
        EverveAPI api = new EverveAPI("your_api_key_here");

        try {
            String userInfo = api.getUser();
            System.out.println("User Info: " + userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
