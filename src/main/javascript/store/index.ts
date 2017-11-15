import Vue from 'vue';
import Vuex, { ActionContext, ActionTree, GetterTree, MutationTree } from 'vuex';
import { MUTATION } from './mutation-types';
import { ACTION } from './action-types';
import { Api } from '../api';
import { Sandbox } from '../api/sandbox';

Vue.use(Vuex);

export class State {
  // sandbox
  userName = '';
  enthusiasm = 0;
  postResponse = '';
  wsResponse = '';

  // App
  sql = '';

  // TODO
  results = '';
}

const sandbox = new Sandbox();
const api = new Api();

const actions = <ActionTree<State, any>> {
  // sandbox
  [ACTION.INPUT_NAME](store: ActionContext<State, State>, userName: string) {
    store.commit(MUTATION.INPUT_NAME, userName);
  },
  [ACTION.INCREMENT](store: ActionContext<State, State>) {
    store.commit(MUTATION.INCREMENT);
  },
  [ACTION.DECREMENT](store: ActionContext<State, State>) {
    store.commit(MUTATION.DECREMENT);
  },
  [ACTION.HTTP_POST](store: ActionContext<State, State>) {
    sandbox.post(store.state.userName, (data: string) => {
      store.commit(MUTATION.HTTP_POST, data);
    });
  },
  [ACTION.WS_SEND](store: ActionContext<State, State>) {
    sandbox.send(store, store.state.userName);
  },

  // App
  [ACTION.INPUT_SQL](store: ActionContext<State, State>, sql: string) {
    store.commit(MUTATION.INPUT_SQL, sql);
  },
  // [ACTION.CANCEL](store: ActionContext<State, State>, sql: string) {
  //   store.commit(MUTATION.CANCEL, sql);
  // },
  [ACTION.SUBMIT](store: ActionContext<State, State>) {
    api.submit(store, store.state.sql);
  },
};

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
    state.results = result;
  },
};

const getters = <GetterTree<State, any>> {
  // sandbox
  exclamationMarks: (state: State) => Array(state.enthusiasm + 1).join('!'),
  postResponse: (state: State) => state.postResponse,
  wsResponse: (state: State) => state.wsResponse,

  // App
  results: (state: State) => state.results,
};

export default new Vuex.Store({
  state: new State(),
  actions,
  mutations,
  getters,
});
