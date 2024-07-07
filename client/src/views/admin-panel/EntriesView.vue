<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import SearchBar from "@/components/SearchBar.vue";
import PaginationItem from "@/components/PaginationItem.vue";
import type EntryType from "@/types/Entry";
import EntryCard from "@/components/Cards/EntryCard.vue";
import { onMounted, ref } from "vue";
import { matchSearch } from "@/utils/matchSearch";
import { useAuthenticatedFetch } from "@/composables/useBaseFetch";
import type GeneralResponse from "@/types/GeneralResponse";
import type Page from "@/types/Page";
import type Pagination from "@/types/utils/Pagination";

enum Message {
  TITLE = "Entradas",
  SINGULAR = "Residencia",
  PEOPLE = "Habitantes",
  DESCRIPTION = "Descripción",
}

const searchText = ref("");
const hideNoResults = ref(false);
const fieldsToSearch = ["description", "user.firstName", "user.lastName", "user.identifier"];

const entryList = ref<EntryType[]>([]);

const pagination = ref<Partial<Pagination>>({
  page: 0,
});
const page = ref<Page>();

onMounted(async () => {
  const { data } =
    await useAuthenticatedFetch("/api/entry/all").json<GeneralResponse<EntryType[]>>();
  const response = data.value;
  if (Array.isArray(response?.data)) {
    entryList.value = response.data as EntryType[];
  }
});
</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="folder" action="read_only" />
  <article class="flex w-full flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar @search="searchText = $event" @toggle-no-results="hideNoResults = $event" />
    <ul class="grid gap-4 lg:grid-cols-2">
      <EntryCard
        :class="{
          'animate-scale-up border-2 border-blue-400':
            matchSearch(entry, searchText, fieldsToSearch) && searchText.length > 2,
          hidden:
            !matchSearch(entry, searchText, fieldsToSearch) &&
            hideNoResults &&
            searchText.length > 2,
        }"
        :entry="entry"
        v-for="(entry, index) in entryList"
        :key="index"
      />
    </ul>
    <PaginationItem v-bind="page" v-model="pagination.page" v-if="page" />
  </article>
</template>
