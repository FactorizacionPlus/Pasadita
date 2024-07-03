<script setup lang="ts">
import SquareButton from '@/components/SquareButton.vue';
import type { SquareButtonIconsType } from '@/types/SquareButtonIconsType';

const RESIDENT_ROOT = "/residente/"

interface RouteProp {
  title: string,
  icon: SquareButtonIconsType,
  href: string,
}

const routes : RouteProp[] = [
  {
    title: "Mis Solicitudes",
    icon: "custom-eye-watch",
    href: "historial-solicitudes",
  },
]


</script>

<template>
  <section class="flex size-full items-center justify-center gap-4">
    <SquareButton type="link" :icon="item.icon" :title="item.title" :href="RESIDENT_ROOT + item.href" :key="index" v-for="(item, index) in routes" />
  </section>
</template>
