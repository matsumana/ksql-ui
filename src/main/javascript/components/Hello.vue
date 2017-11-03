<template>
  <div>
    <div class="greeting">Hello {{name}}{{exclamationMarks}}</div>
    <button @click="decrement">-</button>
    <button @click="increment">+</button>
    <button @click="post">post</button>
    <button @click="send">send</button>
  </div>
</template>

<script lang="ts" src="./Hello.ts"></script>

<style>
  .greeting {
    font-size: 20px;
  }
</style>
