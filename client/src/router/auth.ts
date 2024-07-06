import { useAuth } from "@/stores/auth";
import { useTerminal } from "@/stores/terminal";
import type { RouteRecordName, Router } from "vue-router";
import { RoleRootPath, type CustomRouteMeta } from ".";
import { getViewRole } from "@/utils/viewRole";

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

    const isInAnonymous = allowedAnonymous.includes(to.name ?? "");

    const role = getViewRole();
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
