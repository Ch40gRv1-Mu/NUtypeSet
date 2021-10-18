<template>
  <div class ="Education">
      
        <br>
        <br>
          <button class="bc1" v-on:click="addInput"><h1>Add Input</h1></button>
      <div class="inputArea" v-for="(input,k) in inputs" :key="k">
        <!--<div class="inputArea" v-for="(input,k) in inputs" :key="k">-->
        <br>
        <p> School:{{input.id}}
          <button class="bc2" v-on:click="deleteInput(k)"><h1>Delete</h1></button>
        </p>
        <hr>
        <!-- school -->
        <p>School:</p>
         <textarea class="input" id="c1" rows="1" cols="100" onscroll="this.rows++;" v-model="input.school" placeholder="National University of Singapore" @mouseleave="getCode"></textarea>
         <br>
         <br>

        <!-- duration from year -->
        <div>From Year:</div>
        <textarea class="input" id="c2" rows="1" cols="10"  v-model="input.durationFromYear" placeholder="2020" @mouseleave="getCode"></textarea>
        <br>
        <br>
        <!-- duration from month -->
        <p>From Month:</p>
        <textarea class="input" id="c2" rows="1" cols="10"  v-model="input.durationFromMonth" placeholder="September" @mouseleave="getCode"></textarea>
        <br>
        <br>

        <!-- duration to year -->
        <p>To Year:</p>
        <textarea class="input" id="c2" rows="1" cols="10"  v-model="input.durationToYear" placeholder="2024" @mouseleave="getCode"></textarea>
        <br>
        <br>

        <!-- duration To month -->
        <p>To Month:</p>
        <textarea class="input" id="c2" rows="1" cols="10"  v-model="input.durationToMonth" placeholder="June" @mouseleave="getCode"></textarea>
        <br>
        <br>

        <!-- degree -->
        <p>Degree:</p>
        <textarea class="input" id="c1" rows="1" cols="100"  v-model="input.degree" placeholder="Bachelor of Computting(information security)" @mouseleave="getCode"></textarea>
        <br>
        <br>

        <!-- country -->
        <p>Country:</p>
        <textarea class="input" id="c3" rows="1" cols="50"  v-model="input.country" placeholder="Singapore" @mouseleave="getCode"></textarea>
        <br>
        <br>
        <!-- city -->
        <p>City:</p>
        <textarea class="input" id="c3" rows="1" cols="50"  v-model="input.city" placeholder="Singapore" @mouseleave="getCode"></textarea>
        <br>
        <br>
        <hr>
        <br>
        <br>
        
        
      </div>

      <!--  <input> types: https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/Input -->
      <!--<pre>{{ value }} </pre>-->

    </div>
</template>
<script>


export default {
  components: {
  },
  data() {
    return {
      data: "",
      counter: 1,
      inputs: [
        {
          id:"1",
          school:"National University of Singapore",
		  durationFromYear: "2020",
		  durationFromMonth: "Septemter",
		  durationToYear:"2024",
		  durationToMonth:"June",
		  degree:"Bachelor of Computting(information security)",
		  city:"Singapore",
		  country:"Singapore"
        },
      ],
    };
  },
  //http://localhost:8080/temp/temp.pdf
  // 这是新增的方法

  methods: {
    addInput() {
      this.inputs.push({
        id: `${++this.counter}`,
        school:"National University of Singapore",
		durationFromYear: "2020",
		durationFromMonth: "Septemter",
		durationToYear:"2024",
		durationToMonth:"June",
		degree:"Bachelor of Computting(information security)",
		city:"Singapore",
		country:"Singapore"
      });
      //console.log("add successfully");
    },
    deleteInput(k){
      this.inputs.splice(k, 1);
    },
    
    getCode(val) {
      //this.input[key]['code']=val;
      ////console.log(this.inputs);
      ////console.log(val);
      this.outputCode ="%-----------EDUCATION-----------\n\\section{Education}\n  \n";
      for (var i =0 ; i<this.inputs.length; i++)
      {
           var outputCode = "  \\resumeSubHeadingListStart\n    \\resumeSubheading\n      {";
           //school
           outputCode += this.inputs[i].school;
           outputCode += "}{";

           //duration
           var duration = this.inputs[i].durationFromMonth+" "+this.inputs[i].durationFromYear+" -- "+this.inputs[i].durationToMonth+" "+this.inputs[i].durationToYear;
           outputCode += duration;
           outputCode += "}\n      {";

           //degree
           outputCode += this.inputs[i].degree;
           outputCode += "}{";

           //city
           outputCode += this.inputs[i].city;
           outputCode += ", ";

           //country
           outputCode += this.inputs[i].country;

           outputCode +="}\n  \\resumeSubHeadingListEnd\n\n"

           this.outputCode += outputCode ;


      }
      this.$emit('educationCode',this.outputCode)
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
.bc1{
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
.bc1:hover{
    background-color: white;
    color: #1b9ff7;
}

.input{
    background-color: #e1e5e8;
    border: 0ch;
    border-radius: 6px;
    margin-top: 10px;
    width: 300px;
    height: 50px;

}

.bc2{
    color: #aeb8bf; 
    background-color: white;
    border: none;
    margin-bottom: 10px;
    margin-left: 10px;
    text-align: center;
    text-decoration:underline #aeb8bf;

    
}

#c1{
  width: 300px;
  height: 40px;
}
#c2{
  width: 80px;
  height: 20px;
}
#c3{
  width: 150px;
  height: 20px;
}
</style>