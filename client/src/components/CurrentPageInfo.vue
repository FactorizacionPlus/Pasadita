<script setup lang="ts">
import { actions, type Actions} from "@/utils/actions";
import { defineProps } from "vue";
import VueFeather from "vue-feather";

interface Props {
  icon: "grid" | "user" | "tablet" | "file-text" | "home" | "folder" | "file" | "settings";
  title: string;
  action?: Actions;
}
const props = defineProps<Props>();
const currentAction = props.action ?  actions[props.action] : undefined;

</script>

<template>
  <section class="flex flex-col justify-between gap-2 p-4 text-blue-500 sm:flex-row">
    <ul class="flex gap-4">
      <li class="flex items-center gap-1">
        <VueFeather stroke-width="2" size="32" :type="props.icon" />
        <h1 class="text-3xl font-bold">{{ props.title }}</h1>
      </li>
      <li class="flex items-center gap-1" v-if="currentAction">
        <VueFeather stroke-width="2" size="16" :type="currentAction.icon" />
        <h1>{{ currentAction.name }}</h1>
      </li>
    </ul>
    <div class="ml-auto">
      <slot />
    </div>
  </section>
</template>
