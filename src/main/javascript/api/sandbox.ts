import { ActionContext } from 'vuex';
import axios from 'axios';
import { State } from '../store/State';
import { MUTATION } from '../store/mutation-types';

const HTTP_URL = 'http://localhost:8080/hello';
const WS_URL = 'ws://localhost:8080/greeter';

export class Sandbox {

  private ws: WebSocket;
  private store: ActionContext<State, State>;

  constructor() {
    this.initWs();
  }

  private initWs() {
    this.ws = new WebSocket(WS_URL);

    this.ws.onopen = () => {
      console.log('WebSocket onopen');
    };

    this.ws.onerror = (ev: Event) => {
      console.log('WebSocket Error ' + ev);
    };

    this.ws.onmessage = (ev: MessageEvent) => {
      console.log('Server: ' + ev.data);
      this.store.commit(MUTATION.WS_RECEIVE, ev.data);
    };
  }

  post(value: string, callback: (data: string) => void): void {
    const param: { [key: string]: string; } = {
      name: value,
    };

    axios.post(HTTP_URL, param).then((response) => {
      console.log(response.data);
      callback(response.data);
    }).catch(error => {
      console.error(error);
    });
  }

  send(store: ActionContext<State, State>, value: string): void {
    this.store = store;
    this.ws.send(value);
  }
}
