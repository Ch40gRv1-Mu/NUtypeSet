<template>
<div>
    <div class="container">
      <div class="nav">
      <button class="b1" @click="goIndex">back</button>
    </div>
        <div class="p_top">
          <!--
            <div>
                <router-link to="/login" v-if = "getToken === ''"> 
                <p class="b1">login</p>
                
                </router-link>
                
            </div>
            -->
        </div>
        <br>
        <br>
        <div class="info" v-if="getToken !== ''" >
                <p>name: {{info.username}} </p>
                
                <br>

                <p>phone: {{info.phone}} </p>
                
                <br>
                <p>email: {{info.email}} </p>
                
                <br>
        </div>
                  <button class="b3" v-on:click="downloadResume">Download My Resume</button>
        <br>
        <br>
         <div id="n2">
          <button class="green" v-if ="getToken === ''" @click="goLogin">Login</button>

        <button class="green" @click="goResume">Resume Mode</button>
        <button class="green" @click="goEditor">Editor Mode</button> 
        <button v-if="getToken !== ''" class="green" @click="signOut">log out</button>
    </div>
       
    </div>
  </div>

</template>

<script>

import { getUserInfo } from "@/api/http.js";


export default {


  data() {
    return {
      info: {},
    };
  },

  computed: {
    getToken() {
      return this.$store.state.token;
    }
  },

  methods: {
    //获取用户信息
    async getInfo() {
      try {
        const result = await getUserInfo({token:this.getToken});
        //console.log(this.getToken)
        //console.log(result)
        if (result.code === 0) {
          this.info = result.data;
          localStorage.setItem('username',this.info.username)
          this.$store.dispatch('set_Username',this.info.username)
          localStorage.setItem('pdfsrc',this.info.pdfsrc)
          this.$store.dispatch('set_Pdfsrc',this.info.pdfsrc)
          localStorage.setItem('latexFinalCode',this.info.latexFinalCode)
          this.$store.dispatch('set_Latexfinalcode',this.info.latexFinalCode)
          localStorage.setItem('webUserEmail',this.info.email)
          this.$store.dispatch('set_Webuseremail',this.info.email)
          
        }
      } catch (error) {
        //console.log(error);
      }
    },
      downloadResume(){
      ////console.log(this.$store.state.pdfsrc);

      //window.location.href = this.$store.state.pdfsrc;
      window.open(this.$store.state.pdfsrc);


    },

    //退出登录
    async signOut() {
      //清除token
      await this.$store.dispatch('clearToken');
      localStorage.removeItem("token");

      //刷新页面
      location.reload();
    },
    goIndex:function() {
            this.$router.push({path:'/index'})
        },
        goResume:function(){
           this.$router.push({path: '/resume'});
       },
       goEditor:function(){
           this.$router.push({path: '/editor'});
       },
       goLogin:function(){
           this.$router.push({path: '/login'});
       },
  },

  mounted() {
    if (this.getToken) {
      this.getInfo();
    }
  }
};
</script>

<style lang="scss" scoped>
.green{
    background-color: #1b9ff7;
    color:white; 
    width: 100px;
    height: 35px;
    border: 0;
    margin-left: 30px;
    float: left;
    text-align: center;
    border-radius: 6px;
    margin-top: 5px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.green:hover{
    background-color: white;
    color: #1b9ff7;
}
.nav{
  width: 100px;
  height: 35px;
  float: left;
  margin-left: 30px;
  margin-top: -110px;
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
h1{
  font-weight: 22pt;
}
.info{
  font-size: 18pt;
  margin-top: 50px;
}
#n2{
    width: 400px;
    height: 35px;
    margin-top: 30px;
    margin-left: auto;
    margin-right: auto;
}
.b3{
    background-color: #1b9ff7;
    color:white; 
    width: 150px;
    height: 35px;

    border: 0;
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
</style>