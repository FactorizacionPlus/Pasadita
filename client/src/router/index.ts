import { createRouter, createWebHistory, type RouteMeta } from "vue-router";
import GuestLayout from "@/layouts/GuestLayout.vue";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import AdminLayout from "@/layouts/AdminLayout.vue";
import type { UserRole } from "@/types/User/UserRole";

export type ViewRole = UserRole | "TERMINAL" | "TERMINAL_MANUAL";

export const RoleRootPath: { [key in ViewRole]: string } = {
  ROLE_INVITED: "/invitado",
  ROLE_RESIDENT: "/residente",
  ROLE_RESIDENT_SUDO: "/residente-encargado",
  ROLE_ADMIN: "/admin",
  TERMINAL: "/terminal/camera",
  TERMINAL_MANUAL: "/terminal",
};

export interface CustomRouteMeta extends RouteMeta {
  rootPath?: string;
  role: ViewRole;
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: DefaultLayout,
      children: [
        {
          path: "/",
          name: "page-home",
          component: () => import("../views/HomeView.vue"),
        },
      ],
    },
    {
      path: "/auth",
      component: GuestLayout,
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("../views/LoginView.vue"),
        },
        {
          path: "login-google",
          name: "login-google",
          component: () => import("../views/AuthGoogleView.vue"),
        },
        {
          path: "register-google",
          name: "register-google",
          component: () => import("../views/AuthGoogleView.vue"),
        },
        {
          path: "terminal",
          name: "terminal-login",
          component: () => import("../views/terminal/LoginView.vue"),
        },
      ],
    },
    {
      path: "/admin",
      name: "admin",
      component: AdminLayout,
      meta: { rootPath: "/admin", role: "ROLE_ADMIN" } as CustomRouteMeta,
      children: [
        {
          path: "",
          name: "admin-dashboard",
          component: () => import("../views/admin-panel/DashboardView.vue"),
        },
        {
          path: "configuraciones",
          name: "admin-settings",
          component: () => import("../views/admin-panel/SettingsView.vue"),
        },
        {
          path: "about",
          name: "admin-about",
          component: () => import("../views/guest/AboutView.vue"),
        },
        {
          path: "entradas",
          name: "admin-entry",
          component: () => import("../views/admin-panel/EntriesView.vue"),
        },
        {
          path: "terminales",
          name: "admin-terminales",
          component: () => import("../views/admin-panel/TerminalsView.vue"),
        },
        {
          path: "terminal/:terminal",
          name: "admin-terminal-history",
          component: () => import("../views/admin-panel/TerminalHistoryView.vue"),
        },
        {
          path: "residencias",
          name: "admin-residence",
          component: () => import("../views/admin-panel/ResidencyView.vue"),
        },
        {
          path: "usuarios",
          name: "admin-users",
          component: () => import("../views/admin-panel/UsersView.vue"),
        },
        {
          path: "usuario/:usuario",
          name: "admin-user-history",
          component: () => import("../views/admin-panel/UserHistoryView.vue"),
        },
      ],
    },
    {
      path: "/invitado",
      name: "guest",
      component: DefaultLayout,
      meta: { rootPath: "/invitado", role: "ROLE_INVITED" } as CustomRouteMeta,
      children: [
        {
          path: "",
          name: "guest-home",
          component: () => import("../views/guest/HomeView.vue"),
        },
      ],
    },
    {
      path: "/residente",
      name: "resident",
      component: DefaultLayout,
      meta: { rootPath: "/residente", role: "ROLE_RESIDENT" } as CustomRouteMeta,
      children: [
        {
          path: "",
          name: "resident-home",
          component: () => import("../views/resident-panel/HomeView.vue"),
        },
        {
          path: "historial-solicitudes",
          name: "resident-requests-history",
          component: () => import("../views/resident-panel/AccessRequestsView.vue"),
        },
      ],
    },
    {
      path: "/residente-encargado",
      name: "resident-chief",
      component: DefaultLayout,
      meta: { rootPath: "/residente-encargado", role: "ROLE_RESIDENT_SUDO" } as CustomRouteMeta,
      children: [
        {
          path: "",
          name: "resident-chief-home",
          component: () => import("../views/resident-manager-panel/HomeView.vue"),
        },
        {
          path: "permisos",
          name: "resident-chief-permission",
          component: () => import("../views/resident-manager-panel/PermissionView.vue"),
        },
        {
          path: "residentes",
          name: "resident-chief-residents",
          component: () => import("../views/resident-manager-panel/ResidentsView.vue"),
        },
      ],
    },
    {
      path: "/terminal",
      name: "terminal",
      component: DefaultLayout,
      meta: { rootPath: "/terminal", role: "TERMINAL" } as CustomRouteMeta,
      children: [
        {
          path: "",
          name: "terminal-home",
          meta: { role: "TERMINAL_MANUAL" } as CustomRouteMeta,
          component: () => import("../views/terminal/HomeView.vue"),
        },
        {
          path: "camera",
          name: "terminal-camera",
          meta: { role: "TERMINAL" } as CustomRouteMeta,
          component: () => import("../views/terminal/CameraView.vue"),
        },
      ],
    },
  ],
});

export default router;
