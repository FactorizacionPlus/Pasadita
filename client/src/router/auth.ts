import { useAuth } from "@/stores/auth";
import type { RouteRecordName, Router } from "vue-router";

const allowedAnonymous: RouteRecordName[] = ["login", "login-google", "register-google"];

export function applyAuthRouting(router: Router) {
  const auth = useAuth();

  router.beforeEach((to, from, next) => {
    const isLoggedIn = auth.token != null;

    const isInAnonymous = allowedAnonymous.includes(to.name ?? "");

    if (isLoggedIn) {
      // Logged in

      if (to.path.startsWith("/logout")) {
        auth.logout();
        return next("/login");
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
        return next("/login");
      }
    }
  });
}
