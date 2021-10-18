import axios from "../request"
import { get, post } from './axios.js'
//登陆接口
export const loginApi = (email, password) => axios.post("/user/login",{
    "id":id,
    "username": username,
    "password":password
})

//注册接口
export const registerApi = p => post('http://localhost:8080/resume/resumeGenerate', p)//例子


//latex compile api
export const latexCompileApi = (fileContends) => axios.post("/window/latexOneCompile",{
    "fileContends":fileContends
})
