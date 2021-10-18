import { get, post } from './axios.js'
export const api1 = p1 => get('http://localhost:8080/api/v1/pub/window/download/file/', p1)
export const api2 = p2 => post('http://localhost:8080/api/v1/pub/resume/resumeGenerate', p2)//例子

//注册接口
export const registerApi = p3 => post('http://localhost:8080/api/v1/pri/user/register', p3)//例子
export const loginApi = p4 => post('http://localhost:8080/api/v1/pri/user/login', p4)//例子

export const getUserInfo = (token)=>get("http://localhost:8080/api/v1/pri/user/find_by_token",token)