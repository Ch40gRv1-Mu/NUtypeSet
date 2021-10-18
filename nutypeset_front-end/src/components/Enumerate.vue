<template>
    <div class ="Enumerate">
 <hr>
      <p>Enumerate </p>
        <textarea
      
      id="story"
      name="story"
      rows="5"
      cols="100"
      onscroll="this.rows++;"
      v-model="value"
      @mouseleave="getCode"
      >
  </textarea>
  <hr>
  <div> list style</div>
  <textarea v-model="listStyle" ></textarea>
   <!--<button @click = "getCode" style = "front-size:25px;">generateCode</button>-->
    <!--<div> {{outputCode}} </div>-->
    </div>
</template>


<script>
import latexCompileApi from "@/api/getData.js";
export default {
  name: "Enumerate",
  component: {},
  data() {
    return { 
      value:"first argument\nsecond argument \nthird argument",
      listStyle:"",
      outputCode:""
       };
  },
  // 这是新增的方法
  methods: {
    
    add(){
      this.counter.push({});
    },
    getCode(){
        var itemLis = this.value.split('\n');
        //console.log(itemLis.length);
        var outputCode ="\\begin{enumerate}";
        if (this.listStyle != ""){
            outputCode+= ("["+this.listStyle+"]");
        }
        outputCode += "\n";
        for (var i=0;i<itemLis.length;i++)
        { 
            outputCode += "\\item ";
            outputCode += itemLis[i];
            outputCode += "\n";
        }
        outputCode += "\\end{enumerate} \n";
        this.outputCode = outputCode;
        this.$emit('returnCode',this.outputCode)
        //console.log("generatw")
    } 
  },

  computed: {
    count: function () {
      return this.value.length;
    },
  },
};
</script>