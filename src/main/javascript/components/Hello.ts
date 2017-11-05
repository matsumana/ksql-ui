import Vue from 'vue';
import Component from 'vue-class-component';
import { ACTION } from '../store/action-types';
import store from '../store';

@Component
export default class Hello extends Vue {

  // --- input field -----------------------------------------
  userName: string = '';

  // --- method ----------------------------------------------
  inputName(): void {
    store.dispatch(ACTION.INPUT_NAME, this.userName);
  }

  increment(): void {
    store.dispatch(ACTION.INCREMENT);
  }

  decrement(): void {
    store.dispatch(ACTION.DECREMENT);
  }

  post(): void {
    store.dispatch(ACTION.HTTP_POST);
  }

  send(): void {
    store.dispatch(ACTION.WS_SEND);
  }

  // --- computed --------------------------------------------
  get exclamationMarks(): string {
    return store.getters.exclamationMarks;
  }

  get postResponse(): string {
    return store.getters.postResponse;
  }

  get wsResponse(): string {
    return store.getters.wsResponse;
  }
}
