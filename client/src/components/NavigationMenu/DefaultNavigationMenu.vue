<template>
  <header
    class="flex flex-col justify-between border-b border-b-pasadita-shade-2 bg-pasadita-shade-3 py-2"
  >
    <div class="mx-auto flex w-full max-w-screen-2xl items-center justify-between px-4">
      <img class="object-fit h-8 w-auto" src="/identity/pasaditaLogo.svg" />
      <div class="flex items-center gap-1">
        <div
          class="flex cursor-pointer items-center gap-1 rounded-lg p-2 text-pasadita-blue-2 transition-all hover:bg-black/5 active:scale-95"
        >
          <div
            class="flex size-10 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
          >
            <VueFeather type="user" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
          </div>

          <div class="flex-1">
            <p class="text-xs leading-none">Rol</p>
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
          class="grid size-10 place-items-center gap-2 rounded-md text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-2 hover:text-white active:scale-95"
          to="configuraciones"
        >
          <VueFeather type="settings" size="25" stroke-width="1.5"></VueFeather>
        </RouterLink>
      </div>
    </div>
  </header>
</template>

<script setup lang="ts">
import VueFeather from "vue-feather";
</script>

<style scoped></style>
