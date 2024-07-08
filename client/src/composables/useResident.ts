import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type Resident from "@/types/User/Resident";
import type ChangeRole from "@/types/Residence/ChangeRole";

export async function getResidents() {
  return useAuthenticatedFetch("/api/residents").json<GeneralResponse<Resident[]>>();
}

export async function updateResidentRole(changeRole: ChangeRole) {
  return useAuthenticatedFetch("/api/residents/role")
    .json<GeneralResponse<string>>()
    .post(changeRole);
}
