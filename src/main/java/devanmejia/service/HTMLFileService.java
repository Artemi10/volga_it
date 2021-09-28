package devanmejia.service;

import devanmejia.service.parser.HTMLLineParser;

import java.io.File;

class HTMLFileService extends AbstractFileService {

    public HTMLFileService(File htmlFile) {
        super(htmlFile, new HTMLLineParser(), "</.*?>");
    }
}
