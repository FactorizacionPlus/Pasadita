<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import InputForm from "@/components/Forms/InputForm.vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";

const modal = ref<typeof Modal>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Permiso Activo" icon="grid" action="view" />

      <div class="flex max-h-[80vh] flex-col gap-4 px-4 py-6">
        <!-- Inputs -->
        <div class="flex flex-row gap-2">
          <InputForm
            name="quantity"
            title="Cantidad de habitantes"
            type="date"
            placeholder="Inserte la cantidad de habitantes"
          />
          <InputForm
            name="quantity"
            title="Cantidad de habitantes"
            type="date"
            placeholder="Inserte la cantidad de habitantes"
          />
        </div>
        <InputForm
          name="quantity"
          title="Cantidad de habitantes"
          type="text"
          placeholder="Inserte la cantidad de habitantes"
        />

        <!-- QR Code Section -->
        <div class="flex items-center justify-center">
          <div class="flex flex-col items-center justify-center">
            <img src="/public/qr-code.svg" class="size-64" />
            <div class="mt-4 flex justify-center">
              <button
                class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
              >
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
          class="inline-flex items-center rounded-lg bg-pasadita-green-2 p-2 text-center text-sm font-normal text-pasadita-green-1 transition-all hover:rounded-xl hover:bg-pasadita-green-1 hover:text-white active:scale-95"
        >
          <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
          <span>Aceptar</span>
        </button>
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
