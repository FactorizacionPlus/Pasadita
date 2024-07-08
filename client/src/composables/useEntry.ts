import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type Entry from "@/types/Entry";
import type { IdentifierType } from "@/types/User/IdentifierType";

export async function getEntriesByUser(uuid: string) {
  return useAuthenticatedFetch("/api/entry/user/" + uuid).json<GeneralResponse<Entry[]>>();
}

export async function createAnonymousUser(data: {
  firstName: string;
  lastName: string;
  identifier: string;
  identifierType: IdentifierType;
}) {
  return useAuthenticatedFetch("/api/access/anonymous/user")
    .json<GeneralResponse<Entry[]>>()
    .post(data);
}

export async function createAnonymousEntry(data: { description: string; identifier: string }) {
  return useAuthenticatedFetch("/api/access/anonymous/entry")
    .json<GeneralResponse<Entry[]>>()
    .post(data);
}
