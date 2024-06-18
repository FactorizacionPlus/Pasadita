<template>
  <nav
    class="sticky top-0 z-10 flex min-w-52 flex-col border-b border-r-pasadita-shade-2 bg-pasadita-shade-3 p-2 md:relative md:h-dvh md:border-r md:p-4"
  >
    <div class="flex justify-between gap-4 md:flex-col md:justify-start">
      <img class="mx-auto hidden h-12 w-4/5 md:block" src="/identity/pasaditaLogo.svg" />
      <img class="block h-12 md:hidden" src="/identity/pasaditaLogoMin.svg" />
      <button
        :class="
          isNavActive
            ? 'bg-pasadita-blue-2 text-white hover:!bg-pasadita-blue-3 hover:text-white'
            : ''
        "
        @click="toggleNav"
        class="grid size-12 place-items-center rounded-lg text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-6 active:scale-95 md:hidden"
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
            ? 'bg-pasadita-blue-2 text-white hover:!bg-pasadita-blue-3 hover:text-white'
            : ''
        "
        class="flex min-w-32 items-center gap-1.5 rounded-md p-3 text-pasadita-blue-2 transition-all last:mt-auto hover:bg-pasadita-blue-6 active:scale-95"
        :to="`${ADMIN_ROUTE}${route.link}`"
      >
        <VueFeather :type="route.icon" class="w-6" stroke-width="1.5"></VueFeather>
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
