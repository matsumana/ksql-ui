import { MutationTree } from 'vuex';
import { MUTATION } from './mutation-types';
import { State } from './State';
import { ResultBase } from './model/ResultBase';

const mutations = <MutationTree<State>> {
  // sandbox
  [MUTATION.INPUT_NAME](state: State, userName: string) {
    state.userName = userName;
  },
  [MUTATION.INCREMENT](state: State) {
    state.enthusiasm += 1;
  },
  [MUTATION.DECREMENT](state: State) {
    if (state.enthusiasm > 1) {
      state.enthusiasm -= 1;
    }
  },
  [MUTATION.HTTP_POST](state: State, value: string) {
    state.postResponse = value;
  },
  [MUTATION.WS_RECEIVE](state: State, value: string) {
    state.wsResponse = value;
  },

  // App
  [MUTATION.INPUT_SQL](state: State, sql: string) {
    state.sql = sql;
  },
  // [MUTATION.CANCEL](state: State, sql: string) {
  //   state.sql = sql;
  // },
  [MUTATION.WS_ON_MESSAGE](state: State, result: string) {
    // TODO
    // state.results = result;
    console.log(result);
    const jsonObj: ResultBase = convertToJsonObj('{\n' +
        '  "mode": 0,\n' +
        '  "sequence": 10,\n' +
        '  "sql": "CREATE STREAM pageviews_female' +
        ' AS ' +
        'SELECT users_original.userid AS userid,' +
        ' pageid, regionid, gender' +
        ' FROM pageviews_original' +
        ' LEFT JOIN users_original' +
        ' ON pageviews_original.userid = users_original.userid WHERE gender = \'FEMALE\'",\n' +
        '  "text": "Message\\n----------------------------\\nStream created and running"\n' +
        '}\n');
    console.log(jsonObj);
    state.results.unshift(jsonObj);
    console.log(state.results.length);
    console.log(jsonObj.mode);
  },
};

export const convertToJsonObj = (json: string): ResultBase => {
  return JSON.parse(json);
};

export default mutations;
