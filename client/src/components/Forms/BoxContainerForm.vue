<script setup lang="ts">
import { defineProps } from "vue";
import VueFeather from 'vue-feather';
import type BoxContainerItem from "@/types/BoxContainerItem";

interface Props {
  title: string
  disabled?: boolean
  items?: BoxContainerItem[]
}

const props = defineProps<Props>()

</script>

<template>
  <div class="bg-white rounded-lg">
    <div class="relative bg-inherit">
      <ul
        class="peer flex-wrap gap-2 flex p-4 min-h-10 w-full rounded-[4px] bg-pasadita-shade-3 focus:bg-pasadita-blue-4 transition-all text-pasadita-blue-0 placeholder-transparent ring-1 px-2 ring-pasadita-shade-1 focus:ring-pasadita-blue-3 focus:outline-none disabled:opacity-40">
        <li :key="index" v-for="item, index in items" class="flex gap-1 items-center p-2 rounded-lg bg-pasadita-blue-4">
          <div
            class="flex justify-center items-center flex-shrink-0 border-2 border-pasadita-blue-2 rounded-full size-6 bg-pasadita-blue-4">
            <VueFeather type="user" size="16" stroke="#004f82" stroke-width="1.5"></VueFeather>
          </div>
          <div class="flex items-center gap-2 text-pasadita-blue-2">
            <span>{{ item.text }}</span>
            <button :disabled="props.disabled"
              class="grid disabled:opacity-40 place-items-center rounded-full p-1 size-5 cursor-pointer text-pasadita-blue-2 transition-all disabled:pointer-events-none active:scale-95 hover:text-white hover:bg-pasadita-blue-3">
              <VueFeather type="x" size="16" stroke-width="3"></VueFeather>
            </button>
          </div>
        </li>
      </ul>
      <label
        class="absolute cursor-text left-0 -top-3 text-sm text-pasadita-shade-1 bg-inherit mx-1 px-1 peer-placeholder-shown:text-base peer-placeholder-shown:text-pasadita-shade-0 peer-disabled:opacity-40 peer-placeholder-shown:top-2 peer-focus:-top-3 peer-focus:text-pasadita-blue-3 peer-focus:text-sm transition-all">
        {{ props.title }}
      </label>
    </div>
  </div>
</template>
