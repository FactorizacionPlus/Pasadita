<script setup lang="ts">
import type { SquareButtonIconsType } from "@/types/SquareButtonIconsType";
import { ref, onMounted, shallowRef } from "vue";
import VueFeather from "vue-feather";

const CUSTOM_SLUG = "custom-";
const isCustomIcon = ref(false);
const dynamicLoadComponent = shallowRef();

const props = defineProps<{
  title: string;
  href?: string;
  icon: SquareButtonIconsType;
  type: "button" | "link"
}>();

const emit = defineEmits(['click']);

const loadSvgComponent = async (icon: string) => {
  try {
    const module = await import(`@/assets/custom-icons/${icon}.svg?component`);
    dynamicLoadComponent.value = module.default;
  } catch (error) {
    console.error(`Error loading SVG component: ${error}`);
  }
};

isCustomIcon.value = props.icon.includes(CUSTOM_SLUG);

onMounted(async () => {
  if (props.icon.includes(CUSTOM_SLUG)) {
    await loadSvgComponent(props.icon.replace(CUSTOM_SLUG, ''));
  }
});

const handleClick = () => {
  if (props.type === 'button') {
    emit('click');
  }
}
</script>

<template>
  <component :is="props.type === 'button' ? 'button' : 'a'" :class="{
    'flex size-80 flex-col items-center justify-center gap-6 rounded-md bg-white p-4 text-blue-400 transition-all hover:bg-blue-400 hover:text-white hover:shadow-glow active:scale-95': true,
    'text-center': true
  }" :href="props.href" @click="handleClick">
    <component v-if="isCustomIcon" :is="dynamicLoadComponent"></component>
    <VueFeather v-else :type="props.icon" class="size-[7.5rem]" />
    <span class="text-2xl font-medium">{{ props.title }}</span>
  </component>
</template>
