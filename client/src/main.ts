import "./assets/main.css";

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import { applyAuthRouting } from "./router/auth";

const app = createApp(App);

app.use(createPinia());
app.use(router);

applyAuthRouting(router);

app.mount("#app");
