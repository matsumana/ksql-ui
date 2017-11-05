import Vue from 'vue';
import Vuex, { ActionContext, ActionTree, GetterTree, MutationTree } from 'vuex';
import { MUTATION } from './mutation-types';
import { ACTION } from './action-types';
import { Sandbox } from '../api/sandbox';

Vue.use(Vuex);

class State {
  userName: string = '';
  enthusiasm: number = 0;
  postResponse: string = '';
  wsResponse: string = '';
}

const sandbox = new Sandbox();

const actions = <ActionTree<State, any>> {
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
    sandbox.send(store.state.userName, (ev: MessageEvent) => {
      store.commit(MUTATION.WS_SEND, ev.data);
    });
  },
};

const mutations = <MutationTree<State>> {
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
  [MUTATION.WS_SEND](state: State, value: string) {
    state.wsResponse = value;
  },
};

const getters = <GetterTree<State, any>> {
  exclamationMarks: (state: State) => Array(state.enthusiasm + 1).join('!'),
  postResponse: (state: State) => state.postResponse,
  wsResponse: (state: State) => state.wsResponse,
};

export default new Vuex.Store({
  state: new State(),
  actions,
  mutations,
  getters,
});
