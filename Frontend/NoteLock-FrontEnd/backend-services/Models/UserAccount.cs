namespace backend_services.Models;

public class UserAccount
{
    public string Id { get; set; }
    public string Nome { get; set; }
    public string Cognome { get; set; }
    public string Username { get; set; }
    public string Email { get; set; }
    public List<Folder> Cartelle { get; set; }
    public List<Authority> Authorities { get; set; }
    public bool Enabled { get; set; }
    public bool AccountNonExpired { get; set; }
    public bool CredentialsNonExpired { get; set; }
    public bool AccountNonLocked { get; set; }
}