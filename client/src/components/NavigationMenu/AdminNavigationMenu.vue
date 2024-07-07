AdminNavigationMenu:
<template>
  <nav
    class="sticky z-10 flex flex-col bg-white p-2 shadow-header transition-all sm:top-4 sm:rounded-lg md:relative md:top-0 md:p-4"
    :class="isFullNav ? 'md:w-[360px]' : 'md:w-[80px]'"
  >
    <div class="relative flex justify-between gap-4 md:flex-col md:justify-start">
      <img
        v-if="isFullNav"
        class="mx-auto hidden h-[45px] w-auto md:block"
        src="/identity/pasaditaLogo.svg"
      />
      <img v-else class="hidden h-8 w-auto md:block" src="/identity/pasaditaLogoMin.svg" />
      <button
        :class="isNavActive ? 'bg-blue-200 hover:!bg-blue-100' : ''"
        @click="
          () => {
            isNavActive = !isNavActive;
          }
        "
        class="grid size-12 place-items-center rounded-lg text-blue-400 transition-all hover:bg-blue-200 active:scale-95 md:hidden"
      >
        <VueFeather type="menu" class="w-6" stroke-width="1.5"></VueFeather>
      </button>
      <button
        @click="
          () => {
            isFullNav = !isFullNav;
          }
        "
        class="absolute -right-8 top-1/2 hidden size-8 -translate-y-1/2 place-items-center rounded-lg bg-blue-100 text-blue-400 shadow-header transition-all hover:rounded-xl hover:bg-blue-200 active:scale-95 md:grid"
      >
        <VueFeather
          :class="isFullNav && 'rotate-180'"
          type="chevron-right"
          class="w-4 transition-transform"
          stroke-width="2.5"
        ></VueFeather>
      </button>
    </div>
    <div
      :class="isNavActive ? 'block' : 'hidden'"
      class="mt-4 h-full flex-col justify-between gap-1 md:flex"
    >
      <div
        :class="isUserPresetActive && 'bg-shades-100'"
        class="mb-4 flex flex-col gap-2 rounded-lg transition-colors md:mb-0"
      >
        <button
          @click="
            () => {
              isUserPresetActive = !isUserPresetActive;
            }
          "
          class="flex items-center gap-1 rounded-lg p-2 text-blue-400 transition-all hover:bg-shades-100 active:scale-95"
        >
          <UserImage
            :class="!isFullNav && 'md:!size-8 md:mx-auto'"
            class="size-12"
            :image="user?.imageUrl"
          />

          <div class="text-left" :class="!isFullNav && 'md:hidden'">
            <span>Administrador del Sistema</span>
            <p class="font-semibold">{{ user?.firstName }}</p>
          </div>
          <div :class="!isFullNav && 'md:hidden'" type="button" class="ml-auto">
            <VueFeather class="size-4" type="chevron-down" />
          </div>
        </button>

        <div class="p-2 pt-0" v-if="isUserPresetActive">
          <RouterLink
            :key="index"
            v-for="(route, index) in userPresets"
            :class="[!isFullNav && '!p-2 md:aspect-square md:justify-center']"
            class="flex items-center gap-1.5 p-3 text-xl text-blue-400 transition-all last:mt-auto hover:rounded-xl hover:bg-white active:scale-95"
            :to="route.link"
          >
            <VueFeather :type="route.icon" class="w-4" stroke-width="2"></VueFeather>
            <span :class="!isFullNav && 'md:hidden'">{{ route.text }}</span>
          </RouterLink>
        </div>
      </div>

      <RouterLink
        :key="index"
        v-for="(route, index) in routes"
        :class="[
          currentPath == ADMIN_ROUTE + route.link
            ? 'translate-x-1 rounded-xl bg-blue-200 font-medium hover:bg-blue-100'
            : 'rounded-md hover:bg-blue-100',
          isFullNav ? 'min-w-full' : 'md:aspect-square md:justify-center',
        ]"
        class="flex items-center gap-1.5 p-3 text-xl text-blue-400 transition-all last:mt-auto hover:rounded-xl active:scale-95"
        :to="`${ADMIN_ROUTE}${route.link}`"
      >
        <VueFeather :type="route.icon" class="w-6" stroke-width="2"></VueFeather>
        <span :class="!isFullNav && 'md:hidden'">{{ route.text }}</span>
      </RouterLink>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { useUser } from "@/stores/user";
import { RouterLink, useRoute } from "vue-router";
import VueFeather from "vue-feather";
import { ref, watch } from "vue";
import UserImage from "../UserImage.vue";

const isNavActive = ref(false);
const isFullNav = ref(true);
const isUserPresetActive = ref(false);

const route = useRoute();
const currentPath = ref(route.path);

const user = useUser().user;

watch(route, (newRoute) => {
  currentPath.value = newRoute.path;
});

const userPresets = [
  {
    text: "Modificar Perfil",
    link: "",
    icon: "edit-2",
  },
  {
    text: "Cerrar Sesión",
    link: "",
    icon: "log-out",
  },
];

const ADMIN_ROUTE = "/admin/";
const routes = [
  {
    text: "Dashboard",
    link: "",
    icon: "grid",
  },
  {
    text: "Entradas",
    link: "entradas",
    icon: "folder",
  },
  {
    text: "Terminales",
    link: "terminales",
    icon: "tablet",
  },
  {
    text: "Residencias",
    link: "residencias",
    icon: "home",
  },
  {
    text: "Usuarios",
    link: "usuarios",
    icon: "user",
  },
  {
    text: "Configuraciones",
    link: "configuraciones",
    icon: "settings",
  },
];
</script>

<style scoped></style>
