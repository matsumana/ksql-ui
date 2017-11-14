<template>
  <div>
    <div class="tile is-parent">
      <article class="tile is-child box">
        <brace
          style="min-height: 500px; min-width: 100%"
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

    <hr>

    <div class="tile is-parent">
      <article class="tile is-child box">
        <div>SQL</div>
        <pre>SELECT * FROM pageviews_female LIMIT 3</pre>
        <br>
        <div>Result</div>
        <table class="table is-bordered is-striped is-narrow">
          <thead>
          <tr>
            <th></th>
            <th>Col1</th>
            <th>Col2</th>
            <th>Col3</th>
            <th>Col4</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td class="is-icon">
              1
            </td>
            <td>
              aaa
            </td>
            <td>
              bbb
            </td>
            <td>
              ccc
            </td>
            <td>
              ddd
            </td>
          </tr>
          <tr>
            <td class="is-icon">
              2
            </td>
            <td>
              aaa
            </td>
            <td>
              bbb
            </td>
            <td>
              ccc
            </td>
            <td>
              ddd
            </td>
          </tr>
          <tr>
            <td class="is-icon">
              3
            </td>
            <td>
              aaa
            </td>
            <td>
              bbb
            </td>
            <td>
              ccc
            </td>
            <td>
              ddd
            </td>
          </tr>
          </tbody>
        </table>
      </article>
    </div>
    <div class="tile is-parent">
      <article class="tile is-child box">
        <div>SQL</div>
        <pre>CREATE STREAM pageviews_female AS SELECT users_original.userid AS userid, pageid, regionid, gender FROM pageviews_original LEFT JOIN users_original ON pageviews_original.userid = users_original.userid WHERE gender = 'FEMALE'</pre>
        <br>
        <div>Result</div>
        <pre>Message
----------------------------
Stream created and running</pre>
      </article>
    </div>
  </div>
</template>

<style lang="sass" src="bulma"></style>
<style lang="scss">
</style>

<script lang="ts" src="./App.ts"></script>
