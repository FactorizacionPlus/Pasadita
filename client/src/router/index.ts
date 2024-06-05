import { createRouter, createWebHistory } from "vue-router";
import GuestLayout from "@/layouts/GuestLayout.vue";
import DefaultLayout from "@/layouts/DefaultLayout.vue";

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
      ],
    },
    {
      path: "/guest",
      component: GuestLayout,
      children: [
        {
          path: "about",
          name: "about",
          component: () => import("../views/guest/AboutView.vue"),
        },
        {
          path: "access",
          name: "access",
          component: () => import("../views/guest/AccessView.vue"),
        },
        {
          path: "terminal",
          name: "terminal",
          component: () => import("../views/guest/TerminalView.vue"),
        },
      ],
    },
  ],
});

export default router;
