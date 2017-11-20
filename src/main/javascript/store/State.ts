import { ResponseBase } from './model/ResponseBase';

export class State {
  // sandbox
  userName = '';
  enthusiasm = 0;
  postResponse = '';
  wsResponse = '';

  // App
  sequence = 0;
  sql = '';
  results: ResponseBase[] = [];
}
