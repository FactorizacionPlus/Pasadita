<template>
  <div class="flex h-[100vh] max-w-screen-2xl mx-auto">
    <AdminNavigationMenu class="flex-shrink-0"/>
    <main class="flex-shrink-0 w-full flex-grow-[1] p-8 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import AdminNavigationMenu from '@/components/NavigationMenu/AdminNavigationMenu.vue';
</script>

<style scoped>

</style>
