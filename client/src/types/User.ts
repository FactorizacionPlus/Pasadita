export default interface User {
  uuid: string,
  identifier: string;
  firstName: string;
  lastName: string;
  imageUrl: string;
  identifierType: "DUI" | "PASSPORT";
  role: string
}
