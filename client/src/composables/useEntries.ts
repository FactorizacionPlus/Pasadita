import type Entry from "@/types/Entry";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function getAllEntries() {
  return useAuthenticatedFetch("/api/entry/all").json<GeneralResponse<Entry[]>>();
}
