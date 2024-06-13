package com.habsida.morago.resolver;

import com.habsida.morago.config.graphql.FileUpload;
import com.habsida.morago.serviceImpl.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class FileMutationResolver {
    private FileService fileService;

    //    @MutationMapping
//    public File addFile(@Argument Scalars file, DataFetchingEnvironment environment) throws IOException, java.io.IOException {
//        DefaultGraphQLServletContext context = environment.getContext();
//        context.getFileParts().forEach(part -> System.out.println(part.getSubmittedFileName() + part.getSubmittedFileName() + part.getContentType()));
//        Part part = environment.getArgument("file");
//        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
//        return fileService.uploadFile(mfile);
//    }
//@MutationMapping
//    public String addFiles(@Argument Scalars file) {
//       return "fileService.uploadFile(mfile)";
//    }
    @MutationMapping
    public Boolean addFile(@Argument FileUpload file) {

        String fileContentType = file.getContentType();
        byte[] fileContent = file.getContent();
//        MultipartFile mfile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
        return true;
    }
}
