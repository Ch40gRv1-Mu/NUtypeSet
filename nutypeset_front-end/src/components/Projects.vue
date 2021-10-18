<template>
  <div class ="Prpjects">
            <br>
        <br>
    <button class="be1" v-on:click="addInput"><h1>Add Input</h1></button>
      <div class="inputArea" v-for="(input,k) in inputs" :key="k">
            
        <br>

        <div> Project:{{input.id}}</div>
        <button class="be2" v-on:click="inputs.splice(k, 1);"><h1>Delete</h1></button>
        <hr>
        <!-- school -->
        <div>Project Event:</div>
         <textarea class="input" id="e1" rows="1" cols="100" onscroll="this.rows++;" v-model="input.event" placeholder="National University of Singapore" @mouseleave="getCode"></textarea>
         <br>
         <br>

        <!-- duration from year -->
        <div>From Year:</div>
        <textarea class="input" id="e2" rows="1" cols="10"  v-model="input.durationFromYear" placeholder="2020" @mouseleave="getCode"></textarea>
        <br>
        <br>
        <!-- duration from month -->
        <div>From Month:</div>
        <textarea class="input" id="e2" rows="1" cols="10"  v-model="input.durationFromMonth" placeholder="September" @mouseleave="getCode"></textarea>
        <br>
        <br>

        <!-- duration to year -->
        <div>To Year:</div>
        <textarea class="input" id="e2" rows="1" cols="10"  v-model="input.durationToYear" placeholder="2024" @mouseleave="getCode"></textarea>
        <br>
        <br>

        <!-- duration To month -->
        <div>To Month:</div>
        <textarea class="input" id="e2" rows="1" cols="10"  v-model="input.durationToMonth" placeholder="June" @mouseleave="getCode"></textarea>
        <br>
        <br>

        <!-- degree 
        <div>Position:</div>
        <textarea rows="1" cols="100"  v-model="input.degree" placeholder="Studend Researcher" @mouseleave="getCode"></textarea>
        <br>
        <br>
        -->
        <!-- country 
        <div>Country:</div>
        <textarea rows="1" cols="50"  v-model="input.country" placeholder="Singapore" @mouseleave="getCode"></textarea>
        <br>
        <br>-->
        <!-- city 
        <div>City:</div>
        <textarea rows="1" cols="50"  v-model="input.city" placeholder="Singapore" @mouseleave="getCode"></textarea>
        <br>
        <br>-->

         <!-- technique applied: -->
        <div> Technique Applied:</div>
        <br>
        <button class="be1" v-on:click="input.technicApplied.push({id: `${++input.technicAppliedCount}`, technique: 'Latex'})"><h1>Add Input</h1></button>
        <br>
      <div class="inputArea" v-for="(technic,k2) in input.technicApplied" :key="k2">
        <textarea class="input" id="e1" rows="1" cols="100"  v-model="technic.technique" placeholder="Latex" @mouseleave="getCode"></textarea>
      <button class="be2" v-on:click="input.technicApplied.splice(k2, 1);"><h1>Delete</h1></button>
                
      </div>
      <br>
      <br>
      <div> Work Description:</div>
      <br>
        <resume-item-list @resumeItemListCode="input.jobDescription = arguments[0]"></resume-item-list>
        
        <hr>
        <br>
        <br>
        
        
      </div>

      <!--  <input> types: https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/Input -->
      <!--<pre>{{ value }} </pre>-->


    </div>
</template>
<script>
import ResumeItemList from "@/components/ResumeItemList.vue"

export default {
  name:"Projects",
  components: {
    ResumeItemList
  },
  data() {
    return {
      data: "",
      pdfsrc: "https://www.openssl.org/~bodo/ssl-poodle.pdf",
      counter: 1,
      selected: "",
      inputs: [
        {
      id:"1",
      event:"Orbital",
		  durationFromYear: "2020",
		  durationFromMonth: "Septemter",
		  durationToYear:"2024",
		  durationToMonth:"June",
		  //position:"Student Researcher",
		  //city:"Singapore",
		  //country:"Singapore",
      jobDescription:"",
      technicAppliedCount:5,
    technicApplied:[{
        id: "1",
        technique:"SpringBoot"
        },
        {
          id: "2",
          technique: "Mybatis"
        },
        {
          id: "3",
          technique: "Mysql"
        },
        {
          id: "4",
          technique: "Vue.js"
        },
        {
          id: "5",
          technique: "Latex"
        }]
        },
      ],
      outputCode:""
    };
  },
  //http://localhost:8080/temp/temp.pdf
  // 这是新增的方法

  methods: {
    addInput() {
      this.inputs.push({
        id: `${++this.counter}`,
        event:"Orbital",
		durationFromYear: "2020",
		durationFromMonth: "Septemter",
		durationToYear:"2024",
		durationToMonth:"June",
		//position:"Student Researcher",
		//city:"Singapore",
		//country:"Singapore",

    jobDescription:"",
    technicAppliedCount:5,
    technicApplied:[{
        id: "1",
        technique:"SpringBoot"
        },
        {
          id: "2",
          technique: "Mybatis"
        },
        {
          id: "3",
          technique: "Mysql"
        },
        {
          id: "4",
          technique: "Vue.js"
        },
        {
          id: "5",
          technique: "Latex"
        }]
    
      });
      //console.log("add successfully");
    },
    
    getCode(val) {
      //this.input[key]['code']=val;
      ////console.log(this.inputs);
      ////console.log(val);
      this.outputCode="%-----------PROJECTS-----------\n\\section{Projects}\n    \\vspace{-5pt}\n    \\resumeSubHeadingListStart\n";
      for ( var i = 0; i<this.inputs.length;i++)
      {
      var outputCode = "";
      if (i>0)  outputCode+= "\\vspace{-13pt}\n";
      outputCode +="      \\resumeProjectHeading\n          {\\textbf{Gym Reservation Bot} $|$ \\emph{";
      
      //Technic Applied
      var techniqueApplied="";


      for (var j = 0 ; j<this.inputs[i].technicAppliedCount; j++)
      {
        if (techniqueApplied =="") techniqueApplied +=this.inputs[i].technicApplied[j].technique;
        else techniqueApplied += (", "+this.inputs[i].technicApplied[j].technique);
      }
      outputCode += techniqueApplied;
      outputCode += "}}{";

      //Duration
       var duration = this.inputs[i].durationFromMonth+" "+this.inputs[i].durationFromYear+" -- "+this.inputs[i].durationToMonth+" "+this.inputs[i].durationToYear;
       outputCode += duration;
       outputCode += "}\n ";     

      //Position
      //outputCode += this.inputs[i].position;
      //outputCode += "}{";
      
      //City
      //outputCode += this.inputs[i].city;
      //outputCode += ",";

      //Country
      //outputCode += this.inputs[i].country;
      //outputCode += "}\n";

      //jobDescription
      outputCode +=this.inputs[i].jobDescription;
      this.outputCode += outputCode;
    }
    this.outputCode+="    \\resumeSubHeadingListEnd\n\\vspace{-15pt}\n\n\n%\n";
    this.$emit('projectsCode',this.outputCode);

      //console.log("已经同步");
    },
  },

  computed: {
    count: function () {
      return this.value.length;
    },
  },
};
</script>

<style lang="scss">
.be1{
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
.be1:hover{
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

.be2{
    color: #aeb8bf;
    background-color:white;
    border: none;
    float: inherit;
    margin-bottom: 10px;
    margin-left: 10px;
    text-align: center;
    text-decoration:underline #aeb8bf;

    
}

#e1{
  width: 300px;
  height: 40px;
}
#e2{
  width: 80px;
  height: 20px;
}
</style>