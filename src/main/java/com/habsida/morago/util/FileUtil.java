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
    @Value("${local.path.static}")
    private String localPath;


    public String getUUID() {
        //UUID without dashed
        return UUID.randomUUID().toString().replace("-", "");
    }
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
