<script setup lang="ts">
import VueFeather from "vue-feather";
import UserEntry from "@/components/Cards/UserEntryCard.vue"
import type EntryCard from "@/types/EntryCard"
import type UserEntryT  from "@/types/UserEntry";

const props = defineProps<{
  entry: EntryCard
}>();

const userEntryTList: UserEntryT[] = [
  {
    rol: "Peatonal",
    id: 12344567,
    name:"Ponce",
    lastName:"De Leon"
  },
]
</script>

<template>
  <div class="border bg-pasadita-shade-3 border-pasadita-shade-2 rounded-lg p-2">
    <div class="flex flex-row justify-between">
      <div v-for="userEntry in userEntryTList" :key="userEntry.id">
        <UserEntry :userEntry="userEntry" />
      </div>
      <div class="flex row px-2 gap-3 items-center">
        <div class="flex flex-col text-pasadita-blue-1">
          <p class="font-semibold">Fecha y hora de entrada</p>
          <p>{{ props.entry.dateTime }}</p>
        </div>
      </div>
    </div>

    <div class="flex flex-row items-center px-2 py-2 gap-1">
      <div class="flex-shrink-0">
        <VueFeather type="home" size="24" stroke="#01193F" stroke-width="1.5"></VueFeather>
      </div>

      <p>{{ props.entry.direction }}</p>
    </div>
  </div>
</template>
