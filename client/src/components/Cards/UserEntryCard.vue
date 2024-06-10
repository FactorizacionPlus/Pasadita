<script setup lang="ts">
import UserImage from "@/components/UserImage.vue";
import type User from "@/types/User";

const props = defineProps<{ user: User }>();
</script>

<template>
  <li class="flex flex-col gap-2">
    <p class="text-base font-semibold leading-none text-pasadita-blue-1">
      {{ props.user.role }}
    </p>
    <div class="flex items-center gap-2 rounded-t-lg">
      <UserImage class="size-12" :image="props.user.image" size="25" padding="0px" />
      <div class="flex-1">
        <p class="text-sm font-semibold text-pasadita-blue-1">
          {{ props.user.id }}
        </p>
        <p class="text-base font-normal text-pasadita-blue-1">
          {{ props.user.name }} {{ props.user.lastName }}
        </p>
      </div>
    </div>
  </li>
</template>
