import { ResponseBase } from './ResponseBase';

export interface ResponseTable extends ResponseBase {
  title: string[];
  data: any[][];
}
