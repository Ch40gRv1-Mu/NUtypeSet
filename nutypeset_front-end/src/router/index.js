import Vue from 'vue'
import VueRouter from 'vue-router'
//import Resume from '../views/Resume/Resume.vue'

Vue.use(VueRouter)

const routes = [
  /*
  {
    path:"/",
    redirect: '/index' 
  },
  */
  
  // {
  //   path: '/',
  //   name:"Home",
  //  component: () =>
  //       import ('../App.vue'),

  // },
  // {
  //   path:"/resume",
  //   name:"Resume",
  //   component:() => 
  //       import("../views/Resume/Resume.vue"),
  // },


  // {
  //   path:'/index',component:index,
  //   component: textWindow
  // }
  
]

const router = new VueRouter({
  routes
})

export default router
