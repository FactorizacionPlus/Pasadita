<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import SearchBar from "@/components/SearchBar.vue";
import PaginationItem from "@/components/PaginationItem.vue";
import type EntryType from "@/types/Entry";
import EntryCard from "@/components/Cards/EntryCard.vue";
import { ref } from "vue";
import { matchSearch } from "@/utils/matchSearch";

enum Message {
  TITLE = "Entradas",
  SINGULAR = "Residencia",
  PEOPLE = "Habitantes",
  DESCRIPTION = "Descripción",
}

const searchText = ref("");
const hideNoResults = ref(false);
const fieldsToSearch = ["description", "user.firstName", "user.lastName", "user.identifier"];

const entryList: EntryType[] = [];
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
    <PaginationItem :total-pages="6" />
  </article>
</template>
