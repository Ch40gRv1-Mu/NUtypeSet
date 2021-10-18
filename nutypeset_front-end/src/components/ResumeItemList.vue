<template>
    <div class ="Itemize">

          <button class="bb1" value="Add Item" v-on:click="addItem"><h1>Add Input</h1></button>
        
        <br>
        <br>
    <div class="inputArea" v-for="(input,k) in inputs" :key="k">
       <textarea class="input" id="b4" rows="3" cols="70"  v-model="input.value" placeholder="Developed a crawler to automatically crawl huge number of blogs online for big data analysis" @mouseleave="getCode"></textarea>
      <button class="bb2" v-on:click="deleteInput(k)"><h1>Delete</h1></button>
 
    </div>

    </div>
</template>

<script>
import latexCompileApi from "@/api/getData.js";
export default {
  name: "Itemize",
  component: {},
  data() {
    return { 
      value:"first argument\nsecond argument \nthird argument",
      listStyle:"",
      counter:1,
      outputCode:"",
      inputs:[{ id: "1",value: "Developed a crawler to automatically crawl huge number of blogs online for big data analysis" }]
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
    addItem() {
      this.inputs.push({
        id: `${++this.counter}`,
        value:"Developed a crawler to automatically crawl huge number of blogs online for big data analysis"
      });
      //console.log("add successfully");
    },
    deleteInput(k){
        this.inputs.splice(k, 1);
      }, 

    add(){
      this.counter.push({});
      
    },
    getCode(){
        //var itemLis = this.value.split('\n');
        ////console.log(itemLis.length);
        var outputCode ="      \\resumeItemListStart\n";
        for (var i=0;i<this.inputs.length;i++)
        { 
        
            outputCode += "        \\resumeItem{";
            outputCode += this.inputs[i].value;
            outputCode += "}\n";
        }
        outputCode += "      \\resumeItemListEnd\n";
        this.outputCode = outputCode;
        this.$emit('resumeItemListCode',this.outputCode);
        //console.log("ResumeItemList已经同步");

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
.bb1{
    background-color: #1b9ff7;
    color:white; 
    width: 80px;
    height: 35px;
    border: 0;
    text-align: center;
    border-radius: 6px;
    margin-top: 5px;
    margin-left: auto;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.bb1:hover{
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
.bb2{
    color: #aeb8bf;
    background-color: white;
    border: none;
    margin-bottom: 10px;
    margin-left: 10px;
    text-align: center;
    text-decoration:underline #aeb8bf;

    
}

#b4{
  margin: auto;
  width: 500px;
  height: 100px;
}

</style>