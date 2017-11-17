<template>
  <div>
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
