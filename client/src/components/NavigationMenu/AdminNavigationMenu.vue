<template>
  <nav
    class="flex h-dvh min-w-52 flex-col justify-between border-r border-r-pasadita-shade-2 bg-pasadita-shade-3 p-4"
  >
    <div>
      <div class="flex justify-center">
        <img class="object-fit h-12 w-4/5" src="/identity/pasaditaLogo.svg" />
      </div>
      <div class="h-70 ml-1 mt-2 flex h-full flex-col justify-between">
        <RouterLink
          :key="index"
          v-for="(route, index) in routes"
          class="flex h-10 min-w-32 flex-row items-center gap-2 rounded-md text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-2 hover:text-white active:scale-95"
          :to="`${ADMIN_ROUTE}${route.link}`"
        >
          <div class="ml-1 flex shrink-0 items-center justify-center">
            <VueFeather :type="route.icon" size="25" stroke-width="1.5"></VueFeather>
          </div>
          {{ route.text }}
        </RouterLink>
      </div>
    </div>

    <RouterLink
      class="ml-1 flex h-10 flex-row items-center gap-2 rounded-md text-pasadita-blue-2 hover:bg-pasadita-blue-2 hover:text-white"
      to="/"
    >
      <div class="ml-1 flex shrink-0 items-center justify-center">
        <VueFeather type="settings" size="25" stroke-width="1.5"></VueFeather>
      </div>
      Configuraciones
    </RouterLink>
  </nav>
</template>

<script setup lang="ts">
import { RouterLink } from "vue-router";
import VueFeather from "vue-feather";

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
];
</script>

<style scoped></style>
