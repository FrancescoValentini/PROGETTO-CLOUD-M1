using backend_services.HTTP;
using backend_services.Models;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;

namespace backend_services.BackendServices;

public class NoteController
{
    private String BaseUrl;
    private String authToken;

    public NoteController(String BaseUrl, String authToken)
    {
        this.BaseUrl = BaseUrl;
        this.authToken = authToken;
    }

    public NoteController(HttpClient httpClient, IConfiguration configuration)
    {
        if(configuration["BackendAPI:BaseUrl"] == null) throw new ArgumentNullException("BaseUrl is not configured in appsettings.json");
        BaseUrl = configuration["BackendAPI:BaseUrl"] + configuration["BackendAPI:Notes"];
    }

    public void SetToken(String token) {
        authToken = token;
    }

    public List<Note> GetNotes()
    {
        string json = HTTPUtils.GET(authToken, BaseUrl);
        return JsonConvert.DeserializeObject<List<Note>>(json);
    }

    public Note GetNote(string noteID) {
        string json = HTTPUtils.GET(authToken, BaseUrl + "/" + noteID);
        return JsonConvert.DeserializeObject<Note>(json);
    }

    public String AddNote(NoteDTO note) {
        string result = HTTPUtils.POST(note, authToken, this.BaseUrl);
        return result;
    }

    public String UpdateNote(NoteDTO note, String id) {
        string tmpUrl = this.BaseUrl + "/" + id;
        string result = HTTPUtils.PUT(note, authToken, tmpUrl);
        Console.WriteLine(id);
        Console.WriteLine(note.subject);
        Console.WriteLine(tmpUrl);
        return result;
    }

    public void DeleteNote(String id) {
        string tmpUrl = this.BaseUrl + "/" + id;

        HTTPUtils.DELETE(authToken, tmpUrl);
    }
}