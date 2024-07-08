// src/services/terminalService.ts
import type Entry from "@/types/Entry";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type { TerminalType } from "@/types/TerminalType";

export async function getAllEntries() {
  return useAuthenticatedFetch("/api/entry/all").json<GeneralResponse<Entry[]>>();
}

export async function getEntriesByTerminalType(terminalType: TerminalType) {
  return useAuthenticatedFetch(`/api/entry/terminal/${terminalType}`).json<
    GeneralResponse<Entry[]>
  >();
}

export function createEntrySSEByTerminal(terminalType: TerminalType) {
  const serverEndpoint = import.meta.env.VITE_SERVER_ENDPOINT;
  return new EventSource(`${serverEndpoint}/sse/entry/terminal/${terminalType}`);
}
