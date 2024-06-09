<template>
  <div class="mx-auto flex h-screen flex-col">
    <DefaultNavigationMenu />
    <main class="mx-auto w-full max-w-screen-2xl shrink-0 grow overflow-y-auto p-4">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import DefaultNavigationMenu from "@/components/NavigationMenu/DefaultNavigationMenu.vue";
</script>

<style scoped></style>
