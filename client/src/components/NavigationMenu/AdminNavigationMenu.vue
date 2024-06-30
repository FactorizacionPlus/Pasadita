<template>
  <nav
    class="sticky top-4 z-10 flex flex-col rounded-lg bg-white p-2 shadow-header md:relative md:top-0 md:p-4"
  >
    <div class="relative flex justify-between gap-4 md:flex-col md:justify-start">
      <img v-if="isFullNav" class="mx-auto hidden h-[45px] w-auto md:block" src="/identity/pasaditaLogo.svg" />
      <img v-else class="hidden h-8 w-auto md:block" src="/identity/pasaditaLogoMin.svg" />
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
      <button @click="() => { isFullNav = !isFullNav }" class="absolute -right-8 top-1/2 hidden size-8 -translate-y-1/2 place-items-center rounded-lg bg-blue-100 text-blue-400 shadow-header transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95 md:grid">
        <VueFeather :class="isFullNav && 'rotate-180'" type="chevron-right" class="w-4 transition-transform" stroke-width="2.5"></VueFeather>
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
          [currentPath == ADMIN_ROUTE + route.link
            ? 'translate-x-1 rounded-xl bg-blue-200 font-medium hover:bg-blue-100'
            : 'rounded-md hover:bg-blue-100', isFullNav ? 'min-w-[328px]' : 'md:aspect-square md:justify-center']
        "
        class="flex items-center gap-1.5 p-3 text-xl text-blue-400 transition-all last:mt-auto hover:rounded-xl active:scale-95"
        :to="`${ADMIN_ROUTE}${route.link}`"
      >
        <VueFeather :type="route.icon" class="w-6" stroke-width="2"></VueFeather>
        <span :class="!isFullNav && 'md:hidden'" >{{ route.text }}</span>
      </RouterLink>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { RouterLink, useRoute } from "vue-router";
import VueFeather from "vue-feather";
import { ref, watch } from "vue";

const isNavActive = ref(false);
const isFullNav = ref(true);

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
    text: "Usuarios",
    link: "usuarios",
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
