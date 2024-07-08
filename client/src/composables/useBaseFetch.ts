import { useAuth } from "@/stores/auth";
import { useTerminal } from "@/stores/terminal";
import { buildGeneralResponse } from "@/utils/generalResponse";
import { createFetch } from "@vueuse/core";

const auth = useAuth();
const terminal = useTerminal();

export const useAuthenticatedFetch = createFetch({
  baseUrl: import.meta.env.VITE_SERVER_ENDPOINT,
  fetchOptions: {
    mode: "cors",
  },
  options: {
    updateDataOnError: true,
    async beforeFetch({ options, cancel }) {
      if (!auth.token && !terminal.terminalLogin) {
        cancel();
      }

      const headers = (options.headers as { [key: string]: string }) ?? {};
      if (!headers["Authorization"]) {
        if (auth.token) {
          headers["Authorization"] = "Bearer " + auth.token;
        } else if (terminal.terminalLogin) {
          const login = terminal.terminalLogin;
          headers["Authorization"] =
            "Basic " + window.btoa(login.terminalType + ":" + login.password);
        }
      }

      options.headers = headers;
      return { options };
    },
    afterFetch(ctx) {
      if (typeof ctx.data != "object") {
        return ctx;
      }
      ctx.data = buildGeneralResponse(ctx.data, ctx.response.ok);
      return ctx;
    },
    onFetchError(ctx) {
      if (ctx.data == null) {
        ctx.data = buildGeneralResponse({ message: "Unknown error" }, false);
      } else {
        if (typeof ctx.data != "object") {
          return ctx;
        }
        ctx.data = buildGeneralResponse(ctx.data, false);
      }
      return ctx;
    },
  },
});

export const useBaseFetch = createFetch({
  baseUrl: import.meta.env.VITE_SERVER_ENDPOINT,
  fetchOptions: {
    mode: "cors",
  },
  options: {
    updateDataOnError: true,
    afterFetch(ctx) {
      if (typeof ctx.data != "object") {
        return ctx;
      }
      ctx.data = buildGeneralResponse(ctx.data, ctx.response.ok);
      return ctx;
    },
    onFetchError(ctx) {
      if (ctx.data == null) {
        ctx.data = buildGeneralResponse({ message: "Unknown error" }, false);
      } else {
        if (typeof ctx.data != "object") {
          return ctx;
        }
        ctx.data = buildGeneralResponse(ctx.data, false);
      }
      return ctx;
    },
  },
});
