import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type Permission from "@/types/Permission";

export async function getOwnPermissions() {
  return useAuthenticatedFetch("/api/permission/own").json<GeneralResponse<Permission[]>>();
}
