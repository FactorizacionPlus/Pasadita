<script setup lang="ts">
import type Administrator from "@/types/Administrator";
import UserImage from "../UserImage.vue";

enum dic {
  ADMINISTRATOR_NAME = "Administrador del Sistema",
}

const props = defineProps<{ admin: Administrator }>();
</script>

<template>
  <div
    class="flex flex-col items-center justify-center gap-1 text-center xl:flex-row xl:justify-normal xl:text-left"
  >
    <UserImage class="size-20" :image="props.admin.imageUrl" />
    <div class="text-blue-400">
      <span class="text-2xl">{{ dic.ADMINISTRATOR_NAME }}</span>
      <h2 class="text-[32px] font-semibold">
        {{ props.admin.firstName }} {{ props.admin.lastName }}
      </h2>
    </div>
  </div>
</template>
