import type GeneralResponse from "@/types/GeneralResponse";
import { useBaseFetch } from "./useBaseFetch";

type GOOGLE_PROMPT = "select_account" | "consent" | "none";

function buildUrl(redirect: string, prompt: GOOGLE_PROMPT = "select_account") {
  const url = new URL(import.meta.env.VITE_GOOGLE_OAUTH);
  const params = new URLSearchParams();

  const redirect_uri = new URL(redirect, window.location.origin);

  params.set("client_id", import.meta.env.VITE_GOOGLE_CLIENT_ID);
  params.set("response_type", "code");
  params.set("scope", "email profile openid");
  params.set("prompt", prompt);
  params.set("redirect_uri", redirect_uri.toString());

  url.search = params.toString();

  return url;
}

export function useLoginUrl(prompt: GOOGLE_PROMPT | undefined = undefined) {
  return buildUrl("/auth/login-google", prompt);
}

export function useRegisterUrl(prompt: GOOGLE_PROMPT | undefined = undefined) {
  return buildUrl("/auth/register-google", prompt);
}

function buildParams(code: string) {
  const params = new URLSearchParams();
  params.set("code", code);
  params.set("redirect_uri", window.location.origin + window.location.pathname);
  return params;
}

export async function makeLoginRequest(tempCode: string) {
  const params = buildParams(tempCode);
  return useBaseFetch("/auth/login/google?" + params.toString()).json<GeneralResponse<string>>();
}
export async function makeRegisterRequest(tempCode: string) {
  const params = buildParams(tempCode);
  return useBaseFetch("/auth/register/google?" + params.toString()).json<GeneralResponse<string>>();
}
