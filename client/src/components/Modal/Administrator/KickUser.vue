<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import BodyModal from "@/components/Modal/BodyModal.vue";
import ControlsModal from "../ControlsModal.vue";
import { useToast } from "@/stores/toast";
import type RegisteredUser from "@/types/User/RegisteredUser";
import { ToastType } from "@/types/Toast";

const modal = ref<typeof Modal>();

const { addToast } = useToast();

const props = defineProps<{user: RegisteredUser}>()

enum Message {
  BUTTON_ACCEPT = "Aceptar",
  BUTTON_CANCEL = "Cancelar",
  ACCOUNT = "Usuario",
}

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});

function handleSubmit(){

  addToast({message: "Grande", type: ToastType.SUCCESS}) 
}

</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-xl overflow-hidden rounded-md bg-white" @submit="handleSubmit">
      <HeaderModal :title="Message.ACCOUNT" icon="user" action="delete" />
      <BodyModal>
        <p>¿Deseas eliminar a {{ props.user.firstName }} {{ props.user.lastName }}? Esta acción expulsará al residente del sistema y perderá acceso de forma irrevocable a la plataforma.</p>
      </BodyModal>
      <ControlsModal>
        <button
          type="submit"
          class="inline-flex items-center gap-0.5 rounded-lg bg-green-100 p-2 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-200 active:scale-95"
        >
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>{{ Message.BUTTON_ACCEPT }}</span>
        </button>
        <button
          type="button"
          @click="modal?.close()"
          class="inline-flex items-center gap-0.5 rounded-lg bg-red-100 p-2 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        >
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>{{ Message.BUTTON_CANCEL }}</span>
        </button>
      </ControlsModal>
    </form>
  </Modal>
</template>
