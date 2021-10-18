<template>
  <div>
    <div id="test"></div>
    <div id="topcontainer">
      <div id="logo">
        <img alt="Vue logo" src="@/assets/logo.png" width="841px" height="80" />
      </div>
      <div id="navigator">
        <div class="button" id="b1">
          <input type="button" value="Add Input" v-on:click="addInput" />
        </div>
        <div class="button" id="b2">
          <input type="button" value="Compile" v-on:click="send" />
          File name:<br />
          <textarea v-model="fileName"></textarea>
          <br />
        </div>
      </div>
    </div>
    <div id="app">
      <!-- <input type="text" v-model="value">  -->
      <!--<img alt="Vue logo" src="./assets/logo.png"> -->

      <div class="inputArea" v-for="input in inputs" :key="input.id">
        <div class="custparam">
          <div id="v-model-select" class="select">
            <select v-model="input.window_type">
              <option disabled value="">Please select window type</option>
              <option>latex_file</option>
              <option>enumerate</option>
              <option>itemize</option>
            </select>
          </div>
          <!--  <input v-model="fontsize" placeholder="please enter font size" />-->
        </div>
        <!--
      <textarea
      id="input.id"
      placeholder="Enter text..."
      name="story"
      rows="5"
      cols="100"
      onscroll="this.rows++;"
      v-model="input.value">
    
  </textarea>-->

        <div class="pure_text" v-show="input.window_type == 'latex_file'">
          <latex-code @returnCode="input.code = arguments[0]"></latex-code>
        </div>
        <div class="enumerate" v-show="input.window_type == 'enumerate'">
          <enumerate @returnCode="input.code = arguments[0]"></enumerate>
        </div>
        <div class="itemize" v-show="input.window_type == 'itemize'">
          <itemize @returnCode="input.code = arguments[0]"></itemize>
        </div>
        <div>{{ input.code }}</div>
        <!--<textarea placeholder="Enter text... " :id="input.id" v-model="input.value"></textarea>-->
      </div>

      <!--  <input> types: https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/Input -->
      <!--<pre>{{ value }} </pre>-->

      <div class="pdfpreview">
        <iframe :src="pdfsrc" framework="0" width="80%" height="500px">
        </iframe>
      </div>
    </div>
  </div>
</template>
<script>
import latexCompileApi from "@/api/getData.js";
import LatexCode from "@/components/LatexCode.vue";
import Enumerate from "@/components/Enumerate.vue";
import Itemize from "@/components/Itemize.vue";
import Header from "@/components/Header.vue";

export default {
  components: {
    LatexCode,
    Enumerate,
    Header,
    Itemize,
  },
  data() {
    return {
      data: "",
      pdfsrc: "https://www.openssl.org/~bodo/ssl-poodle.pdf",
      counter: 0,
      selected: "",
      inputs: [
        {
          id: "text0",
          value:
            "\\documentclass{article}\n\\usepackage[utf8]{inputenc}\n\\usepackage[english]{babel}\n\\usepackage[dvipsnames]{xcolor}\n\\usepackage{geometry}\n\\usepackage{setspace}\n\\geometry{paperheight=2.0cm, paperwidth=21cm}\n\\renewcommand{\\baselinestretch}{0.75}\n\\definecolor{ec7c25}{RGB}{  236, 124, 37}\n\\definecolor{1c427a}{RGB}{28, 66,122}\n\\definecolor{mygray}{gray}{0.6}\n\\begin{document}\n\\begin{center}\n\\Huge  \\textcolor{ec7c25}{$\\textbf{N}_\\textbf{U}$}\\textcolor{1c427a}{$^\\textbf{t}\\textbf{y}_\\textbf{p}\\textbf{e}$}\\textcolor{ec7c25}{\\textbf{S}}\\textcolor{1c427a}{\\textbf{et}} $\\times$ \\LaTeX \\\\ \n\\end{center}\n\\end{document}\n",
          window_type: "latex_file",
          code: "",
        },
      ],

      title: "",
      author: "",
      header: "",
      packages: "",
      codeMiddle: "",
      fileName: `text${Date.now()}`,
    };
  },
  //http://localhost:8080/temp/temp.pdf
  // 这是新增的方法

  methods: {
    addInput() {
      this.inputs.push({
        id: `text${++this.counter}`,
        value:
          "\\documentclass{article}\n\\usepackage[utf8]{inputenc}\n\\usepackage[english]{babel}\n\\usepackage[dvipsnames]{xcolor}\n\\usepackage{geometry}\n\\usepackage{setspace}\n\\geometry{paperheight=2.0cm, paperwidth=21cm}\n\\renewcommand{\\baselinestretch}{0.75}\n\\definecolor{ec7c25}{RGB}{  236, 124, 37}\n\\definecolor{1c427a}{RGB}{28, 66,122}\n\\definecolor{mygray}{gray}{0.6}\n\\begin{document}\n\\begin{center}\n\\Huge  \\textcolor{ec7c25}{$\\textbf{N}_\\textbf{U}$}\\textcolor{1c427a}{$^\\textbf{t}\\textbf{y}_\\textbf{p}\\textbf{e}$}\\textcolor{ec7c25}{\\textbf{S}}\\textcolor{1c427a}{\\textbf{et}} $\\times$ \\LaTeX \\\\ \n\\end{center}\n\\end{document}\n",
        window_type: "latex_file",
        code: "",
      });
      //console.log("add successfully");
    },
    send: function () {
      let param = {};
      param.title = this.title;
      param.author = this.author;
      param.header = this.header;
      param.packages = this.packges;
      this.codeMiddle = "";
      for (var i = 0; i < this.inputs.length; i++) {
        this.codeMiddle += this.inputs[i].code;
      }
      //param.fileContends = this.value;
      param.codeMiddle = this.codeMiddle;
      param.fileName = this.fileName;
      this.$axios({
        url: "http://localhost:8080/api/v1/pub/window/submitPartialCodeAndCompile",
        method: "post",
        data: param,
      }).then((res) => {
        //console.log("已经执行");
        //console.log(res);
        this.data = res.data;
      });
    },
    getCode(val) {
      //this.input[key]['code']=val;
      ////console.log(this.inputs);
      ////console.log(val);
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
#logo {
  width: 100%;
  height: 80px;
  //position: fixed;
  background-color: white;
}
#navigator {
  width: 100%;
  height: 50px;
  background-color: white;
  //position: fixed;
  //z-index: 90;
  //float: left;
}
#topcontainer {
  width: 100%;
  height: auto;
  position: fixed;
  top: 0;
}

.button {
  width: 80px;
  height: 50px;
  float: left;
  background-color: white;
}
#b1 {
  margin-left: 10%;
}
#b2 {
  margin-left: 20px;
}
.pdfpreview {
  margin-top: 50px;
}
.custparam {
  width: 100%;
  background-color: white;
}
.select {
  font-family: sans-serif;
  border: 1px solid #eee;
  border-radius: 2px;
  padding: 20px 30px;
  margin-top: 1em;
  margin-bottom: 40px;
  user-select: none;
  overflow-x: auto;
}
</style>