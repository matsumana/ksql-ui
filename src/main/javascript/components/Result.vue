<template>
  <div>
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

<style lang="scss">
</style>

<script lang="ts" src="./Result.ts"></script>
