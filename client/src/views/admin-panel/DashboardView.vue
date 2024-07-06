<script setup lang="ts">
import { ref, onMounted } from "vue";
import AdministratorCurrentUserCard from "@/components/Cards/AdministratorCurrentUserCard.vue";
import AdministratorTerminalCard from "@/components/Cards/AdministratorTerminalCard.vue";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import GenericTable from "@/components/GenericTable.vue";
import { useUser } from "@/stores/user";
import { getAllTerminals } from "@/composables/useTerminals";
import type Terminal from "@/types/Terminal";
import VueFeather from "vue-feather";

enum Message {
  TITLE = "Dashboard",
  ACTIVE_TERMINALS = "Terminales Activas",
  RECENT_ENTRIES = "Entradas Recientes",
}

const user = useUser();

const terminals = ref<Terminal[]>([]);
const entries: Entry[] = [];

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
    name: "Terminal",
    icon: "tablet",
    items: [...entries.map((item) => item.terminal.type)],
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

onMounted(async () => {
  await loadTerminals();
});

async function loadTerminals() {
  const user = useUser();
  if (!user.user) {
    return;
  }
  const { data } = await getAllTerminals();
  const terminalData = data.value;

  if (!terminalData || !terminalData.ok) return;
  terminals.value = terminalData.data ?? [];
  console.log(terminals.value);
}

</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="grid" />
  <section class="grid flex-1 grid-rows-3 gap-4 lg:grid-cols-2">
    
    <article class="grid place-items-center rounded-lg bg-white p-4">
      <AdministratorCurrentUserCard :admin="user.user" v-if="user.user" />
    </article>

    <article class="flex flex-col gap-2 rounded-lg bg-white p-4">
      <div class="flex items-center gap-1 font-medium text-blue-500">
        <VueFeather type="tablet" class="size-[18px]" />
        <h2 class="text-xl">{{ Message.ACTIVE_TERMINALS }}</h2>
      </div>
      <ul class="grid grid-cols-2 gap-2">
        <AdministratorTerminalCard
          v-for="(terminal, index) in terminals"
          :key="index"
          :terminal="terminal"
        />
      </ul>
    </article>

    <article
      class="row-span-2 flex flex-col gap-2 overflow-x-auto rounded-lg bg-white p-4 lg:col-span-2"
    >
      <div class="flex items-center gap-1 font-medium text-blue-500">
        <VueFeather type="tablet" class="size-[18px]" />
        <h2 class="text-xl">{{ Message.RECENT_ENTRIES }}</h2>
      </div>
      <GenericTable :table="rows" />
    </article>

  </section>
</template>
