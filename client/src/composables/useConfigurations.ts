import type Configuration from "@/types/Configuration";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";

export async function getConfigurations() {
  return useAuthenticatedFetch("/api/configuration/").json<GeneralResponse<Configuration>>();
}

export async function updateConfigurations(configuration: Configuration) {
  return useAuthenticatedFetch("/api/configuration/")
    .json<GeneralResponse<string>>()
    .patch(configuration);
}
export async function resetConfigurations() {
  return useAuthenticatedFetch("/api/configuration/reset").json<GeneralResponse<string>>();
}
