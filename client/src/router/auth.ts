import { useAuth } from "@/stores/auth";
import { useTerminal } from "@/stores/terminal";
import type { RouteRecordName, Router } from "vue-router";

const allowedAnonymous: RouteRecordName[] = [
  "login",
  "login-google",
  "register-google",
  "terminal-login",
];

export function applyAuthRouting(router: Router) {
  const auth = useAuth();
  const terminal = useTerminal();

  router.beforeEach((to, from, next) => {
    console.log({ to, from });
    const isLoggedIn = auth.token != null || terminal.terminalLogin != null;

    const isInAnonymous = allowedAnonymous.includes(to.name ?? "");

    if (isLoggedIn) {
      // Logged in

      if (to.path.startsWith("/auth/logout")) {
        auth.logout();
        terminal.logout();
        return next("/auth/login");
      }

      if (isInAnonymous) {
        // Is accessing anonymous routes like "login" and "register"
        return next("/");
      } else {
        // Allow if logged in
        return next();
      }
    } else {
      // Not logged in
      if (isInAnonymous) {
        // Allow anonymous routes
        return next();
      } else {
        // Deny other routes
        return next("/auth/login");
      }
    }
  });
}
