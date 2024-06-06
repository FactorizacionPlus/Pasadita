<script setup lang="ts">
import { defineEmits, defineProps, onMounted } from "vue";

interface Props {
    value?: boolean;
    disabled?: boolean;
    name: string
}

const props = defineProps<Props>();

const emitValue = defineEmits(["update:value"]);

const handleChange = (event: Event) => {
  const inputElement = event.target as HTMLInputElement;
  emitValue("update:value", inputElement.checked);
};

onMounted(() => {
  emitValue("update:value", props.value);
});

</script>

<template>
    <label class="inline-flex items-center cursor-pointer">
        <input :name="props.name" :disabled="props.disabled" @change="handleChange" type="checkbox"
            :value="props.value" class="sr-only peer" />
        <div
            class="peer-disabled:opacity-40 transition-colors relative w-11 h-6 bg-gray-200 peer-focus:outline-none rounded-full peer peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-pasadita-blue-3">
        </div>
    </label>
</template>
