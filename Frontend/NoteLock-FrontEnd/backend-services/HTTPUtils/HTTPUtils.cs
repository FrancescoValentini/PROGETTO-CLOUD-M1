using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace backend_services.HTTPUtils {
    public class HTTPUtils {
        public static String GET(String authToken, String BaseUrl) {
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
        
        public static String POST(object? data, String authToken, String BaseUrl) {
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
        
        
        public static String PUT(object? data, String authToken, String BaseUrl) {
            using (HttpClient client = new HttpClient()) {
                try {
                    // Aggiungi l'header Authorization con il token Bearer
                    client.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", authToken);

                    string json = JsonConvert.SerializeObject(data);
                    HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

                    // Invia la richiesta PUT
                    HttpResponseMessage response = client.PutAsync(BaseUrl, content).Result;

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
        public static void DELETE(String authToken, String BaseUrl) {
            using (HttpClient client = new HttpClient()) {
                try {
                    // Aggiungi l'header Authorization con il token Bearer
                    client.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", authToken);

                    // Invia la richiesta DELETE
                    HttpResponseMessage response = client.DeleteAsync(BaseUrl).Result;

                    // Controlla che la risposta sia stata positiva
                    response.EnsureSuccessStatusCode(); // Lancia un'eccezione se lo stato HTTP non è 2xx
                    
                } 
                catch (Exception ex)
                { 
                    Console.WriteLine($"Errore: {ex.Message}");
                   
                }
            }
        } 
    }
    
}
