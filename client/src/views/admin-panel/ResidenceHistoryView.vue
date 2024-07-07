<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import GenericTable from "@/components/GenericTable.vue";
import type Entry from "@/types/Entry";
import SearchBar from "@/components/SearchBar.vue";
import ResidenceMinimalCard from "@/components/Cards/ResidenceMinimalCard.vue";
import VueFeather from "vue-feather";
import { onMounted, ref } from "vue";
import { getEntryByResident } from "@/composables/useResidence";
import { useRoute } from "vue-router";
import type Residence from "@/types/Residence";
import type { GenericTableType } from "@/types/GenericTableType";
import { getResidenceByUUID } from "@/composables/useResidence";
import EventsWarning from "@/components/EventsWarning.vue";
import { useToast } from "@/stores/toast";
import { ToastType } from "@/types/Toast";

const searchText = ref("");
const hideNoResults = ref(false);
const route = useRoute();
const residenceIdentifier = route.params.residencia as string;
const residenceMinimal = ref<Residence>();
enum Message {
  TITLE = "Residencia",
  RELOAD_TEXT = "Recargar",
}

console.log("UUID", residenceIdentifier);

const residenceEntry = ref<Entry[]>([]);
const rows = ref<GenericTableType[]>([]);
const code = ref<number | null>();
const { addToast } = useToast();

onMounted(async () => {
  refreshEntries();
});

async function refreshEntries() {
  await fetchResidence();
  const response = await getEntryByResident(residenceIdentifier);
  const { data } = response;
  residenceEntry.value = data.value?.data ?? [];
  code.value = response.statusCode.value;

  rows.value = [
    {
      name: "Nombre y Apellidos",
      icon: "user",
      items: [
        ...residenceEntry.value.map((item) => item.user.firstName + " " + item.user.lastName),
      ],
    },
    {
      name: "Identificacion",
      icon: "user",
      items: [
        ...residenceEntry.value.map(
          (item) => item.user.identifierType + " : " + item.user.identifier
        ),
      ],
    },
    {
      name: "Residencia/Descripción",
      icon: "align-left",
      items: [...residenceEntry.value.map((item) => item.description)],
    },
    {
      name: "Fecha de Acceso",
      icon: "align-left",
      items: [...residenceEntry.value.map((item) => item.accessDate)],
    },
  ];
}

function handleClick() {
  addToast({ message: "Recargando las entradas por Usuario...", type: ToastType.LOADING });
  refreshEntries();
}

async function fetchResidence() {
  const { data } = await getResidenceByUUID(residenceIdentifier as string);
  const record = data.value;
  residenceMinimal.value = record?.data;
  console.log("ResidenceByUUID", residenceMinimal.value);
}
console.log(rows);
</script>
<template>
  <CurrentPageInfo :title="Message.TITLE" icon="home" action="history" />
  <EventsWarning v-if="code != 200 && code != undefined" :code="code" />
  <article class="flex flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar @toggle-no-results="hideNoResults = $event" @search="searchText = $event" />
    <section class="overflow-x-auto rounded-xl pb-1">
      <GenericTable :hide-no-results="hideNoResults" :table="rows" :search-term="searchText">
        <div class="flex items-center justify-between p-2">
          <ResidenceMinimalCard v-if="residenceMinimal" :residence="residenceMinimal" />
          <button
            @click="handleClick"
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
