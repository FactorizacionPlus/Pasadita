# Funcionalidad de las Tablas
Las tablas requieren que les especifiquen las filas que deben llevar, si llevan ícono y los items.
```ts
export interface GenericTableType {
  name: string;
  icon?: string;
  items: any[];
}
```

en este caso, al momento de hacer la creación de la tabla y tener los items, deberemos mapear por cada uno de las filas, piensa que cada uno de estos es una fila e `items` son las columnas a mostrar.

```ts

export default interface Entry {
  uuid: string;
  accessDate: Date;
  description: string;
  terminal: Terminal;
  user: User;
}

const entries: Entry[] = [];

const rows = [
  {
    name: "Nombre y Apellidos",
    icon: "user",
    items: [...entries.map((item) => item.user.firstName + " " + item.user.lastName)],
  },
  {
    name: "Tipo",
    icon: "menu",
    items: [...entries.map((item) => item.user.identifierType)],
  },
  {
    name: "Identificacion",
    icon: "user-check",
    items: [...entries.map((item) => item.user.identifier)],
  },
  {
    name: "Descripción",
    icon: "align-left",
    items: [...entries.map((item) => item.description)],
  },
  {
    name: "Fecha de Entrada",
    icon: "calendar",
    items: [...entries.map((item) => item.accessDate)],
  },
];
```

# Búsqueda para tablas
Este es un método genérico que lo trae implementado el componente `GenericTable.vue`, para ver una implementación real de este, puedes ir a `src/views/admin-panel/TerminalHistoryView.vue`.

Este funciona exactamente igual que la búsqueda, lo que haremos en este caso, es extraer de la barra de búsqueda los valores que requerimos y por otra parte mandarlos al `GenericTable`

```vue
    <SearchBar @toggle-no-results="hideNoResults = $event" @search="searchText = $event" />
    <GenericTable :hide-no-results="hideNoResults" :table="rows" :search-term="searchText">
```