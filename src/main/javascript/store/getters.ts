import { GetterTree } from 'vuex';
import { State } from './State';

const getters = <GetterTree<State, any>> {
  // sandbox
  exclamationMarks: (state: State) => Array(state.enthusiasm + 1).join('!'),
  postResponse: (state: State) => state.postResponse,
  wsResponse: (state: State) => state.wsResponse,

  // App
  results: (state: State) => state.results,
};

export default getters;
