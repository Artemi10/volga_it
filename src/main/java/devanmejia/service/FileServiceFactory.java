package devanmejia.service;

import devanmejia.service.type.FileType;

import java.io.File;

public class FileServiceFactory {

    public static FileService createService(File file, FileType fileType){
        FileService fileService;
        // Another FileService implementations can be added
        switch (fileType){
            case HTML: fileService = new FileServiceExceptionLogger(new HTMLFileService(file));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fileType);
        }
        return fileService;
    }
}
