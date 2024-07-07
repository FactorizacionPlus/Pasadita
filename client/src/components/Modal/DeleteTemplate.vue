<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import BodyModal from "@/components/Modal/BodyModal.vue";
import ControlsModal from "@/components/Modal/ControlsModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
const props = defineProps<{ reason: string }>();

const modal = ref<typeof Modal>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Invitado" icon="grid" action="delete" />
      <BodyModal>
        {{ props.reason }}
      </BodyModal>
      <!-- Botones -->
      <ControlsModal>
        <button
          class="inline-flex items-center rounded-lg bg-green-100 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-200 active:scale-95">
            <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
            <span>Aceptar</span>
          </button>
          <button @click="modal?.close()" type="button"
          class="inline-flex items-center rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95">
            <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
            <span>Cancelar</span>
          </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
