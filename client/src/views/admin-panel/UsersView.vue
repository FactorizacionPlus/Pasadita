<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import SearchBar from "@/components/SearchBar.vue";
import UserCard from "@/components/Cards/UserCard.vue";
import { getEverythingEverywhereAllAtOnce } from "@/composables/useRegisteredUser";
import { onMounted, ref } from "vue";
import type RegisteredUser from "@/types/User/RegisteredUser";
import { matchSearch } from "@/utils/matchSearch";

enum Message {
  TITLE = "Usuarios",
}

const users = ref<RegisteredUser[]>([])

const searchText = ref("");
const hideNoResults = ref(false);
const fieldsToSearch = ["firstName", "lastName", "identifier", "email"];

onMounted(async () => {
  const { data } = await getEverythingEverywhereAllAtOnce()
  const response = data.value;
  users.value = response?.data || []

  console.log(matchSearch(users.value[0],"Juan", fieldsToSearch))
})


</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="user" />
  <article class="flex w-full flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar @search="searchText = $event" @toggle-no-results="hideNoResults = $event" />
    <ul class="grid gap-4 lg:grid-cols-2">
      <UserCard :class="{
        'animate-scale-up border-2 border-blue-400':
          matchSearch(user, searchText, fieldsToSearch) && searchText.length > 2,
        hidden:
          !matchSearch(user, searchText, fieldsToSearch) &&
          hideNoResults &&
          searchText.length > 2,
      }" :user="user" v-for="(user, index) in users" :key="index" />
    </ul>
  </article>

</template>