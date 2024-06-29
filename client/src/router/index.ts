import { createRouter, createWebHistory } from "vue-router";
import GuestLayout from "@/layouts/GuestLayout.vue";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import AdminLayout from "@/layouts/AdminLayout.vue";
import ResidentLayout from "@/layouts/ResidentLayout.vue";
import ResidentManagerLayout from "@/layouts/ResidentManagerLayout.vue";

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
        {
          path: "login",
          name: "login",
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
      ],
    },
    {
      path: "/admin",
      name: "admin",
      component: AdminLayout,
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
          path: "permisos",
          name: "admin-history",
          component: () => import("../views/admin-panel/PermissionsView.vue"),
        },
        {
          path: "terminales",
          name: "admin-terminales",
          component: () => import("../views/admin-panel/TerminalsView.vue"),
        },
        {
          path: "residencias",
          name: "admin-residence",
          component: () => import("../views/admin-panel/ResidencyView.vue"),
        },
        {
          path: "residentes",
          name: "admin-resident",
          component: () => import("../views/admin-panel/ResidentsView.vue"),
        },
        {
          path: "invitados",
          name: "admin-guest",
          component: () => import("../views/admin-panel/GuestsUsersView.vue"),
        },
      ],
    },
    {
      path: "/invitado",
      name: "guest",
      component: GuestLayout,
      children: [
        {
          path: "login",
          name: "guest-login",
          component: () => import("../views/guest/LoginView.vue"),
        },
        {
          path: "",
          name: "guest-home",
          component: () => import("../views/guest/HomeView.vue"),
        },
        {
          path: "terminal",
          name: "guest-terminal",
          component: () => import("../views/guest/TerminalView.vue"),
        },
      ],
    },
    {
      path: "/residente",
      name: "resident",
      component: ResidentLayout,
      children: [
        {
          path: "",
          name: "resident-home",
          component: () => import("../views/resident-panel/HomeView.vue"),
        },
      ],
    },
    {
      path: "/residente-encargado",
      name: "resident-chief",
      component: ResidentManagerLayout,
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
      component: ResidentManagerLayout,
      children: [
        {
          path: "login",
          name: "terminal-login",
          component: () => import("../views/terminal/LoginView.vue"),
        },
        {
          path: "",
          name: "terminal-home",
          component: () => import("../views/terminal/HomeView.vue"),
        },
      ],
    },
  ],
});

export default router;
