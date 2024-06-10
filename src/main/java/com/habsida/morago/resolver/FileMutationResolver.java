package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.File;
import com.habsida.morago.serviceImpl.FileService;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.schema.DataFetchingEnvironment;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FileMutationResolver {
    private FileService fileService;

    @MutationMapping(name = "addFile")
    public File addFile(@Argument Part part1) throws IOException, java.io.IOException {
        DataFetchingEnvironment environment = null;
        DefaultGraphQLServletContext context = environment.getContext();
        context.getFileParts().forEach(
                part -> System.out.println(part.getSubmittedFileName() + part.getSubmittedFileName() + part.getContentType()));
        Part part = environment.getArgument("file");
        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(),
                part.getInputStream());
        File file = fileService.uploadFile(mfile);
        return file;
    }


//    public File addFile(@Argument Part part1,  DataFetchingEnvironment environment) throws IOException, java.io.IOException {
//        Part part = environment.getArgument("file");
//        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(),
//                part.getInputStream());
//        File file = fileService.uploadFile(mfile);
//        return file;
//    }

    @MutationMapping
    public Boolean deleteFileById(@Argument Long id) {
        fileService.deleteById(id);
        return true;
    }
}
