package devanmejia.service;

import devanmejia.model.Stats;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;


class FileServiceExceptionLogger implements FileService {
    private static final String FILE_NAME = "/logger.config";
    private static final Logger LOGGER;
    static {
        try(InputStream inputStream = FileServiceExceptionLogger.class.getResourceAsStream(FILE_NAME)){
            LogManager.getLogManager().readConfiguration(inputStream);
        }catch (IOException e){
            System.err.printf("Can not find %s file. Create standard logger\n", FILE_NAME);
        }
        LOGGER = Logger.getLogger(FileServiceExceptionLogger.class.getName());
    }

    private final FileService fileService;

    public FileServiceExceptionLogger(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Stats createWordsStatistic() throws IOException {
        try {
            return fileService.createWordsStatistic();
        }
        catch (IOException e){
            LOGGER.log(Level.WARNING, "IOException",  e);
            throw e;
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING, "Exception",  e);
            throw e;
        }
    }
}
