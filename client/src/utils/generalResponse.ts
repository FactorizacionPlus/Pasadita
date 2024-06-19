import type GeneralResponse from "@/types/GeneralResponse";

export function buildGeneralResponse(object: any, ok: boolean): GeneralResponse<any> {
  return {
    message: object.message,
    data: object.data,
    ok,
  };
}
