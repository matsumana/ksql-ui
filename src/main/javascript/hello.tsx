import * as React from "react";

export interface Props {
  content: string;
}

export default class Hello extends React.Component<Props, {}> {
  render() {
    return <div>{this.props.content}</div>
  }
}
