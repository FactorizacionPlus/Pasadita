<template>
  <div class="flex flex-col h-screen mx-auto">
    <DefaultNavigationMenu />
    <main class="flex-shrink-0 w-full max-w-screen-2xl mx-auto flex-grow-[1] p-4">
      <router-view  />
    </main>
  </div>
</template>

<script setup lang="ts">
import DefaultNavigationMenu from '@/components/NavigationMenu/DefaultNavigationMenu.vue';

</script>

<style scoped></style>
