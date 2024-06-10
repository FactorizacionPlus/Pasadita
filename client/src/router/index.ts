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
          name: "home",
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
      path: "/admin",
      component: AdminLayout,
      children: [
        {
          path: "about",
          name: "admin-about",
          component: () => import("../views/guest/AboutView.vue"),
        },
        {
          path: "accesos",
          name: "admin-access",
          component: () => import("../views/guest/AccessView.vue"),
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
        {
          path: "configuraciones",
          name: "admin-configuration",
          component: () => import("../views/admin-panel/ConfigurationsView.vue"),
        },
      ],
    },
    {
      path: "/invitado",
      component: GuestLayout,
      children: [
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
      component: ResidentLayout,
      children: [
        {
          path: "",
          name: "home-resident",
          component: () => import("../views/resident-panel/HomeView.vue"),
        },
        {
          path: "solicitudes",
          name: "resident-request",
          component: () => import("../views/resident-panel/RequestView.vue"),
        },
      ],
    },
    {
      path: "/residente-encargado",
      component: ResidentManagerLayout,
      children: [
        {
          path: "",
          name: "home",
          component: () => import("../views/resident-manager-panel/HomeView.vue"),
        },
        {
          path: "permisos",
          name: "resident-permission",
          component: () => import("../views/resident-manager-panel/PermissionView.vue"),
        },
        {
          path: "residentes",
          name: "resident-resident",
          component: () => import("../views/resident-manager-panel/ResidentsView.vue"),
        },
      ],
    },
  ],
});

console.log(router.getRoutes());

export default router;
