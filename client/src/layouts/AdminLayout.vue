<template>
  <div
    class="mx-auto box-border flex flex-col gap-4 overflow-hidden sm:p-4 md:h-screen md:flex-row"
  >
    <AdminNavigationMenu class="shrink-0" />
    <main class="flex grow flex-col overflow-x-hidden rounded-lg md:w-min md:overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import AdminNavigationMenu from "@/components/NavigationMenu/AdminNavigationMenu.vue";
</script>

<style>
body {
  overflow-y: hidden;
}
</style>
