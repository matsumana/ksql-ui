import Vue from 'vue';
import VueRouter from 'vue-router';
import Sandbox from './components/Sandbox';

Vue.use(VueRouter);

const routes = [
  {
    path: '/', component: Sandbox,
  },
];

export default new VueRouter({
  routes,
});
