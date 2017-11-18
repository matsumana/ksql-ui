import * as assert from 'power-assert';
import { convertToJsonObj } from '../../../main/javascript/store/mutations';

describe('mutations', () => {
  it('convert CREATE statement response', () => {
    const json: string = `{
      "mode": 0,
      "sequence": 10,
      "sql": "CREATE STREAM pageviews_female AS SELECT users_original.userid AS userid, pageid, regionid, gender FROM pageviews_original LEFT JOIN users_original ON pageviews_original.userid = users_original.userid WHERE gender = 'FEMALE'",
      "text": "Message\\n----------------------------\\nStream created and running"
      }`;

    const jsonObj = convertToJsonObj(json);

    assert.equal(jsonObj.mode, 0);
    assert.equal(jsonObj.sequence, 10);
    assert.equal(jsonObj.sql.substring(0, 13), 'CREATE STREAM');
  });
});
