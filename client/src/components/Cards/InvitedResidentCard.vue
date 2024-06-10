<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import type InvitedResident from "@/types/InvitedResident";
import UserImage from "@/components/UserImage.vue";
import ModalDelete from "@/components/Modal/DeleteGuest.vue";
import ModalSee from "@/components/Modal/Administrator/SeeGuest.vue"
const modalDel = ref<typeof ModalDelete>();
const modalSee = ref<typeof ModalSee>();

const props = defineProps<{ invitedResident: InvitedResident }>();
</script>

<template>
  <li class="flex max-w-sm flex-col bg-white">
    <!--Information-->
    <div class="flex flex-col gap-1 rounded-t-lg border border-pasadita-shade-2 bg-pasadita-shade-3 p-2">
      <p class="text-xl font-semibold text-pasadita-blue-1">Invitado</p>
    </div>
    <!--Description-->
    <div class="flex flex-row border-x border-pasadita-shade-2 p-2">
      <div class="flex w-full flex-col justify-center gap-2">
        <div class="row flex items-center gap-3 px-2">
          <div
            class="flex size-24 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4">
            <UserImage :image="invitedResident.image" size="70" padding="16px" />
          </div>
          <div class="flex flex-col text-pasadita-blue-1">
            <p class="font-semibold">Nombre</p>
            <p>{{ props.invitedResident.name }}</p>
            <p class="font-semibold">Apellido</p>
            <p>{{ props.invitedResident.secondName }}</p>
            <p class="font-semibold">Identificacion</p>
            <p>{{ props.invitedResident.identification }}</p>
          </div>
        </div>
        <p class="font-semibold text-pasadita-blue-1">
          Numero de entradas: {{ props.invitedResident.entries }}
        </p>
      </div>
    </div>
    <!--CRUD-->
    <div class="flex flex-row justify-end gap-2 rounded-b-lg border border-pasadita-shade-2 p-2">
      <button type="button"
        class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2.5 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95" @click="modalDel?.show()">
        <VueFeather type="trash-2" stroke-width="3.5" size="16"></VueFeather>
      </button>
      <button type="button"
        class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95" @click="modalSee?.show()">
        <VueFeather type="eye" stroke-width="3.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
  <ModalDelete :name="invitedResident.name" :lastname="invitedResident.secondName"  ref="modalDel"> Hola </ModalDelete>
  <ModalSee :image="props.invitedResident.image" :name="props.invitedResident.name" :secondname="props.invitedResident.secondName" :identification="props.invitedResident.identification" :entries="props.invitedResident.entries" ref="modalSee"> Hola </ModalSee>
</template>
