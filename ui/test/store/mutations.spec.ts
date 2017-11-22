import * as assert from 'power-assert';
import { ResponseTable } from '../../src/store/model/ResponseTable';
import { ResponseText } from '../../src/store/model/ResponseText';

describe('mutations', () => {
  it('convert from CREATE response', () => {
    const json: string = `{
  "mode": 0,
  "sequence": 10,
  "sql": "CREATE STREAM pageviews_xx AS SELECT userid FROM pageviews",
  "text": "Message\\n----------------------------\\nStream created and running"
}`;

    const jsonObj = JSON.parse(json) as ResponseText;

    assert.equal(jsonObj.mode, 0);
    assert.equal(jsonObj.sequence, 10);
    assert.equal(jsonObj.sql, 'CREATE STREAM pageviews_xx AS SELECT userid FROM pageviews');
    assert.equal(jsonObj.text, 'Message\n----------------------------\nStream created and running');
  });

  it('convert from SELECT response', () => {
    const json: string = `{
  "mode": 1,
  "sequence": 11,
  "sql": "SELECT * FROM pageviews_female LIMIT 3",
  "title": [
    "col1", "col2", "col3", "col4"
  ],
  "data": [
    [ "aaa1", "bbb1", "ccc1", "ddd1" ],
    [ "aaa2", "bbb2", "ccc2", "ddd2" ],
    [ "aaa3", "bbb3", "ccc3", "ddd3" ]
  ]
}`;

    const jsonObj = JSON.parse(json) as ResponseTable;

    assert.equal(jsonObj.mode, 1);
    assert.equal(jsonObj.sequence, 11);
    assert.equal(jsonObj.sql, 'SELECT * FROM pageviews_female LIMIT 3');
    assert.equal(jsonObj.title.length, 4);
    assert.equal(jsonObj.title[0], 'col1');
    assert.equal(jsonObj.title[1], 'col2');
    assert.equal(jsonObj.title[2], 'col3');
    assert.equal(jsonObj.title[3], 'col4');
    assert.equal(jsonObj.data.length, 3);
    assert.equal(jsonObj.data[0].length, 4);
    assert.equal(jsonObj.data[0][0], 'aaa1');
    assert.equal(jsonObj.data[0][1], 'bbb1');
    assert.equal(jsonObj.data[0][2], 'ccc1');
    assert.equal(jsonObj.data[0][3], 'ddd1');
  });
});
