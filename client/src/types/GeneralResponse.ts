import type Page from "./Page";

export default interface GeneralResponse<T> {
  message: string;
  data: T;
  page: Page | undefined;
  ok: boolean;
}
