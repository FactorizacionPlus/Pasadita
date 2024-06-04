<script setup lang="ts">
import { usePrefferedCamera } from "@/stores/prefferedCamera";
import {
  Html5Qrcode,
  Html5QrcodeSupportedFormats,
  type CameraDevice,
  type Html5QrcodeResult,
} from "html5-qrcode";
import { onMounted, onUnmounted, ref } from "vue";

const camerasOptions = ref<CameraDevice[]>([]);

const prefferedCamera = usePrefferedCamera();

let html5QrCode: Html5Qrcode;

const emit = defineEmits<{
  (e: "update", token: string): void;
}>();

prefferedCamera.$subscribe(async (_, state) => {
  await html5QrCode.stop();
  html5QrCode.clear();
  await startHtmlQrCode(state.id);
});

function onScanSuccess(decodedText: string, _decodedResult: Html5QrcodeResult) {
  emit("update", decodedText);
}

function startHtmlQrCode(deviceId: string | undefined) {
  return html5QrCode.start(
    deviceId ?? {},
    {
      fps: 1,
      aspectRatio: 1,
      videoConstraints: {
        deviceId: deviceId ?? "",
        aspectRatio: 1,
      },
    },
    onScanSuccess,
    undefined
  );
}

onMounted(async () => {
  const cameras = await Html5Qrcode.getCameras();
  camerasOptions.value = cameras;

  html5QrCode = new Html5Qrcode("reader", {
    verbose: false,
    formatsToSupport: [Html5QrcodeSupportedFormats.QR_CODE],
  });
  startHtmlQrCode(prefferedCamera.id);
});

onUnmounted(() => {
  if (html5QrCode) {
    html5QrCode.stop();
  }
});
</script>

<template>
  <div class="flex flex-col gap-3">
    <!-- <select @change="(v) => prefferedCamera.setId((v.target as HTMLSelectElement).value)"> -->
    <!--   <option -->
    <!--     v-for="camera in camerasOptions" -->
    <!--     :key="camera.id" -->
    <!--     :value="camera.id" -->
    <!--     :selected="camera.id == prefferedCamera.id" -->
    <!--   > -->
    <!--     {{ camera.label }} -->
    <!--   </option> -->
    <!-- </select> -->
    <div id="reader" class="xl:size-[500px]"></div>
  </div>
</template>
