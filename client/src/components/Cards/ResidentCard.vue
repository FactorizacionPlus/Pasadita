<script setup lang="ts">
import VueFeather from "vue-feather";
import type Resident from "@/types/Resident";
import UserImage from "@/components/UserImage.vue"

const props = defineProps<{ resident: Resident }>();
</script>

<template>
  <li class="flex max-w-sm flex-col bg-white">
    <!--Information-->
    <div
      class="flex flex-col gap-1 rounded-t-lg border border-pasadita-shade-2 bg-pasadita-shade-3 p-2"
    >
      <p class="text-xl font-semibold text-pasadita-blue-1">Residente</p>
    </div>
    <!--Description-->
    <div class="flex flex-row border-x border-pasadita-shade-2 p-2">
      <div class="flex w-full flex-col justify-center gap-2">
        <div class="row flex items-center gap-3 px-2">
          <div
<<<<<<< HEAD
            class="flex justify-center items-center border-2 border-pasadita-blue-2 bg-pasadita-blue-4 rounded-full flex-shrink-0 w-24 h-24">
            <UserImage :image="resident.image" size="70" padding="16px" />
=======
            class="flex size-24 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
          >
            <img
              v-if="props.resident.image"
              :src="props.resident.image"
              alt="User Image"
              class="size-full rounded-full object-cover"
            />
            <VueFeather
              v-else
              type="user"
              size="70"
              stroke="#004f82"
              stroke-width="1.5"
              class="!flex p-5"
            ></VueFeather>
>>>>>>> origin/master
          </div>
          <div class="flex flex-col text-pasadita-blue-1">
            <p class="font-semibold">Nombre</p>
            <p>{{ props.resident.name }}</p>
            <p class="font-semibold">Apellido</p>
            <p>{{ props.resident.secondName }}</p>
            <p class="font-semibold">Identificacion</p>
            <p>{{ props.resident.identification }}</p>
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
      >
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
</template>
