import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type RegisteredUser from "@/types/User/RegisteredUser";

export async function getEverythingEverywhereAllAtOnce() {
    return useAuthenticatedFetch('/api/users/everything/everywhere/all-at-once').json<GeneralResponse<RegisteredUser>>();
}