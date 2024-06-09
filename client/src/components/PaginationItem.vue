<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";

const props = defineProps<{
  totalPages: number;
}>();

// Estado activo para la paginación
const activePage = ref<number>(props.totalPages > 0 ? 1 : 0);

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
  if (activePage.value < props.totalPages) {
    setActivePage(activePage.value + 1);
  }
};
</script>

<template>
  <nav class="flex flex-row justify-center">
    <ul class="flex h-10 items-center gap-16 text-base">
      <li>
        <a
          href="#"
          @click.prevent="previousPage"
          class="font-base flex size-8 items-center justify-center rounded-lg bg-white text-sm text-pasadita-blue-2 transition-all hover:rounded-xl active:scale-95"
        >
          <span class="sr-only">Previous</span>
          <VueFeather type="chevron-left" size="20" stroke-width="3" />
        </a>
      </li>
      <!--Numbers-->
      <div class="flex flex-row gap-1">
        <li v-for="page in props.totalPages" :key="page">
          <a
            href="#"
            @click.prevent="setActivePage(page)"
            :class="[
              'font-base flex size-8 items-center justify-center rounded-lg border border-pasadita-shade-2 text-sm transition-all active:scale-95',
              activePage === page
                ? 'bg-pasadita-blue-2 text-white'
                : 'bg-white text-pasadita-blue-2 hover:rounded-xl hover:bg-pasadita-blue-2 hover:text-white',
            ]"
          >
            {{ page }}
          </a>
        </li>
      </div>
      <li>
        <a
          href="#"
          @click.prevent="nextPage"
          class="font-base flex size-8 items-center justify-center rounded-lg bg-white text-sm text-pasadita-blue-2 transition-all hover:rounded-xl active:scale-95"
        >
          <span class="sr-only">Next</span>
          <VueFeather type="chevron-right" size="20" stroke-width="3" />
        </a>
      </li>
    </ul>
  </nav>
</template>
