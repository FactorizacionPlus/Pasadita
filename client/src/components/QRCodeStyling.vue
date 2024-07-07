<template>
  <div id="canvas"></div>
</template>

<script setup lang="ts">
import QRCodeStyling from "qr-code-styling";
import { onMounted, watch } from "vue";

const props = defineProps<{
  data: string;
}>();

const qrCode = new QRCodeStyling({
  width: 240,
  height: 240,
  type: "svg",
  data: props.data,
  dotsOptions: {
    color: "#003474",
    type: "rounded",
  },
  cornersSquareOptions: {
    type: "extra-rounded",
  },
  cornersDotOptions: {
    type: "dot",
  },
  backgroundOptions: {
    color: "#fff",
  },
  imageOptions: {
    crossOrigin: "anonymous",
    margin: 5,
    imageSize: 0.5,
  },
  image: "/identity/pasaditaLogoMin.svg",
});

watch(
  () => props.data,
  (value) => {
    qrCode.update({ data: value });
    console.log(value);
  }
);
onMounted(() => {
  qrCode.append(document.getElementById("canvas") ?? undefined);
});
</script>
