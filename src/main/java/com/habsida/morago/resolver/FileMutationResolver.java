package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.File;
import com.habsida.morago.serviceImpl.FileService;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.context.DefaultGraphQLServletContext;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import graphql.schema.DataFetchingEnvironment;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FileMutationResolver {
    private  FileService fileService;

//    @MutationMapping
//    public File addFile(@Argument Part part1 ) throws IOException, java.io.IOException {
//        DataFetchingEnvironment environment = null;
//        DefaultGraphQLServletContext context = environment.getContext();
//        context.getFileParts().forEach(
//                part -> System.out.println(part.getSubmittedFileName()+ part.getSubmittedFileName()+ part.getContentType()));
//        Part part = environment.getArgument("file");
//        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(),
//                part.getInputStream());
//        File file = fileService.uploadFile(mfile);
//        return file;
//    }

    @MutationMapping
    public File addFiled( Part part1,  DataFetchingEnvironment environment) throws IOException, java.io.IOException {
        Part part = environment.getArgument("file");
        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(),
                part.getInputStream());
        File file = fileService.uploadFile(mfile);
        return file;
    }
    @MutationMapping
    public File addFile() throws IOException, java.io.IOException {

        return null;
    }
    @MutationMapping
    public Boolean deleteFileById(@Argument Long id) {
        System.out.println("zaha");
        fileService.deleteById(id);
        return true;
    }
}
