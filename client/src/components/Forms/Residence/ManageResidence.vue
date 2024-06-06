<script setup lang="ts">
import { ref } from 'vue';
import HeaderModal from '@/components/Modal/HeaderModal.vue';
import InputForm from '../InputForm.vue';
import SelectForm from '../SelectForm.vue';
import type Option from '@/types/Option';
import VueFeather from 'vue-feather';
import TextAreaForm from '../TextAreaForm.vue';
import BoxContainerForm from '../BoxContainerForm.vue';
import type BoxContainerItem from '@/types/BoxContainerItem';

const people: Option[] = [
    {
        text: "Juan Daniel",
        value: "cat"
    },
    {
        text: "Juana Daniela",
        value: "cat2"
    },
    {
        text: "Juan Pablo",
        value: "cat3"
    },
];

const boxItems : BoxContainerItem[] = [
  {
    text: "Rita",
    icon: true,
    value: "hello"
  },
  {
    text: "Wilson",
    icon: true,
    value: "hello"
  }
]

const dialogRef = ref<HTMLDialogElement | null>(null);

const closeModal = () => {
    if (dialogRef.value) {
        dialogRef.value.close();
    }
};
</script>

<template>
    <dialog ref="dialogRef" open class="w-dvw hidden open:grid h-dvh place-items-center bg-black/50 fixed top-0">
        <form class="bg-white rounded-md overflow-hidden">
            <HeaderModal title="Residencia" icon="file-text" action="add" />
            <div class="flex flex-col gap-6 px-4 py-6 max-h-[80vh]">
                <InputForm name="quantity" title="Cantidad de habitantes" type="number" placeholder="Inserte la cantidad de habitantes" />
                <TextAreaForm name="description" title="Descripcion de la residencia" placeholder="Inserte la cantidad de habitantes" />
                <BoxContainerForm :items="boxItems" title="Residentes Encargados" />
                <div class="flex gap-2">
                    <SelectForm name="resident-admin" defaultOption="Seleccione al Residente Encargado" title="Residentes Encargados" :options="people" />
                    <button class="bg-pasadita-blue-4 font-normal rounded-lg text-sm p-2 text-center inline-flex items-center text-pasadita-blue-2 hover:bg-pasadita-blue-3 hover:text-white active:scale-95 hover:rounded-xl transition-all">
                        <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
                        <span>Agregar</span>
                    </button>
                </div>
            </div>
            <div class="flex justify-end gap-2 p-2 border-t border-t-pasadita-shade-2">
                <button class="bg-pasadita-green-2 font-normal rounded-lg text-sm p-2 text-center inline-flex items-center text-pasadita-green-1 hover:bg-pasadita-green-1 hover:text-white active:scale-95 hover:rounded-xl transition-all">
                    <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
                    <span>Aceptar</span>
                </button>
                <button @click="closeModal" type="button" class="bg-pasadita-red-2 font-normal rounded-lg text-sm p-2 text-center inline-flex items-center text-pasadita-red-0 hover:bg-pasadita-red-1 active:scale-95 hover:rounded-xl transition-all">
                    <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
                    <span>Cancelar</span>
                </button>
            </div>
        </form>
    </dialog>
</template>
