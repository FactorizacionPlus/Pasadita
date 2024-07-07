<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import GenericTable from "@/components/GenericTable.vue";
import type Entry from "@/types/Entry";
import SearchBar from "@/components/SearchBar.vue";
import VueFeather from "vue-feather";
import { onMounted, ref } from "vue";
import { getEntriesByUser } from "@/composables/useEntry";
import { useRoute } from "vue-router";
import { getUserByIdentifier } from "@/composables/useRegisteredUser";
import UserMinimalCard from "@/components/Cards/UserMinimalCard.vue";
import type RegisteredUser from "@/types/User/RegisteredUser";
import type { GenericTableType } from "@/types/GenericTableType";
import EventsWarning from "@/components/EventsWarning.vue";
import { useToast } from "@/stores/toast";
import { ToastType } from "@/types/Toast";

const searchText = ref("");
const hideNoResults = ref(false);

const route = useRoute();
const identifier = route.params.usuario as string;

enum Message {
  TITLE = "Usuario",
  RELOAD_TEXT = "Recargar",
}

const user = ref<RegisteredUser>();
const rows = ref<GenericTableType[]>([]);
const entries = ref<Entry[]>([]);
const code = ref<number | null>();

const { addToast } = useToast();

onMounted(async () => {
  const response = await getUserByIdentifier(identifier);
  const { data } = response;
  user.value = data.value?.data;
  code.value = response.statusCode.value;
  if (!data.value?.ok) return;

  refreshEntries();
});

function handleClick() {
  addToast({ message: "Recargando las entradas por Usuario...", type: ToastType.LOADING });
  refreshEntries();
}

async function refreshEntries() {
  const { data } = await getEntriesByUser(identifier);

  entries.value = data.value?.data || [];

  rows.value = [
    {
      name: "Residencia",
      icon: "home",
      items: [...entries.value.map((item) => item.residence.description)],
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
  ];
}
</script>
<template>
  <CurrentPageInfo :title="Message.TITLE" icon="user" action="history" />
  <EventsWarning v-if="code != 200 && code != undefined" :code="code" />
  <article v-if="code == 200" class="flex flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar @toggle-no-results="hideNoResults = $event" @search="searchText = $event" />
    <section class="overflow-x-auto rounded-xl pb-1">
      <GenericTable :hide-no-results="hideNoResults" :table="rows" :search-term="searchText">
        <div class="flex items-center justify-between p-2">
          <UserMinimalCard v-if="user" :user="user" />
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
