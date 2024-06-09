<template>
  <div class="flex flex-col md:flex-row h-[100vh] mx-auto">
    <AdminNavigationMenu class="flex-shrink-0" />
    <main class="flex-shrink-0 md:w-min flex-grow-[1] p-4 md:p-8 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import AdminNavigationMenu from '@/components/NavigationMenu/AdminNavigationMenu.vue';
</script>

<style>
@media screen and (width > 768px) {
  body {
    overflow-y: hidden;
  }
}
</style>
