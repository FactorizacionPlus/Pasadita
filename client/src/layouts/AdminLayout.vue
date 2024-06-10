<template>
  <div class="mx-auto flex h-screen flex-col md:flex-row">
    <AdminNavigationMenu class="shrink-0" />
    <main class="shrink-0 grow overflow-y-auto p-4 md:w-min md:p-8">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import AdminNavigationMenu from "@/components/NavigationMenu/AdminNavigationMenu.vue";
</script>

<style>
@media screen and (width > 768px) {
  body {
    overflow-y: hidden;
  }
}
</style>
