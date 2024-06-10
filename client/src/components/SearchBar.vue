<script setup lang="ts">
import VueFeather from "vue-feather";
import SelectForm from "./Forms/SelectForm.vue";
import type Option from "@/types/Option";

const orderOption: Option[] = [
  {
    text: "Ordenar por Nombre",
    value: "name",
  },
  {
    text: "Ordenar por Descripción",
    value: "description",
  },
];

const filterBy: Option[] = [
  {
    text: "Filtrar por Nombre",
    value: "name",
  },
  {
    text: "Filtrar por Fecha",
    value: "description",
  },
];
</script>

<template>
  <form class="flex gap-2">
    <div class="relative flex-1">
      <input
        type="text"
        class="peer h-10 w-full rounded-[4px] bg-transparent px-2 pl-8 text-pasadita-blue-0 ring-1 ring-pasadita-shade-2 transition-all placeholder:text-pasadita-shade-1 hover:bg-pasadita-blue-5 focus:bg-pasadita-blue-4 focus:outline-none focus:ring-pasadita-blue-3 disabled:opacity-40"
        placeholder="Buscar"
        required
      />
      <label
        class="absolute left-0 top-1/2 translate-y-[-45%] pl-1 text-pasadita-shade-2 peer-focus:text-pasadita-blue-2"
      >
        <VueFeather class="size-5" type="search" stroke-width="2.5" />
      </label>
    </div>
    <SelectForm
      class="hidden max-w-32 sm:block"
      :options="orderOption"
      default-option="Ordenar por"
      name="Ordenar Por"
    />
    <SelectForm
      class="hidden max-w-32 !bg-pasadita-blue-6 text-pasadita-blue-2 !ring-pasadita-blue-2 hover:bg-pasadita-blue-4 sm:block"
      :options="filterBy"
      default-option="Filtrar por"
      name="Filtrar Por"
    />
  </form>
</template>
