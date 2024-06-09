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
  <div class="grid grid-cols-[repeat(2,400px)] justify-center items-center h-[100%] gap-8">
    <RouterLink
      :key="index"
      v-for="(route, index) in routes"
      class="flex flex-col min-w-32 gap-2 justify-center items-center h-96 font-semibold rounded-md text-2xl bg-pasadita-blue-4 text-pasadita-blue-2 hover:bg-pasadita-blue-2 hover:text-pasadita-blue-4 transition-all active:scale-95"
      :to="`${ROUTE}${route.link}`"
    >
      <div class="ml-1 flex justify-center items-center flex-shrink-0">
        <VueFeather :type="route.icon" size="150" stroke-width="1.5"></VueFeather>
      </div>

      {{ route.text }}
    </RouterLink>
  </div>
</template>
