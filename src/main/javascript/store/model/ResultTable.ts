import { ResultBase } from './ResultBase';

export interface ResultTable extends ResultBase {
  title: string[];
  data: any[][];
}
