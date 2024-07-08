<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import Modal from "@/components/Modal/ModalComponent.vue";
import HeaderModal from "../HeaderModal.vue";
import InputForm from "@/components/Forms/InputForm.vue";
import { AlertType } from "@/types/Alert";

const modal = ref<InstanceType<typeof Modal>>();

const identifier = ref<string>("");

const emit = defineEmits<{
  (e: "next", data: string): void;
}>();

defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white" @submit.prevent>
      <HeaderModal title="Añadir Entrada" icon="user" action="add" />

      <div class="flex flex-col gap-4 p-4">
        <InputForm
          name="identificacion"
          title="Identificación"
          type="text"
          placeholder="Identificación"
          :alert="{ type: AlertType.INFO, message: 'Busca un Usuario de antemano' }"
          v-model="identifier"
        />
      </div>

      <!-- Botones -->
      <div class="flex justify-end gap-2 border-t border-t-pasadita-shade-2 p-2">
        <button
          class="inline-flex items-center rounded-lg bg-pasadita-green-2 p-2 text-center text-sm font-normal text-pasadita-green-1 transition-all hover:rounded-xl hover:bg-pasadita-green-1 hover:text-white active:scale-95"
          @click="emit('next', identifier)"
        >
          <VueFeather type="chevron-right" stroke-width="2.5" size="16"></VueFeather>
          <span>Continuar</span>
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
