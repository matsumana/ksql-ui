import { ActionContext } from 'vuex';
import { State } from '../store/State';
import { MUTATION } from '../store/mutation-types';

export class Api {

  private store: ActionContext<State, State>;

  submit(store: ActionContext<State, State>, sql: string): void {
    // TODO
    console.log(sql);

    this.store = store;

    // TODO move to websocket onmessage
    this.store.commit(MUTATION.WS_ON_MESSAGE, sql);
  }
}
