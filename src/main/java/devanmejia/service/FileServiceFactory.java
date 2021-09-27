package devanmejia.service;

import java.io.File;

public class FileServiceFactory {

    public static FileService createService(File file){
        return new FileServiceExceptionLogger(new HTMLFileService(file));
    }
}
