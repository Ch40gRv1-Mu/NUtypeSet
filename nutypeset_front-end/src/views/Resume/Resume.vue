
<template>

  <div id="Resume">
    <div id="wrap">
      <div class="n2">
      <button class="b5" @click="goIndex">back</button>
    </div>
        <div id="edit">

          <div id="logo" @click="goIndex">
      <img alt="Vue logo" src="./assets/logo.png">
      
   </div> 
            <div id="nav">
                <!--<button class="b1" ><h1>Add Input</h1></button>-->
                <button class="b1" v-on:click="send" >Compile</button>
                <button class="b3" v-on:click="downloadLatex">Download Latex File</button>
                <button class="b1" v-if="getToken !== ''" @click="save">Save</button>
                <button class="b4" @click="goEditor">Switch to Editor</button>

                 <el-switch v-model="showPDF" ></el-switch>              
              </div>
            <div id="application">
                <div class="inputArea">
                 

                    <div class="custparam">
                        <div id="selection">
                            <select class="select" data-placeholder="Please select section" v-model="window_type">
                                <option disabled value="">Please select section</option>
                                <option>basic information</option>
                                <option>education</option>
                                <option>relevant coursework</option>
                                <option>experience</option>
                                <option>projects</option>
                                <option>techincal skills</option>
                                <option>leadership / extra curricular</option>
                            </select>
                        </div>
                    </div>
                  <div class="basic_information" v-show="window_type=='basic information'">
                     <br>
                    <!-- name -->
                    <p> Name: </p>
                    <textarea v-show="window_type=='basic information'" class="input" placeholder="Nutypeset Nu" id="s1" v-model="name"></textarea>
                    <br>
                    <br>
                    <!-- address -->
                    <div> Address: </div>
                    <textarea rows="1" cols="100" v-show="window_type=='basic information'" class="input" id="s1" placeholder="27 Prince George's Park, National University of Singapore, Singapore 118425" v-model="address"></textarea>
                    <br>
                    <br>
                    <!-- emailAddress -->
                    <div> Email Address: </div>
                    <textarea rows="1" cols="100" v-show="window_type=='basic information'" class="input" id="s1" placeholder="nutypeset@u.nus.edu" v-model="emailAddress"></textarea>
                    <br>
                    <br>
                    <!-- Phone Number -->
                    <div> Phone Number: </div>
                    <textarea rows="1" cols="50" v-show="window_type=='basic information'" class="input" id="s2" placeholder="88888888" v-model="phoneNumber"></textarea>
                    <br>
                    <br>
                    <!-- linkedin -->
                    <div> Linkedin: </div>
                    <textarea rows="1" cols="100" v-show="window_type=='basic information'" class="input" id="s1" placeholder="optional" v-model="linkedin"></textarea>
                    <br>
                    <br>
                    <!-- github -->
                    <div> Github: </div>
                    <textarea rows="1" cols="100" v-show="window_type=='basic information'" class="input" id="s1" placeholder="optional" v-model="github"></textarea>
                  </div>


                 <!-- education -->
                  <div class = "education" v-show="window_type=='education'">
                    <education @educationCode="educationCode = arguments[0]"></education>
                  </div>

                  <!-- relevant coursework -->
                  <div class = "relevantCoursework" v-show="window_type=='relevant coursework'">
                    <relevantCoursework @relevantCourseworkCode="relevantCourseworkCode= arguments[0]"></relevantCoursework>
                  </div>

                  <!-- experience -->
                  <div class = "experience" v-show="window_type=='experience'">
                    <experience @experienceCode="experienceCode = arguments[0]"></experience>
                  </div>

                  <!-- projects -->
                  <div class = "projects" v-show="window_type=='projects'">
                    <projects @projectsCode="projectsCode = arguments[0]"></projects>
                  </div>

                  <!-- techincal skills -->
                  <div class = "techincal skills" v-show="window_type=='techincal skills'">
                    <technical-skills @technicalSkillsCode="technicalSkillsCode = arguments[0]"></technical-skills>
                  </div>


                  <!-- Leadership / Extracurricular} -->
                  <div class = "leadership / extra curricular" v-show="window_type=='leadership / extra curricular'">
                    <extracurricular @extracurricularCode="extracurricularCode = arguments[0]"></extracurricular>
                  </div>


                    <!--<button class="b2"><h1>Delete</h1></button>-->
                    

                </div>

            </div>
            
        </div>
        <div class="preview"  v-if="showPDF==true">
          <pre>
            <iframe :src="pdfsrc" v-if="pdfUpdate==true" framework="0" width=100% height="1000px" >
            </iframe> 
          </pre>
        </div>

        <div class="preview2" v-if="showPDF==false">
           <pre v-highlightjs="LaTex">
             <code class="latex" v-text="latexCode">
             </code>
          </pre>
        </div>

    </div>   
  </div>

</template>
<script src="prism.js"></script>
<script>
import { api1, api2 } from "@/api/http.js";
import Education from "@/components/Education.vue"
import RelevantCoursework from '@/components/RelevantCoursework.vue'
import Experience from '@/components/Experience.vue'
import Projects from '@/components/Projects.vue'
import TechnicalSkills from '@/components/TechnicalSkills.vue'
import Extracurricular from '@/components/Extracurricular.vue'
export default {
  name: "Resume",
  components: {
    Education,
    RelevantCoursework,
    Experience,
    Projects,
    TechnicalSkills,
    Extracurricular


  },
  data() {
    return {
      window_type:"basic information",
      pdfsrc: this.$store.state.pdfsrc || "http://localhost:8080/temp/Nutypeset_CV.pdf",
      pdfOrLatex:"pdf",
      showPDF: true,
      showLatex: false,
      pdfUpdate: true,

      //basic information
      name:"Nutypeset",
    address:"27 Prince George's Park, National University of Singapore, Singapore 118425",
    phoneNumber:"8888",
    emailAddress:"nutypeset@u.nus.edu",
    linkedin:"",
    github:"",
    latexSrc: this.$store.state.latexSrc ||  "http://localhost:8080/temp/Nutypeset_CV.tex",
      educationCode:"%-----------EDUCATION-----------\n\\section{Education}\n  \\resumeSubHeadingListStart\n    \\resumeSubheading\n      {National University of Singapore}{Sep. 2024 -- June 2028}\n      {Ph.D (information security)}{Singapore, Singapore}\n  \\resumeSubHeadingListEnd\n  \\resumeSubHeadingListStart\n    \\resumeSubheading\n      {National University of Singapore}{Sep. 2020 -- May 2024}\n      {Bachelor of Computting(information security)}{Singapore, Singapore}\n  \\resumeSubHeadingListEnd\n\n",
      relevantCourseworkCode:"%------RELEVANT COURSEWORK-------\n\\section{Relevant Coursework}\n    %\\resumeSubHeadingListStart\n        \\begin{multicols}{4}\n            \\begin{itemize}[itemsep=-5pt, parsep=3pt]\n                \\item Data Structures\n                \\item Software Methodology\n                \\item Algorithms Analysis\n                \\item Database Management\n                \\item Artificial Intelligence\n                \\item Internet Technology\n                \\item Systems Programming\n                \\item Computer Architecture\n            \\end{itemize}\n        \\end{multicols}\n        \\vspace*{2.0\\multicolsep}\n    %\\resumeSubHeadingListEnd\n\n\n\n",
      experienceCode:"%-----------EXPERIENCE-----------\n\\section{Experience}\n  \\resumeSubHeadingListStart\n\n    \\resumeSubheading\n      {Cisco}{June 2021 -- September 2021}\n      {Software Engineer Intern}{Singapore, Singapore}\n      \\resumeItemListStart\n        \\resumeItem{Developed a crawler to automatically crawl huge number of blogs online for big data analysis.}\n        \\resumeItem{Developed a sentiment anaylysis based on NLP for searching.}\n        \\resumeItem{Incorporated scripts using Python and PowerShell to aggregate XML test results into an organized format and to load the latest build code onto the hardware, so that daily testing can be performed.}\n        \\resumeItem{Implemented Selenium to create an instance of Chrome in order to interact with the correct elements of the web page.}\n      \\resumeItemListEnd\n\n    \\resumeSubheading\n      {National University of Singapore}{Januanry 2021 -- June 2021}\n      {Student Researcher}{Singapore, Singapore}\n      \\resumeItemListStart\n        \\resumeItem{Developed a crawler to automatically crawl huge number of blogs online for big data analysis.}\n        \\resumeItem{Developed a sentiment anaylysis based on NLP for searching.}\n        \\resumeItem{Incorporated scripts using Python and PowerShell to aggregate XML test results into an organized format and to load the latest build code onto the hardware, so that daily testing can be performed.}\n        \\resumeItem{Implemented Selenium to create an instance of Chrome in order to interact with the correct elements of the web page.}\n      \\resumeItemListEnd\n    \n  \\resumeSubHeadingListEnd\n\\vspace{-16pt}\n\n",
      projectsCode:"%-----------PROJECTS-----------\n\\section{Projects}\n    \\vspace{-5pt}\n    \\resumeSubHeadingListStart\n      \\resumeProjectHeading\n          {\\textbf{Gym Reservation Bot} $|$ \\emph{SpringBoot, Mybatis, Mysql, Vue.js, Latex}}{Septemter 2020 -- June 2024}\n       \\resumeItemListStart\n        \\resumeItem{Developed a crawler to automatically crawl huge number of blogs online for big data analysis}\n        \\resumeItem{Implemented Selenium to create an instance of Chrome in order to interact with the correct elements of the web page.}\n        \\resumeItem{Created a Linux virtual machine to run on Google Cloud so that the program is able to run everyday from the cloud.}\n        \\resumeItem{Used Cron to schedule the program to execute automatically at 11 AM every morning so a reservation is made for me.}\n\n      \\resumeItemListEnd\n      \\vspace{-13pt}\n      \\resumeProjectHeading\n          {\\textbf{Ticket Price Calculator App} $|$ \\emph{Java, Android Studio}}{November 2020}\n          \\resumeItemListStart\n            \\resumeItem{Created an Android application using Java and Android Studio to calculate ticket prices for trips to museums in NYC.}\n            \\resumeItem{Processed user inputted information in the back-end of the app to return a subtotal price based on the tickets selected.}\n            \\resumeItem{Utilized the layout editor to create a UI for the application in order to allow different scenes to interact with each other.}\n          \\resumeItemListEnd \n          \\vspace{-13pt}\n          \\resumeProjectHeading\n          {\\textbf{Transaction Management GUI} $|$ \\emph{Java, Eclipse, JavaFX}}{October 2020}\n          \\resumeItemListStart\n            \\resumeItem{Designed a sample banking transaction system using Java to simulate the common functions of using a bank account.}\n            \\resumeItem{Used JavaFX to create a GUI that supports actions such as creating an account, deposit, withdraw, list all acounts, etc.}\n            \\resumeItem{Implemented object-oriented programming practices such as inheritance to create different account types and databases.}\n          \\resumeItemListEnd\n    \\resumeSubHeadingListEnd\n\\vspace{-15pt}\n\n\n",
      technicalSkillsCode:"%-----------PROGRAMMING SKILLS-----------\n\\section{Technical Skills}\n \\begin{itemize}[leftmargin=0.15in, label={}]\n    \\small{\\item{\n     \\textbf{Languages}{: Python, Java, C, HTML/CSS, JavaScript, SQL} \\\\\n     \\textbf{Developer Tools}{: VS Code, Eclipse, Google Cloud Platform, Android Studio} \\\\\n     \\textbf{Technologies/Frameworks}{: Linux, Jenkins, GitHub, JUnit, WordPress} \\\\\n     \\textbf{Technologies/Frameworks}{: Linux, Jenkins, GitHub, JUnit, WordPress} \\\\\n    }}\n \\end{itemize}\n \\vspace{-16pt}\n\n\n",
      extracurricularCode:"%-----------INVOLVEMENT---------------\n\\section{Leadership / Extracurricular}\n    \\resumeSubHeadingListStart\n        \\resumeSubheading{National University of Singapore Chinese Society}{Septemter 2020 -- June 2021}{Vice President}{Singapore, Singapore}\n            \\resumeItemListStart\n                \\resumeItem{Achieved a 4 star fraternity ranking by the Office of Fraternity andSorority Affairs (highest possible ranking).}\n                \\resumeItem{Managed executive board of 5 members and ran weekly meetings to oversee progress in essential parts of the chapter.}\n                \\resumeItem{Led chapter of 30+ members to work towards goals that improve and promote community service, academics, and unity.}\n            \\resumeItemListEnd\n        \n    \\resumeSubHeadingListEnd\n\n\n",
      latexCode :"\\documentclass[letterpaper,11pt]{article}\n\n\\usepackage{latexsym}\n\\usepackage[empty]{fullpage}\n\\usepackage{titlesec}\n\\usepackage{marvosym}\n\\usepackage[usenames,dvipsnames]{color}\n\\usepackage{verbatim}\n\\usepackage{enumitem}\n\\usepackage[hidelinks]{hyperref}\n\\usepackage{fancyhdr}\n\\usepackage[english]{babel}\n\\usepackage{tabularx}\n\\usepackage{fontawesome5}\n\\usepackage{multicol}\n\\setlength{\\multicolsep}{-3.0pt}\n\\setlength{\\columnsep}{-1pt}\n%\\input{glyphtounicode}\n\n\n%----------FONT OPTIONS----------\n% sans-serif\n% \\usepackage[sfdefault]{FiraSans}\n% \\usepackage[sfdefault]{roboto}\n% \\usepackage[sfdefault]{noto-sans}\n% \\usepackage[default]{sourcesanspro}\n\n% serif\n% \\usepackage{CormorantGaramond}\n% \\usepackage{charter}\n\n\n\\pagestyle{fancy}\n\\fancyhf{} % clear all header and footer fields\n\\fancyfoot{}\n\\renewcommand{\\headrulewidth}{0pt}\n\\renewcommand{\\footrulewidth}{0pt}\n\n% Adjust margins\n\\addtolength{\\oddsidemargin}{-0.6in}\n\\addtolength{\\evensidemargin}{-0.5in}\n\\addtolength{\\textwidth}{1.19in}\n\\addtolength{\\topmargin}{-.7in}\n\\addtolength{\\textheight}{1.4in}\n\n\\urlstyle{same}\n\n\\raggedbottom\n\\raggedright\n\\setlength{\\tabcolsep}{0in}\n\n% Sections formatting\n\\titleformat{\\section}{\n  \\vspace{-4pt}\\scshape\\raggedright\\large\\bfseries\n}{}{0em}{}[\\color{black}\\titlerule \\vspace{-5pt}]\n\n% Ensure that generate pdf is machine readable/ATS parsable\n%\\pdfgentounicode=1\n\n%-------------------------\n% Custom commands\n\\newcommand{\\resumeItem}[1]{\n  \\item\\small{\n    {#1 \\vspace{-2pt}}\n  }\n}\n\n\\newcommand{\\classesList}[4]{\n    \\item\\small{\n        {#1 #2 #3 #4 \\vspace{-2pt}}\n  }\n}\n\n\\newcommand{\\resumeSubheading}[4]{\n  \\vspace{-2pt}\\item\n    \\begin{tabular*}{1.0\\textwidth}[t]{l@{\\extracolsep{\\fill}}r}\n      \\textbf{#1} & \\textbf{\\small #2} \\\\\n      \\textit{\\small#3} & \\textit{\\small #4} \\\\\n    \\end{tabular*}\\vspace{-7pt}\n}\n\n\\newcommand{\\resumeSubSubheading}[2]{\n    \\item\n    \\begin{tabular*}{0.97\\textwidth}{l@{\\extracolsep{\\fill}}r}\n      \\textit{\\small#1} & \\textit{\\small #2} \\\\\n    \\end{tabular*}\\vspace{-7pt}\n}\n\n\\newcommand{\\resumeProjectHeading}[2]{\n    \\item\n    \\begin{tabular*}{1.001\\textwidth}{l@{\\extracolsep{\\fill}}r}\n      \\small#1 & \\textbf{\\small #2}\\\\\n    \\end{tabular*}\\vspace{-7pt}\n}\n\n\\newcommand{\\resumeSubItem}[1]{\\resumeItem{#1}\\vspace{-4pt}}\n\n\\renewcommand\\labelitemi{$\\vcenter{\\hbox{\\tiny$\\bullet$}}$}\n\\renewcommand\\labelitemii{$\\vcenter{\\hbox{\\tiny$\\bullet$}}$}\n\n\\newcommand{\\resumeSubHeadingListStart}{\\begin{itemize}[leftmargin=0.0in, label={}]}\n\\newcommand{\\resumeSubHeadingListEnd}{\\end{itemize}}\n\\newcommand{\\resumeItemListStart}{\\begin{itemize}}\n\\newcommand{\\resumeItemListEnd}{\\end{itemize}\\vspace{-5pt}}\n\n%-------------------------------------------\n%%%%%%  RESUME STARTS HERE  %%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n\n\\begin{document}\n\n\n\\begin{center}\n    {\\Huge \\scshape Nutypeset} \\\\ \\vspace{1pt}\n    27 Prince George's Park, National University of Singapore, Singapore 118425 \\\\ \n \\vspace{1pt}\n\\small \\raisebox{-0.1\\height}\\faPhone\\ 8888 ~ \\href{mailto:nutypeset@u.nus.edu}{\\raisebox{-0.2\\height}\\faEnvelope\\  \\underline{nutypeset@u.nus.edu}}\n    \\vspace{-8pt}\n\\end{center}\n\n\n%-----------EDUCATION-----------\n\\section{Education}\n  \\resumeSubHeadingListStart\n    \\resumeSubheading\n      {National University of Singapore}{Sep. 2024 -- June 2028}\n      {Ph.D (information security)}{Singapore, Singapore}\n  \\resumeSubHeadingListEnd\n  \\resumeSubHeadingListStart\n    \\resumeSubheading\n      {National University of Singapore}{Sep. 2020 -- May 2024}\n      {Bachelor of Computting(information security)}{Singapore, Singapore}\n  \\resumeSubHeadingListEnd\n\n%------RELEVANT COURSEWORK-------\n\\section{Relevant Coursework}\n    %\\resumeSubHeadingListStart\n        \\begin{multicols}{4}\n            \\begin{itemize}[itemsep=-5pt, parsep=3pt]\n                \\item Data Structures\n                \\item Software Methodology\n                \\item Algorithms Analysis\n                \\item Database Management\n                \\item Artificial Intelligence\n                \\item Internet Technology\n                \\item Systems Programming\n                \\item Computer Architecture\n            \\end{itemize}\n        \\end{multicols}\n        \\vspace*{2.0\\multicolsep}\n    %\\resumeSubHeadingListEnd\n\n\n\n%-----------EXPERIENCE-----------\n\\section{Experience}\n  \\resumeSubHeadingListStart\n\n    \\resumeSubheading\n      {Cisco}{June 2021 -- September 2021}\n      {Software Engineer Intern}{Singapore, Singapore}\n      \\resumeItemListStart\n        \\resumeItem{Developed a crawler to automatically crawl huge number of blogs online for big data analysis.}\n        \\resumeItem{Developed a sentiment anaylysis based on NLP for searching.}\n        \\resumeItem{Incorporated scripts using Python and PowerShell to aggregate XML test results into an organized format and to load the latest build code onto the hardware, so that daily testing can be performed.}\n        \\resumeItem{Implemented Selenium to create an instance of Chrome in order to interact with the correct elements of the web page.}\n      \\resumeItemListEnd\n\n    \\resumeSubheading\n      {National University of Singapore}{Januanry 2021 -- June 2021}\n      {Student Researcher}{Singapore, Singapore}\n      \\resumeItemListStart\n        \\resumeItem{Developed a crawler to automatically crawl huge number of blogs online for big data analysis.}\n        \\resumeItem{Developed a sentiment anaylysis based on NLP for searching.}\n        \\resumeItem{Incorporated scripts using Python and PowerShell to aggregate XML test results into an organized format and to load the latest build code onto the hardware, so that daily testing can be performed.}\n        \\resumeItem{Implemented Selenium to create an instance of Chrome in order to interact with the correct elements of the web page.}\n      \\resumeItemListEnd\n    \n  \\resumeSubHeadingListEnd\n\\vspace{-16pt}\n\n%-----------PROJECTS-----------\n\\section{Projects}\n    \\vspace{-5pt}\n    \\resumeSubHeadingListStart\n      \\resumeProjectHeading\n          {\\textbf{Gym Reservation Bot} $|$ \\emph{SpringBoot, Mybatis, Mysql, Vue.js, Latex}}{Septemter 2020 -- June 2024}\n       \\resumeItemListStart\n        \\resumeItem{Developed a crawler to automatically crawl huge number of blogs online for big data analysis}\n        \\resumeItem{Implemented Selenium to create an instance of Chrome in order to interact with the correct elements of the web page.}\n        \\resumeItem{Created a Linux virtual machine to run on Google Cloud so that the program is able to run everyday from the cloud.}\n        \\resumeItem{Used Cron to schedule the program to execute automatically at 11 AM every morning so a reservation is made for me.}\n\n      \\resumeItemListEnd\n      \\vspace{-13pt}\n      \\resumeProjectHeading\n          {\\textbf{Ticket Price Calculator App} $|$ \\emph{Java, Android Studio}}{November 2020}\n          \\resumeItemListStart\n            \\resumeItem{Created an Android application using Java and Android Studio to calculate ticket prices for trips to museums in NYC.}\n            \\resumeItem{Processed user inputted information in the back-end of the app to return a subtotal price based on the tickets selected.}\n            \\resumeItem{Utilized the layout editor to create a UI for the application in order to allow different scenes to interact with each other.}\n          \\resumeItemListEnd \n          \\vspace{-13pt}\n          \\resumeProjectHeading\n          {\\textbf{Transaction Management GUI} $|$ \\emph{Java, Eclipse, JavaFX}}{October 2020}\n          \\resumeItemListStart\n            \\resumeItem{Designed a sample banking transaction system using Java to simulate the common functions of using a bank account.}\n            \\resumeItem{Used JavaFX to create a GUI that supports actions such as creating an account, deposit, withdraw, list all acounts, etc.}\n            \\resumeItem{Implemented object-oriented programming practices such as inheritance to create different account types and databases.}\n          \\resumeItemListEnd\n    \\resumeSubHeadingListEnd\n\\vspace{-15pt}\n\n\n%-----------PROGRAMMING SKILLS-----------\n\\section{Technical Skills}\n \\begin{itemize}[leftmargin=0.15in, label={}]\n    \\small{\\item{\n     \\textbf{Languages}{: Python, Java, C, HTML/CSS, JavaScript, SQL} \\\\\n     \\textbf{Developer Tools}{: VS Code, Eclipse, Google Cloud Platform, Android Studio} \\\\\n     \\textbf{Technologies/Frameworks}{: Linux, Jenkins, GitHub, JUnit, WordPress} \\\\\n     \\textbf{Technologies/Frameworks}{: Linux, Jenkins, GitHub, JUnit, WordPress} \\\\\n    }}\n \\end{itemize}\n \\vspace{-16pt}\n\n\n%-----------INVOLVEMENT---------------\n\\section{Leadership / Extracurricular}\n    \\resumeSubHeadingListStart\n        \\resumeSubheading{National University of Singapore Chinese Society}{Septemter 2020 -- June 2021}{Vice President}{Singapore, Singapore}\n            \\resumeItemListStart\n                \\resumeItem{Achieved a 4 star fraternity ranking by the Office of Fraternity andSorority Affairs (highest possible ranking).}\n                \\resumeItem{Managed executive board of 5 members and ran weekly meetings to oversee progress in essential parts of the chapter.}\n                \\resumeItem{Led chapter of 30+ members to work towards goals that improve and promote community service, academics, and unity.}\n            \\resumeItemListEnd\n        \n    \\resumeSubHeadingListEnd\n\n\n\\end{document}\n"
       };
  },
  computed: {
    getToken() {
      return this.$store.state.token;
    }
  },
  created() {
    api1({
      type: 0,
      sort: 1,
    })
      .then((res) => {
        // success
        //console.log(res);
      })
      .catch((error) => {
        // error
        //console.log(error);
      });
    api2({})
      .then((res) => {
        // success
        //console.log(res);
      })
      .catch((error) => {
        // error
        //console.log(error);
      });
  },
  methods: {
    
      
    send:function () {
    var data = {};
    data.name = this.name;
    data.address = this.address;
    data.phoneNumber = this.phoneNumber;
    data.emailAddress = this.emailAddress;
    data.linkedin = this.linkedin;
    data.github = this.github;
    data.educationCode = this.educationCode;
    data.relevantCourseworkCode = this.relevantCourseworkCode;
    data.experienceCode = this.experienceCode;
    data.projectsCode = this.projectsCode;
    data.technicalSkillsCode = this.technicalSkillsCode;
    data.extracurricularCode = this.extracurricularCode;
    if(this.$store.state.token!==""){
      data.webUserEmail=this.$store.state.webUserEmail;
    }
    else{
      data.webUserEmail="";
    }
    api2 (data)
    .then((res) => {
        // success
        //console.log(res);
        this.pdfsrc = "http://localhost:8080/temp/"+res.url;
        this.latexSrc = "http://localhost:8080/temp/"+res.latexFileName;
        this.latexCode = res.latexCode;
        localStorage.setItem('pdfsrc',this.pdfsrc);
        this.$store.dispatch('set_Pdfsrc',this.pdfsrc);
        localStorage.setItem('latexSrc',this.latexSrc);
        this.$store.dispatch('set_Latexsrc',this.latexSrc);
        //this.$store.dispatch('clearLatexfinalcode');
        localStorage.setItem('latexFinalCode',this.latexCode);
         this.$store.dispatch('set_Latexfinalcode',this.latexCode);
          
         ////console.log(this.$store.state.pdfsrc)
      })
      .catch((error) => {
        // error
        //console.log(error);
      });
     
      
      this.pdfUpdate = false;
      this.$nextTick(() => {
            this.pdfUpdate = true;
      }) 
      this.pdfUpdate = false;
      this.$nextTick(() => {
            this.pdfUpdate = true;
      })  
       //console.log(this.latexCode);
      console.log(this.$store.state.latexFinalCode);
      console.log(this.pdfsrc);
       console.log(this.$store.state.pdfsrc);
    
    },
    
    save: function () {
      let param = {};
      param.latexFinalCode = this.$store.state.latexFinalCode;
      param.pdfsrc = this.$store.state.pdfsrc;
      //console.log(this.$store.state.pdfsrc)
      this.$axios({
        url: "http://localhost:8080/api/v1/pri/user/update_pdfsrc",
        method: "post",
        data: param,
        params: {'token':this.$store.state.token }

      }).then((res) => {
        //console.log(res)
        if(res.data.code==0){
            const toast = this.$createToast({
              txt: "Save successfully",
              type: "success",
              time: 1500
            });
            toast.show();
        }
        else{
            const toast = this.$createToast({
              txt: "Save fail",
              type: "error",
              time: 1500
            });
            toast.show();

        }
      });
    },

    downloadLatex(){

        //    this.$axios({
       // url: "http://localhost:8080/temp/"+this.latexFileName,
       // method: "get",
     // }).then((res) => {
        ////console.log("已经执行");
       // //console.log(res);
       // this.data = res.data;
       //latexFileName
       window.location.href = this.latexSrc;
       console.log(this.latexSrc);
      //});

    },

    onChange(checked) {
      //console.log(`a-switch to ${checked}`);
    },
    goEditor:function(){
           this.$router.push({path: '/editor'})
           
       },
    goIndex:function(){
           this.$router.push({path: '/index'})
          
       },
      
  },
};
</script>


<style lang="scss" scoped>
#wrap{
  width: 100%;
  height: 100%;
  margin-top: -130px;
}
#logo{
  margin-top: 5px;
}
.preview{
  position: fixed;
  float: right;
  width: 50%;
  margin-right: -10px;
  margin-left: 50%;
  margin-top: 0px;
}
.preview2{
  float: right;
  /*position: fixed;*/
  width: 50%;
  margin-right: -5px;
  /*margin-left: 50%;*/
  margin-top: 20px;
}
#edit{
    float: left;
    width: 50%;
    height: 1000px;
    margin-left: -4px;
}
#nav{
    /*background-color:#0099ff;*/
    width: 100%;
    height: 45px;
    margin-top: -20px;
}
.n2{
    width: 100%;
    height: 35px;
    float: left;
    margin-top: 10px;
}
.b1{
    background-color: #1b9ff7;
    color:white; 
    width: 80px;
    height: 35px;
    margin-left: 30px;
    border: 0;
    float: left;
    text-align: center;
    border-radius: 6px;
    margin-top: 5px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.b1:hover{
    background-color: white;
    color: #1b9ff7;
}
h1{
    font-family: Arial, Helvetica, sans-serif;
    font-size: 10pt;
    font-weight: lighter;
}

.input{
    background-color: #e1e5e8;
    border: 0ch;
    border-radius: 6px;
    margin-top: 10px;
    width: 300px;
    height: 50px;

}

.b2{
    color: #aeb8bf; 
    background-color: white;
    border: none;
    float: inherit;
    margin-bottom: 10px;
    margin-left: 10px;
    text-align: center;
    text-decoration:underline #aeb8bf;

    
}
.b3{
    background-color: #1b9ff7;
    color:white; 
    width: 150px;
    height: 35px;
    margin-left: 30px;
    border: 0;
    float: left;
    text-align: center;
    border-radius: 6px;
    margin-top: 5px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.b3:hover{
    background-color: white;
    color: #1b9ff7;
}

.b4{
    background-color: #1b9ff7;
    color:white; 
    width: 120px;
    height: 35px;
    margin-left: 30px;
    border: 0;
    float: left;
    text-align: center;
    border-radius: 6px;
    margin-top: 5px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.b4:hover{
    background-color: white;
    color: #1b9ff7;
}
.b5{
    background-color: #ebf5fa;
    color:black; 
    height: 35px;
    width: 80px;
    border: 0;
    float: left;
    margin-left: 30px;
    text-align: center;
    border-radius: 6px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.b5:hover{
    background-color:#1b9ff7;
    color: white;
}

.select{
  width: 180px;
  height: 30px;
  background-color:#e1e5e8;
  border: 0;
  border-radius: 6px;
}

pre{
  /*white-space: pre-wrap;*/
  width: 100%;
  height: 1500px;
  overflow-x: auto;
  overflow-y: auto;
  text-align: left;

}
img{
  max-width: 35%;
  max-height: 35%;
  width: auto;
  height: auto;
}
</style>
