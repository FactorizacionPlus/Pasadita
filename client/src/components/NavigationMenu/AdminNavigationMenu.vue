<template>
  <nav
    class="sticky top-0 z-10 flex min-w-52 flex-col rounded-lg bg-white p-2 shadow-header md:relative md:p-4"
  >
    <div class="flex justify-between gap-4 md:flex-col md:justify-start">
      <img class="mx-auto hidden h-[45px] w-auto md:block" src="/identity/pasaditaLogo.svg" />
      <img class="block h-12 md:hidden" src="/identity/pasaditaLogoMin.svg" />
      <button
        :class="
          isNavActive
            ? 'bg-blue-200 hover:!bg-blue-100'
            : ''
        "
        @click="toggleNav"
        class="grid size-12 place-items-center rounded-lg text-blue-400 transition-all hover:bg-blue-200 active:scale-95 md:hidden"
      >
        <VueFeather type="menu" class="w-6" stroke-width="1.5"></VueFeather>
      </button>
    </div>
    <div
      :class="isNavActive ? 'block' : 'hidden'"
      class="mt-4 h-full flex-col justify-between gap-1 md:flex"
    >
      <RouterLink
        :key="index"
        v-for="(route, index) in routes"
        :class="
          currentPath == ADMIN_ROUTE + route.link
            ? 'bg-blue-200 hover:!bg-blue-100 font-medium rounded-xl'
            : 'rounded-md hover:bg-blue-100'
        "
        class="flex min-w-[328px] items-center gap-1.5 p-3 text-xl text-blue-400 transition-all last:mt-auto hover:rounded-xl active:scale-95"
        :to="`${ADMIN_ROUTE}${route.link}`"
      >
        <VueFeather :type="route.icon" class="w-6" stroke-width="2"></VueFeather>
        <span>{{ route.text }}</span>
      </RouterLink>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { RouterLink, useRoute } from "vue-router";
import VueFeather from "vue-feather";
import { ref, watch } from "vue";

const isNavActive = ref(false);

const toggleNav = () => {
  isNavActive.value = !isNavActive.value;
};

const route = useRoute();
const currentPath = ref(route.path);

watch(route, (newRoute) => {
  currentPath.value = newRoute.path;
});

const ADMIN_ROUTE = "/admin/";
const routes = [
  {
    text: "Dashboard",
    link: "",
    icon: "grid",
  },
  {
    text: "Entradas",
    link: "entradas",
    icon: "folder",
  },
  {
    text: "Historial de Permisos",
    link: "permisos",
    icon: "file-minus",
  },
  {
    text: "Terminales",
    link: "terminales",
    icon: "tablet",
  },
  {
    text: "Residencias",
    link: "residencias",
    icon: "home",
  },
  {
    text: "Residentes",
    link: "residentes",
    icon: "user",
  },
  {
    text: "Invitados",
    link: "invitados",
    icon: "user",
  },
  {
    text: "Configuraciones",
    link: "configuraciones",
    icon: "settings",
  },
];
</script>

<style scoped></style>
