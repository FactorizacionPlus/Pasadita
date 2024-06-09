<script setup lang="ts">
import { ref } from "vue";

const dialogRef = ref<HTMLDialogElement>();

function close() {
  if (dialogRef.value) {
    dialogRef.value.close();
  }
}
function show() {
  if (dialogRef.value) {
    dialogRef.value.show();
  }
}

defineExpose({
  show,
  close,
});
</script>

<template>
  <dialog
    ref="dialogRef"
    open
    class="w-dvw h-dvh hidden fixed left-0 top-0 bg-black/50 open:grid place-items-center"
  >
    <slot></slot>
  </dialog>
</template>
