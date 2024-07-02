<script setup lang="ts">
import { ref } from 'vue';
import VueFeather from 'vue-feather';

enum dic {
  HIDE = "Ocultar",
  SHOW = "Mostrar"
}

const searchText = ref('');
const hideNoResults = ref(false);

const emit = defineEmits(['search', 'toggleNoResults']);

const handleSubmit = (event: Event) => {
  event.preventDefault();
  emit('search', searchText.value);
};

const handleToggleHideNoResults = (event: Event) => {
  event.preventDefault();
  hideNoResults.value = !hideNoResults.value;
  emit('toggleNoResults', hideNoResults)
}

const clearSearch = () => {
  searchText.value = '';
  emit('search', searchText.value);
};
</script>

<template>
  <form @submit.prevent="handleSubmit" class=" ml-auto flex w-full max-w-screen-sm justify-end gap-1 overflow-auto">
    <slot/>
    <div class="relative w-full">
      <input v-model="searchText" type="text"
        class="peer h-10 w-full rounded-md border-x border-b-2 border-t border-shades-400 bg-shades-100 px-8 text-blue-500 outline-none transition-all placeholder:text-shades-400 hover:bg-blue-100 focus:border-blue-400 focus:bg-blue-200 focus:text-blue-400 focus:placeholder:text-blue-300 disabled:opacity-40"
        placeholder="Buscar" />
      <VueFeather
        class="absolute left-0 top-1/2 size-5 -translate-y-1/2 translate-x-[6px] text-shades-400 peer-focus:text-blue-400"
        type="search" stroke-width="2.5" />
      <button @click="clearSearch" type="button">
        <VueFeather type="x"
          class="absolute right-0 top-1/2 size-5 -translate-y-1/2 translate-x-[-16px] text-shades-400 transition-all hover:text-blue-400 active:scale-90"
          :class="searchText.length > 0 ? 'scale-100' : 'scale-0'" />
      </button>
    </div>
    <button @click="handleToggleHideNoResults" type="button"
      class="flex aspect-square h-10 w-auto place-items-center items-center justify-center gap-1 rounded-lg p-2 transition-all hover:rounded-xl active:scale-95"
      :class="hideNoResults ? 'bg-red-200 text-red-400 hover:bg-red-300' : 'bg-green-200 text-green-400 hover:bg-green-300'">
      <VueFeather :type="hideNoResults ? 'eye-off' : 'eye'" class="size-4" />
      <span class="hidden">{{ hideNoResults ? dic.HIDE : dic.SHOW }}</span>
    </button>
  </form>
</template>
