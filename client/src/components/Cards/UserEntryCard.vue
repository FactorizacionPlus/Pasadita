<script setup lang="ts">
import UserImage from "@/components/UserImage.vue";
import IdentityCard from "@/IdentityCard.svg?component"
import type Invited from "@/types/Invited";
import type Resident from "@/types/Resident";
import type User from "@/types/User";

const props = defineProps<{ user: User | Resident | Invited }>();
</script>

<template>
  <li class="flex flex-col gap-2">
    <div class="flex items-center gap-1 rounded-t-lg">
      <UserImage class="size-9" :image="props.user.imageUrl" size="25" padding="0px" />
      <div class="flex-1">
        <div class="flex items-center gap-0.5 text-sm text-blue-500">
          <IdentityCard class="h-2.5" />
          <span class="text-xs">
            {{ props.user.identifier }}
          </span>
        </div>
        <p class="text-base leading-tight text-blue-400">
          {{ props.user.firstName }} {{ props.user.lastName }}
        </p>
      </div>
    </div>
  </li>
</template>
