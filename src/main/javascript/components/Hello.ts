import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  props: ['name', 'initialEnthusiasm'],
  data() {
    return {
      ws: (() => {
        const ws = new WebSocket('ws://localhost:8080/greeter');
        ws.onopen = function () {
          console.log('WebSocket onopen');
        };
        ws.onerror = function (error) {
          console.log('WebSocket Error ' + error);
        };
        ws.onmessage = function (e) {
          console.log('Server: ' + e.data);
        };

        return ws;
      })(),
      enthusiasm: this.initialEnthusiasm,
    };
  },
  methods: {
    increment() {
      this.enthusiasm = this.enthusiasm + 1;
    },
    decrement() {
      if (this.enthusiasm > 1) {
        this.enthusiasm = this.enthusiasm - 1;
      }
    },
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
    },
    send() {
      this.ws.send('hoge');
    },
  },
  computed: {
    exclamationMarks(): string {
      return Array(this.enthusiasm + 1).join('!');
    },
  },
});
