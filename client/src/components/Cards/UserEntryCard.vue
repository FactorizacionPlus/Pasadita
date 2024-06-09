<script setup lang="ts">
import VueFeather from "vue-feather";
import type UserEntry from "@/types/UserEntry";

const props = defineProps<{
  userEntry: UserEntry;
}>();
</script>

<template>
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
