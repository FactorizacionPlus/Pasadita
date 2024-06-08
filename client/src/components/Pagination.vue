<script setup lang="ts">
import { ref } from 'vue';
import VueFeather from 'vue-feather';

// Definimos el tipo de la prop pagination
interface PaginationItem {
  page: number;
}

const props = defineProps<{
  pagination: PaginationItem[]
}>();

// Estado activo para la paginación
const activePage = ref<number>(props.pagination.length > 0 ? props.pagination[0].page : 1);

// Método para manejar el clic en el botón de paginación
const setActivePage = (page: number) => {
  activePage.value = page;
};

// Método para manejar el clic en el chevron izquierdo
const previousPage = () => {
  if (activePage.value > 1) {
    setActivePage(activePage.value - 1);
  }
};

// Método para manejar el clic en el chevron derecho
const nextPage = () => {
  if (activePage.value < props.pagination.length) {
    setActivePage(activePage.value + 1);
  }
};
</script>

<template>
  <nav class="flex flex-row justify-center">
    <ul class="flex items-center -space-x-px h-10 text-base gap-16">
      <li>
        <a href="#"
          @click.prevent="previousPage"
          class="flex items-center justify-center text-sm w-8 h-8 font-base text-pasadita-blue-2 bg-white rounded-lg active:scale-95 hover:rounded-xl transition-all">
          <span class="sr-only">Previous</span>
          <VueFeather type="chevron-left" size="20" stroke-width="3" />
        </a>
      </li>
      <!--Numbers-->
      <div class="flex flex-row gap-1">
        <li v-for="pagination in props.pagination" :key="pagination.page">
          <a href="#"
             @click.prevent="setActivePage(pagination.page)"
             :class="[
               'flex items-center justify-center text-sm w-8 h-8 font-base border border-pasadita-shade-2 rounded-lg active:scale-95 transition-all',
               activePage === pagination.page ? 'bg-pasadita-blue-2 text-white' : 'bg-white text-pasadita-blue-2 hover:bg-pasadita-blue-2 hover:text-white hover:rounded-xl'
             ]">
            {{ pagination.page }}
          </a>
        </li>
      </div>
      <li>
        <a href="#"
          @click.prevent="nextPage"
          class="flex items-center justify-center text-sm w-8 h-8 font-base text-pasadita-blue-2 bg-white rounded-lg active:scale-95 hover:rounded-xl transition-all">
          <span class="sr-only">Next</span>
          <VueFeather type="chevron-right" size="20" stroke-width="3" />
        </a>
      </li>
    </ul>
  </nav>
</template>
