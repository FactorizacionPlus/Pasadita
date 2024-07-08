<script setup lang="ts">
import Modal from "@/components/Modal/ModalComponent.vue";
import Pasadita from "@/assets/PasaditaLogo.svg?url";
import { ref } from "vue";
import HeaderModal from "../HeaderModal.vue";

const modal = ref<InstanceType<typeof Modal>>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <div class="w-full max-w-[480px] overflow-hidden rounded-md bg-white">
      <HeaderModal title="No tienes residencia" icon="grid" action="view" />

      <div class="flex max-h-[80vh] items-center gap-4 px-4 py-6">
        <img :src="Pasadita" alt="pasadita" />
        <p class="text-3xl font-semibold text-pasadita-blue-1">
          No tienes una residencia asignada.
        </p>
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </div>
  </Modal>
</template>
