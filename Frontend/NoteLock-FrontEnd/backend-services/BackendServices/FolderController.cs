
using Newtonsoft.Json;
using System.Text;
namespace backend_services.BackendServices
{


    public class FolderController
    {
        private String BaseUrl;
        private String authToken;

        public FolderController(String BaseUrl, String authToken)
        {
            this.BaseUrl = BaseUrl;
            this.authToken = authToken;
        }

        public List<FolderDTO> GetFolders()
        {
            string json = HTTPUtils.GET(authToken, BaseUrl);
            return JsonConvert.DeserializeObject<List<FolderDTO>>(json);
        }


    }
}


