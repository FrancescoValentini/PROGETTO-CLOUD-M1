namespace backend_services.Models;

public class Note
{
    public string Id { get; set; }
    public DateTime Timestamp { get; set; }
    public string Title { get; set; }
    public string Body { get; set; }
    public Folder Cartella { get; set; }
    public string Encrypted { get; set; }
}