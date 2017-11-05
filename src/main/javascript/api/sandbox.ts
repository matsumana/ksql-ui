import axios from 'axios';

const HTTP_URL = 'http://localhost:8080/hello';
const WS_URL = 'ws://localhost:8080/greeter';

export class Sandbox {

  private ws: WebSocket;

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

    // FIXME can't handle onmessage, when click a button
    this.ws.onmessage = (ev: MessageEvent) => {
      console.log('Server: ' + ev.data);
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

  send(value: string, callback: (ev: MessageEvent) => void): void {
    // FIXME can't handle onmessage, when click a button
    // this.ws.onmessage = callback;
    console.log(callback);
    this.ws.send(value);
  }
}
