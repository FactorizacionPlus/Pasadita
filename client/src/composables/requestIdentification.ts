import type SaveIdentification from "@/types/Guest/SaveIdentification";
import type GeneralResponse from "@/types/GeneralResponse";
import { useAuthenticatedFetch } from "./useBaseFetch";
import type RegisteredUser from "@/types/User/RegisteredUser";

export async function requestIdentification(identification: SaveIdentification){
    return useAuthenticatedFetch("/api/registered/finish-register")
    .json<GeneralResponse<RegisteredUser>>()
    .post(identification);
}