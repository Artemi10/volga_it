package devanmejia.service;

import devanmejia.model.Stats;

import java.io.IOException;

public interface FileService {
    Stats getWordsStatistic() throws IOException;
}
