import Vue from 'vue';
import store from './store';
import router from './router';
import Sandbox from './components/Sandbox.vue';

new Vue({
  router,
  store,
  render: h => h(Sandbox),
}).$mount('#app');
