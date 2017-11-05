<template>
  <div>
    <div>
      Name: <input type="text" v-model="userName" @input="inputName">
    </div>
    <div>
      <div class="greeting">Hello {{userName}}{{exclamationMarks}}</div>
      <button @click="decrement">-</button>
      <button @click="increment">+</button>
    </div>
    <br>
    <div>
      <button @click="post">post</button>
      {{postResponse}}
    </div>
    <br>
    <div>
      <button @click="send">send</button>
      {{wsResponse}}
    </div>
  </div>
</template>

<script lang="ts" src="./Hello.ts"></script>

<style>
  .greeting {
    font-size: 20px;
  }
</style>
