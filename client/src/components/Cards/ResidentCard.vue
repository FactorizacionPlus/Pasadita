<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import UserImage from "@/components/UserImage.vue";
import ModalDelete from "@/components/Modal/Resident/DeleteResident.vue";
import type Resident from "@/types/Resident"
import IdentityCard from "@/IdentityCard.svg?component"

const modalDel = ref<typeof ModalDelete>();

enum dic {
  TITLE = "Residente",
  ACCESS_QUANTITY = "Cantidad de Entradas: "
}
const accessCount = 0;
const props = defineProps<{ resident: Resident }>();
</script>

<template>
  <li class="flex flex-col overflow-hidden rounded-lg border border-blue-200 bg-white">
    <div class="flex flex-col gap-2 bg-shades-100 p-2 text-blue-400">
      <h2 class="text-xl font-semibold text-blue-500">{{ dic.TITLE }}</h2>
      <div class="flex items-center gap-1">
        <UserImage class="size-16" :image="props.resident.imageUrl" />
        <div class="flex flex-col">
          <div class="flex items-center gap-1">
            <IdentityCard class="aspect-square h-4 w-auto" />
            <p class="leading-none">{{ props.resident.identifier }}</p>
          </div>
          <p class="text-xl font-medium">{{ props.resident.firstName }} {{ props.resident.lastName }}</p>
          <p class="text-xs font-medium text-blue-500">{{ dic.ACCESS_QUANTITY }} <span class="font-normal text-blue-400">{{accessCount }}</span></p>
        </div>
      </div>
    </div>
    <div class="flex items-center gap-1 p-2 text-blue-400">
      <VueFeather type="home" class="size-6"/>
      <p class="leading-tight">{{ props.resident.residence.description }}</p>
    </div>
    <div class="flex flex-row justify-end gap-2 p-2">
      <button type="button"
        class="inline-flex items-center rounded-lg bg-red-100 p-2.5 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95">
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>

      <button type="button"
        class="inline-flex items-center rounded-lg bg-blue-100 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95">
        <VueFeather type="file-text" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
</template>
