package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.dto.FileDTO;
import com.habsida.morago.serviceImpl.FileService;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.context.DefaultGraphQLServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FileMutationResolver implements GraphQLMutationResolver {
    private final FileService fileService;

    public FileDTO addFile(DataFetchingEnvironment environment) throws IOException {
        DefaultGraphQLServletContext context = environment.getContext();
        context.getFileParts().forEach(part -> System.out.println(part.getSubmittedFileName() + "  " + part.getSubmittedFileName() + part.getContentType()));
        Part part = environment.getArgument("file");
        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
        return fileService.uploadFile(mfile);
    }
}
