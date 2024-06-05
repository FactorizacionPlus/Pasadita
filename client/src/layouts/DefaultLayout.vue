<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/about">About</RouterLink>
      <RouterLink to="/access">Access</RouterLink>
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