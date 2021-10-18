<template>
<div id="wrap">
    <div class="nav" id="n1">
        <button class="b1" v-if="getToken=== ''" @click="goLogin">login</button>
        <button class="b1" v-if="getToken=== ''" @click="goRegister">register</button>
        <button class="b1" v-if="getToken!== ''" @click="goPersonal">{{this.$store.state.username}}</button>
        <button class="b1" @click="goFeedback">feedback</button>
    </div>
    <div id="welcome">
        <img alt="Vue logo" src="./assets/logo.png">
    </div>
    <div id="intro">
    A LaTex Editor Web App
    <br>
    <br>
    <h1>Have a try of our new features!</h1>
    </div>
    <div id="n2">
        <button class="b3" @click="goResume">Resume Mode</button>
        <button class="b3" @click="goEditor">Editor Mode</button> 
    </div>
</div>
</template>
<script>

export default {
   name:"Index",
   data(){
       return{

       };
   },
   computed: {
    getToken() {
      return this.$store.state.token;
    }
  },
   methods:{
       goResume:function(){
           this.$router.push({path: '/resume'});
       },
       goEditor:function(){
           this.$router.push({path: '/editor'});
       },
       goLogin:function(){
           this.$router.push({path: '/login'});
       },
       goRegister:function(){
           this.$router.push({path: '/register'});
       },
       goPersonal:function(){
           this.$router.push({path: '/personal'});
       },
       goFeedback:function(){
           window.location.href = "https://www.surveymonkey.com/r/DYMN2MW";
       }


   },
    watch: {
        '$route' (to, from) {
        this.$router.go(0);
        }
    },
}
</script>


<style lang="scss" scoped>
#wrap{
    width: 100%;
    height: 100%;
    margin-top: -110px;
}
#welcome{
    width:100%;
    margin-top: 10%;
    animation: fade-in;
    animation-duration: 2.5s;
    -webkit-animation:fade-in 2.5s;
}

@keyframes fade-in {
    0% {opacity: 0;}/*初始状态 透明度为0*/
    50% {opacity: 0;}/*过渡状态 透明度为0*/
    100% {opacity: 1;}/*结束状态 透明度为1*/
}
@-webkit-keyframes fade-in {/*针对webkit内核*/
    0% {opacity: 0;}
    50% {opacity: 0;}
    100% {opacity: 1;}
}
@keyframes scroll {
    from{}
}
@-webkit-keyframes scroll {/*针对webkit内核*/
    0% {opacity: 0;}
    50% {opacity: 0;}
    100% {opacity: 1;}
}

@keyframes fade-in2 {
    0% {opacity: 0;}/*初始状态 透明度为0*/
    80% {opacity: 0;}/*过渡状态 透明度为0*/
    100% {opacity: 1;}/*结束状态 透明度为1*/
}
@-webkit-keyframes fade-in2 {/*针对webkit内核*/
    0% {opacity: 0;}
    80% {opacity: 0;}
    100% {opacity: 1;}
}

#intro{
    font-family:  Avenir, Helvetica, Arial, sans-serif;
    text-align: center;
    color: black;
    font-size: 30pt;
    animation: fade-in;
    animation-duration: 5s;
    -webkit-animation:fade-in 5s;

    
}
.nav{
    height: 35px;
    margin-top: 30px;
    width: 350px;
    
}
#n2{
    animation: fade-in2;
    animation-duration: 5s;
    -webkit-animation:fade-in2 5s;
    width: 300px;
    height: 35px;
    margin-top: 30px;
    margin-left: auto;
    margin-right: auto;
}
.b1{
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
.b1:hover{
    background-color:#1b9ff7;
    color: white;
}
.b3{
    background-color: #1b9ff7;
    color:white; 
    height: 35px;
    border: 0;
    width: 100px;
    float: left;
    margin-left: 30px;
    text-align: center;
    border-radius: 6px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.b3:hover{
    background-color: white;
    color: #1b9ff7;
}
h1{
    font-family:  Avenir, Helvetica, Arial, sans-serif;
    text-align: center;
    color: black;
    font-size: 16pt;

}
img{
  max-width: 40%;
  max-height: 40%;
  width: auto;
  height: auto;
}
</style>