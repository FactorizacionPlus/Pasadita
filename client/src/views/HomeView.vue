<template>
  <p class="text-3xl font-bold text-blue-500">Pasadita: Rutas disponibles</p>
  <ul class="mt-4 grid gap-4 md:grid-cols-3">
    <li
      class="rounded-lg bg-white text-blue-400 transition-all hover:bg-blue-400 hover:text-white hover:shadow-glow active:scale-95"
      v-for="(route, index) in availableRoutes" :key="index">
      <a :href="route.path" class="flex flex-col p-2">
        <span class="text-xl font-semibold capitalize">{{
          route.name?.toString().replace("-", " ")
          }}</span>
        <span class="text-xs">{{ route.path }}</span>
      </a>
    </li>
  </ul>
  <div class="flex justify-center py-6">
    <button @click="handleClick"
      class="border border-blue-300 p-4 text-blue-400 hover:bg-blue-400 hover:text-white active:scale-95">
      <span>Agregar Toast</span>
    </button>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import type Toast from "@/types/Toast";
import { ToastType } from "@/types/Toast";
import { useToast } from "@/stores/toast";

const { addToast } = useToast();

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

const handleClick = () => {
  let type = ToastType.ERROR;

  switch (Math.round(Math.random() * 3)) {
    case 0:
      type = ToastType.ERROR;
      break;
    case 1:
      type = ToastType.INFO;
      break;
    case 2:
      type = ToastType.SUCCESS;
      break;
    case 3:
      type = ToastType.WARNING;
      break;
  }

  const toast: Toast = {
    message:
      "Lamentablemente hemos detectado tu homosexualidad, así que negaremos tu acceso a esta plataforma.",
    type: type,
  };
  addToast(toast);
};
</script>
