package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.File;
import com.habsida.morago.repository.FileRepository;
import com.habsida.morago.util.FileUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository repository;
    private final FileUtil fileUtil;

    public File uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        String type = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String filename = fileUtil.getUUID() + "." + type;
        String path = fileUtil.uploadFileLocal(file, filename);
        File fileDB = new File();
        fileDB.setPath(path);
        fileDB.setOriginalTitle(originalFilename);
        fileDB.setType(type);
        return repository.save(fileDB);
    }

    public File getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("File by this ID not found exception"));
    }

    public void deleteById(Long id) {
        File file = getById(id);
        System.out.println(file.toString());
        fileUtil.deleteLocalFile(file.getPath());
        repository.deleteById(id);
    }

}
