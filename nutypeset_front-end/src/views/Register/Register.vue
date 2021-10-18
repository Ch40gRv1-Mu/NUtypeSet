<template>
<div id="wrap">
    <div class="nav">
      <button class="b1" @click="goIndex">back</button>
    </div>
     <div class="main">
    <cube-form :model="model" @submit="submitHandler">
      
      <cube-form-group>
         <!--名称-->
        <cube-form-item :field="fields[0]"></cube-form-item>
        <!--email-->
        <cube-form-item :field="fields[1]"></cube-form-item>
        <!--phone-->
        <cube-form-item :field="fields[2]"></cube-form-item>
        <!--密码-->
        <cube-form-item :field="fields[3]"></cube-form-item>
      </cube-form-group>


      <cube-form-group>
        <button class="b3">register</button>
      </cube-form-group>


    </cube-form>
    <router-link to="/login" class="reg">login</router-link> 
  </div>
</div>
</template>

<script>

import { registerApi } from "@/api/http.js";
export default {
    data(){
        return {
            model:{
                username:"",
                phone:"",
                email:"",
                password:""
            },
            fields:[
                
                {
                    type:'input',
                    modelKey:'username',
                    label:'username',
                    props:{
                        placeHolder:"please enter the username"
                    },
                    rules:{
                        required:true

                    },
                    messages:{
                        required:'username cannot be empty'
                    }

                },
                {
                    type:'input',
                    modelKey:'email',
                    label:'email',
                    props:{
                        placeHolder:"please enter the email"
                    },
                    rules:{
                        required:true

                    },
                    messages:{
                        required:'email cannot be empty'
                    }

                },
                {
                    type:'input',
                    modelKey:'phone',
                    label:'phone',
                    props:{
                        placeHolder:"please enter the phone number"
                    },
                    rules:{
                        required:true

                    },
                    messages:{
                        required:'phone number cannot be empty'
                    }

                },
                {
                    type:'input',
                    modelKey:'password',
                    label:'password',
                    props:{
                        placeHolder:"please enter the username",
                        type:"password",
                        ele:{
                            open:false
                        }
                    },
                    rules:{
                        required:true

                    },
                    messages:{
                        required:'password cannot be empty'
                    }
                }

            ]
        }
    },
    methods:{
        submitHandler(e,model){
            e.preventDefault();
            //apply registerApi
            var info = {};
            info.email = model.email;
            info.username = model.username;
            info.password = model.password;
            info.phone = model.phone;

            registerApi(info).then(
                (res)=>{
                    //console.log(res);
                    if (res.code===0){
                        const toast = this.$createToast(
                            {
                                txt: "register successfully!!",
                                type: 'correct',
                                time: 1500
                            }
                        )
                        toast.show()
                    }
                    else{
                        const toast = this.$createToast(
                            {
                                txt: "Register fail, please use another email(*▔＾▔*)",
                                type: 'error',
                                time: 5000
                            }
                        )
                        toast.show()

                    }
                }
            )
        },
        goIndex:function() {
            this.$router.push({path:'/index'})
        }
    }


 
}
</script>

<style lang="scss" scoped>
.main {
  padding: 50px 5% 0;
  text-align: center;
}
//注册
.cube-btn {
  margin-top: 20px;
}
// 登录
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