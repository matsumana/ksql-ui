<template>
  <div>
    <div class="tile is-parent">
      <article class="tile is-child box">
        <brace
          style="min-height: 150px; min-width: 100%"
          :theme="'chrome'"
          :mode="'sql'"
          @code-change="oncodeChange">
        </brace>
      </article>
    </div>
    <div class="tile is-parent">
      <div class="control is-grouped">
        <div class="control">
          <a class="button is-primary" @click="submit">Submit</a>
          <!--
          Acutually, brace is having itself value internally. so brace has methods both setValue and getValue.
          However, Brace.vue doen't provide a method calling brace's setValue method.
          Therefore, I can't implemente this feature.
          -->
          <!--<a class="button" @click="cancel">Cancel</a>-->
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="sass" src="bulma"></style>
<style lang="scss">
</style>

<script lang="ts" src="./Editor.ts"></script>
