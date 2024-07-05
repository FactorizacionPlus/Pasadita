import { useAuth } from "@/stores/auth";
import { useTerminal } from "@/stores/terminal";
import { useUser } from "@/stores/user";
import type { RouteRecordName, Router } from "vue-router";
import { RoleRootPath, type CustomRouteMeta, type ViewRole } from ".";

const allowedAnonymous: RouteRecordName[] = [
  "login",
  "login-google",
  "register-google",
  "terminal-login",
];

export function applyAuthRouting(router: Router) {
  const auth = useAuth();
  const user = useUser();
  const terminal = useTerminal();

  router.beforeEach((to, from, next) => {
    console.log({ to, from });

    const isInAnonymous = allowedAnonymous.includes(to.name ?? "");

    let role: ViewRole | null = null;
    if (user.user) {
      role = user.user.role;
    } else if (terminal.terminalLogin) {
      if (terminal.terminalLogin.type == "MANUAL") {
        role = "TERMINAL_MANUAL";
      } else {
        role = "TERMINAL";
      }
    }

    if (role != null) {
      // Logged in
      if (to.path.startsWith("/auth/logout")) {
        auth.logout();
        terminal.logout();
        return next("/auth/login");
      }

      const rootPath = RoleRootPath[role];

      if (isInAnonymous) {
        // Is accessing anonymous routes like "login" and "register"
        return next(rootPath);
      }

      const meta = to.meta as CustomRouteMeta;
      if (role == meta.role) {
        return next();
      }

      // Allow if logged in
      if (to.path != rootPath) return next(rootPath);
      else return next();
    } else {
      // Not logged in
      if (isInAnonymous) {
        // Allow anonymous routes
        return next();
      }

      // Deny other routes
      return next("/auth/login");
    }
  });
}
