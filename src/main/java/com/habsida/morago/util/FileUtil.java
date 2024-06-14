package com.habsida.morago.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUtil {
    /*    private final AmazonS3 s3client;

        @Value("${amazon.s3.endpoint-url}")
        private String endpointUrl;
        @Value("${amazon.s3.bucket-name}")
        private String bucketName;*/
    @Value("${local.path.static}")
    private String localPath;

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

//    AWS S3 file upload
   /* private File convertMultipartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    private void uploadFileToS3Bucket(String filename, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, filename, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile, String filename) {
        try {
            File file = convertMultipartToFile(multipartFile);
            uploadFileToS3Bucket(filename, file);
            file.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filename;
    }
    public String getPathFromS3(String pathDB) {
        return endpointUrl + pathDB;
    }
    public void deleteFileFromS3Bucket(String fileUrl) {
        String filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, filename));
    }*/

    //    Local file upload
    public String getLocalPath(String pathFromDB) {
        return localPath + pathFromDB;
    }

    public String uploadFileLocal(MultipartFile file, String hashFilename) {
        try {
            File targetFile = new File(localPath);

            if (targetFile.exists()) {
                targetFile.mkdirs();
            }
            localPath += hashFilename;

            FileOutputStream out = new FileOutputStream(localPath);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashFilename;
    }

    public void deleteLocalFile(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
