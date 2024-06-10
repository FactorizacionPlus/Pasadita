<script setup lang="ts">
import { ref } from "vue";
import ModalEntrie from "@/components/Modal/Terminal/CreateEntrie.vue";
import CreateEntrie from "@/assets/Crear-Entrada.svg";
const modalEntrie = ref<typeof ModalEntrie>();
</script>

<template>
    <div class="flex h-screen items-center justify-center">
        <button type="button"
            class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-semibold text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-2 hover:text-white active:scale-95"
            @click="modalEntrie?.show()">
            <div class="flex flex-col items-center justify-center gap-2 p-5">
                <CreateEntrie class="hover:text-white"/>
                <span class="text-xl">Ver Permiso Activo</span>
            </div>
        </button>
    </div>
    <ModalEntrie ref="modalEntrie">Hola</ModalEntrie>
</template>