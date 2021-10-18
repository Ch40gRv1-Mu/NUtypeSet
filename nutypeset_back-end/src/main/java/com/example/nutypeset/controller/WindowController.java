package com.example.nutypeset.controller;

import com.example.nutypeset.model.entity.LatexFile;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Random;

import com.example.nutypeset.service.LatexFileService;
//用来对文本框请求交接
@Controller
@RequestMapping("api/v1/pub/window")
public class WindowController {
    @Autowired
    private LatexFileService latexFileService;
    
    @GetMapping("test")
    public void test(HttpServletResponse response) throws IOException {
        //  windowService.test();
        System.out.println(System.getProperty("user.dir"));
        //response.getWriter().print("Let Me try");
    }

    @PostMapping(value = "latexOneCompile")
    public void latexOneCompile(
            HttpServletResponse response,
            // @RequestBody(value = "fileContends", required = false) String fileContends
            @RequestBody(required = false) JSONObject fileContendsOBJ)
        //@RequestParam(value = "fileName", required = false) String fileName)
            throws IOException {
        String webUserEmail = fileContendsOBJ.getAsString("webUserEmail");
        String fileName ="";
        if (webUserEmail!=""){
            fileName=webUserEmail;
        }
        else{
            fileName="temp";

        }
                try {
                    //String order1 = "rm /temp/" + fileName + "*";
                    String order1 = "rm " + System.getProperty("user.dir") + "/temp/" + fileName + "*";

                    Process ps = Runtime.getRuntime().exec(order1);
                    ps.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }

        Random r = new Random();
        double d1 = r.nextDouble();
        long randomInt=  Math.round(d1*10000000);
        fileName = fileName+ String.valueOf(randomInt);
        System.out.println(fileName);
        System.out.println(fileContendsOBJ);




        //    String fileName="temp";
        // JSONObject fileContendsOBJ= new JSONObject(fileContends);
        String fileContends = fileContendsOBJ.getAsString("fileContends");
        System.out.println(fileContends);
        boolean writeDone =
                latexFileService.fileWriter(System.getProperty("user.dir") + "/temp/" + fileName+".tex", fileContends);

        if (writeDone) {
            String returnV = latexFileService.windowCompile(fileName);
            System.out.println(returnV);
            JSONObject jsonReturnValues = new JSONObject();
            jsonReturnValues.put("url",returnV);
            jsonReturnValues.put("latexFileName",fileName+=".tex");
            jsonReturnValues.put("latexCode",fileContends);
            response.getWriter().print(jsonReturnValues);

        }




    }

    @PostMapping("submitPartialCodeAndCompile")
    public String submitPartialCodeAndCompile(
            HttpServletResponse response,
            @RequestBody(required = false) JSONObject fileContendsOBJ) throws IOException {
        LatexFile latexFile = new LatexFile();
        String title = fileContendsOBJ.getAsString("title");
        String author = fileContendsOBJ.getAsString("author");
        String header = fileContendsOBJ.getAsString("header");
        String packages = fileContendsOBJ.getAsString("packages");
        String codeMiddle = fileContendsOBJ.getAsString("codeMiddle");
        String fileName = "temp";
        fileName = fileContendsOBJ.getAsString("fileName");
        if(!(title == "")) latexFile.setTitle(title);
        if(!(author == "")) latexFile.setAuthor(author);
        if(!(header == "")) latexFile.setAdditionalHeader(header);
        if (!(packages =="")) latexFile.setAdditionalPackets(packages);
        if (!(codeMiddle =="")) latexFile.setCodeMiddle(codeMiddle);
        System.out.println(codeMiddle+"----------\n");
        try {
            //String order1 = "rm /temp/" + fileName + "*";
            //find . -type f -name /Users/mcr/IdeaProjects/nutypeset_back-end/temp/temp\* -exec rm {} \;
            String order1 = "find . -type f -name " + System.getProperty("user.dir") + "/temp/" + fileName + "\\* -exec rm {} \\;";

            Process ps = Runtime.getRuntime().exec(order1);
            ps.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileContends = latexFile.returnLatexFile();
        boolean writeDone =
                latexFileService.fileWriter(System.getProperty("user.dir") + "/temp/" +fileName+ ".tex", fileContends);

        if (writeDone) {
            String returnV = latexFileService.windowCompile(fileName);
            System.out.println(returnV);
        }
        String returnValue = "/temp/" + fileName + ".pdf";

        return returnValue;









    }

    @RequestMapping("/download/file")
    public void downloadTemplate(HttpServletResponse response, @RequestParam("name") String fileName)
            throws IOException {
        String path =System.getProperty("user.dir")+"/temp/" + fileName;
        System.out.println(path);
        downloadFile(response, path, fileName);
    }

    private void downloadFile(HttpServletResponse response, String path, String fileName)
            throws IOException {
        /** 将文件名称进行编码 */
        System.out.println(path);
        response.setHeader(
                "content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("content-type:octet-stream");
        /** 读取服务器端模板文件 */
        InputStream inputStream = this.getClass().getResourceAsStream(path);

        /** 将流中内容写出去 */
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();

    }
}
