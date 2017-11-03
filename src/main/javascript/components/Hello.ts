import Vue from 'vue';
import Component from 'vue-class-component';
import axios from 'axios';

@Component({
  props: {
    name: String,
    enthusiasm: Number,
  },
})
export default class Hello extends Vue {
  name: string;
  enthusiasm: number;
  ws: WebSocket;

  constructor() {
    super();

    this.ws = new WebSocket('ws://localhost:8080/greeter');
    this.ws.onopen = function () {
      console.log('WebSocket onopen');
    };
    this.ws.onerror = function (error) {
      console.log('WebSocket Error ' + error);
    };
    this.ws.onmessage = function (e) {
      console.log('Server: ' + e.data);
    };
  }

  // --- method ----------------------------------------------
  increment() {
    this.enthusiasm = this.enthusiasm + 1;
  }

  decrement() {
    if (this.enthusiasm > 1) {
      this.enthusiasm = this.enthusiasm - 1;
    }
  }

  post() {
    const url = 'http://localhost:8080/hello';
    const param: { [key: string]: string; } = {
      name: this.name,
    };

    axios.post(url, param).then((response) => {
      console.log(response.data);
    }).catch(error => {
      console.error(error);
    });
  }

  send() {
    this.ws.send('hoge');
  }

  // --- computed --------------------------------------------
  get exclamationMarks(): string {
    return Array(this.enthusiasm + 1).join('!');
  }
}
