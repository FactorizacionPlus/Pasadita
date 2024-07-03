<template>
  <div class="mx-auto flex h-screen flex-col">
    <DefaultNavigationMenu :root="root as string" :role="role as string" />
    <main class="mx-auto mt-4 w-full max-w-screen-2xl shrink-0 grow 2xl:mt-8">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import DefaultNavigationMenu from "@/components/NavigationMenu/DefaultNavigationMenu.vue";
import { useRoute } from "vue-router";

const route = useRoute();
const role = route.meta.roleToShow;
const root = route.meta.rootPath;
</script>

<style scoped></style>
