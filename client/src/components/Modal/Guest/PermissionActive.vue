<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import QrCode from "@/assets/qr-code.svg?url";
import UserImage from "@/components/UserImage.vue";
import QRCodeStyling from "@/components/QRCodeStyling.vue";
import type ActivePermission from "@/types/Guest/ActivePermission";

const modal = ref<typeof Modal>();
const props = defineProps<{ activePermission: ActivePermission }>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="w-full max-w-[480px] overflow-hidden rounded-md bg-white">
      <HeaderModal title="Permiso Activo" icon="grid" action="view" />

      <div class="flex max-h-[80vh] flex-col gap-4 overflow-y-auto px-4 py-6">

        <div class="flex flex-col gap-1 rounded-t-lg text-blue-400">
          <p class="text-base font-medium text-blue-500">Información de la Residencia</p>
          <div class="flex items-center gap-1">
            <VueFeather type="home" size="40" stroke-width="1.5" />
            <p class="text-base font-normal"> 5 / {{ props.activePermission.maxHabitants }} Habitantes</p>
          </div>
        </div>

        <p class="text-lg text-blue-500">Casa Mira Flores</p>

        <div class="flex flex-row justify-between gap-4">

          <div class="flex flex-col gap-1 rounded-t-lg text-blue-400">
            <p class="text-base font-medium text-blue-500">Fecha de Inicio</p>
            <div class="flex items-center gap-1">
              <VueFeather type="calendar" size="40" stroke-width="1.5" />
              <p class="text-base font-normal">{{ props.activePermission.startDate }}</p>
            </div>
          </div>

          <div class="flex flex-col gap-1 rounded-t-lg text-blue-400">
            <p class="text-base font-medium text-blue-500">Fecha Fin</p>
            <div class="flex items-center gap-1">
              <VueFeather type="calendar" size="40" stroke-width="1.5" />
              <p class="text-base font-normal">{{ props.activePermission.endDate }}</p>
            </div>
          </div>
        </div>

        <div class="flex gap-2">
          <UserImage class="size-12" size="25" padding="0px" />
          <div class="flex-1 text-pasadita-blue-1">
            <p class="text-base font-semibold">
              A Solicitud de:
            </p>
            <p class="text-base font-normal">
              {{ props.activePermission.residentCreator }}
            </p>
          </div>
        </div>

        <!-- QR Code Section -->
        <div class="flex items-center justify-center">
          <div class="flex flex-col items-center justify-center">
            <img :src="QrCode" class="size-72" />
            <div class="mt-4 flex justify-center">
              <button
                class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95">
                <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
                <span>Generar QR</span>
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          class="inline-flex items-center rounded-lg bg-pasadita-green-2 p-2 text-center text-sm font-normal text-pasadita-green-1 transition-all hover:rounded-xl hover:bg-pasadita-green-1 hover:text-white active:scale-95">
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>Aceptar</span>
        </button>
        <button @click="modal?.close()" type="button"
          class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95">
          <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
          <span>Cancelar</span>
        </button>
      </div>
    </form>
  </Modal>
</template>
