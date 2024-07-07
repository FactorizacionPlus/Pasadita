<script setup lang="ts">
import type Page from "@/types/Page";
import VueFeather from "vue-feather";

const props = defineProps<Page>();

// Estado activo para la paginación
const activePage = defineModel<number>({ default: 0 });

// Método para manejar el clic en el botón de paginación
const setActivePage = (page: number) => {
  activePage.value = page;
};

// Método para manejar el clic en el chevron izquierdo
const previousPage = () => {
  if (activePage.value > 0) {
    setActivePage(activePage.value - 1);
  }
};

// Método para manejar el clic en el chevron derecho
const nextPage = () => {
  if (activePage.value < props.totalPages - 1) {
    setActivePage(activePage.value + 1);
  }
};
</script>

<template>
  <nav class="flex w-full flex-row justify-center">
    <button
      @click="previousPage"
      class="flex size-10 items-center justify-center rounded-lg bg-white text-sm text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-6 active:scale-95"
    >
      <span class="sr-only">Previous</span>
      <VueFeather type="chevron-left" size="20" stroke-width="3" />
    </button>
    <!--Numbers-->
    <ul class="mx-auto flex flex-row gap-1">
      <li v-for="page in props.totalPages" :key="page">
        <button
          @click="setActivePage(page - 1)"
          :class="[
            'flex size-10 items-center justify-center rounded-lg text-sm transition-all active:scale-95',
            activePage + 1 === page
              ? 'bg-pasadita-blue-2 text-white'
              : 'bg-pasadita-blue-4 text-pasadita-blue-2 hover:rounded-xl hover:bg-pasadita-blue-6',
          ]"
        >
          {{ page }}
        </button>
      </li>
    </ul>
    <button
      @click="nextPage"
      class="flex size-10 items-center justify-center rounded-lg bg-white text-sm text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-6 active:scale-95"
    >
      <span class="sr-only">Next</span>
      <VueFeather type="chevron-right" size="20" stroke-width="3" />
    </button>
  </nav>
</template>
