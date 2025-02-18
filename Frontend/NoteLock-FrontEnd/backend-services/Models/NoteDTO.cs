namespace backend_services.Models;

public class NoteDTO
{
    public string folderId { get; set; }
    public string subject { get; set; }
    public string body { get; set; }
    public string encrypted { get; set; }
}