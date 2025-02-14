namespace backend_services.Models;

public class NoteDTO
{
    public string FolderId { get; set; }
    public string Subject { get; set; }
    public string Body { get; set; }
    public string Encrypted { get; set; }
}