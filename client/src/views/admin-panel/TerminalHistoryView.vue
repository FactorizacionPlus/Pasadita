<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import GenericTable from "@/components/GenericTable.vue";
import type Entry from "@/types/Entry";
import type Terminal from "@/types/Terminal";
import SearchBar from "@/components/SearchBar.vue";
import TerminalMinimalCard from "@/components/Cards/TerminalMinimalCard.vue";
import VueFeather from "vue-feather";
import { computed, onMounted, ref, watch } from "vue";
import { useRoute } from "vue-router";
import { getAllTerminals } from "@/composables/useTerminals";
import { createEntrySSEByTerminal, getEntriesByTerminalType } from "@/composables/useEntries";
import type { GenericTableType } from "@/types/GenericTableType";

const searchText = ref("");
const hideNoResults = ref(false);
const route = useRoute()
const param = route.params.terminal;

const terminalList = ref<Terminal[]>([]);

const terminalDef: Terminal = {
  type: "DOOR",
  uuid: "xdd",
  entryCount: 10,
}

const terminalRef = ref<Terminal>(terminalDef);
const entries = ref<Entry[]>([]);

onMounted(async () => {

  const { data } = await getAllTerminals();
  const response = data.value;
  if (Array.isArray(response?.data)) {
    terminalList.value = response.data as Terminal[];
  }

  terminalRef.value = terminalList.value.find((terminal) => terminal.type == param) || terminalDef;
  fetchEntries();
})

createEntrySSEByTerminal(terminalRef.value.type).onmessage = (e) => {
  fetchEntries();
}
async function fetchEntries() {
  const { data } = await getEntriesByTerminalType(terminalRef.value.type);
  const response = data.value;
  console.log(response, terminalRef.value.type)
  if (Array.isArray(response?.data)) {
    entries.value = response.data as Entry[];
  }
}

enum Message {
  TITLE = "Terminal",
  RELOAD_TEXT = "Recargar",
}

watch(entries, (v) => {
  const a = [...entries.value.map((item) => item.user.firstName + " " + item.user.lastName)]
  console.log(a)
})

const rows = computed(() => [
  {
    name: "Nombre y Apellidos",
    icon: "user",
    items: [...entries.value.map((item) => item.user.firstName + " " + item.user.lastName)],
  },
  {
    name: "Tipo",
    icon: "menu",
    items: [...entries.value.map((item) => item.user.identifierType)],
  },
  {
    name: "Identificacion",
    icon: "user-check",
    items: [...entries.value.map((item) => item.user.identifier)],
  },
  {
    name: "Descripción",
    icon: "align-left",
    items: [...entries.value.map((item) => item.description)],
  },
  {
    name: "Fecha de Entrada",
    icon: "calendar",
    items: [...entries.value.map((item) => item.accessDate)],
  },
]);
</script>
<template>
  <CurrentPageInfo :title="Message.TITLE" icon="tablet" action="history" />
  <article class="flex flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar @toggle-no-results="hideNoResults = $event" @search="searchText = $event" />
    <section class="overflow-x-auto rounded-xl pb-1">
      <GenericTable :hide-no-results="hideNoResults" :table="rows" :search-term="searchText">
        <div class="flex items-center justify-between p-2">
          <TerminalMinimalCard :terminal="terminalRef" />
          <button
            class="inline-flex items-center gap-1 rounded-lg bg-green-200 p-2.5 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95">
            <VueFeather type="loader" stroke-width="2.5" size="16" />
            <span>{{ Message.RELOAD_TEXT }}</span>
          </button>
        </div>
      </GenericTable>
    </section>
  </article>
</template>
