using backend_services.HTTP;
using backend_services.Models;
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

    public List<Note> GetNotes()
    {
        string json = HTTPUtils.GET(authToken, BaseUrl);
        return JsonConvert.DeserializeObject<List<Note>>(json);
    }

    public String AddNote(NoteDTO note) {
        string result = HTTPUtils.POST(note, authToken, this.BaseUrl);
        return result;
    }

    public String UpdateNote(NoteDTO note, String id) {
        string tmpUrl = this.BaseUrl + "/" + id;
        string result = HTTPUtils.PUT(note, authToken, tmpUrl);
        return result;
    }

    public void DeleteNote(String id) {
        string tmpUrl = this.BaseUrl + "/" + id;

        HTTPUtils.DELETE(authToken, tmpUrl);
    }
}