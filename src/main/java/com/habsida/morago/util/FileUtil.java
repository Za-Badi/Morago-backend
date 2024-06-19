package com.habsida.morago.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUtil {
    private final AmazonS3 s3client;

    @Value("${amazon.s3.endpoint-url}")
    private String endpointUrl;
    @Value("${amazon.s3.bucket-name}")
    private String bucketName;
    @Value("${local.path.static}")
    private String localPath;

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //    AWS S3 file upload
    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException ex) {
            System.out.print(ex.toString());
        }
        return convertedFile;
    }

    public String uploadFileToS3Bucket(String filename, MultipartFile file) {
        File convertedFile = null;
        try {
            convertedFile = convertMultipartToFile(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        s3client.putObject(new PutObjectRequest(bucketName, filename, convertedFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return getPathFromS3(filename);
    }

    public String uploadFile(MultipartFile multipartFile, String filename) {
        try {
            File file = convertMultipartToFile(multipartFile);
            uploadFileToS3Bucket(filename, multipartFile);
            file.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filename;
    }

    public String getPathFromS3(String pathDB) {
        return bucketName + "." + endpointUrl + "/" + pathDB;
    }

    public void deleteFileFromS3Bucket(String fileUrl) {
        String filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, filename));
    }

    //    Local file upload
    public String getLocalPath(String pathFromDB) {
        return localPath + pathFromDB;
    }

    public String uploadFileLocal(MultipartFile file, String hashFilename) {
        File targetFile = null;
        try {
            targetFile = new File(localPath);
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

    public byte[] downloadFileFromS3Bucket(String fileName) {
        S3Object s3Object = s3client.getObject(bucketName, fileName);
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(s3ObjectInputStream);
        } catch (IOException ex) {
        }
        return null;
    }
}
