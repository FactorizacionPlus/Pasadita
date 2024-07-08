<template>
  <div
    class="mx-auto box-border flex flex-col gap-4 overflow-hidden sm:p-4 md:h-screen md:flex-row"
    :class="[contrastClass, fontSizeClass, dyslexicClass]"
  >
    <AdminNavigationMenu class="shrink-0" />
    <main class="flex grow flex-col overflow-x-hidden rounded-lg md:w-min md:overflow-y-auto">
      <router-view />
    </main>
    <ul
      style="bottom: 8px; right: 8px; max-width: 512px; z-index: 20"
      class="fixed bottom-4 right-4 w-full max-w-lg"
    >
      <ToastAlert v-for="(toast, index) in toasts" :toast="toast" :key="index" />
    </ul>
  </div>
</template>

<script setup lang="ts">
import ToastAlert from "@/components/ToastAlert.vue";
import { useToast } from "@/stores/toast";
const { toasts } = useToast();
import AdminNavigationMenu from "@/components/NavigationMenu/AdminNavigationMenu.vue";
import { usePreferences } from "@/stores/preferences";
import { computed } from "vue";

const preferences = usePreferences();

const contrastClass = computed(() => (preferences.preferences.highContrast ? "contrast-150" : ""));
const dyslexicClass = computed(() =>
  preferences.preferences.dyslexicFont ? "!font-openDyslexic" : ""
);

const fontSizeClass = computed(() => {
  switch (preferences.preferences.fontSize) {
    case "BIG":
      return "text-[24px]";
    case "SMALL":
      return "text-[12px]";
    default:
      return "";
  }
});
</script>

<style>
body {
  overflow-y: hidden;
}
</style>
