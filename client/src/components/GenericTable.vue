<script setup lang="ts">
import type { GenericTableType } from "@/types/GenericTableType";
import VueFeather from "vue-feather";

const props = defineProps<{
  table: GenericTableType[];
  searchTerm?: string;
  hideNoResults?: boolean;
}>();

const catchFormat = (entry: any) => {
  switch (true) {
    case entry instanceof Date:
      return (entry as Date).toLocaleDateString();

    case typeof entry === "string":
      return entry;

    default:
      return entry;
  }
};

const rowMatchesSearch = (rowIndex: number): boolean => {
  if (!props.searchTerm || props.searchTerm.length < 2) {
    return false;
  }
  return props.table.some((column) => {
    const entry = column.items[rowIndex];
    return (
      typeof entry === "string" &&
      entry.toLocaleLowerCase().includes(props.searchTerm?.toLocaleLowerCase()!)
    );
  });
};
</script>

<template>
  <div class="flex w-min min-w-full flex-col overflow-hidden rounded-xl border border-blue-200 bg-blue-100">
    <slot />
    <table class="min-w-full">
      <thead class="bg-blue-100 text-left text-blue-500">
        <tr>
          <th class="border-r border-r-blue-300 p-2 font-medium last:border-r-0" v-for="(item, index) in props.table"
            :key="index">
            <div class="flex items-center gap-1.5">
              <VueFeather v-if="item.icon" :type="item.icon" class="size-4 min-w-4" />
              <span class="whitespace-nowrap">{{ item.name }}</span>
            </div>
          </th>
        </tr>
      </thead>
      <tbody class="text-blue-400">
        <tr v-for="i in props.table.at(0)?.items.length" :key="i" :class="{
          'bg-blue-400 font-bold text-white': rowMatchesSearch(i - 1),
          'transition-all odd:bg-shades-100 even:bg-white hover:bg-blue-200': !rowMatchesSearch(
            i - 1
          ),
          hidden: !rowMatchesSearch(i - 1) && hideNoResults,
        }">
          <td class="border-r border-r-blue-300 p-2 last:border-r-0" v-for="j in props.table.length" :key="j">
            {{ catchFormat(props.table[j - 1]?.items[i - 1]) }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
