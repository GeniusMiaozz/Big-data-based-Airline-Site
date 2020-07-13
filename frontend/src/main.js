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
import echarts from 'echarts'
import vueResource from 'vue-resource'
import 'font-awesome/css/font-awesome.css'

// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.use(vueResource)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.prototype.$message = ElementUI.Message
Vue.prototype.$echarts = echarts;

require("./assets/axios/mock")
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
