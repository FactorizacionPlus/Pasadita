<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import BodyModal from "../BodyModal.vue";
import ControlsModal from "../ControlsModal.vue";
import { deletePermission } from "@/composables/useDeletePermission";

const props = defineProps<{ permissionId: string }>();
const modal = ref<typeof Modal>();
const isLoading = ref(false);
const errorMessage = ref<string | null>(null);

const emit = defineEmits(["permission-deleted"]);

async function handleDelete() {
  isLoading.value = true;
  errorMessage.value = null;

  const { success, error } = await deletePermission(props.permissionId);
  isLoading.value = false;

  if (success) {
    emit("permission-deleted", props.permissionId);
    modal.value?.close();
  } else {
    errorMessage.value = error;
  }
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-xl overflow-hidden rounded-md bg-white">
      <HeaderModal title="Eliminar Permiso" icon="user" action="delete" />
      <BodyModal>
        <p>¿Deseas eliminar este permiso?</p>
        <p v-if="errorMessage" class="text-red-500">{{ errorMessage }}</p>
      </BodyModal>
      <ControlsModal>
        <button
          type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-green-2 p-2 text-center text-sm font-normal text-pasadita-green-1 transition-all hover:rounded-xl hover:bg-pasadita-green-1 hover:text-white active:scale-95"
          @click="handleDelete"
          :disabled="isLoading"
        >
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span v-if="!isLoading">Aceptar</span>
          <span v-else>Eliminando...</span>
        </button>
        <button
          @click="modal?.close()"
          type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
