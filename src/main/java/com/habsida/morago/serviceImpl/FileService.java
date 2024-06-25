package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.dto.FileDTO;
import com.habsida.morago.model.entity.File;
import com.habsida.morago.repository.FileRepository;
import com.habsida.morago.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository repository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;

    public FileDTO uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String filename = fileUtil.getUUID() + "." + type;
        String path = fileUtil.uploadFileToS3Bucket(filename, file);

        File fileDB = new File();
        fileDB.setPath(path);
        fileDB.setOriginalTitle(originalFilename);
        fileDB.setType(type);

        File savedFile = repository.save(fileDB);
        return modelMapper.map(savedFile, FileDTO.class);
    }

    public FileDTO getById(Long id) {
        File file = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File by this ID not found exception"));
        return modelMapper.map(file, FileDTO.class);
    }

    public void deleteById(Long id) {
        File file = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File by this ID not found exception"));
        fileUtil.deleteFileFromS3Bucket(file.getPath());
        repository.deleteById(id);
    }
}
