import type GeneralResponse from "@/types/GeneralResponse";
import type Page from "@/types/Page";

export function buildGeneralResponse(object: any, ok: boolean): GeneralResponse<any> {
  const message = object.message ?? "";
  let data = object.data;

  let page: Page | undefined;
  if (data && typeof data === "object" && data["pageable"]) {
    page = {
      empty: data["empty"],
      first: data["first"],
      last: data["last"],
      number: data["number"],
      numberOfElements: data["numberOfElements"],
      size: data["size"],
      totalElements: data["totalElements"],
      totalPages: data["totalPages"],
    };

    data = data["content"];
  }

  return {
    message,
    data,
    page,
    ok,
  };
}
