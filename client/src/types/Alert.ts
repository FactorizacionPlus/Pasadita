export enum AlertType {
    INFO,
    ERROR,
    SUCCESS,
    WARNING,
  }
  export default interface Alert {
    message: string;
    type: AlertType;
  }
  