<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import GenericTable from "@/components/GenericTable.vue";
import type Entry from "@/types/Entry";
import type Terminal from "@/types/Terminal";
import SearchBar from "@/components/SearchBar.vue";
import TerminalMinimalCard from "@/components/Cards/TerminalMinimalCard.vue";
import VueFeather from "vue-feather";
import { onMounted, ref } from "vue";
import { getEntriesByUser } from "@/composables/useEntry";
import { useRoute } from "vue-router";

const searchText = ref("");
const hideNoResults = ref(false);

const route = useRoute();
const userIdentifier = route.params.usuario as string;



console.log(userIdentifier)


enum Message {
  TITLE = "Usuario",
  RELOAD_TEXT = "Recargar",
}

const terminal: Terminal = {
  type: "Peatonal",
  uuid: "xd",
};

const entries: Entry[] = [];

onMounted(async () => {
  const { data } = await getEntriesByUser(userIdentifier)
  console.log(data)
})

const rows = [
  {
    name: "Nombre y Apellidos",
    icon: "user",
    items: [...entries.map((item) => item.user.firstName + " " + item.user.lastName)],
  },
  {
    name: "Tipo",
    icon: "menu",
    items: [...entries.map((item) => item.user.identifierType)],
  },
  {
    name: "Identificacion",
    icon: "user-check",
    items: [...entries.map((item) => item.user.identifier)],
  },
  {
    name: "Descripción",
    icon: "align-left",
    items: [...entries.map((item) => item.description)],
  },
  {
    name: "Fecha de Entrada",
    icon: "calendar",
    items: [...entries.map((item) => item.accessDate)],
  },
];
</script>
<template>
  <CurrentPageInfo :title="Message.TITLE" icon="user" action="history" />
  <article class="flex flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar @toggle-no-results="hideNoResults = $event" @search="searchText = $event" />
    <section class="overflow-x-auto rounded-xl pb-1">
      <GenericTable :hide-no-results="hideNoResults" :table="rows" :search-term="searchText">
        <div class="flex items-center justify-between p-2">
          <TerminalMinimalCard :terminal="terminal" />
          <button
            class="inline-flex items-center gap-1 rounded-lg bg-green-200 p-2.5 text-center text-sm font-normal text-green-400 transition-all hover:rounded-xl hover:bg-green-300 active:scale-95"
          >
            <VueFeather type="loader" stroke-width="2.5" size="16" />
            <span>{{ Message.RELOAD_TEXT }}</span>
          </button>
        </div>
      </GenericTable>
    </section>
  </article>
</template>
