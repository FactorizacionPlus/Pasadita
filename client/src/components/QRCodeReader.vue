<script setup lang="ts">
import { usePrefferedCamera } from "@/stores/prefferedCamera";
import {
  Html5Qrcode,
  Html5QrcodeSupportedFormats,
  type QrcodeSuccessCallback,
  type CameraDevice,
  type Html5QrcodeResult,
  type QrcodeErrorCallback,
  Html5QrcodeScannerState,
} from "html5-qrcode";
import type { Html5QrcodeError } from "html5-qrcode/esm/core";
import { onMounted, onUnmounted, ref } from "vue";

const camerasOptions = ref<CameraDevice[]>([]);

const prefferedCamera = usePrefferedCamera();

let html5QrCode: Html5Qrcode;

const emit = defineEmits<{
  (e: "update", token: string): void;
  (e: "exception", exception: DOMException): void;
}>();

prefferedCamera.$subscribe(async (_, state) => {
  await html5QrCode.stop();
  html5QrCode.clear();
  await startHtmlQrCode(state.id);
});

const onScanSuccess: QrcodeSuccessCallback = (decodedText: string, result: Html5QrcodeResult) => {
  emit("update", decodedText);
};
const onScanError: QrcodeErrorCallback = (errorMessage: string, error: Html5QrcodeError) => {};

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
    onScanError
  );
}

onMounted(async () => {
  console.log("Mounted");

  html5QrCode = new Html5Qrcode("reader", {
    verbose: false,
    formatsToSupport: [Html5QrcodeSupportedFormats.QR_CODE],
  });

  let cameras: CameraDevice[];
  try {
    cameras = await Html5Qrcode.getCameras();
    camerasOptions.value = cameras;
  } catch (e: any) {
    emit("exception", e);
    return;
  }
  startHtmlQrCode(prefferedCamera.id);
});

onUnmounted(() => {
  if (html5QrCode && html5QrCode.getState() == Html5QrcodeScannerState.SCANNING) {
    html5QrCode.stop();
  }
});
</script>

<template>
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
  <div id="reader" class="size-full"></div>
</template>
