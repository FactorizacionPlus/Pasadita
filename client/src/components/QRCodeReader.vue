<script setup lang="ts">
import { useCameraPreferences } from "@/stores/cameraPreferences";
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
import { storeToRefs } from "pinia";
import { onMounted, onUnmounted, ref, watch } from "vue";

enum PermissionState {
  REQUESTING,
  FALSE,
  TRUE,
}

const cameraStore = useCameraPreferences();
const { preferences } = storeToRefs(cameraStore);

const permissionState = ref(PermissionState.FALSE);

const cameras = ref<CameraDevice[]>([]);
const canUseTorch = ref(false);

let html5QrCode: Html5Qrcode;

const emit = defineEmits<{
  (e: "update", token: string): void;
  (e: "exception", exception: DOMException): void;
  (e: "init"): void;
}>();

watch(
  () => ({ ...preferences.value }),
  async (current, old) => {
    if (html5QrCode.getState() != Html5QrcodeScannerState.SCANNING) return;

    if (current.cameraId != old.cameraId) {
      await html5QrCode.stop();
      html5QrCode.clear();
      await startHtmlQrCode(current.cameraId);
    }

    const torchFeature = html5QrCode.getRunningTrackCameraCapabilities().torchFeature();
    canUseTorch.value = torchFeature.isSupported();

    if (canUseTorch.value) {
      torchFeature.apply(current.useTorch);
    }
  }
);

const onScanSuccess: QrcodeSuccessCallback = (decodedText: string, result: Html5QrcodeResult) => {
  emit("update", decodedText);
};
const onScanError: QrcodeErrorCallback = (errorMessage: string, error: Html5QrcodeError) => {};

function startHtmlQrCode(deviceId: string | null) {
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

async function requestAccess() {
  emit("init");
  permissionState.value = PermissionState.REQUESTING;
  try {
    cameras.value = await Html5Qrcode.getCameras();
    permissionState.value = PermissionState.TRUE;
  } catch (e: any) {
    permissionState.value = PermissionState.FALSE;
    cameras.value = [];
    emit("exception", e);
  }
}

watch(permissionState, (v) => {
  if (v == PermissionState.TRUE) {
    startHtmlQrCode(preferences.value?.cameraId ?? null);

    if (preferences.value.cameraId == null) {
      preferences.value.cameraId = cameras.value[0].id;
    }
  }
});

onMounted(async () => {
  html5QrCode = new Html5Qrcode("reader", {
    verbose: false,
    formatsToSupport: [Html5QrcodeSupportedFormats.QR_CODE],
  });

  requestAccess();
});

onUnmounted(() => {
  if (html5QrCode && html5QrCode.getState() == Html5QrcodeScannerState.SCANNING) {
    html5QrCode.stop();
  }
});

defineExpose({ requestAccess, startReader: startHtmlQrCode, cameras, canUseTorch });
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
