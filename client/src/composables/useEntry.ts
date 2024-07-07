import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type Entry from "@/types/Entry";

export async function getEntriesByUser(uuid: string) {
  return useAuthenticatedFetch("/api/entry/user/" + uuid).json<GeneralResponse<Entry[]>>();
}
