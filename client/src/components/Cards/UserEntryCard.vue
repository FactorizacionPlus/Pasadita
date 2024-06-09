<script setup lang="ts">
<<<<<<< HEAD

import VueFeather from 'vue-feather';
import type UserEntry from "@/types/UserEntry"
import UserImage from "@/components/UserImage.vue"
=======
import VueFeather from "vue-feather";
import type UserEntry from "@/types/UserEntry";
>>>>>>> origin/master

const props = defineProps<{
  userEntry: UserEntry;
}>();
</script>

<template>
<<<<<<< HEAD
    <li class="flex flex-col max-w-sm">
        <div class="ml-3">
            <p class="text-base font-semibold text-pasadita-blue-1">
                {{ props.userEntry.rol }}
            </p>
        </div>
        <div class="flex items-center px-2 py-2 rounded-t-lg gap-2">
            <div
                class="flex justify-center items-center flex-shrink-0 border-2 border-pasadita-blue-2 rounded-full w-10 h-10 bg-pasadita-blue-4 overflow-hidden">
                <UserImage :image="userEntry.image" size="25" padding="0px" />
            </div>
=======
  <li class="flex max-w-sm flex-col">
    <div class="ml-3">
      <p class="text-base font-semibold text-pasadita-blue-1">
        {{ props.userEntry.rol }}
      </p>
    </div>
    <div class="flex items-center gap-2 rounded-t-lg p-2">
      <div
        class="flex size-10 shrink-0 items-center justify-center overflow-hidden rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
      >
        <img
          v-if="props.userEntry.image"
          :src="props.userEntry.image"
          alt="User Image"
          class="size-full object-cover"
        />
        <VueFeather v-else type="user" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
      </div>
>>>>>>> origin/master

      <div class="flex-1">
        <p class="text-base font-semibold text-pasadita-blue-1">
          {{ props.userEntry.id }}
        </p>
        <p class="text-base font-normal text-pasadita-blue-1">
          {{ props.userEntry.name }} {{ props.userEntry.lastName }}
        </p>
      </div>
    </div>
  </li>
</template>
