<template>
    <div class ="Itemize">

    <div class="button" id="b1">
          <input type="button" value="Add Item" v-on:click="addItem" />
        </div>
        <br>
    <div class="inputArea" v-for="input in inputs" :key="input.id">
       <textarea rows="3" cols="70"  v-model="input.value" placeholder="Developed a crawler to automatically crawl huge number of blogs online for big data analysis" @mouseleave="getCode"></textarea>
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
    add(){
      this.counter.push({});
      
    },
    getCode(){
        var itemLis = this.value.split('\n');
        //console.log(itemLis.length);
        var outputCode ="\\begin{itemize}";
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
        outputCode += "\\end{itemize} \n";
        this.outputCode = outputCode;

    } 
  },

  computed: {
    count: function () {
      return this.value.length;
    },
  },
};
</script>