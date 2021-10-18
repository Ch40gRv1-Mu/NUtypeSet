package com.example.nutypeset.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LatexFileService {
    public  String windowCompile(String fileName);
    public boolean fileWriter(String fileName, String fileContends);
    public boolean test();
    public void downloadFile(HttpServletResponse response, String path, String fileName) throws IOException;
}
