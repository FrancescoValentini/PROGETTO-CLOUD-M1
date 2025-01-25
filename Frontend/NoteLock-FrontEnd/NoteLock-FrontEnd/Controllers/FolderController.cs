
using Newtonsoft.Json;
using System.Text;

public class FolderController {
    private String BaseUrl;
    private String authToken;

    public FolderController(String BaseUrl, String authToken) {
        this.BaseUrl = BaseUrl;
        this.authToken = authToken;
    }


    public List<FolderDTO> GetFolders() {
        string json = PerformGETrequest(authToken);
        return JsonConvert.DeserializeObject<List<FolderDTO>>(json);
    }

    private String PerformGETrequest(String authToken) {
        using (HttpClient client = new HttpClient()) {
            try {
                // Aggiungi l'header Authorization con il token Bearer
                client.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", authToken);

                // Invia la richiesta GET
                HttpResponseMessage response = client.GetAsync(BaseUrl).Result;

                // Controlla che la risposta sia stata positiva
                response.EnsureSuccessStatusCode(); // Lancia un'eccezione se lo stato HTTP non è 2xx

                // Leggi il corpo della risposta
                string responseBody = response.Content.ReadAsStringAsync().Result;
                return responseBody;
            } catch (Exception ex) {
                Console.WriteLine($"Errore: {ex.Message}");
                return null;
            }
        }
    }


    private String PerformPOSTrequest(object? data, String authToken) {
        using (HttpClient client = new HttpClient()) {
            try {
                // Aggiungi l'header Authorization con il token Bearer
                client.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", authToken);

                string json = JsonConvert.SerializeObject(data);
                HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

                // Invia la richiesta POST
                HttpResponseMessage response = client.PostAsync(BaseUrl, content).Result;

                // Controlla che la risposta sia stata positiva
                response.EnsureSuccessStatusCode(); // Lancia un'eccezione se lo stato HTTP non è 2xx

                // Leggi il corpo della risposta
                string responseBody = response.Content.ReadAsStringAsync().Result;
                return responseBody;
            } catch (Exception ex) {
                Console.WriteLine($"Errore: {ex.Message}");
                return null;
            }
        }
    }

}

