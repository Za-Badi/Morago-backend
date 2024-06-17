package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.config.graphql.FileUpload;
import com.habsida.morago.model.entity.File;
import com.habsida.morago.serviceImpl.FileService;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.context.DefaultGraphQLServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;

@Component
public class FileMutationResolver implements GraphQLMutationResolver {
    private FileService fileService;

    public FileMutationResolver(FileService fileService) {
        this.fileService = fileService;
    }

    public File addFile(Part part1, DataFetchingEnvironment environment) throws IOException, java.io.IOException {
        DefaultGraphQLServletContext context = environment.getContext();
        context.getFileParts().forEach(part -> System.out.println(part.getSubmittedFileName() + "  " + part.getSubmittedFileName() + part.getContentType()));
        Part part = environment.getArgument("file");
        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
        return fileService.uploadFile(mfile);
    }
//@MutationMapping
//    public String addFiles(@Argument Scalars file) {
//       return "fileService.uploadFile(mfile)";
//    }
//    public Boolean addFile(Part part1, DataFetchingEnvironment environment) throws IOException {
//
//
//        Part part = environment.getArgument("file");
//        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
//
//        File file = fileService.uploadFile(mfile);
//        return true;
//    }
}
