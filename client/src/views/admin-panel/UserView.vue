<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import SearchBar from "@/components/SearchBar.vue";
import UserCard from "@/components/Cards/UserCard.vue";
import { getEverythingEverywhereAllAtOnce } from "@/composables/useRegisteredUser";
import { onMounted, ref } from "vue";
import type RegisteredUser from "@/types/User/RegisteredUser";

enum Message {
  TITLE = "Usuarios",
}

const users = ref<RegisteredUser[]>([])

onMounted(async ()=>{
  const { data } = await getEverythingEverywhereAllAtOnce()
  const response = data.value;
  if (Array.isArray(response?.data)) {
    users.value = response.data as RegisteredUser[];
  } })

</script>

<template>
  <CurrentPageInfo :title="Message.TITLE" icon="user" />
  <article class="flex w-full flex-col gap-8 rounded-lg bg-white p-4">
    <SearchBar />
    <ul class="grid gap-4 lg:grid-cols-2">
      <UserCard :user="user" v-for="(user,index) in users" :key="index" />
    </ul>
  </article>
</template>
