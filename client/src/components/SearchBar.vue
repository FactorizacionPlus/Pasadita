<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from "vue";
import VueFeather from "vue-feather";

// Define variables reactivas para la visibilidad de cada dropdown
const isDropdownVisible1 = ref(false);
const isDropdownVisible2 = ref(false);

// Funciones para alternar la visibilidad de cada dropdown
const toggleDropdown1 = () => {
  isDropdownVisible1.value = !isDropdownVisible1.value;
};

const toggleDropdown2 = () => {
  isDropdownVisible2.value = !isDropdownVisible2.value;
};

// Funciones para cerrar cada dropdown
const closeDropdown1 = () => {
  isDropdownVisible1.value = false;
};

const closeDropdown2 = () => {
  isDropdownVisible2.value = false;
};

// Detectar clics fuera de los dropdowns y cerrarlos
const handleClickOutside = (event: MouseEvent) => {
  const dropdownElement1 = document.getElementById("dropdown1");
  const dropdownElement2 = document.getElementById("dropdown2");
  const buttonElement1 = document.getElementById("dropdownDefaultButton1");
  const buttonElement2 = document.getElementById("dropdownDefaultButton2");

  if (
    dropdownElement1 &&
    buttonElement1 &&
    !dropdownElement1.contains(event.target as Node) &&
    !buttonElement1.contains(event.target as Node)
  ) {
    closeDropdown1();
  }

  if (
    dropdownElement2 &&
    buttonElement2 &&
    !dropdownElement2.contains(event.target as Node) &&
    !buttonElement2.contains(event.target as Node)
  ) {
    closeDropdown2();
  }
};

// Añadir y eliminar el evento de clic en el montaje/desmontaje del componente
onMounted(() => {
  document.addEventListener("click", handleClickOutside);
});

onBeforeUnmount(() => {
  document.removeEventListener("click", handleClickOutside);
});
</script>

<template>
  <div class="flex flex-row gap-2">
    <div class="relative inline-block text-left">
      <button
        id="dropdownDefaultButton1"
        @click="toggleDropdown1"
        class="font-sm inline-flex flex-row items-center gap-2 rounded-lg border border-pasadita-shade-2 bg-white px-5 py-2.5 text-center text-sm text-pasadita-shade-1 hover:bg-pasadita-blue-5 hover:text-pasadita-shade-0 focus:outline-none focus:ring-1 focus:ring-pasadita-shade-2"
        type="button"
      >
        Ordenar por:
        <VueFeather type="chevron-down" size="16"></VueFeather>
      </button>

      <!-- Menú Dropdown 1 con transición -->
      <transition name="fade">
        <div
          id="dropdown1"
          v-show="isDropdownVisible1"
          class="absolute left-0 z-10 mt-2 w-44 rounded-lg bg-white shadow"
        >
          <ul class="py-2 text-sm text-pasadita-shade-0" aria-labelledby="dropdownDefaultButton1">
            <li>
              <a href="#" class="block px-4 py-2 hover:bg-gray-100">Fecha</a>
            </li>
            <li>
              <a href="#" class="block px-4 py-2 hover:bg-gray-100">Nombre</a>
            </li>
            <li>
              <a href="#" class="block px-4 py-2 hover:bg-gray-100">Descripción</a>
            </li>
          </ul>
        </div>
      </transition>
    </div>

    <form class="flex-1">
      <div class="relative">
        <div class="pointer-events-none absolute inset-y-0 start-0 flex items-center ps-3">
          <VueFeather type="search" stroke="#d7d7d7" stroke-width="2.5" size="16"></VueFeather>
        </div>
        <input
          type="text"
          id="simple-search"
          class="block w-full rounded-lg border border-pasadita-shade-2 bg-white p-2.5 ps-10 text-sm text-pasadita-shade-0 focus:outline-none focus:ring-1 focus:ring-pasadita-shade-2"
          placeholder="Buscar por descripcion..."
          required
        />
      </div>
    </form>

    <div class="relative inline-block text-left">
      <button
        id="dropdownDefaultButton2"
        @click="toggleDropdown2"
        class="font-sm inline-flex flex-row items-center gap-2 rounded-lg border border-pasadita-shade-2 bg-pasadita-blue-4 px-5 py-2.5 text-center text-sm text-pasadita-shade-1 hover:bg-pasadita-blue-5 hover:text-pasadita-shade-0 focus:outline-none focus:ring-1 focus:ring-pasadita-shade-2"
        type="button"
      >
        Buscar por:
        <VueFeather type="chevron-down" size="16"></VueFeather>
      </button>

      <!-- Menú Dropdown 2 con transición -->
      <transition name="fade">
        <div
          id="dropdown2"
          v-show="isDropdownVisible2"
          class="absolute left-0 z-10 mt-2 w-44 rounded-lg bg-pasadita-blue-4 shadow"
        >
          <ul class="py-2 text-sm text-pasadita-shade-0" aria-labelledby="dropdownDefaultButton2">
            <li>
              <a href="#" class="block px-4 py-2 hover:bg-gray-100">Nombre</a>
            </li>
            <li>
              <a href="#" class="block px-4 py-2 hover:bg-gray-100">Entradas</a>
            </li>
            <li>
              <a href="#" class="block px-4 py-2 hover:bg-gray-100">Descripción</a>
            </li>
          </ul>
        </div>
      </transition>
    </div>
  </div>
</template>

<style scoped>
/* Tailwind CSS classes for the transition */
.fade-enter-active,
.fade-leave-active {
  @apply transition-opacity duration-300;
}

.fade-enter-from,
.fade-leave-to {
  @apply opacity-0;
}
</style>
