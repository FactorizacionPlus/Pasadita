<script setup lang="ts">
import { ref } from "vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import type Option from "@/types/Option";
import InputForm from "@/components/Forms/InputForm.vue";
import SelectForm from "@/components/Forms/SelectForm.vue";
import VueFeather from "vue-feather";
import TextAreaForm from "@/components/Forms/TextAreaForm.vue";
import BoxContainerForm from "@/components/Forms/BoxContainerForm.vue";
import Modal from "@/components/Modal/ModalComponent.vue";
import type BoxContainerItem from "@/types/BoxContainerItem";

const people: Option[] = [
  {
    text: "Juan Daniel",
    value: "cat",
  },
  {
    text: "Juana Daniela",
    value: "cat2",
  },
  {
    text: "Juan Pablo",
    value: "cat3",
  },
];

const boxItems: BoxContainerItem[] = [
  {
    text: "Rita",
    icon: true,
    value: "hello",
  },
  {
    text: "Wilson",
    icon: true,
    value: "hello",
  },
];

const modal = ref<typeof Modal>();
defineExpose({
  show: () => modal.value?.show(),
  close: () => modal.value?.close(),
});
</script>

<template>
  <Modal ref="modal">
    <form class="overflow-hidden rounded-md bg-white">
      <HeaderModal title="Residencia" icon="home" action="edit" />
      <div class="flex max-h-[80vh] flex-col gap-6 px-4 py-6">
        <InputForm
          name="quantity"
          title="Cantidad de habitantes"
          type="number"
          placeholder="Inserte la cantidad de habitantes"
        />
        <TextAreaForm
          name="description"
          title="Descripcion de la residencia"
          placeholder="Inserte la cantidad de habitantes"
        />
        <BoxContainerForm :items="boxItems" title="Residentes Encargados" />
        <div class="flex gap-2">
          <SelectForm
            name="resident-admin"
            defaultOption="Seleccione al Residente Encargado"
            title="Residentes Encargados"
            :options="people"
          />
          <button
            class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
          >
            <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
            <span>Agregar</span>
          </button>
        </div>
      </div>
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
