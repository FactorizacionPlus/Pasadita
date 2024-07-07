import type Permission from "@/types/Permission";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function deletePermission(permissionId: string) {
  const { data, error } = await useAuthenticatedFetch(`/api/entry/${permissionId}`, {
    method: "DELETE",
  }).json<GeneralResponse<Permission>>();

  if (error.value) {
    console.error("Error deleting permission:", error.value);
    return { success: false, error: error.value };
  }

  return { success: true, data: data.value };
}
