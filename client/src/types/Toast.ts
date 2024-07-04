export enum ToastType {
  INFO,
  ERROR,
  SUCCESS,
  WARNING,
}
export default interface Toast {
  id?: number;
  message: string;
  type: ToastType;
}
