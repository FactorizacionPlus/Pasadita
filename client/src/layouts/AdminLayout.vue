<template>
    <div>
      <header>
        <h2>Admin</h2>
      </header>
      <main>
        <router-view/>
      </main>
    </div>
  </template>
  
  <script>
  export default {
    name: 'AdminLayout',
  };
  </script>