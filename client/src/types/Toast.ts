export enum ToastType {
  INFO,
  ERROR,
  SUCCESS,
  WARNING,
  LOADING,
}
export default interface Toast {
  id?: number;
  message: string;
  type: ToastType;
}
