using Microsoft.AspNetCore.Components.Server.ProtectedBrowserStorage;

namespace NoteLock_FrontEnd.Utils;

public class TokenService
{
    private ProtectedLocalStorage Storage;

    public TokenService(ProtectedLocalStorage storage)
    {
        Storage = storage;
    }

    public async Task SetToken(string value)
    {
        await Storage.SetAsync("token", value);
    } 

    public async Task<string> GetToken()
    {
        var token = await Storage.GetAsync<string>("token");
        if (token.Success)
        {
            return token.Value;
        }
        else
        {
            return null;
        }
    }

    public async Task RemoveToken()
    {
        await Storage.DeleteAsync("token");
    }
}