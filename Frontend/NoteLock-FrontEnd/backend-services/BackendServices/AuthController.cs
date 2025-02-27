
using backend_services.HTTP;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using System.Text;

public class AuthController
{
    private String BaseUrl;
    private IConfiguration configuration;

    public AuthController(String BaseUrl)
    {
        this.BaseUrl = BaseUrl;
    }

    public AuthController(HttpClient httpClient, IConfiguration configuration)
    {
        this.configuration = configuration;
        if (configuration["BackendAPI:BaseUrl"] == null) throw new ArgumentNullException("BaseUrl is not configured in appsettings.json");
        BaseUrl = configuration["BackendAPI:BaseUrl"];
    }

    public void Register(string email, string password, string username, string name, string surname)
    {

    }

    public String Login(LoginDTO l)
    {
        String LoginUrl = this.BaseUrl + this.configuration["BackendAPI:Login"];
        return HTTPUtils.POST(l, LoginUrl);

    }

    public String Register(RegisterDTO r)
    {
        String RegisterUrl = this.BaseUrl + this.configuration["BackendAPI:Register"];
        return HTTPUtils.POST(r, RegisterUrl);
    }


}


