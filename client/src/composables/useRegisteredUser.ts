import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type RegisteredUser from "@/types/User/RegisteredUser";
import type UserStatus from "@/types/User/UserStatus";

export async function getEverythingEverywhereAllAtOnce() {
  return useAuthenticatedFetch("/api/users/everything/everywhere/all-at-once").json<
    GeneralResponse<RegisteredUser[]>
  >();
}

export async function getUserByIdentifier(identifier: string) {
  return useAuthenticatedFetch("/api/users/" + identifier).json<GeneralResponse<RegisteredUser>>();
}

export async function setUserStatus(userStatus: UserStatus) {
  return useAuthenticatedFetch("/api/registered/status")
    .json<GeneralResponse<RegisteredUser>>()
    .post(userStatus);
}

export async function migrateUserToResident(identifier: string) {
  return useAuthenticatedFetch("/api/registered/migrate/resident/" + identifier)
    .json<GeneralResponse<string>>().post()
}
