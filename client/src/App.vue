<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/about">About</RouterLink>
      <RouterLink to="/residences/create">Create Residence</RouterLink>
      <RouterLink to="/access">Access</RouterLink>
    </nav>
  </header>

  <RouterView />
</template>
