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
    class="fixed left-0 top-0 z-10 hidden h-dvh w-dvw place-items-center bg-black/50 open:grid"
  >
    <slot></slot>
  </dialog>
</template>
