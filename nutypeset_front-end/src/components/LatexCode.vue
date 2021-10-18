<template>
    <div class ="LatexCode">
 <hr>
      <p>Entire latex file code</p>
      <textarea
      id="story"
      name="story"
      rows="5"
      cols="100"
      onscroll="this.rows++;"
      v-model="value">
      </textarea>
    </div>
</template>


<script>
import latexCompileApi from "@/api/getData.js";
export default {
  name: "LatexCode",
  component: {},
  data() {
    return { 
      value:"\\documentclass{article}\n\\usepackage[utf8]{inputenc}\n\\usepackage[english]{babel}\n\\usepackage[dvipsnames]{xcolor}\n\\usepackage{geometry}\n\\usepackage{setspace}\n\\geometry{paperheight=2.0cm, paperwidth=21cm}\n\\renewcommand{\\baselinestretch}{0.75}\n\\definecolor{ec7c25}{RGB}{  236, 124, 37}\n\\definecolor{1c427a}{RGB}{28, 66,122}\n\\definecolor{mygray}{gray}{0.6}\n\\begin{document}\n\\begin{center}\n\\Huge  \\textcolor{ec7c25}{$\\textbf{N}_\\textbf{U}$}\\textcolor{1c427a}{$^\\textbf{t}\\textbf{y}_\\textbf{p}\\textbf{e}$}\\textcolor{ec7c25}{\\textbf{S}}\\textcolor{1c427a}{\\textbf{et}} $\\times$ \\LaTeX \\\\ \n\\end{center}\n\\end{document}\n",
      data:""
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
    }

    
  },

  computed: {
    count: function () {
      return this.value.length;
    },
  },
};
</script>