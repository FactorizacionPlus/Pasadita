value: "hello",
<script setup lang="ts">
import VueFeather from "vue-feather";

const props = defineProps<{
  icon: "grid" | "user" | "tablet" | "file-text" | "house" | "home" | "user";
  title: string;
}>();
</script>

<template>
  <section class="my-5 flex w-full flex-col bg-white">
    <!--Information-->
    <div
      class="flex flex-col gap-1 rounded-t-lg border border-pasadita-shade-2 bg-pasadita-shade-3 p-4"
    >
      <div class="flex flex-row items-center gap-1 text-pasadita-shade-0">
        <div class="flex shrink-0 items-center justify-center">
          <VueFeather :type="props.icon" size="40" stroke-width="2"></VueFeather>
        </div>
        <p class="flex items-center justify-center text-4xl font-medium">{{ props.title }}</p>
      </div>
    </div>
    <!--Description-->
    <div class="flex w-full flex-col border border-t-0 border-pasadita-shade-2">
      <slot />
    </div>
    <!--CRUD-->
  </section>
</template>
