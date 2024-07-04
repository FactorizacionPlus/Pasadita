<template>
  <div class="relative mx-auto flex h-screen flex-col">
    <DefaultNavigationMenu :root="root as string" :role="role as string" />
    <main class="mx-auto mt-4 w-full max-w-screen-2xl shrink-0 grow 2xl:mt-8">
      <router-view />
    </main>
    <ul
      style="bottom: 8px; right: 8px; max-width: 512px; z-index: 20"
      class="fixed bottom-4 right-4 w-full max-w-lg"
    >
      <ToastAlert v-for="(toast, index) in toasts" :toast="toast" :key="index" />
    </ul>
  </div>
</template>

<script setup lang="ts">
import DefaultNavigationMenu from "@/components/NavigationMenu/DefaultNavigationMenu.vue";
import ToastAlert from "@/components/ToastAlert.vue";
import { useToast } from "@/stores/toast";
import { useRoute } from "vue-router";

const { toasts } = useToast();
const route = useRoute();
const role = route.meta.roleToShow;
const root = route.meta.rootPath;
</script>

<style scoped></style>
