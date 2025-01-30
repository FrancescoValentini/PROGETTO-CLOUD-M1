
using backend_services.HTTP;
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

        return HTTPUtils.POST(l, BaseUrl);

    }

    public String Register(RegisterDTO r)
    {
        return HTTPUtils.POST(r, BaseUrl);
    }

    

}

