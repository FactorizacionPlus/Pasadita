<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import UserImage from "@/components/UserImage.vue";
import ModalDelete from "@/components/Modal/Resident/DeleteResident.vue";
import type Resident from "@/types/Resident";
const modalDel = ref<typeof ModalDelete>();

const props = defineProps<{ resident: Resident }>();
</script>

<template>
  <li class="flex flex-col bg-white">
    <!--Information-->
    <div
      class="flex flex-col gap-1 rounded-t-lg border border-pasadita-shade-2 bg-pasadita-blue-5 p-2"
    >
      <p class="text-xl font-semibold text-pasadita-blue-1">Residente</p>
    </div>
    <!--Description-->
    <div class="flex flex-row border-x border-pasadita-shade-2 p-4">
      <div class="flex w-full flex-col justify-center gap-2">
        <div class="flex items-center gap-3">
          <UserImage class="size-36" :image="props.resident.image" size="84" padding="16px" />
          <div class="flex flex-col text-pasadita-blue-1">
            <p class="text-sm font-semibold leading-none">Nombre</p>
            <p class="text-lg leading-relaxed">{{ props.resident.name }}</p>
            <p class="text-sm font-semibold leading-none">Apellido</p>
            <p class="text-lg leading-relaxed">{{ props.resident.secondName }}</p>
            <p class="text-sm font-semibold leading-none">Identificacion</p>
            <p class="text-lg leading-relaxed">{{ props.resident.identification }}</p>
          </div>
        </div>
        <p class="font-semibold text-pasadita-blue-1">
          Numero de entradas: {{ props.resident.entries }}
        </p>
      </div>
    </div>
    <!--CRUD-->
    <div class="flex flex-row justify-end gap-2 rounded-b-lg border border-pasadita-shade-2 p-2">
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2.5 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
        @click="modalDel?.show()"
      >
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
  <ModalDelete :name="resident.name" :lastname="resident.secondName" ref="modalDel">
    Hola
  </ModalDelete>
</template>
