import Vue from 'vue';
import VueRouter from 'vue-router';
import Hello from './components/Hello';

Vue.use(VueRouter);

const routes = [
  {
    path: '/', component: Hello,
  },
];

export default new VueRouter({
  routes,
});
