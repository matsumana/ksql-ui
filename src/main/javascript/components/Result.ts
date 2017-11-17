import Vue from 'vue';
import Component from 'vue-class-component';
import store from '../store';

@Component
export default class Result extends Vue {

  // --- computed --------------------------------------------
  get results(): string {
    return store.getters.results;
  }
}
