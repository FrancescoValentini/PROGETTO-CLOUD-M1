
using Newtonsoft.Json;
using System.Text;

public class AuthController
{
    private String BaseUrl;

    public AuthController(String BaseUrl)
    {
        this.BaseUrl = BaseUrl;
    }

    public void Register(string email, string password, string username, string name, string surname)
    {

    }

    public String Login(LoginDTO l)
    {
        return PerformPOSTrequest(l);

    }

    public String Register(RegisterDTO r)
    {
        return PerformPOSTrequest(r);
    }

    private String PerformPOSTrequest(object ?data)
    {
        using (HttpClient client = new HttpClient())
        {
            try
            {
                string json = JsonConvert.SerializeObject(data);
                HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

                HttpResponseMessage response = client.PostAsync(BaseUrl, content).Result;

                response.EnsureSuccessStatusCode(); // Lancia un'eccezione se lo stato HTTP non è 2xx

                string responseBody = response.Content.ReadAsStringAsync().Result;
                return responseBody;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Errore: {ex.Message}");
                return null;
            }
        }
    }

}

