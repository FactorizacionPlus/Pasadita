<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/guest/about">About</RouterLink>
      <RouterLink to="/guest/access">Access</RouterLink>
    </nav>
  </header>
  <main>
    <router-view/>
  </main>
  </template>
  
  <script>
  export default {
    name: 'DefaultLayout',
  };
  </script>