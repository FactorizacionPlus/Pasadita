<template>
  <div class="mx-auto flex flex-col md:h-screen md:flex-row">
    <AdminNavigationMenu class="shrink-0" />
    <main class="shrink-0 grow p-4 md:w-min md:overflow-y-auto md:p-8">
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
