import type GeneralResponse from "@/types/GeneralResponse";
import { buildGeneralResponse } from "@/utils/generalResponse";

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

async function buildAuthRequest(
  endpoint: string,
  tempCode: string
): Promise<GeneralResponse<string> | null> {
  const loginEndpoint = new URL(endpoint, import.meta.env.VITE_SERVER_ENDPOINT);
  const params = buildParams(tempCode);
  loginEndpoint.search = params.toString();

  let response: Response;
  let json: any;
  try {
    response = await fetch(loginEndpoint);
    json = await response.json();
    if (!response.ok) {
      console.error(json);
      return buildGeneralResponse(json, response.ok);
    }
  } catch (err) {
    console.error(err);
    return null;
  }

  return buildGeneralResponse(json, response.ok);
}

export async function makeLoginRequest(tempCode: string) {
  return buildAuthRequest("/auth/login/google", tempCode);
}
export async function makeRegisterRequest(tempCode: string) {
  return buildAuthRequest("/auth/register/google", tempCode);
}
