package com.example.nutypeset.controller;
import com.example.nutypeset.service.LatexFileService;
import com.example.nutypeset.model.entity.ResumeLatexFile;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("api/v1/pub/resume")
public class ResumeController {
    @Autowired
    private LatexFileService latexFileService;
    @PostMapping(value = "resumeGenerate")
    public void resumeGenerate(
            HttpServletResponse response,
            // @RequestBody(value = "fileContends", required = false) String fileContends
            @RequestBody(required = false) JSONObject fileContendsOBJ)
        //@RequestParam(value = "fileName", required = false) String fileName)
            throws IOException {
               ResumeLatexFile latexFile = new ResumeLatexFile();

               String name  = fileContendsOBJ.getAsString("name");
               String address  = fileContendsOBJ.getAsString("address");
               String phoneNumber  = fileContendsOBJ.getAsString("phoneNumber");
               String emailAddress  = fileContendsOBJ.getAsString("emailAddress");
               String linkedin  = fileContendsOBJ.getAsString("linkedin");
               String github = fileContendsOBJ.getAsString("github");
               String educationCode = fileContendsOBJ.getAsString("educationCode");
               String relevantCourseworkCode = fileContendsOBJ.getAsString("relevantCourseworkCode");
               String experienceCode  = fileContendsOBJ.getAsString("experienceCode");
               String  projectsCode  = fileContendsOBJ.getAsString("projectsCode");
               String  technicalSkillsCode  = fileContendsOBJ.getAsString("technicalSkillsCode");
               String  extracurricularCode  = fileContendsOBJ.getAsString("extracurricularCode");

               String webUserEmail = fileContendsOBJ.getAsString("webUserEmail");

               latexFile.setName(name);
               latexFile.setAddress(address);
               latexFile.setPhoneNumber(phoneNumber);
               latexFile.setEmailAddress(emailAddress);
               latexFile.setLinkedin(linkedin);
               latexFile.setGithub(github);
               latexFile.setEducationCode(educationCode);
               latexFile.setRelevantCourseworkCode(relevantCourseworkCode);
               latexFile.setExperienceCode(experienceCode);
               latexFile.setProjectsCode(projectsCode);
               latexFile.setTechnicalSkillsCode(technicalSkillsCode);
               latexFile.setExtracurricularCode(extracurricularCode);

               String fileName;
                char[] nameLis = name.toCharArray();
                for(int i =0; i < nameLis.length; i++) {
                    if(nameLis[i] == ' ') {
                        nameLis[i] = '_';
                    }
                }
                fileName = new String(nameLis) +"_CV";
                if (webUserEmail!=""){
                    fileName+=("_"+webUserEmail);
                }
                else{
                    fileName+=("_temp");
                }


                try {
                    //String order1 = "rm /temp/" + fileName + "*";
                    //String order1 = "rm " + System.getProperty("user.dir") + "/temp/" + fileName + "*";
                    String order1 = "find . -type f -name " + System.getProperty("user.dir") + "/temp/" + fileName + "\\* -exec rm {} \\;";

                    Process ps = Runtime.getRuntime().exec(order1);
                    ps.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Random r = new Random();
                double d1 = r.nextDouble();
                long randomInt=  Math.round(d1*1000);
                fileName = fileName +"_"+System.currentTimeMillis()+String.valueOf(randomInt);

                String fileContends = latexFile.returnLatexFile();
                boolean writeDone = latexFileService.fileWriter(System.getProperty("user.dir") + "/temp/" +fileName+ ".tex", fileContends);
                if (writeDone) {


                    String returnV = latexFileService.windowCompile(fileName);
                    JSONObject jsonReturnValues = new JSONObject();
                    jsonReturnValues.put("url",returnV);
                    jsonReturnValues.put("latexCode",fileContends);
                    //System.out.println(jsonReturnValues);
                    jsonReturnValues.put("latexFileName",fileName+=".tex");


                    response.getWriter().print(jsonReturnValues);
                }










                //System.out.println(educationCode);
                //System.out.println(relevantCourseworkCode);
                //System.out.println(experienceCode);


            }


}
