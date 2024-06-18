<script setup lang="ts">
import AdminTerminal from "@/components/Cards/TerminalCard.vue";
import SearchBar from "@/components/SearchBar.vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import type Terminal from "@/types/AdminTerminal";

const terminalList: Terminal[] = [
  {
    type: "Peatonal",
    numentries: 10,
  },
  {
    type: "Vehicular",
    numentries: 11,
  },
  {
    type: "Vigilante",
    numentries: 12,
  },
];
</script>

<template>
  <CurrentPageInfo title="Terminales" icon="tablet" />
  <SearchBar class="py-4" />
  <ul class="grid gap-4 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
    <AdminTerminal :terminal="terminal" v-for="terminal in terminalList" :key="terminal.type" />
  </ul>
</template>
