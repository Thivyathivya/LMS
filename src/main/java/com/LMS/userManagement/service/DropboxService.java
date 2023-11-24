package com.LMS.userManagement.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.ListSharedLinksResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class DropboxService {

   // @Autowired
   //private DbxClientV2 dropboxClient;



   // @Value("${dropbox.accessToken}")
    private String accessToken="sl.BqtWHyUt_LHd81Ur7CmUz2r9HX3sXQ4ILpvEFdHF57NwV5D1w4XQ2NvC926X6jBL6aqC3AE0cjoIc2i_WuHyjxh3lSqXg0K_blmjwdf-qhUpvrpwJ6Fz02j8MxXlORbqn__6TF3yauIr0JLvqfoKihU";
  //  DbxRequestConfig config = DbxRequestConfig.newBuilder("Krays-LMS").build();

    public void uploadVideo(MultipartFile videoFile, String destinationPath) throws IOException {

        DbxRequestConfig config = DbxRequestConfig.newBuilder("Krays-LMS").build();
        try (InputStream inputStream = videoFile.getInputStream()) {
            DbxClientV2 client = new DbxClientV2(config, accessToken);
            client.files().uploadBuilder(destinationPath + "/" + videoFile.getOriginalFilename())
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date(System.currentTimeMillis()))
                    .uploadAndFinish(inputStream);
        } catch (UploadErrorException e) {
            throw new RuntimeException(e);
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }


    public String downloadFile(String filePath) throws IOException, DbxException {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("Krays-LMS").build();

        String path="/All files/Apps/Krays-LMS/Apps/Krays-LMS/";
        DbxClientV2 client = new DbxClientV2(config, accessToken);
        ListSharedLinksResult listSharedLinksResult = client.sharing().listSharedLinksBuilder().withPath(path).withDirectOnly(true).start();
        String result = listSharedLinksResult.getLinks().get(0).getUrl();
        return result;
    }
}
