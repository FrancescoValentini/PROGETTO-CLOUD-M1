
using backend_services.HTTP;
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

        public String AddFolder(FolderDTO folder) {
            string result = HTTPUtils.POST(folder, authToken, this.BaseUrl);
            return result;
        }

        public String UpdateFolder(FolderDTO folder, String id) {
            string tmpUrl = this.BaseUrl + "/" + id;
            string result = HTTPUtils.PUT(folder, authToken, this.BaseUrl);
            return result;
        }

        public void DeleteFolder(String id) {
            string tmpUrl = this.BaseUrl + "/" + id;

            HTTPUtils.DELETE(authToken, tmpUrl);
        }


    }
}


