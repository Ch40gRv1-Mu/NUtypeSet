<template>
<div id="wrap">
    <div class="nav">
      <button class="b1" @click="goIndex">back</button>
    </div>
  <div class="main">
    <cube-form :model="model" @submit="submitHandler">
      
      <cube-form-group>
       
        <!--email-->
        <cube-form-item :field="fields[0]"></cube-form-item>
        <!--密码-->
        <cube-form-item :field="fields[1]"></cube-form-item>
      </cube-form-group>

      <cube-form-group>
        <button class="b3">Login</button>
      </cube-form-group>

    </cube-form>
    <router-link to="/register" class="reg">Register</router-link> 
  </div>
</div>
</template>

<script>
//登录接口
import { loginApi } from "@/api/http.js";
export default {
  data() {
    return {
      model: {
        email: "",
        password: ""
      },
      fields: [
        {
          type: "input",
          modelKey: "email",
          label: "email",
          props: {
            placeholder: "Please Enter Email"
          },
          rules: {
            required: true
          },
          messages: {
            required: "Email Cannot Be Empty!"
          }
        },
        {
          type: "input",
          modelKey: "password",
          label: "password",
          props: {
            placeholder: "Please Enter Password",
            type: "password",
            eye: {
              open: false
            }
          },
          rules: {
            required: true
          },
          messages: {
            required: "Password Cannot Be Empty!!"
          }
        }
      ]
    };
  },
  methods: {
    submitHandler(e, model) {
      e.preventDefault();
      //调用注册接口
      var info = {};
      //console.log(model);
      info.email = model.email;
      info.password = model.password;
      //console.log(info);
      loginApi(info).then(
        res => {

          
          if (res.code === 0) {
            //登录成功，跳转到个人中心
            localStorage.setItem('token',res.data)
            this.$store.dispatch('set_Token',res.data)

            //跳转页面, 根据业务需要
            this.$router.push({path:'/personal'})

          }else{
             const toast = this.$createToast({
              txt: "Login Fail",
              type: "error",
              time: 1500
            });
            toast.show();
          }
        }
      );
    },
    goIndex:function() {
      this.$router.push({path:'/index'})

    },
  }
}
  
</script>
<style lang="scss" scoped>
.main {
  padding: 50px 5% 0;
  text-align: center;
}
// 登录
.cube-btn {
  margin-top: 20px;
}
//注册
.reg {
  display: inline-block;
  margin-top: 30px;
  font-size: 18px;
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

.b3{
    background-color: #1b9ff7;
    color:white; 
    height: 35px;
    border: 0;
    width: 350px;
    margin-top: 30px;
    text-align: center;
    font-size: 14pt;
    border-radius: 6px;
    -webkit-transition-duration: 0.3s; /* Safari */
    transition-duration: 0.3s;
}
.b3:hover{
    background-color: white;
    color: #1b9ff7;
}
</style>