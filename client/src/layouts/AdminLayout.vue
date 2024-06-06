<template>
  <div class="flex h-[100vh]">
    <AdminNavigationMenu class="flex-shrink-0"/>
    <main class="flex-shrink-0 flex-grow-[1] p-4 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import AdminNavigationMenu from '../components/AdminNavigationMenu.vue';
</script>

<style scoped>

</style>
