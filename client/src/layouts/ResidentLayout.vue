<template>
  <div class="mx-auto flex h-screen flex-col sm:p-4">
    <DefaultNavigationMenu />
    <main class="mx-auto flex w-full max-w-screen-2xl shrink-0 grow flex-col overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import DefaultNavigationMenu from "@/components/NavigationMenu/DefaultNavigationMenu.vue";
</script>

<style scoped></style>
