import Vue from 'vue'
import VueRouter from 'vue-router'
import Resume from './views/Resume/Resume.vue'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    redirect: '/index' 
  },
  {
    path: '/index',
    name:'Index',
   component: () =>
        import ('./views/Resume/Index.vue'),
  },
  {
    path:'/resume',
    name:'Resume',
    component:() => 
        import("./views/Resume/Resume.vue"),
  },
  {
    path:'/editor',
    name:'Editor',
    component:() =>
        import("./views/Resume/Editor.vue"),
  },
  {
    path:'home',
    name:"Home",
    component:() =>
      import("./views/Home/Home.vue")
  },
  {
    path: '/register',
    name:'Register',
   component: () =>
        import ('./views/Register/Register.vue'),
  },
  {
    path: '/login',
    name:'Login',
   component: () =>
        import ('./views/Login/Login.vue'),
  },
  {
    path: '/personal',
    name:'Personal',
   component: () =>
        import ('./views/Personal/Personal.vue'),
  }
  

  

  // {
  //   path:'/index',component:index,
  //   component: textWindow
  // }
  
]

//const router = new VueRouter({
//  routes
//})

export default routes
