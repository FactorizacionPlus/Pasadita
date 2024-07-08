<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";
import type Residence from "../../types/Residence";
import ModalDelete from "@/components/Modal/DeleteTemplate.vue";
import ModalSee from "@/components/Modal/Administrator/SeeResidence.vue";
import ModalEdit from "@/components/Modal/Administrator/EditResidence.vue"
import { RouterLink } from "vue-router";
const modalDel = ref<typeof ModalDelete>();
const modalSee = ref<typeof ModalSee>();
const modalEdit = ref<typeof ModalEdit>();

const props = defineProps<{
  residence: Residence;
}>();

const RESIDENCE_ROUTE = "/admin/residencia/";

enum Message {
  SINGULAR = "Residencia",
  PEOPLE = "Habitantes",
  DESCRIPTION = "Descripción",
}
</script>

<template>
  <li class="flex flex-col overflow-hidden rounded-lg border border-blue-200">
    <div class="flex flex-col gap-1 bg-blue-100 p-2">
      <h2 class="text-base font-medium text-pasadita-blue-1">{{ Message.SINGULAR }}</h2>
      <div class="flex items-center gap-2 text-blue-400">
        <VueFeather type="home" size="24" stroke-width="1.5"></VueFeather>
        <p class="text-base font-normal">
          {{ props.residence.habitants }} / {{ props.residence.maxHabitants }} {{ Message.PEOPLE }}
        </p>
      </div>
    </div>
    <div class="flex h-auto flex-1 flex-row bg-white p-2">
      <p class="line-clamp-2 font-normal text-pasadita-blue-1">
        {{ props.residence.description }}
      </p>
    </div>
    <div class="flex flex-row justify-end gap-2 bg-white p-2">
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-red-100 p-2.5 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
        @click="modalDel?.show()"
      >
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-blue-100 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
        @click="modalEdit?.show()"
      >
        <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-blue-100 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
        @click="modalSee?.show()"
      >
        <VueFeather type="eye" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <RouterLink
        :to="RESIDENCE_ROUTE + props.residence.uuid"
        class="inline-flex items-center rounded-lg bg-blue-100 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
      >
        <VueFeather type="file-text" stroke-width="2.5" size="16"></VueFeather>
      </RouterLink>
    </div>
    <ModalDelete reason="¿Desea eliminar esta residencia?" ref="modalDel"> Hola </ModalDelete>
    <ModalSee :residence="residence" ref="modalSee" />
    <ModalEdit :residence="props.residence" ref="modalEdit" />
  </li>
</template>
