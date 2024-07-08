import type Residence from "@/types/Residence";
import type Resident from "@/types/User/Resident";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type Entry from "@/types/Entry";
import type SaveResidence from "@/types/Residence/SaveResidence";
import type AsignResident from "@/types/Residence/AsignResident";

export async function getResidence() {
  return useAuthenticatedFetch("/api/residence").json<GeneralResponse<Residence[]>>();
}

export async function getResidentByResidence(residence: string) {
  return useAuthenticatedFetch("/api/residents/residence/" + residence).json<
    GeneralResponse<Resident[]>
  >();
}

export async function getEntryByResident(residence: string) {
  return useAuthenticatedFetch("/api/entry/residence/" + residence).json<
    GeneralResponse<Entry[]>
  >();
}

export async function getResidenceByUUID(uuid: string) {
  return useAuthenticatedFetch("/api/residence/" + uuid).json<GeneralResponse<Residence>>();
}

export async function saveResidence(residence: SaveResidence) {
  return useAuthenticatedFetch("/api/residence").json<GeneralResponse<string>>().post(residence);
}

export async function asignResidentToResidence(asignResident: AsignResident) {
  return useAuthenticatedFetch("/api/residence/assign").json<GeneralResponse<string>>().post(asignResident);
}