package com.example.nutypeset.model.entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.Map;

public class ResumeLatexFile {

    //header
    String name = "NutypeSet Nu";
    String address = "27 Prince George's Park, National University of Singapore, Singapore 118425";
    String phoneNumber = "88888888";
    String emailAddress = "nutypeset@u.nus.edu";
    String linkedin = "";
    String github = "";
    String educationCode = "";
    String relevantCourseworkCode = "";
    String experienceCode = "";
    String projectsCode = "";
    String technicalSkillsCode = "";
    String extracurricularCode = "";


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    public void setRelevantCourseworkCode(String relevantCourseworkCode) {
        this.relevantCourseworkCode = relevantCourseworkCode;
    }

    public void setExperienceCode(String experienceCode) {
        this.experienceCode = experienceCode;
    }

    public void setProjectsCode(String projectsCode) {
        this.projectsCode = projectsCode;
    }

    public void setTechnicalSkillsCode(String technicalSkillsCode) {
        this.technicalSkillsCode = technicalSkillsCode;
    }

    public void setExtracurricularCode(String extracurricularCode) {
        this.extracurricularCode = extracurricularCode;
    }

    /*

    private LinkedList<String> packets;

    {
        packets = new LinkedList();
    }

    private Map<String,String> setlength;
    private Map<String,String> addtolength;
    private LinkedList<Window> windows;



    @Autowired
    public resumeLatexFile(){
        try {
            System.out.print("Start Generate CV");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
  */



    public String returnLatexFile(){

        String latexFile = "\\documentclass[letterpaper,11pt]{article}\n\n\\usepackage{latexsym}\n\\usepackage[empty]{fullpage}\n\\usepackage{titlesec}\n\\usepackage{marvosym}\n\\usepackage[usenames,dvipsnames]{color}\n\\usepackage{verbatim}\n\\usepackage{enumitem}\n\\usepackage[hidelinks]{hyperref}\n\\usepackage{fancyhdr}\n\\usepackage[english]{babel}\n\\usepackage{tabularx}\n\\usepackage{fontawesome5}\n\\usepackage{multicol}\n\\setlength{\\multicolsep}{-3.0pt}\n\\setlength{\\columnsep}{-1pt}\n%\\input{glyphtounicode}\n\n\n%----------FONT OPTIONS----------\n% sans-serif\n% \\usepackage[sfdefault]{FiraSans}\n% \\usepackage[sfdefault]{roboto}\n% \\usepackage[sfdefault]{noto-sans}\n% \\usepackage[default]{sourcesanspro}\n\n% serif\n% \\usepackage{CormorantGaramond}\n% \\usepackage{charter}\n\n\n\\pagestyle{fancy}\n\\fancyhf{} % clear all header and footer fields\n\\fancyfoot{}\n\\renewcommand{\\headrulewidth}{0pt}\n\\renewcommand{\\footrulewidth}{0pt}\n\n% Adjust margins\n\\addtolength{\\oddsidemargin}{-0.6in}\n\\addtolength{\\evensidemargin}{-0.5in}\n\\addtolength{\\textwidth}{1.19in}\n\\addtolength{\\topmargin}{-.7in}\n\\addtolength{\\textheight}{1.4in}\n\n\\urlstyle{same}\n\n\\raggedbottom\n\\raggedright\n\\setlength{\\tabcolsep}{0in}\n\n% Sections formatting\n\\titleformat{\\section}{\n  \\vspace{-4pt}\\scshape\\raggedright\\large\\bfseries\n}{}{0em}{}[\\color{black}\\titlerule \\vspace{-5pt}]\n\n% Ensure that generate pdf is machine readable/ATS parsable\n%\\pdfgentounicode=1\n\n%-------------------------\n% Custom commands\n\\newcommand{\\resumeItem}[1]{\n  \\item\\small{\n    {#1 \\vspace{-2pt}}\n  }\n}\n\n\\newcommand{\\classesList}[4]{\n    \\item\\small{\n        {#1 #2 #3 #4 \\vspace{-2pt}}\n  }\n}\n\n\\newcommand{\\resumeSubheading}[4]{\n  \\vspace{-2pt}\\item\n    \\begin{tabular*}{1.0\\textwidth}[t]{l@{\\extracolsep{\\fill}}r}\n      \\textbf{#1} & \\textbf{\\small #2} \\\\\n      \\textit{\\small#3} & \\textit{\\small #4} \\\\\n    \\end{tabular*}\\vspace{-7pt}\n}\n\n\\newcommand{\\resumeSubSubheading}[2]{\n    \\item\n    \\begin{tabular*}{0.97\\textwidth}{l@{\\extracolsep{\\fill}}r}\n      \\textit{\\small#1} & \\textit{\\small #2} \\\\\n    \\end{tabular*}\\vspace{-7pt}\n}\n\n\\newcommand{\\resumeProjectHeading}[2]{\n    \\item\n    \\begin{tabular*}{1.001\\textwidth}{l@{\\extracolsep{\\fill}}r}\n      \\small#1 & \\textbf{\\small #2}\\\\\n    \\end{tabular*}\\vspace{-7pt}\n}\n\n\\newcommand{\\resumeSubItem}[1]{\\resumeItem{#1}\\vspace{-4pt}}\n\n\\renewcommand\\labelitemi{$\\vcenter{\\hbox{\\tiny$\\bullet$}}$}\n\\renewcommand\\labelitemii{$\\vcenter{\\hbox{\\tiny$\\bullet$}}$}\n\n\\newcommand{\\resumeSubHeadingListStart}{\\begin{itemize}[leftmargin=0.0in, label={}]}\n\\newcommand{\\resumeSubHeadingListEnd}{\\end{itemize}}\n\\newcommand{\\resumeItemListStart}{\\begin{itemize}}\n\\newcommand{\\resumeItemListEnd}{\\end{itemize}\\vspace{-5pt}}\n\n%-------------------------------------------\n%%%%%%  RESUME STARTS HERE  %%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n\n\\begin{document}\n\n";

        /* *****************
         *       Title       *
          ********* ********/

        //name
        latexFile += "\n\\begin{center}\n    {\\Huge \\scshape ";
        //String nameFirstLast = this.firstName +" "+ this.lastName;
        latexFile += (this.name+"} \\\\ \\vspace{1pt}\n    ");

        //address
        latexFile += (this.address+" \\\\ \n \\vspace{1pt}\n\\small \\raisebox{-0.1\\height}");


        //phoneNumber
        latexFile +=  ("\\faPhone\\ "+this.phoneNumber);

        //emailAddress
        latexFile += (" ~ "+"\\href{mailto:"+this.emailAddress+"}{\\raisebox{-0.2\\height}\\faEnvelope\\  \\underline{"+this.emailAddress+"}}\n");

        //linkedin
        if (this.linkedin != "") latexFile += (" ~   \\href{https://linkedin.com/in//}{\\raisebox{-0.2\\height}\\faLinkedin\\ \\underline{"+this.linkedin +"}}\n");

        //github
        if (this.github != "") latexFile += (" ~ \\href{https://github.com/}{\\raisebox{-0.2\\height}\\faGithub\\ \\underline{"+this.github+"}}\n");

        latexFile +=("    \\vspace{-8pt}\n\\end{center}\n\n\n");


        latexFile += this.educationCode;

        latexFile += this.relevantCourseworkCode;

        latexFile += this.experienceCode;

        latexFile += this.projectsCode;

        latexFile += this.technicalSkillsCode;

        latexFile += this.extracurricularCode;



        //end of document
        latexFile += ("\\end{document}\n");


        return latexFile;

    }
}
