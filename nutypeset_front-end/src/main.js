import Vue from 'vue'
import './cube-ui'
import App from './App.vue'
import VueHighlightJS from 'vue-highlightjs'
import 'highlight.js/styles/arduino-light.css' // or other highlight.js theme
import Resume from './views/Resume/Resume.vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import routers from './routers'
import axios from 'axios'
import Prism from "prismjs";
import { MarkdownPreview } from 'vue-meditor'
import store from './store'
import VueCodeMirror from 'vue-codemirror'
import 'codemirror/lib/codemirror.css'

Vue.use(VueCodeMirror)
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueHighlightJS)
Vue.use(MarkdownPreview)


const router = new VueRouter({
  mode: 'history',
  routes: routers
})
Vue.config.productionTip = false
new Vue({
  el: '#wrap',
  router,
  store,
  render: h => h(App)
})

// new Vue({
//   el: '#resume',
//   router,
//   render: h => h(Resume)
// })

