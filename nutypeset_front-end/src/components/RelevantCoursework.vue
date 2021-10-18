<template>
    <div class ="RelevantCoursework">
 <hr>
<button class="be1" v-on:click="addInput"><h1>Add Input</h1></button>
  <div class="inputArea" v-for="(courseWork, k) in relevantCoursework" :key="k">
        <textarea class="input" id="e1" rows="1" cols="100"  v-model="courseWork.course" placeholder="Computer Engineer" @mouseleave="getCode"></textarea>
        <button class="be2" v-on:click="relevantCoursework.splice(k, 1);"><h1>Delete</h1></button>
      
  </div>
  <hr>

    </div>
</template>


<script>
import latexCompileApi from "@/api/getData.js";
export default {
  name: "RelevantCoursework",
  component: {},
  
  data() {
    return { 
      value:"first argument\nsecond argument \nthird argument",
      listStyle:"",
      counter:9,
      relevantCoursework:[
        {
          id:"1",
          course: "Data Structures"
        },
        {
          id:"2",
          course: "Software Methodology"
        },
        {
          id:"3",
          course: "Algorithms Analysis"
        },
        {
          id:"4",
          course: "Database Management"
        },
        {
          id:"5",
          course: "Computer Architecture"
        },
        {
          id:"6",
          course: "Artificial Intelligence"
        },
        {
          id:"7",
          course: "Internet Technology"
        },
        {
          id:"8",
          course: "Systems Programming"
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

      this.relevantCoursework.push({
        id: `${++this.counter}`,
        course:"Computer Engineer",
      });
      //console.log("add successfully");
    },
    getCode(){

        var outputCode ="%------RELEVANT COURSEWORK-------\n\\section{Relevant Coursework}\n    %\\resumeSubHeadingListStart\n        \\begin{multicols}{4}\n            \\begin{itemize}[itemsep=-5pt, parsep=3pt]\n";
        for (var i=0;i<this.relevantCoursework.length;i++)
        { 
            outputCode += "                \\item ";
            outputCode += this.relevantCoursework[i].course;
            outputCode += "\n";
        }
        outputCode += "            \\end{itemize}\n        \\end{multicols}\n        \\vspace*{2.0\\multicolsep}\n    %\\resumeSubHeadingListEnd\n\n\n";
        this.outputCode = outputCode;
        this.$emit('relevantCourseworkCode',this.outputCode);
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
  width: 250px;
  height: 30px;
}
</style>