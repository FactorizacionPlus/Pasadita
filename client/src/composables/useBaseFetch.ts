import { useAuth } from "@/stores/auth";
import { buildGeneralResponse } from "@/utils/generalResponse";
import { createFetch } from "@vueuse/core";

const auth = useAuth();

export const useAuthenticatedFetch = createFetch({
  baseUrl: import.meta.env.VITE_SERVER_ENDPOINT,
  fetchOptions: {
    mode: "cors",
  },
  options: {
    updateDataOnError: true,
    async beforeFetch({ options, cancel }) {
      if (!auth.token) {
        cancel();
      }

      const headers = options?.headers ? new Headers(options.headers) : new Headers();
      if (!headers.has("Authorization")) {
        headers.set("Authorization", "Bearer " + auth.token);
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
