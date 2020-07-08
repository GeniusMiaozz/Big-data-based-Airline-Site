import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
import {BootstrapVue,IconsPlugin} from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.prototype.$message = ElementUI.Message


require("./assets/axios/mock")
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
