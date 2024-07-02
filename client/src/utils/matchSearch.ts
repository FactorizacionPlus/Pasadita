const getNestedPropertyValue = (obj: any, path: string): any => {
  return path.split(".").reduce((acc, key) => acc[key], obj);
};

export const matchSearch = (item: any, searchText: string, fieldsToSearch: any[]): boolean => {
  const searchTextLower = searchText.toLocaleLowerCase();
  for (const field of fieldsToSearch) {
    const value = getNestedPropertyValue(item, field);
    if (typeof value === "string" && value.toLocaleLowerCase().includes(searchTextLower)) {
      return true;
    }
  }
  return false;
};
