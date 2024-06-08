<script setup lang="ts">
import AdminTerminal from "@/components/Cards/TerminalCard.vue"
import SearchBar from "@/components/SearchBar.vue"
import CurrentPageInfo from "@/components/CurrentPageInfo.vue"
import type Terminal from "@/types/AdminTerminal"
import type TestPagination from "@/types/TestPagination";

const terminalList: Terminal[] = [
  {
    type: "Peatonal",
    numentries: 10
  },
  {
    type: "Vehicular",
    numentries: 11
  },
  {
    type: "Vigilante",
    numentries: 12
  },

]

/*const paginationList: TestPagination[] = [
  {
    page: 1
  },
  {
    page: 2
  },
  {
    page: 3
  },
  {
    page: 4
  }
]
*/
</script>

<template>
  <CurrentPageInfo title="Terminales" icon="tablet"/>
  <SearchBar class="py-4"/>
  <ul class="grid grid-cols-4 gap-4">
    <AdminTerminal :terminal="terminal" v-for="terminal in terminalList" :key="terminal.type"/>
  </ul>
</template>