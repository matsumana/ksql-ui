import Vue from 'vue';
import router from './router';
import App from './components/App.vue';
import store from './store';

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
