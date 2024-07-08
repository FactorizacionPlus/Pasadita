<script setup lang="ts">
import AdminTerminal from "@/components/Cards/TerminalCard.vue";
import SearchBar from "@/components/SearchBar.vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import type Terminal from "@/types/Terminal";
import { onMounted, ref } from "vue";
import { getAllTerminals } from "@/composables/useTerminals";

enum Message {
  TITLE = "Terminales",
}

const terminalList = ref<Terminal[]>([]);
onMounted(async () => {
  const { data } = await getAllTerminals();
  const response = data.value;
  if (Array.isArray(response?.data)) {
    terminalList.value = response.data as Terminal[];
  }
});
</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="tablet" />
  <article class="flex w-full flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar />
    <ul class="grid gap-4 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <AdminTerminal :terminal="terminal" v-for="terminal in terminalList" :key="terminal.type" />
    </ul>
  </article>
</template>
