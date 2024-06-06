<script setup lang="ts">
import VueFeather from "vue-feather";

import UserCardEntry from "../components/UserCardEntry.vue";
</script>

<template>
  <div class="w-[550px] border bg-pasadita-shade-3 border-pasadita-shade-2 rounded-lg p-4">
    <div class="flex flex-row justify-between">
      <UserCardEntry />

      <div class="flex row px-2 gap-3 items-center">
        <div class="flex flex-col text-pasadita-blue-1">
          <p class="font-semibold">Fecha y hora de entrada</p>
          <p>29 de agosto 2023 - 12:36hrs</p>
        </div>
      </div>
    </div>

    <div class="flex flex-row items-center px-2 py-2 gap-1">
      <div class="flex-shrink-0">
        <VueFeather type="home" size="24" stroke="#01193F" stroke-width="1.5"></VueFeather>
      </div>

      <p>Rumbo al palacio de miraflores para realizar tareas de mantenimiento</p>
    </div>
  </div>
</template>

