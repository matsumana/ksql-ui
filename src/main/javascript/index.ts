import Vue from 'vue';
import store from './store';
import router from './router';
import Hello from './components/Hello.vue';

new Vue({
  router,
  store,
  render: h => h(Hello),
}).$mount('#app');
