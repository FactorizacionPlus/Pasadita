<script setup lang="ts">
import VueFeather from 'vue-feather';
import type Residence from '../../types/Residence';

const props = defineProps<{
  residence: Residence
}>();
</script>

<template>
  <li class="flex flex-col bg-white">
    <!--Information-->
    <div class="flex items-center border bg-pasadita-shade-3 border-pasadita-shade-2 px-2 py-2 rounded-t-lg gap-2">
      <div class="flex-shrink-0">
        <VueFeather type="home" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
      </div>
      <div class="flex-1">
        <p class="text-base font-medium text-pasadita-blue-1">
          Residencia
        </p>
        <p class="text-base font-normal text-pasadita-blue-1">
          Habitantes: {{ props.residence.maxHabitants }}
        </p>
      </div>
    </div>
    <!--Description-->
    <div class="flex flex-row h-[4.5rem] border-x border-pasadita-shade-2 px-2 py-2">
      <p class="font-normal line-clamp-2 text-pasadita-blue-1">
        {{ props.residence.description }}
      </p>
    </div>
    <!--CRUD-->
    <div class="flex flex-row justify-end border border-pasadita-shade-2 px-2 py-2 rounded-b-lg gap-2">
      <button type="button"
        class="bg-pasadita-red-2 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-red-0 hover:bg-pasadita-red-1 active:scale-95 hover:rounded-xl transition-all">
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
      <button type="button"
        class="bg-pasadita-blue-4 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-blue-2 hover:bg-pasadita-blue-3 hover:text-white active:scale-95 hover:rounded-xl transition-all">
        <VueFeather type="edit-2" stroke-width="2.5" size="16"></VueFeather>
        <span class="sr-only">Icon description</span>
      </button>
      <button type="button"
        class="bg-pasadita-blue-4 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-blue-2 hover:bg-pasadita-blue-3 hover:text-white active:scale-95 hover:rounded-xl transition-all">
        <VueFeather type="eye" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>

</template>
