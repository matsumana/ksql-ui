<template>
  <div>
    <div class="greeting">Hello {{name}}{{exclamationMarks}}</div>
    <button @click="decrement">-</button>
    <button @click="increment">+</button>
    <button @click="post">post</button>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import axios from "axios";

  export default Vue.extend({
    props: ['name', 'initialEnthusiasm'],
    data() {
      return {
        enthusiasm: this.initialEnthusiasm,
      }
    },
    methods: {
      increment() {
        this.enthusiasm++;
      },
      decrement() {
        if (this.enthusiasm > 1) {
          this.enthusiasm--;
        }
      },
      post() {
        const url = "http://localhost:8080/hello";
        const param: { [key: string]: string; } = {
          "name": this.name
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
      }
    }
  });
</script>

<style>
  .greeting {
    font-size: 20px;
  }
</style>
