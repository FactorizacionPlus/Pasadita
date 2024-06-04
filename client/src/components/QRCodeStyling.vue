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
  width: 300,
  height: 300,
  type: "svg",
  data: props.data,
  dotsOptions: {
    color: "#004f82",
    type: "dots",
  },
  cornersSquareOptions: {
    type: "dot",
  },
  cornersDotOptions: {
    type: "dot",
  },
  backgroundOptions: {
    color: "#fff",
  },
  imageOptions: {
    crossOrigin: "anonymous",
    margin: 20,
  },
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
