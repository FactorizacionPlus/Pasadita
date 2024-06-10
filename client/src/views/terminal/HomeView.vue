<script setup lang="ts">
import { ref } from "vue";
import ModalEntry from "@/components/Modal/Terminal/CreateEntry.vue";
import CreateEntry from "@/assets/Crear-Entrada.svg";
const modalEntry = ref<typeof ModalEntry>();
</script>

<template>
  <div class="flex h-screen items-center justify-center">
    <button
      type="button"
      class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-semibold text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-2 hover:text-white active:scale-95"
      @click="modalEntry?.show()"
    >
      <div class="flex flex-col items-center justify-center gap-2 p-5">
        <CreateEntry class="hover:text-white" />
        <span class="text-xl">Ver Permiso Activo</span>
      </div>
    </button>
  </div>
  <ModalEntrie ref="modalEntry">Hola</ModalEntrie>
</template>
