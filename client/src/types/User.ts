export default interface User {
  uuid: string,
  identifier: string;
  firstName: string;
  lastName: string;
  identifierType: "DUI" | "PASSPORT";
}
