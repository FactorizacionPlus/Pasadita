<template>
  <header
    class="sticky top-0 z-10 mx-auto flex w-full max-w-screen-2xl items-center justify-between bg-white px-4 py-2 shadow-header 2xl:top-4 2xl:rounded-xl"
  >
    <RouterLink :to="root">
      <img class="hidden h-8 w-auto md:block" src="/identity/pasaditaLogo.svg" />
      <img class="block h-8 w-auto md:hidden" src="/identity/pasaditaLogoMin.svg" />
    </RouterLink>
    <div class="flex items-center gap-1">
      <div
        class="flex cursor-pointer items-center gap-1 rounded-lg p-2 text-blue-400 transition-all hover:bg-shades-100 active:scale-95"
      >
        <div
          class="flex size-10 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-white"
        >
          <VueFeather type="user" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
        </div>

        <div class="hidden flex-1 sm:block">
          <p class="text-xs leading-none">{{ props.role }}</p>
          <p class="text-xl font-semibold leading-tight text-pasadita-blue-1">Username</p>
        </div>

        <div class="flex items-center">
          <VueFeather
            type="chevron-down"
            size="25"
            stroke="#004f82"
            stroke-width="1.5"
          ></VueFeather>
        </div>
      </div>
      <RouterLink
        class="grid size-10 place-items-center gap-2 rounded-md text-blue-400 transition-all hover:bg-shades-100 active:scale-95"
        to="configuraciones"
      >
        <VueFeather type="settings" size="25" stroke-width="1.5"></VueFeather>
      </RouterLink>
    </div>
  </header>
</template>

<script setup lang="ts">
import VueFeather from "vue-feather";
const props = defineProps<{ role: string; root: string }>();
</script>

<style scoped></style>
