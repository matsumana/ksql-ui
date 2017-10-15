import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  props: ['name', 'initialEnthusiasm'],
  data() {
    return {
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
  },
  computed: {
    exclamationMarks(): string {
      return Array(this.enthusiasm + 1).join('!');
    },
  },
});
