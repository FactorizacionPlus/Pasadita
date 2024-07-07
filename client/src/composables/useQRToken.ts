import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function getQrToken() {
  return useAuthenticatedFetch("/api/access/generate").json<GeneralResponse<string>>();
}
