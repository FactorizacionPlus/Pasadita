<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import SearchBar from "@/components/SearchBar.vue";
import PaginationItem from "@/components/PaginationItem.vue";
import type EntryType from "@/types/Entry";
import EntryCard from "@/components/Cards/EntryCard.vue";
import type RegisteredUser from "@/types/User/RegisteredUser";
import type Pagination from "@/types/utils/Pagination";
import type Page from "@/types/Page";
import { ref } from "vue";

enum Message {
  TITLE = "Usuarios",
}

const pagination = ref<Partial<Pagination>>({
  page: 0,
});
const page = ref<Page>();

/*const entryList: EntryType[] = [
  {
    uuid: "",
    user: {
      identifier: "0011904041016S",
      imageUrl: "https://www.github.com/davidquintr.png",
      firstName: "David",
      lastName: "Quintanilla",
      identifierType: "PASSPORT",
      role: "Residente",
      status: "ACTIVE",
    } as unknown as RegisteredUser,
    description: "Rumbo a la casas de los pueblos",
    accessDate: new Date("10-04-2005"),
    terminal: {
      type: "",
      uuid: "",
    },
  },
  {
    uuid: "",
    user: {
      identifier: "0011904041016S",
      imageUrl: "https://www.github.com/poncka.png",
      firstName: "David",
      lastName: "Parrales Ponce",
      identifierType: "PASSPORT",
      status: "ACTIVE",
    } as unknown as RegisteredUser,
    description: "Rumbo a la casas de los pueblos",
    accessDate: new Date("10-04-2015 12:00"),
    terminal: {
      type: "",
      uuid: "",
    },
  },
];*/
</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="user" />
  <article class="flex w-full flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar />
    <ul class="grid gap-4 lg:grid-cols-2">
      <!--<EntryCard :entry="entry" v-for="(entry, index) in entryList" :key="index" />-->
    </ul>
    <PaginationItem v-bind="page" v-model="pagination.page" v-if="page" />
  </article>
</template>
