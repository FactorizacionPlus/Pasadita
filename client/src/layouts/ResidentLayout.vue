<template>
  <div class="flex flex-col mx-auto h-screen ">
    <DefaultNavigationMenu />
    <main class="flex-shrink-0 w-full flex-grow-[1] max-w-screen-2xl mx-auto p-4">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import DefaultNavigationMenu from "@/components/NavigationMenu/DefaultNavigationMenu.vue";
</script>

<style scoped></style>
