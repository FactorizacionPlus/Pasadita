<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/guest/about">About</RouterLink>
      <RouterLink to="/guest/access">Access</RouterLink>
      <RouterLink to="/admin/terminal">terminal</RouterLink>
    </nav>
  </header>
  <main>
    <router-view/>
  </main>
  </template>
  
  <script>
import { RouterLink } from 'vue-router';

  export default {
    name: 'DefaultLayout',
  };
  </script>