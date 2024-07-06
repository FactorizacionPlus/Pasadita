<script setup lang="ts">
import type RegisteredUser from "@/types/User/RegisteredUser";
import UserImage from "../UserImage.vue";
import IdentityCard from "@/IdentityCard.svg?component";
import Passport from "@/passport-solid.svg?component";
import VueFeather from "vue-feather";
import type { UserRole } from "@/types/User/UserRole";

enum MESSAGE {
 ENTRY_QUANTITY = "Cantidad de Entradas: "
}

const props = defineProps<{
  user: RegisteredUser;
}>();


const role: { [key in UserRole]: string } = {
  ROLE_INVITED: "Invitado",
  ROLE_RESIDENT: "Residente",
  ROLE_RESIDENT_SUDO: "Residente Encargado",
  ROLE_ADMIN: "Administrador"
};


</script>

<template>
  <li class="flex flex-col rounded-lg border border-blue-200">
    <div class="flex flex-col gap-2 rounded-t-lg bg-shades-100 p-2">
      <h2 class="font-bold">{{ role[props.user.role] }}</h2>
      <div class="flex items-center gap-2">
        <UserImage class="size-[84px]" :image="user.imageUrl" />
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
    <div class="flex justify-end gap-2 p-2">
      <a
        href=""
        class="inline-flex items-center rounded-lg bg-blue-100 p-2.5 text-center text-sm font-normal text-blue-400 transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95"
      >
        <VueFeather type="file-text" stroke-width="2.5" size="16"></VueFeather>
      </a>
      <button
        type="button"
        v-if="props.user.role != 'ROLE_ADMIN'"
        class="inline-flex items-center rounded-lg bg-red-100 p-2.5 text-center text-sm font-normal text-red-400 transition-all hover:rounded-xl hover:bg-red-200 active:scale-95"
      >
        <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
      </button>
    </div>
  </li>
</template>
