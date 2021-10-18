<template>
    <div class ="TechnicalSkills">
 <br>
      <br> 
       <button class="bd3" v-on:click="addInput"><h1>Add Technic Type</h1></button>
       <br>
  <div class="inputArea" v-for="(techType,k) in technicSkills" :key="k">
      <br>
        <p> Technic Type: {{techType.id}}</p>
         <button class="bd2"  v-on:click="technicSkills.splice(k, 1);"><h1>Delete</h1></button>

        <hr>
        <textarea class="input" id="d1" rows="1" cols="100"  v-model="techType .technicsType" placeholder="Language" @mouseleave="getCode"></textarea>
        <br>
      <br>
      <button class="bd3"  v-on:click="techType.technics.push({id:`${++techType.technicsCount}`,technic:'English'})"><h1>Add Technic</h1></button>
      <br>
        <div class="inputArea" v-for="(tech,k2) in techType.technics" :key="k2">
            <br>
            <p> Technic : {{tech.id}}</p>
           <br>
            <textarea class="input" id="d3" rows="1" cols="50"  v-model="tech.technic" placeholder="Computer Engineer" @mouseleave="getCode"></textarea>
             <button class="bd2"  v-on:click="techType.technics.splice(k2, 1);"><h1>Delete</h1></button>
                    
        </div>
        <br>
  </div>
  <hr>

    </div>
</template>


<script>
import latexCompileApi from "@/api/getData.js";
export default {
  name: "TechnicalSkills",
  component: {},
  
  data() {
    return { 
      value:"first argument\nsecond argument \nthird argument",
      listStyle:"",
      counter:3,
      technicSkills:[
          {
          id: "1",
          technicsType: "Languages",
          technicsCount:6,
          technics:[
            { 
                id:"1",
                technic: "Python"
            },
            { id:"2",
            technic:"Java"
            },
            { id:"3",
            technic:"C"
            },
            {
                id:"4",
                technic:"HTML/CSS"
            },
            { id:"5",
            technic:"JavaScript"
            },
            {
                id:"6",
                technic:"SQL"

            }
            ]

      },
      {
          id: "2",
          technicsType: "Developer Tools",
          technicsCount:4,
          technics:[
            { 
                id:"1",
                technic: "VS Code"
            },
            { id:"2",
            technic:"Eclipse"
            },
            { id:"3",
            technic:"Google Cloud Platform"
            },
            {
                id:"4",
                technic:"Android Studio"
            }
        ]

      },
      {
          id: "3",
          technicsType: "Technologies/Frameworks",
          technicsCount:4,
          technics:[
            { 
                id:"1",
                technic: "Linux"
            },
            { id:"2",
            technic:"Jenkins"
            },
            { id:"3",
            technic:"GitHub"
            },
            {
                id:"4",
                technic:"WordPress"
            }
        ]

      },

      ],
      outputCode:""
       };
  },
  // 这是新增的方法
  methods: {
    send: function () {
      let param = {};
      param.fileContends = this.value;
      this.$axios({
        url: "http://localhost:8080/api/v1/pub/window/latexOneCompile",
        method: "post",
        data: param,
      }).then((res) => {
        //console.log("已经执行");
        //console.log(res);
        this.data = res.data
      });
    },
    add(){
      this.counter.push({});
    },
    addInput() {

      this.technicSkills.push({
        id: `${++this.counter}`,
        technicsType: "Languages",
          technicsCount:6,
          technics:[
            { 
                id:"1",
                technic: "Python"
            },
            { id:"2",
            technic:"Java"
            },
            { id:"3",
            technic:"C"
            },
            {
                id:"4",
                technic:"HTML/CSS"
            },
            { id:"5",
            technic:"JavaScript"
            },
            {
                id:"6",
                technic:"SQL"

            }
            ]
      });
      //console.log("add successfully");
    },
    getCode(){

        var outputCode ="%-----------PROGRAMMING SKILLS-----------\n\\section{Technical Skills}\n\\begin{itemize}[leftmargin=0.15in, label={}]\n    \\small{\\item{\n";
        for (var i=0;i<this.technicSkills.length;i++)
        { 
            outputCode += "     \\textbf{";
            outputCode += this.technicSkills[i].technicsType;
            outputCode += "}{: ";
            var techniques="";
            for (var j = 0 ; j<this.technicSkills[i].technics.length; j++)
                { 
                    if (techniques =="") techniques +=this.technicSkills[i].technics[j].technic;
                    else techniques+= (", "+this.technicSkills[i].technics[j].technic);
                }
                outputCode += techniques;

                outputCode +=  "}\\\\\n"
            
        }
        outputCode += "    }}\n \\end{itemize}\n \\vspace{-16pt}\n\n\n";
        this.outputCode = outputCode;
        this.$emit('technicalSkillsCode',this.outputCode);
        //console.log("已经同步");

    } 
  },

  computed: {
    count: function () {
      return this.value.length;
    },
  },
};
</script>

<style lang="scss">
.bd1{
    background-color: #1b9ff7;
    color:white; 
    width: 80px;
    height: 35px;
    border: 0;
    text-align: center;
    border-radius: 6px;
    margin-top: 5px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.bd1:hover{
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

}

.bd2{
    color: #aeb8bf;
    background-color:white;
    border: none;
    float: inherit;
    margin-bottom: 10px;
    margin-left: 10px;
    text-align: center;
    text-decoration:underline #aeb8bf;

    
}

#d1{
  width: 300px;
  height: 40px;
}
#d2{
  width: 80px;
  height: 20px;
}
#d3{
  width: 200px;
  height: 30px;
}
.bd3{
    background-color: #1b9ff7;
    color:white; 
    width: 110px;
    height: 35px;
    border: 0;
    text-align: center;
    border-radius: 6px;
    margin-top: 5px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.bd3:hover{
    background-color: white;
    color: #1b9ff7;
}
</style>