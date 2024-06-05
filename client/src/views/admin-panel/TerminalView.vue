<script setup lang="ts">
import AdminTerminal from "../../components/Administrador/AdminTerminal.vue"
import SearchBar from "../../components/SearchBar.vue"
import Pagination from "../../components/Pagination-Comp.vue"
import type Terminal from "../../types/AdminTerminal"
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

const paginationList: TestPagination[] = [
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

</script>

<template>
  <div class="px-8">
    <SearchBar/>
  </div>
  <div class="grid grid-cols-5 gap-5 p-8">
    <div v-for="terminal in terminalList" :key="terminal.type">
      <AdminTerminal :terminal="terminal" />
    </div>
  </div>
  <div class="px-8">
    <Pagination :pagination="paginationList" />
  </div>
</template>