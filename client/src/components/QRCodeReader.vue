<script setup lang="ts">
import { Html5Qrcode, type Html5QrcodeResult } from "html5-qrcode";
import { onMounted, ref, watch } from "vue";

const token = ref<string>("");

const emit = defineEmits<{
  (e: "update", token: string): void;
}>();

watch(token, (value) => {
  emit("update", value);
});

function onScanSuccess(decodedText: string, _decodedResult: Html5QrcodeResult) {
  token.value = decodedText;
}

function onScanFailure(error: string) {}

onMounted(async () => {
  const cameras = await Html5Qrcode.getCameras();
  console.log(cameras);
  const html5QrCode = new Html5Qrcode("reader");
  html5QrCode.start(
    { facingMode: "environment" },
    {
      fps: 1,
      aspectRatio: 1,
      videoConstraints: {
        aspectRatio: 1,
      },
    },
    onScanSuccess,
    onScanFailure
  );
});
</script>

<template>
  <div id="reader" class="size-1/2"></div>
</template>
