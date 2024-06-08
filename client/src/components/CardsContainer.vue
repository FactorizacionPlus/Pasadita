value: "hello",
<script setup lang="ts">
import VueFeather from 'vue-feather';

const props = defineProps<{
  icon: "grid" | "user" | "tablet" | "file-text" | "house" | "home" | "user",
  title: string,
}>();
</script>

<template>
  <section class="flex flex-col w-full bg-white my-5">
    <!--Information-->
    <div class="flex flex-col border bg-pasadita-shade-3 gap-1 border-pasadita-shade-2 p-4 rounded-t-lg ">
      <div class="flex flex-row gap-1 items-center text-pasadita-shade-0">
        <div class="flex justify-center items-center flex-shrink-0">
          <VueFeather :type="props.icon" size="40" stroke-width="2"></VueFeather>
        </div>
        <p class="flex font-medium justify-center items-center text-4xl">{{ props.title }}</p>
      </div>
    </div>
    <!--Description-->
    <div class="flex flex-row border border-t-0 border-pasadita-shade-2 w-full">
        <slot />
    </div>
    <!--CRUD-->
  </section>

</template>
