import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //数据
    token: localStorage.getItem("token") || "",
    username: localStorage.getItem("username") || "",
    pdfsrc: localStorage.getItem("pdfsrc") || "",
    latexFinalCode: localStorage.getItem("latexFinalCode") || "",
    latexSrc: localStorage.getItem("latexSrc") || ""
  },
  //同步修改state里面的值
  mutations: {
    SET_TOKEN:(state, token)=>{
      state.token = token;
    },
    SET_USERNAME:(state,username)=>{
      state.username = username;
    },
    SET_PDFSRC:(state,pdfsrc)=>{
      state.pdfsrc = pdfsrc;
    },
    SET_LATEXFINALCODE:(state,latexFinalCode)=>{
      state.latexFinalCode= latexFinalCode;
    },
    SET_WEBUSEREMAIL:(state,webUserEmail)=>{
      state.webUserEmail= webUserEmail;
    },
    SET_LATEXSRC:(state,latexSrc)=>{
      state.latexSrc= latexSrc;
    },
    
  },
  //异步调用mutations里面的方法
  //context.commit 利用上下文触发mutations某个方法
  //vue代码里面 this.$store.dispatch触发action里的方法
  actions: {
    set_Token(context, token){
      context.commit('SET_TOKEN',token)
    },
    set_Username(context, username){
      context.commit('SET_USERNAME',username)
    },
    set_Pdfsrc(context, pdfsrc){
      context.commit('SET_PDFSRC',pdfsrc)
    },
    set_Latexfinalcode(context, latexFinalCode){
      context.commit('SET_LATEXFINALCODE',latexFinalCode)
    },
    set_Latexsrc(context, latexSrc){
      context.commit('SET_LATEXSRC',latexSrc)
    },
    set_Webuseremail(context, webUserEmail){
      context.commit('SET_WEBUSEREMAIL',webUserEmail)
    },
    clearToken(context){
      context.commit("SET_TOKEN","");
      context.commit("SET_USERNAME","");
      context.commit("SET_PDFSRC","");
      context.commit("SET_LATEXFINALCODE","");
      context.commit("SET_WEBUSEREMAIL","");
      context.commit("SET_LATEXSRC","");
    },
    clearLatexfinalcode(contex){
      context.commit("SET_LATEXFINALCODE","");
    },
  },
  modules: {
  }
})
