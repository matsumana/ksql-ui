import { ResultBase } from './model/ResultBase';

export class State {
  // sandbox
  userName = '';
  enthusiasm = 0;
  postResponse = '';
  wsResponse = '';

  // App
  sql = '';
  results: ResultBase[] = [];
}
