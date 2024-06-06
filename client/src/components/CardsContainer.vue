<script setup lang="ts">
import VueFeather from 'vue-feather';
import type InvitedResident from '../types/InvitedResident';

const props = defineProps<{
  icon: "grid" | "user" | "tablet" | "file-text" | "house",
  title: string,
}>();
</script>

<template>
  <div class="flex flex-col w-full bg-white my-5">
    <!--Information-->
    <div class="flex flex-col border bg-pasadita-shade-3 gap-1 border-pasadita-shade-2 pb-0 px-2 pt-2 rounded-t-lg ">
      <div class="flex flex-row gap-2 text-pasadita-shade-0">
        <div class="flex justify-center items-center flex-shrink-0">
          <VueFeather :type="props.icon" size="40" stroke-width="1.5"></VueFeather>
        </div>
        <p class="flex justify-center items-center text-[2.1rem]">{{ props.title }}</p>
      </div>
    </div>
    <!--Description-->
    <div class="flex flex-row border border-t-0 border-pasadita-shade-2 px-2 py-2 w-full">
      <div class="flex flex-col justify-center w-full gap-1">
        <slot />
      </div>
    </div>
    <!--CRUD-->
  </div>

</template>
