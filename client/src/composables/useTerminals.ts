// src/services/terminalService.ts
import type Terminal from "@/types/Terminal";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function getAllTerminals() {
  return useAuthenticatedFetch("/api/terminals").json<GeneralResponse<Terminal[]>>();
}
