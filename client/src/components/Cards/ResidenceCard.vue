<script setup lang="ts">
import VueFeather from "vue-feather";
import type Residence from "../../types/Residence";

const props = defineProps<{
  residence: Residence;
}>();
</script>

<template>
  <li class="flex flex-col bg-white">
    <!--Information-->
    <div
      class="flex items-center gap-2 rounded-t-lg border border-pasadita-shade-2 bg-pasadita-shade-3 p-2"
    >
      <div class="shrink-0">
        <VueFeather type="home" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
      </div>
      <div class="flex-1">
        <p class="text-base font-medium text-pasadita-blue-1">Residencia</p>
        <p class="text-base font-normal text-pasadita-blue-1">
          Habitantes: {{ props.residence.maxHabitants }}
        </p>
      </div>
    </div>
    <!--Description-->
    <div class="flex h-[4.5rem] flex-row border-x border-pasadita-shade-2 p-2">
      <p class="line-clamp-2 font-normal text-pasadita-blue-1">
        {{ props.residence.description }}
      </p>
    </div>
    <!--CRUD-->
    <div class="flex flex-row justify-end gap-2 rounded-b-lg border border-pasadita-shade-2 p-2">
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-pasadita-red-2 p-2.5 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
      >
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
      >
        <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
        <span class="sr-only">Icon description</span>
      </button>
      <button
        type="button"
        class="inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
      >
        <VueFeather type="eye" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
</template>
