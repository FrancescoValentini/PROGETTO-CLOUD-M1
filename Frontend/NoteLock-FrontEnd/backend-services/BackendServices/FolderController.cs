
using backend_services.HTTP;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using System.Text;
namespace backend_services.BackendServices
{


    public class FolderController
    {
        private String BaseUrl;
        private String authToken;

        public FolderController(HttpClient httpClient, IConfiguration configuration)
        {
            if (configuration["BackendAPI:BaseUrl"] == null) throw new ArgumentNullException("BaseUrl is not configured in appsettings.json");
            BaseUrl = configuration["BackendAPI:BaseUrl"] + configuration["BackendAPI:Folders"];
        }

        public FolderController(String BaseUrl, String authToken)
        {
            this.BaseUrl = BaseUrl;
            this.authToken = authToken;
        }

        public void SetToken(String token)
        {
            this.authToken = token;
        }

        public List<FolderDTO> GetFolders()
        {
            string json = HTTPUtils.GET(authToken, BaseUrl);
            return JsonConvert.DeserializeObject<List<FolderDTO>>(json);
        }

        public String AddFolder(FolderDTO folder) {
            string result = HTTPUtils.POST(folder, authToken, this.BaseUrl);
            return result;
        }

        public String UpdateFolder(FolderDTO folder, String id) {
            string tmpUrl = this.BaseUrl + "/" + id;
            string result = HTTPUtils.PUT(folder, authToken, tmpUrl);
            return result;
        }

        public void DeleteFolder(String id) {
            string tmpUrl = this.BaseUrl + "/" + id;

            HTTPUtils.DELETE(authToken, tmpUrl);
        }


    }
}


