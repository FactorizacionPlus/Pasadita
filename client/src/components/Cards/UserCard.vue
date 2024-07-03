<script setup lang="ts">
import VueFeather from "vue-feather";
import type User from "@/types/User";

const props = defineProps<{
  user: User;
}>();

/**
 * <p class="text-base font-semibold text-pasadita-blue-1">
      {{ props.user.role }}
    </p>
 */
</script>

<template>
  <li class="flex max-w-sm flex-col">
    <div class="flex items-center gap-2 rounded-t-lg p-2">
      <div
        class="flex size-10 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
      >
        <VueFeather type="user" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
      </div>

      <div class="flex-1">
        <p class="text-base font-semibold text-pasadita-blue-1">
          {{ props.user.identifier }}
        </p>
        <p class="text-base font-normal text-pasadita-blue-1">
          {{ props.user.firstName }} {{ props.user.lastName }}
        </p>
      </div>
    </div>
  </li>
</template>
