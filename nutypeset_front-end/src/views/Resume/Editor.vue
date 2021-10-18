
<template>
<div id="Editor">
    
    <div id="wrap">
        <div id="edit">
            <div class="n2">
      <button class="b5" @click="goIndex">back</button>
    </div>
            <div id="logo" @click="goIndex">
                <img alt="Vue logo" src="./assets/logo.png">
            </div>
            <div id="nav">
                <button class="b1" v-on:click="send" ><h1>Compile</h1></button>
                <button class="b3" v-on:click="downloadLatex"><h1>Download Latex File</h1></button>
                <button class="b4" @click="goResume"><h1>Switch to Resume</h1></button>
                <button class="b1" v-if="getToken !== ''" @click="save"><h1>Save</h1></button>

            </div>
            <div class="inputArea">
                <textarea ref="mycode" v-model="latexCode" ></textarea>
            </div>
        </div>

        <div id="preview">
            <iframe :src="pdfsrc" v-if="pdfUpdate==true" framework="0" width=100%, height="1200px">
            </iframe>
        </div>
    </div>
</div>

</template>

<script>
import latexCompileApi from "@/api/getData.js";
  import "codemirror/theme/duotone-light.css";
  import "codemirror/lib/codemirror.css";
  import "codemirror/addon/hint/show-hint.css";

  let CodeMirror = require("codemirror/lib/codemirror");
  require("codemirror/addon/edit/matchbrackets");
  require("codemirror/addon/selection/active-line");
  require("codemirror/mode/stex/stex");
  require("codemirror/addon/hint/show-hint");

export default{
    name:"Editor",
    component: {
        
    },
    data() {
        return {
            data:"",
            latexCode: this.$store.state.latexFinalCode,
            pdfsrc: this.$store.state.pdfsrc || "http://localhost:8080/temp/Nutypeset_CV.pdf",
            pdfUpdate: true,
            latexSrc: this.$store.state.latexSrc ||  "http://localhost:8080/temp/Nutypeset_CV.tex",
            
            
        };
    },
    computed: {
    getToken() {
      return this.$store.state.token;
    }
    },
   
    methods: {
    async getInfo() {
      try {
        ////console.log(this.getToken)
        //console.log(this.$store.state.latexFinalCode);
        if (this.getToken!==""){
          //console.log(this.getToken)
          this.pdfsrc=this.$store.state.pdfsrc;
          this.latexCode=this.$store.state.latexFinalCode;  
           this.latexSrc=this.$store.state.latexSrc;    
          ////console.log(this.latexCode)
        }
        
      } catch (error) {
        //console.log(error);
      }
    },
    
    send: function () {
      let param = {};
      param.fileContends = this.latexCode;
      if(this.$store.state.token!==""){
      param.webUserEmail=this.$store.state.webUserEmail;
    }
    else{
      param.webUserEmail="";
    }
      //console.log(param.fileContends)
      
      this.$axios({
        url: "http://localhost:8080/api/v1/pub/window/latexOneCompile",
        method: "post",
        data: param,
      }).then((res) => {      
        this.pdfsrc = "http://localhost:8080/temp/"+res.data.url;
        this.latexSrc = "http://localhost:8080/temp/"+res.data.latexFileName;

        //console.log(res.data.url);
        //console.log(res.url);
        //console.log(this.pdfsrc);
        localStorage.setItem('pdfsrc',this.pdfsrc)
        this.$store.dispatch('set_Pdfsrc',this.pdfsrc)
        localStorage.setItem('latexSrc',this.latexSrc)
        this.$store.dispatch('set_Latexsrc',this.latexSrc)
        localStorage.setItem('latexFinalCode',this.latexCode)
        this.$store.dispatch('set_Latexfinalcode',this.latexCode)
      });
      this.pdfUpdate = false;
      this.$nextTick(() => {
            this.pdfUpdate = true;
      }) 
      this.pdfUpdate = false;
      this.$nextTick(() => {
            this.pdfUpdate = true;
      })  
    },

    save: function () {
      let param = {};
      param.latexFinalCode = this.$store.state.latexFinalCode;
      param.pdfsrc = this.$store.state.pdfsrc;
      //console.log(param.fileContends)
      
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
            window.location.href = this.latexSrc;
            console.log(this.latexSrc);

        },
        goIndex:function(){
           this.$router.push({path: '/index'})
          
       },
       goResume:function(){
           this.$router.push({path: '/resume'})
       },
    },

    mounted () {
            if (this.getToken) {
                  this.getInfo();
             }
            
            let mime = 'text/x-stex'
            let theme = 'duotone-light'//设置主题，不设置的会使用默认主题
            let editor = CodeMirror.fromTextArea(this.$refs.mycode, {
            mode: mime,//选择对应代码编辑器的语言，我这边选的是数据库，根据个人情况自行设置即可
            indentWithTabs: true,
            smartIndent: true,
            lineNumbers: true,
            matchBrackets: true,
            theme: theme,
            pasteLinesPerSelection: true,
            // autofocus: true,
            })
            editor.setValue

            //将代码打包存储
            editor.on('blur', (cm) => {
                this.latexCode = cm.getValue();
                //alert(this.latexCode)

            })
            
            
            
            
      }

};
    
</script>

<style lang="scss" scoped>
#wrap{
    width: 100%;
    height: 100%;
    background-color: white;
    margin-top: -130px;
    
}
#logo{
  margin-top: 5px;
}
#preview{
  /*position: fixed;*/
  float: right;
  width: 50%;
  margin-right: 0px;
  margin-left: 50%;
}
#edit{
    float: left;
    position: fixed;
    width: 50%;
    height: 1000px;
    margin-left: -4px;
    overflow: hidden;
}
#nav{
    /*background-color:#0099ff;*/
    width: 100%;
    height: 35px;
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
.inputArea{
    width: 600px;
    height: 800px;
    margin-left: 30px;
    text-align: left;

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
.b4:hover{
    background-color: white;
    color: #1b9ff7;
}
img{
  max-width: 35%;
  max-height: 35%;
  width: auto;
  height: auto;
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

</style>
<style lang="scss">
.CodeMirror{
    height: 540px;
    width: 600px;
    
}

</style>
