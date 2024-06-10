<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/Modal.vue";
import UserImage from "@/components/UserImage.vue";

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
        <div class="flex items-center gap-2 rounded-t-lg">
          <div class="shrink-0">
            <VueFeather type="home" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
          </div>
          <div class="flex-1">
            <p class="text-base font-medium text-pasadita-blue-1">Residencia</p>
            <p class="text-base font-normal text-pasadita-blue-1">Habitantes: 4/5</p>
          </div>
        </div>
        <span class="font-semibold text-pasadita-blue-1">Residentes</span>
        <div class="grid grid-cols-2 gap-8">
          <div class="flex flex-row items-center justify-center gap-2">
            <div
              class="flex size-10 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
            >
              <UserImage
                image="https://upload.wikimedia.org/wikipedia/commons/7/75/Anastasio_Somoza_Debayle.PNG"
                size="25"
                padding="0px"
              />
            </div>
            <div class="flex flex-col">
              <p class="text-pasadita-blue-1">29 de agosto del 2023 - 12:36hrs</p>
              <p class="text-pasadita-blue-1">Somoza</p>
            </div>
          </div>
          <div class="flex flex-row items-center justify-center gap-2">
            <div
              class="flex size-10 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
            >
              <UserImage
                image="https://upload.wikimedia.org/wikipedia/commons/7/75/Anastasio_Somoza_Debayle.PNG"
                size="25"
                padding="0px"
              />
            </div>
            <div class="flex flex-col">
              <p class="text-pasadita-blue-1">29 de agosto del 2023 - 12:36hrs</p>
              <p class="text-pasadita-blue-1">Somoza</p>
            </div>
          </div>
          <div class="flex flex-row items-center justify-center gap-2">
            <div
              class="flex size-10 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
            >
              <UserImage
                image="https://upload.wikimedia.org/wikipedia/commons/7/75/Anastasio_Somoza_Debayle.PNG"
                size="25"
                padding="0px"
              />
            </div>
            <div class="flex flex-col">
              <p class="text-pasadita-blue-1">29 de agosto del 2023 - 12:36hrs</p>
              <p class="text-pasadita-blue-1">Somoza</p>
            </div>
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
