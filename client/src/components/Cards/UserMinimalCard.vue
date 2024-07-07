<script setup lang="ts">
import type RegisteredUser from "@/types/User/RegisteredUser";
import UserImage from "../UserImage.vue";
import IdentityCard from "@/IdentityCard.svg?component";
import Passport from "@/passport-solid.svg?component";
import getUserRole from "@/utils/getUserRole";

enum MESSAGE {
 ENTRY_QUANTITY = "Cantidad de Entradas: "
}

const props = defineProps<{
  user: RegisteredUser;
}>();

</script>

<template>
  <li class="flex flex-col rounded-lg">
    <div class="flex flex-col gap-2 p-2">
      <h2 class="font-bold">{{ getUserRole(user.role) }}</h2>
      <div class="flex items-center gap-2">
        <UserImage class="size-[52px]" :image="user.imageUrl" />
        <div class="text-blue-400">
          <p class="text-xl leading-tight">
            {{ props.user.firstName }} {{ props.user.lastName }}
          </p>
          <span>{{ props.user.email }}</span>
          <div class="flex items-center gap-1">
            <IdentityCard v-if="props.user.identifierType == 'DUI' && props.user.identifier" class="h-4" />
            <Passport v-else-if="props.user.identifierType == 'PASSPORT' && props.user.identifier" class="h-4" />
            <span>
              {{ props.user.identifier }}
            </span>
          </div>
          <p v-if="user.entryCount > 0" class="font-semibold">{{ MESSAGE.ENTRY_QUANTITY }}<span class="font-normal">{{ user.entryCount }}</span></p>
        </div>
      </div>
    </div>
  </li>
</template>
