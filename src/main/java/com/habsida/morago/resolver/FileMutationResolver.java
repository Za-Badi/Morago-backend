package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.File;
import com.habsida.morago.serviceImpl.FileService;
import graphql.Scalars;
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

@Controller
@RequiredArgsConstructor
public class FileMutationResolver {
    private FileService fileService;

    @MutationMapping
    public File addFile(@Argument Scalars file, DataFetchingEnvironment environment) throws IOException, java.io.IOException {
        DefaultGraphQLServletContext context = environment.getContext();
        context.getFileParts().forEach(part -> System.out.println(part.getSubmittedFileName() + part.getSubmittedFileName() + part.getContentType()));
        Part part = environment.getArgument("file");
        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
        return fileService.uploadFile(mfile);
    }
}
