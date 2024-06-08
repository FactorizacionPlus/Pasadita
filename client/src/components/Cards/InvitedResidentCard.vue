<script setup lang="ts">
import VueFeather from 'vue-feather';
import type InvitedResident from '@/types/InvitedResident';

const props = defineProps<{ invitedResident: InvitedResident }>();
</script>

<template>
  <div class="flex flex-col max-w-sm bg-white">
    <!--Information-->
    <div class="flex flex-col border bg-pasadita-shade-3 gap-1 border-pasadita-shade-2 pb-0 px-2 pt-2 rounded-t-lg ">
      <p class="text-pasadita-blue-1 font-semibold text-xl">Invitado</p>
    </div>
    <!--Description-->
    <div class="flex flex-row border-x border-pasadita-shade-2 px-2 py-2">
      <div class="flex flex-col justify-center w-full gap-2">
        <div class="flex row px-2 gap-3 items-center">
          <div
            class="flex justify-center items-center p-5 border-2 border-pasadita-blue-2 bg-pasadita-blue-4 rounded-full flex-shrink-0">
            <VueFeather type="user" size="70" stroke="#004f82" stroke-width="1.5"></VueFeather>
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
        <p class="text-pasadita-blue-1 font-semibold">Numero de entradas: {{ props.invitedResident.entries }}</p>
      </div>
    </div>
    <!--CRUD-->
    <div class="flex flex-row border justify-end border-pasadita-shade-2 px-2 py-2 rounded-b-lg gap-2">
      <button type="button"
        class="bg-pasadita-red-2 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-red-0 hover:bg-pasadita-red-1 active:scale-95 hover:rounded-xl transition-all">
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button type="button"
        class="bg-pasadita-blue-4 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-blue-2 hover:bg-pasadita-blue-3 hover:text-white active:scale-95 hover:rounded-xl transition-all">
        <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </div>

</template>
