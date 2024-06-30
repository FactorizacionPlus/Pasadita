<script setup lang="ts">
import VueFeather from "vue-feather";
</script>

<template>
  <form class="relative ml-auto flex w-full max-w-screen-sm gap-2 overflow-auto">
    <input type="text"
      class="peer h-10 w-full rounded-md border-x border-b-2 border-t border-shades-400 bg-shades-100 px-2 pl-8 text-blue-500 outline-none transition-all placeholder:text-shades-400 hover:bg-blue-100 focus:border-blue-400 focus:bg-blue-200 focus:text-blue-400 focus:placeholder:text-blue-300 disabled:opacity-40"
      placeholder="Buscar" required />
    <VueFeather
      class="absolute left-0 top-1/2 size-5 -translate-y-1/2 translate-x-[6px] text-shades-400 peer-focus:text-blue-400"
      type="search" stroke-width="2.5" />
  </form>
</template>
