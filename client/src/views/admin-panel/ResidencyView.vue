<script setup lang="ts">
import { ref, onMounted } from "vue";
import VueFeather from "vue-feather";
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Residences from "@/components/Cards/ResidenceCard.vue";
import type Residence from "@/types/Residence";
import SearchBar from "@/components/SearchBar.vue";
import AddResidence from "@/components/Modal/Administrator/AddResidence.vue";
import { getResidence } from "@/composables/useResidence";
import { matchSearch } from "@/utils/matchSearch";
const residence = ref<Residence[]>([]);
const modalAdd = ref<typeof AddResidence>();

enum Message {
  TITLE = "Residencias",
  CREATE_RESIDENCE = "Crear Residencia",
}

const searchText = ref("");
const hideNoResults = ref(false);
const fieldsToSearch = ["description"];

onMounted(async () => {
  await fetchResidence();
});

async function fetchResidence() {
  const { data } = await getResidence();
  const record = data.value;
  if (!record || !record.ok) return;
  residence.value = record.data ?? [];
}

</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="home">
    <button
      class="flex justify-center rounded-lg bg-white p-2 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-shades-100 active:scale-95"
      @click="modalAdd?.show()"
    >
      <VueFeather type="plus" stroke-width="2.5" size="16"></VueFeather>
      <span>{{ Message.CREATE_RESIDENCE }}</span>
    </button>
  </CurrentPageInfo>
  <article class="flex w-full flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar @search="searchText = $event" @toggle-no-results="hideNoResults = $event" />
    <ul class="grid w-full gap-4 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5">
      <Residences
        :class="{
          'animate-scale-up border-2 border-blue-400':
            matchSearch(residence, searchText, fieldsToSearch) && searchText.length > 2,
          hidden:
            !matchSearch(residence, searchText, fieldsToSearch) &&
            hideNoResults &&
            searchText.length > 2,
        }"
        :residence="residence"
        v-for="(residence, index) in residence"
        :key="index"
      />
    </ul>
    <!--<PaginationItem v-bind="page" v-model="pagination.page" v-if="page" />-->
    <AddResidence ref="modalAdd" />
  </article>
</template>
