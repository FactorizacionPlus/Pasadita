import { createRouter, createWebHistory } from "vue-router";
import GuestLayout from "@/layouts/GuestLayout.vue";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import AdminLayout from "@/layouts/AdminLayout.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: DefaultLayout,
      children: [
        {
          path: "",
          name: "home",
          component: () => import("../views/HomeView.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("../views/HomeView.vue"),
        }
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
          path: "access",
          name: "admin-access",
          component: () => import("../views/guest/AccessView.vue"),
        },
        {
          path: "entradas",
          name: "admin-entradas",
          component: () => import("../views/admin-panel/EntradasView.vue"),
        },
        {
          path: "permisos",
          name: "admin-historial",
          component: () => import("../views/admin-panel/PermisosView.vue"),
        },
        {
          path: "terminales",
          name: "admin-terminales",
          component: () => import("../views/admin-panel/TerminalesView.vue"),
        },
        {
          path: "residencias",
          name: "admin-residencias",
          component: () => import("../views/admin-panel/ResidenciasView.vue"),
        },
        {
          path: "residentes",
          name: "admin-residentes",
          component: () => import("../views/admin-panel/ResidentesView.vue"),
        },
        {
          path: "invitados",
          name: "admin-invitados",
          component: () => import("../views/admin-panel/InvitadosView.vue"),
        },
      ],
    },
    {
      path: "/guest",
      component: GuestLayout,
      children: [
        {
          path: "terminal",
          name: "guest-terminal",
          component: () => import("../views/guest/TerminalView.vue"),
        },
      ],
    },
  ],
});

console.log(router.getRoutes())

export default router;
