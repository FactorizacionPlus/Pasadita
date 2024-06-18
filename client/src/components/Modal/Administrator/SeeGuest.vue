<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import UserImage from "@/components/UserImage.vue";
const props = defineProps<{
  image: string;
  name: string;
  secondname: string;
  identification: string;
  entries: number;
}>();
const modal = ref<typeof Modal>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Invitado" icon="grid" action="view" />
      <div class="flex max-h-[80vh] flex-col gap-4 p-6">
        <div class="flex flex-row">
          <div class="flex w-full flex-col justify-center gap-2">
            <div class="flex items-center gap-3 px-2">
              <div
                class="flex size-24 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
              >
                <UserImage :image="props.image" size="70" padding="16px" />
              </div>
              <div class="flex flex-col text-pasadita-blue-1">
                <p class="font-semibold">Nombre</p>
                <p>{{ props.name }}</p>
                <p class="font-semibold">Apellido</p>
                <p>{{ props.secondname }}</p>
                <p class="font-semibold">Identificacion</p>
                <p>{{ props.identification }}</p>
              </div>
            </div>
            <p class="font-semibold text-pasadita-blue-1">
              Numero de entradas: {{ props.entries }}
            </p>
          </div>
        </div>
        <span class="font-semibold text-pasadita-blue-1">Entradas</span>
        <div class="grid grid-cols-3 gap-3">
          <div>
            <p class="text-pasadita-blue-1">Fecha y hora de entrada</p>
            <p class="text-pasadita-blue-1">29 de agosto del 2023 - 12:36hrs</p>
          </div>
        </div>
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
    </form>
  </Modal>
</template>
