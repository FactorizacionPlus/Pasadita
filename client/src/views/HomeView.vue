<template>
  <p class="text-3xl font-bold text-pasadita-blue-1">Pasadita: Rutas disponibles</p>
  <ul class="mt-4 grid gap-4 md:grid-cols-3">
    <li
      class="rounded-lg bg-white transition-all hover:bg-shades-100 active:scale-95"
      v-for="(route, index) in availableRoutes"
      :key="index"
    >
      <a :href="route.path" class="flex flex-col p-2 text-pasadita-blue-1">
        <span class="text-xl font-semibold capitalize">{{
          route.name?.toString().replace("-", " ")
        }}</span>
        <span class="text-xs">{{ route.path }}</span>
      </a>
    </li>
  </ul>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();
const availableRoutes = router
  .getRoutes()
  .filter((route) => typeof route.name === "string")
  .sort((a, b) => {
    return (a.name as string).localeCompare(b.name as string);
  })
  .map((route) => ({
    name: route.name,
    path: route.path,
  }));
</script>
