using backend_services.HTTP;
using backend_services.Models;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;

namespace backend_services.BackendServices;

public class WhoamiController
{
    private String BaseUrl;
    private IConfiguration configuration;
    public WhoamiController(HttpClient httpClient, IConfiguration configuration)
    {
        this.configuration = configuration;
        if (configuration["BackendAPI:BaseUrl"] == null) throw new ArgumentNullException("BaseUrl is not configured in appsettings.json");
        BaseUrl = configuration["BackendAPI:BaseUrl"];
    }

    public UserAccount Whoami(String token)
    {
        String WhoamiUrl = this.BaseUrl + this.configuration["BackendAPI:Whoami"];
        string json = HTTPUtils.GET(token, WhoamiUrl);
        return JsonConvert.DeserializeObject<UserAccount>(json);
    }
    
}