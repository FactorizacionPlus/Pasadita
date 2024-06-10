<script setup lang="ts">
import { defineProps } from "vue";
import VueFeather from "vue-feather";
import type BoxContainerItem from "@/types/BoxContainerItem";

interface Props {
  title: string;
  disabled?: boolean;
  items?: BoxContainerItem[];
}

const props = defineProps<Props>();
</script>

<template>
  <div class="rounded-lg bg-white">
    <div class="relative bg-inherit">
      <ul
        class="peer flex min-h-10 w-full flex-wrap gap-2 rounded-[4px] bg-pasadita-shade-3 p-4 px-2 text-pasadita-blue-0 ring-1 ring-pasadita-shade-2 transition-all placeholder:text-transparent focus:bg-pasadita-blue-4 focus:outline-none focus:ring-pasadita-blue-3 disabled:opacity-40"
      >
        <li
          :key="index"
          v-for="(item, index) in items"
          class="flex items-center gap-1 rounded-lg border border-pasadita-blue-6 bg-pasadita-blue-4 p-2"
        >
          <div
            class="flex size-6 shrink-0 items-center justify-center rounded-full border-2 border-pasadita-blue-2 bg-pasadita-blue-4"
          >
            <VueFeather type="user" size="16" stroke="#004f82" stroke-width="1.5"></VueFeather>
          </div>
          <div class="flex items-center gap-2 text-pasadita-blue-2">
            <span>{{ item.text }}</span>
            <button
              :disabled="props.disabled"
              class="grid size-5 cursor-pointer place-items-center rounded-full p-1 text-pasadita-blue-2 transition-all hover:bg-pasadita-blue-6 active:scale-95 disabled:pointer-events-none disabled:opacity-40"
            >
              <VueFeather type="x" size="16" stroke-width="3"></VueFeather>
            </button>
          </div>
        </li>
      </ul>
      <label
        class="absolute -top-3 left-0 mx-1 cursor-text bg-inherit px-1 text-sm text-pasadita-shade-1 transition-all peer-placeholder-shown:top-2 peer-placeholder-shown:text-base peer-placeholder-shown:text-pasadita-shade-0 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-pasadita-blue-3 peer-disabled:opacity-40"
      >
        {{ props.title }}
      </label>
    </div>
  </div>
</template>
