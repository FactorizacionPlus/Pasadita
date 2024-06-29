export interface actionUI  {
    icon: string,
    name: string
}

export const actions: Record<string, actionUI> = {
create: {
    icon: "file-plus",
    name: "Crear",
  },
  view: {
    icon: "eye",
    name: "Visualizar",
  },
  read_only: {
    icon: "eye",
    name: "Solo Lectura",
  },
  edit: {
    icon: "edit",
    name: "Modificar",
  },
  delete: {
    icon: "trash-2",
    name: "Eliminar",
  },
  add: {
    icon: "plus",
    name: "Agregar",
  },
};

export type Actions = "create" | "view" | "edit" | "delete" | "add" | "read_only"