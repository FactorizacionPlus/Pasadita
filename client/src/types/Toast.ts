export enum ToastType {
  INFO,
  ERROR,
  SUCCESS,
  WARNING,
}
export default interface Toast {
  message: string;
  type: ToastType;
}

