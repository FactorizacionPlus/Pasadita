import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type Permission from "@/types/Permission";

export async function getOwnEntry() {
  return useAuthenticatedFetch("/api/permission/own-active").json<GeneralResponse<Permission>>();
}
