<script setup lang="ts">
import AdminTerminal from "../../components/Administrador/AdminTerminal.vue"
import type Terminal from "../../types/AdminTerminal"
const terminalList : Terminal[] = [
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
</script>

<template>
      <div class="grid grid-cols-5 gap-5 p-8">
    <div v-for="terminal in terminalList" :key="terminal.type">
      <AdminTerminal :terminal="terminal" />
    </div>
  </div>
</template>