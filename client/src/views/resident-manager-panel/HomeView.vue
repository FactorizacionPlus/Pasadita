<script setup lang="ts">
import VueFeather from "vue-feather";

const ROUTE = "/residente-encargado/";
const routes = [
  {
    text: "Gestionar permisos",
    link: "permisos",
    icon: "file-minus",
  },
  {
    text: "Gestionar residentes",
    link: "residentes",
    icon: "users",
  },
];
</script>

<template>
  <div class="flex h-full flex-col items-center justify-center gap-8 md:flex-row">
    <RouterLink
      :key="index"
      v-for="(route, index) in routes"
      class="flex aspect-square size-full h-auto max-w-[480px] flex-col items-center justify-center gap-2 rounded-md bg-pasadita-blue-4 text-2xl font-semibold text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-2 hover:text-pasadita-blue-4 active:scale-95"
      :to="`${ROUTE}${route.link}`"
    >
      <div class="ml-1 flex shrink-0 items-center justify-center">
        <VueFeather :type="route.icon" size="150" stroke-width="1.5"></VueFeather>
      </div>

      {{ route.text }}
    </RouterLink>
  </div>
</template>
