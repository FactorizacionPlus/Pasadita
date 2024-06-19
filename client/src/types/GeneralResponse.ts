export default interface GeneralResponse<T> {
  message: string;
  data: T;
  ok: boolean;
}
