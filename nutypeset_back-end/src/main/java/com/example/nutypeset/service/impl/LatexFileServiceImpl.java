package com.example.nutypeset.service.impl;

import com.example.nutypeset.mapper.WindowMapper;
import com.example.nutypeset.service.LatexFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.BufferedReader;
import java.net.URLEncoder;

@Service
public class LatexFileServiceImpl implements LatexFileService {

    @Autowired
    private WindowMapper windowMapper;

    @Override

    public String windowCompile(String fileName) {


        try {
            String shpath = "xelatex  -output-directory "+System.getProperty("user.dir")+"/temp " + System.getProperty("user.dir")+"/temp/"+fileName + ".tex";//fileName不包含后缀
            //String shpath = "xelatex  " + System.getProperty("user.dir")+"/temp/"+fileName + ".tex";//fileName不包含后缀
            Process ps = Runtime.getRuntime().exec(shpath);
            System.out.println("fileName: "+fileName);
            //ps.waitFor();
            System.out.println("fileName: "+fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String s = fileName + ".pdf";
        System.out.println(s);
        return s;


    }

    @Override
    public boolean test() {
        String fileName = "test1.tex";
        try {
            FileWriter writer = new FileWriter(fileName);

            writer.write("\\documentclass[12pt]{article}\n\\begin{document}\nHello world! \\\\ $Hello world!$ %math mode \n \\end{document}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                //String shpath = "xelatex  -output-directory ./temp test1.tex";
                String shpath = "xelatex   test1.tex";

                Process ps = Runtime.getRuntime().exec(shpath);
                ps.waitFor();
                BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Process ps = Runtime.getRuntime().exec("open test1.pdf");
                ps.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean fileWriter(String fileName, String fileContends){
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(fileContends);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void downloadFile(HttpServletResponse response, String path, String fileName) throws IOException {
        /** 将文件名称进行编码 */
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
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