import { MutationTree } from 'vuex';
import { MUTATION } from './mutation-types';
import { State } from './State';

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
  [MUTATION.SUBMIT](state: State) {
    state.sequence = state.sequence + 1;
  },
  [MUTATION.WS_ON_MESSAGE](state: State, json: string) {
    console.log(json);
    const obj = JSON.parse(json);
    state.results.unshift(obj);
  },
};

export default mutations;
