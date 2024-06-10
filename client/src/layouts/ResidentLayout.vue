<template>
  <div class="flex flex-col mx-auto">
    <DefaultNavigationMenu />
    <main class="flex-shrink-0 w-full max-w-screen-2xl mx-auto flex-grow-[1] p-4 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
<<<<<<< HEAD
import DefaultNavigationMenu from '@/components/NavigationMenu/DefaultNavigationMenu.vue';

=======
import DefaultNavigationMenu from "@/components/NavigationMenu/DefaultNavigationMenu.vue";
>>>>>>> master
</script>

<style scoped></style>
