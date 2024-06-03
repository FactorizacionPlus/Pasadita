<template>
    <div class="bg-red-950">
        <form class="max-w-sm mx-auto" @submit.prevent="submitForm">
            <div class="mb-5">
                <label for="text" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Cantidad de
                    habitantes</label>
                <input v-model="habitantes" type="number" id="habitantes"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="Ingrese la cantidad de Habitantes" required />
            </div>
            <div class="mb-5">
                <label for="large-input" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Descripcion</label>
                <input v-model="descripcion" type="text" id="large-input"
                    class="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 text-base focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
            </div>
            <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Aceptar</button>
            <button type="button" @click="cancelForm" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Cancelar</button>
        </form>

    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import type Residence from "../types/Residence"

const habitantes = ref<number>(0);
const descripcion = ref<string>('');

const userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDU0MjcyNEB1Y2EuZWR1LnN2IiwiaWF0IjoxNzE3MzE0NjQzLCJleHAiOjE3MTg2MTA2NDN9.5RUWkAv1p0D8D6zHNyPUw8k0BiyIeLbOv5kk2tQJt5fRPvaFGRQ0F1LKi73awXaqjyE5ZK1zVAO1x9ejnaQLig"

const submitForm = async () => {
  const formData = {
    maxHabitants: habitantes.value,
    description: descripcion.value
  };

  const response = await fetch("http://localhost:8080/api/residence/", {
    method: "POST",
    headers:{
      "Content-Type": "application/json",
      Authorization: "Bearer " + userToken, 
    },
    body: JSON.stringify(formData),
  });

  const responseData = await response.json(); // o response.text() dependiendo del formato de respuesta
    console.log(responseData);

  if (response.ok) {
    // Manejar respuesta exitosa
    console.log("La residencia ha sido creada exitosamente.");
  } else {
    // Manejar error en la respuesta
    console.error("Ha ocurrido un error al crear la residencia.");
  }
};

const cancelForm = () => {
  // Limpiar los campos o realizar cualquier otra acción necesaria al cancelar el formulario
  habitantes.value = 0;
  descripcion.value = '';
};
</script>
